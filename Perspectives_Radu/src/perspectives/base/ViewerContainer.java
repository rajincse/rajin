package perspectives.base;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import perspectives.base.Task;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.SwingWorker;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import com.keypoint.PngEncoder;



/**
 * This is a window class for Viewers. It will be able to accommodate any of the three main types of Viewers: 2D, 3D, GUI. It will call their rendering, simulation, and interactivity functions (if appropriate), it will implement double buffering for them, and it can be added to the viewer area
 * of the Environment. ViewerContainers automatically implementing panning and zooming for 2D viewers via 2dtransforms applied to the Graphics2D objects passed onto the Viewer2D's render function.
 * @author rdjianu
 *
 */
public class ViewerContainer{
	
	//used to implement double buffering for 2d and 3d viewers
	private BufferedImage viewerImage = null;
	
	private ArrayList<BufferedImage[]> renderBuffers = new ArrayList<BufferedImage[]>();
	private ArrayList<int[]> whichBuffer = new ArrayList<int[]>();
	
	private BufferedImage image = null;

	private BufferedImage lastImage = null;
	private int lastImageId = -1;
	private BufferedImage sendImage = null; 
	private int sendImageId = -1;
	
	private int imageIdGenerator = 0;
	
	Object o1 = new Object();
	Object o2 = new Object();
	
	int tilesX = 1;
	int tilesY = 1;
	
	private DrawingArea focusedDrawingArea = null;
	
	
	
	protected ViewerWindow window = null;
		 
	protected Viewer viewer;
	
	public int sentTiles = 0;
	public int changedImages = 0;
	public long interactionTime = 0;
	public long startInteractionTime = -1;
	public long lastInteractionTime = -1;

		
	//a pointer to the parent Environment class (needed for instance to delete the viewer from the Environment if the user activates the 'X')
	private Environment env;
	
	protected int width, height;
	
	public long lastMouseMove;
	
	boolean tooltipOn;
	String prevTooltip;
	
	BufferedImage savedImage = null;
	boolean blocked = false;
	
	
	long MAXDIFF = 50000;
	int keyFrameRate = 40;
	
	
		
	public ViewerContainer(Viewer v, Environment env, int width, int height)
	{		
		this.env = env;		
		
		this.width = width;
		this.height = height;
		
		tooltipOn = false;
		String prevTooltip = "";
		
		viewer = v;
		viewer.setContainer(this);	
		
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=0; i<das.length; i++){
			renderBuffers.add(new BufferedImage[2]);
			whichBuffer.add(new int[]{0});
		}
	}

	public Viewer getViewer()
	{
		return viewer;
	}
	
	public Environment getEnvironment()
	{
		return env;
	}
	
	public void resize(int w, int h)
	{
		width = w;
		height = h;
		getViewer().resized();
		getViewer().requestRender();
	}
	
	public ViewerWindow getViewerWindow()
	{
		return window;
	}
	
	
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	
	public void render(DrawingArea da){

	}
	
	
	public BufferedImage getRenderBuffer(DrawingArea drawingArea)
	{
		int da = -1;
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=0; i<das.length; i++)
			if (das[i] == drawingArea) da = i;
		
		BufferedImage[] renderBuffers = this.renderBuffers.get(da);
		int[] whichBuffer = this.whichBuffer.get(da);
		if (renderBuffers == null)
			renderBuffers = new BufferedImage[2];
		
		if (renderBuffers[whichBuffer[0]] == null || renderBuffers[whichBuffer[0]].getWidth() != this.getWidth() || renderBuffers[whichBuffer[0]].getHeight() != this.getHeight())
		{
			int width = Math.min(this.getWidth(), drawingArea.w); 
			int height = Math.min(this.getHeight(), drawingArea.h); 
			renderBuffers[whichBuffer[0]] = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
			renderBuffers[whichBuffer[0]].createGraphics();
		}
		
		BufferedImage ret = renderBuffers[whichBuffer[0]];
		
		whichBuffer[0]++; if (whichBuffer[0] > 1) whichBuffer[0] = 0;
		
		return ret;		
	}
	
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	
	private BufferedImage setVBuf = null; 
	private Graphics2D setVBufG = null;
	public void setViewerImage(BufferedImage im)
	{	
		this.viewerImage = im;
		
		if (!blocked)
			changeImage(im);
		else
			this.savedImage = im;
		
	    if (tooltipOn && prevTooltip.equals(viewer.getToolTipText()))
	     {
	    			if (setVBuf == null || setVBuf.getWidth() != viewerImage.getWidth() || setVBuf.getHeight() != viewerImage.getHeight())
	    			{
	    				setVBuf = new BufferedImage(viewerImage.getWidth(), viewerImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    				setVBufG = setVBuf.createGraphics();
	    				System.out.println("new buffered image: setviewrimage");
	    			}
	    			BufferedImage imm = setVBuf;	        		
					Graphics2D gc = setVBufG;
					
					gc.drawImage(viewerImage, 0, 0, null);					
					viewer.renderTooltip(gc);
					if (!blocked)					
						changeImage(imm);
					else
						savedImage = imm;
	     }
	     else
	     {
	    	 tooltipOn = false;
	    	 prevTooltip = "";
	     }
	    
	    if (window != null)
	    	this.window.redraw();
	}
	
	
	protected void keyPressed(String keyText, String modifiersText)
	{
		if (focusedDrawingArea == null)
			return;
		keyPressed(focusedDrawingArea, keyText, modifiersText);
	}
	
	protected void keyPressed(DrawingArea da, String keyText, String modifiersText)	{
		
	}
	
	public void scheduleKeyPress(String keyText, String modifiersText)
	{
		final String s1 = keyText;
		final String s2 = modifiersText;
		final ViewerContainer vcf = this;
		
		getViewer().em.scheduleEvent(new PEvent()
		{
			public void process() {
				vcf.keyPressed(s1,s2);	
			}
		});
	}

	protected void keyReleased(String keyText, String modifiersText)
	{		
		if (focusedDrawingArea == null)
			return;
		keyReleased(focusedDrawingArea, keyText, modifiersText);
	}
	
	protected void keyReleased(DrawingArea da, String keyText, String modifiersText)	{
		
	}
	
	public void scheduleKeyRelease(String keyText, String modifiersText)
	{
		final String s1 = keyText;
		final String s2 = modifiersText;
		final ViewerContainer vcf = this;
		getViewer().em.scheduleEvent(new PEvent()
		{
			public void process() {
				vcf.keyReleased(s1,s2);	
			}
		});
	}
	
	protected void keyTyped(KeyEvent arg0) {
	}	
	
	
	protected void mouseClicked(int ex, int ey, int button) {
	}
	protected void mouseEntered(int ex, int ey) {
	}
	protected void mouseExited(int ex, int ey) {
	}
	
	protected void mousePressed(int ex, int ey, int button){
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=das.length-1; i>=0; i--)
			if (das[i].contains(ex, ey))
				focusedDrawingArea = das[i];
		mousePressed(focusedDrawingArea, ex - focusedDrawingArea.x, ey - focusedDrawingArea.y, button);
	}	
	
	protected void mousePressed(DrawingArea das, int ex, int ey, int button){

	}
	
	public void scheduleMousePress(int x, int y, int button)
	{
		final int xf = x;
		final int yf = y;
		final int buttonf = button;
		final ViewerContainer vcf = this;
		getViewer().em.scheduleEvent(new PEvent()
		{
			public void process() {
				vcf.mousePressed(xf,yf,buttonf);							
			}						
		});
	}
	
	
	protected void mouseReleased(int ex, int ey, int button){
		if (focusedDrawingArea == null)
			return;
		mouseReleased(focusedDrawingArea,ex-focusedDrawingArea.x,ey-focusedDrawingArea.y,button);
	}
	
	protected void mouseReleased(DrawingArea da, int ex, int ey, int button)
	{	
	}
	
	public void scheduleMouseRelease(int x, int y, int button)
	{
		final int xf = x;
		final int yf = y;
		final int buttonf = button;
		final ViewerContainer vcf = this;
		getViewer().em.scheduleEvent(new PEvent()
		{
			public void process() {
				vcf.mouseReleased(xf,yf,buttonf);							
			}						
		});	
	}
	
	protected void mouseDragged(int ex, int ey) {
		if (focusedDrawingArea == null)
			return;
		mouseDragged(focusedDrawingArea,ex - focusedDrawingArea.x,ey - focusedDrawingArea.y);
	}
	
	protected void mouseDragged(DrawingArea da, int ex, int ey) {
	}
	
	boolean dragging;
	public void scheduleMouseDrag(int x, int y) {
		
		//if (dragging) return;
		
		final int xf = x;
		final int yf = y;
		
		//dragging = true;
		
		final ViewerContainer vcf = this; 
	
		getViewer().em.replaceEvent(new PEvent()
		{
			public void process() {
				vcf.mouseDragged(xf,yf);	
				vcf.lastMouseMove = new Date().getTime();
				vcf.getViewer().em.scheduleEvent(new PEvent()
				{
					public void process() {
						vcf.renderTooltip();
					}								
				}, vcf.getViewer().getTooltipDelay());
				
				dragging = false;
			}						
		},"mousedrag");
	}
	
	protected void mouseMoved(int ex, int ey){
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=das.length-1; i>=0; i--)
			if (das[i].contains(ex, ey)){
				mouseMoved(das[i],ex - das[i].x,ey - das[i].y);
				return;
			}
	}
	
	protected void mouseMoved(DrawingArea da, int ex, int ey){
	}
	
	public void scheduleMouseMove(int x, int y)
	{	
		final int xf = x;
		final int yf = y;
		
		final ViewerContainer vcf = this;
		getViewer().em.replaceEvent(new PEvent()
		{
			
			public void process() {
				vcf.mouseMoved(xf,yf);
				vcf.lastMouseMove = new Date().getTime();
				vcf.viewer.em.scheduleEvent(new PEvent()
				{
					public void process() {
						vcf.renderTooltip();
					}								
				}, vcf.viewer.getTooltipDelay());
				
				dragging = false;
			}						
		},"mousemove");
	}
	
	public boolean unresponsive()
	{
		return viewer.em.unresponsive(2000);
	}
	

	public void block(boolean blocked)
	{
		if (this.blocked == blocked)
			return;
		
		this.blocked = blocked;
		
		Property[] ps = viewer.getProperties();
		for (int i=0; i<ps.length; i++)
			ps[i].setDisabled(blocked);
		
		if (blocked)
		{
			if (image == null)
				return;
			BufferedImage im = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D)im.createGraphics();
			g.drawImage(image, 0, 0, null);
			g.setColor(new Color(100,100,100,100));
			g.fillRect(0,0,im.getWidth(),im.getHeight());
			savedImage = image;
			changeImage(im);
		}
		else if (savedImage != null)
			changeImage(savedImage);
		
		
	}
	
	public void drawingAreaChanged(DrawingArea r){
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=0; i<das.length; i++)
			if (das[i] == r){
				renderBuffers.get(i)[0] = null;
				renderBuffers.get(i)[1] = null;
				whichBuffer.get(i)[0] = 0;				
			}
	}
	
	public void viewerDrawingAreasAdded(DrawingArea r){
		renderBuffers.add(new BufferedImage[2]);
		whichBuffer.add(new int[]{0});
	}
	
	private BufferedImage renderTooltipBuf = null;
	private Graphics2D renderTooltipBufG = null;
	public void renderTooltip()
	{
		long delta = new Date().getTime()- lastMouseMove;	
		if (delta >= viewer.getTooltipDelay())
		{
			if (viewer.getToolTipText().length() > 0)
			{
				tooltipOn = true;
				prevTooltip = viewer.getToolTipText();
			}
		}
			
	     if (tooltipOn)
	     {
	    	 if (renderTooltipBuf == null || renderTooltipBuf.getWidth() != viewerImage.getWidth() || renderTooltipBuf.getHeight() != viewerImage.getHeight())
	    	 {
	    		 renderTooltipBuf = new BufferedImage(viewerImage.getWidth(), viewerImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    		 renderTooltipBufG = renderTooltipBuf.createGraphics();
	    	 }
	    	
	        BufferedImage im = renderTooltipBuf;
       		Graphics2D gc = renderTooltipBufG;
					
					gc.drawImage(viewerImage, 0, 0, null);					
					viewer.renderTooltip(gc);
					
					changeImage(im);
					
	     }  		
	}
	
	public void changeImage(BufferedImage newimage)
	{
		
		changedImages++;
		long t = new Date().getTime();	

		
		if (lastInteractionTime < 0)
		{
		
			lastInteractionTime = t;
			startInteractionTime = t;
		}
		
		if (t-lastInteractionTime > 100)
		{
			if (startInteractionTime >= 0)
			{
				interactionTime += (lastInteractionTime - startInteractionTime);
			}
			startInteractionTime = t;
			lastInteractionTime = t;
		}
		else
			lastInteractionTime = t;
	
		synchronized(o2)
		{
			image = newimage;
			sendImageId = this.imageIdGenerator++;
			if (this.imageIdGenerator > 1000) this.imageIdGenerator=1;
		}

	}
	
	public void noVisiblePropertiesChanged() {
		// TODO Auto-generated method stub
		
	}

	public void propertyBarHiddenChanged() {
		// TODO Auto-generated method stub
		
	}
	
	private BufferedImage copyImage(BufferedImage source, BufferedImage dest)
	{
		if (source == null) return null;
		if (dest == null || dest.getWidth() != source.getWidth() || dest.getHeight() != source.getHeight())
		{
			dest = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
			System.out.println("new bufferedimage : copy");
			dest.createGraphics();
		}
		
		((Graphics2D)dest.getGraphics()).drawImage(source, 0,0,null);
		return dest;
	}

}
