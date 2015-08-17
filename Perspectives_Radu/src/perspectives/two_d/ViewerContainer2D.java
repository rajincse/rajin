package perspectives.two_d;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

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

import perspectives.base.DrawingArea;
import perspectives.base.Environment;
import perspectives.base.PEvent;
import perspectives.base.Viewer;
import perspectives.base.ViewerContainer;

/**
 * This is a window class for Viewers. It will be able to accommodate any of the three main types of Viewers: 2D, 3D, GUI. It will call their rendering, simulation, and interactivity functions (if appropriate), it will implement double buffering for them, and it can be added to the viewer area
 * of the Environment. ViewerContainers automatically implementing panning and zooming for 2D viewers via 2dtransforms applied to the Graphics2D objects passed onto the Viewer2D's render function.
 * @author rdjianu
 *
 */
public class ViewerContainer2D extends ViewerContainer{
	
	
	int renderCount = 0;
	
	//apply to 2d viewers
	private ArrayList<double[]> zoom = new ArrayList<double[]>();
	private ArrayList<double[]> translatex = new ArrayList<double[]>();
	private ArrayList<double[]> translatey = new ArrayList<double[]>();
	
	BufferedImage wholeImage;
	
	//keeping track of dragging; 
	public boolean rightButtonDown = false;
	public int dragPrevX = 0;
	public int dragPrevY = 0;
	
	public int zoomOriginX = 0;
	public int zoomOriginY = 0;
	
	public ArrayList<AffineTransform[]> transform = new ArrayList<AffineTransform[]>();
		
	JPanel drawArea;
	
	boolean antialiasingSet = false;
	
	public ViewerContainer2D(Viewer v, Environment env, int width, int height)
	{
		super(v,env,width,height);
		
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=0; i<das.length; i++){
			zoom.add(new double[]{1});
			translatex.add(new double[]{0});
			translatey.add(new double[]{0});
			transform.add(new AffineTransform[]{AffineTransform.getTranslateInstance(0, 0)});
		}
			
		v.requestRender();
	
	}
	
	public void render(){
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=0; i<das.length; i++)
			render(viewer.getDrawingAreas()[i]);
	}
	
	
	public void render(DrawingArea da)
	{
		DrawingArea[] das = viewer.getDrawingAreas();
		double[] zoom = null;
		double[] translatex = null;
		double[] translatey = null;
		AffineTransform[] transform = null;
		for (int i=0; i<das.length; i++)
			if (das[i] == da){
				zoom = this.zoom.get(i);
				translatex = this.translatex.get(i);
				translatey = this.translatey.get(i);
				transform = this.transform.get(i);
			}
		
		BufferedImage image = getRenderBuffer(da);
		Graphics2D gc = (Graphics2D)image.getGraphics();		
	

		gc.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

		gc.setColor(((JavaAwtRenderer)viewer).getBackgroundColor());
		gc.fillRect(0, 0, this.getWidth(), this.getHeight()); // fill in background
           		
		gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		gc.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


		zoomOriginX = image.getWidth()/2;
		zoomOriginY = image.getHeight()/2;

		//sets the proper transformation for the Graphics context that will passed into the rendering function	
		gc.setTransform(AffineTransform.getTranslateInstance(zoomOriginX, zoomOriginY));	          
		gc.transform(AffineTransform.getScaleInstance(zoom[0],zoom[0]));		           
		gc.transform(AffineTransform.getTranslateInstance(translatex[0]-zoomOriginX, translatey[0]-zoomOriginY));
		
		transform[0] = gc.getTransform();
		
		getViewer().renderedDrawingArea = da;
		((JavaAwtRenderer)getViewer()).render(gc);

	     gc.setTransform(AffineTransform.getTranslateInstance(0, 0));
         //render tooltip
	     
        
	     if (viewer.getToolTipText().length() > 0) 
                  viewer.renderTooltip(gc); 
	     
	     if (wholeImage == null || wholeImage.getWidth() != this.getWidth() || wholeImage.getHeight() != this.getHeight())
	    	 wholeImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		 
	     Graphics2D wg = wholeImage.createGraphics();
	     wg.drawImage(image, da.x, da.y,null);
		 this.setViewerImage(wholeImage);	
	}

	

	
	public void setWidth(int w)
	{
		width = w;
	}
	public void setHeight(int h)
	{
		height = h;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}

		

	//for 2D viewers we add automatic zooming and panning; this can happen using the arrow keys and +,- keys; the key strokes are also sent to the viewer as interaction events
	 @Override
	protected void keyPressed(DrawingArea da, String code, String modifiers)
	{
			DrawingArea[] das = viewer.getDrawingAreas();
			double[] zoom = null;
			double[] translatex = null;
			double[] translatey = null;
			for (int i=0; i<das.length; i++)
				if (das[i] == da){
					zoom = this.zoom.get(i);
					translatex = this.translatex.get(i);
					translatey = this.translatey.get(i);
				}
			
		if (code == "UP")
		{	translatey[0]++; this.render(da);	}
		else if (code == "DOWN")
		{	translatey[0]--;this.render(da);	}
		else if (code == "LEFT")
		{	translatex[0]--;this.render(da);	}
		else if (code == "RIGHT")
		{	translatex[0]++;this.render(da);	}
		else if (code == "PLUS")
		{	zoom[0] = zoom[0] + 0.1;this.render(da);	}
		else if (code == "MINUS")
		{
			zoom[0] = zoom[0] - 0.1;
			if (zoom[0] < 0.1)
				zoom[0] = 0.1;
			this.render(da);
		}
		getViewer().interactionDrawingArea = da;
		((JavaAwtRenderer)viewer).keyPressed(code, modifiers);
	}

	protected void keyReleased(DrawingArea da, String code, String modifiers) {
		getViewer().interactionDrawingArea = da;
		((JavaAwtRenderer)viewer).keyReleased(code, modifiers);				
	}
	

	//we add listeners both to send those events to the 2D viewer but also to implement zooming an panning; there's a 25pixel offset on the y-axes because of the title bar of ViewerContainers; for zooming and panning we use
	//the raw coordinates; when passing the coordinates to the viewer they need to be transformed so they are in the Viewer's space, hence the operations based on translate and zoom.
	 
	public void mouseEntered(int ex, int ey) {
	}
	public void mouseExited(int ex, int ey) {
	}
	
	@Override
	protected void mousePressed(DrawingArea da, int ex, int ey, int button) {
		
		AffineTransform[] transform = null;
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=0; i<das.length; i++)
			if (das[i] == da)
				transform = this.transform.get(i);
		
		try{
			Point2D.Double tp = new Point2D.Double();
			transform[0].inverseTransform(new Point2D.Double(ex,ey), tp);
			int x = (int)tp.x;
			int y = (int)tp.y;
			
			getViewer().interactionDrawingArea = da;
			((JavaAwtRenderer)viewer).mousepressed(x,y, button);
			
			
			dragPrevX = ex;
			dragPrevY = ey;
			
			if (button == MouseEvent.BUTTON3)
			{
				rightButtonDown = true;
				zoomOriginX = dragPrevX;
				zoomOriginY = dragPrevY;
			}
                        
                        this.render(da);
                        
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
			
		}
	}
	
	@Override
	protected void mouseReleased(DrawingArea da, int ex, int ey, int button)
	{
		AffineTransform[] transform = null;
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=0; i<das.length; i++)
			if (das[i] == da)
				transform = this.transform.get(i);
		
		try{
			Point2D.Double tp = new Point2D.Double();
			transform[0].inverseTransform(new Point2D.Double(ex, ey), tp);
			int x = (int)tp.x;
			int y = (int)tp.y;
		
			getViewer().interactionDrawingArea = da;
			((JavaAwtRenderer)viewer).mousereleased(x,y, button);
			
			if (button == MouseEvent.BUTTON3){
                            rightButtonDown = false;
                        }
				
                        this.render(da);

		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}	
	
	@Override
	protected void mouseDragged(DrawingArea da, int ex, int ey) {
		
		DrawingArea[] das = viewer.getDrawingAreas();
		double[] zoom = null;
		double[] translatex = null;
		double[] translatey = null;
		AffineTransform[] transform = null;
		for (int i=0; i<das.length; i++)
			if (das[i] == da){
				zoom = this.zoom.get(i);
				translatex = this.translatex.get(i);
				translatey = this.translatey.get(i);
				transform = this.transform.get(i);
			}
				
		
		viewer.setToolTipCoordinates(ex,ey);
		
		try{
			Point2D.Double tp = new Point2D.Double();
			transform[0].inverseTransform(new Point2D.Double(ex, ey), tp);
			int x = (int)tp.x;
			int y = (int)tp.y;
			
			transform[0].inverseTransform(new Point2D.Double(dragPrevX, dragPrevY), tp);
			int px = (int)tp.x;
			int py = (int)tp.y;
		
		getViewer().interactionDrawingArea = da;
		if (!((JavaAwtRenderer)viewer).mousedragged(x,y, px, py))
		{
			if (rightButtonDown) //zoom
			{
				if (Math.abs(ex-dragPrevX) > Math.abs(ey-0 - dragPrevY))
					zoom[0] *= (1+(ex-dragPrevX)/100.);
				else
					zoom[0] *= (1+(ey-0-dragPrevY)/100.);
				if (zoom[0] <0.01)
					zoom[0] = 0.01;
			}
			else
			{
				translatex[0] += (ex-dragPrevX)/zoom[0];
				translatey[0] += (ey-dragPrevY)/zoom[0];
			}
			
			viewer.viewChanged();
			//this.render();
		}
                
       this.render(da);
                
		dragPrevX = ex;
		dragPrevY = ey;
		
		}
		catch(Exception ee)
		{
			
			
		}
	}
	
	@Override
	protected void mouseMoved(DrawingArea da, int ex, int ey)
	{
		AffineTransform[] transform = null;
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=0; i<das.length; i++)
			if (das[i] == da)
				transform = this.transform.get(i);
		
		if (viewer == null)
			return;
		
		viewer.setToolTipCoordinates(ex,ey);
		
		try{
			Point2D.Double tp = new Point2D.Double();
			transform[0].inverseTransform(new Point2D.Double(ex,ey), tp);
			int x = (int)tp.x;
			int y = (int)tp.y;	
			
		getViewer().interactionDrawingArea = da;
		((JavaAwtRenderer)viewer).mousemoved(x,y);
		
		//if (viewer.getToolTipText() != "")                
        // this.render();
                
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}
	
	public Point modelToScreen(Point modelPoint){
		return modelToScreen(viewer.getDrawingAreas()[0], modelPoint);
	}
	
	public Point modelToScreen(DrawingArea da, Point modelPoint)
	{
		AffineTransform[] transform = null;
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=0; i<das.length; i++)
			if (das[i] == da)
				transform = this.transform.get(i);
		
		Point2D p = transform[0].transform(modelPoint, null);
		return new Point((int)p.getX(), (int)p.getY());
		
	}
	
	public Point screenToModel(Point screenPoint){
		return screenToModel(viewer.getDrawingAreas()[0], screenPoint);
	}
	
	public Point screenToModel(DrawingArea da, Point screenPoint)
	{
		AffineTransform[] transform = null;
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=0; i<das.length; i++)
			if (das[i] == da)
				transform = this.transform.get(i);
		
		Point2D p;
		try {
			p = transform[0].inverseTransform(screenPoint, null);
			return new Point((int)p.getX(), (int)p.getY());
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public double getZoom()
	{
		return getZoom(viewer.getDrawingAreas()[0]);
	}
	
	public double getZoom(DrawingArea da){
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=0; i<das.length; i++)
			if (das[i] == da)
				return zoom.get(i)[0];
		return 0;
	}
	
	public void setZoom(double z){
		 setZoom(viewer.getDrawingAreas()[0],z);
	}
	public void setZoom(DrawingArea da, double z)
	{
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=0; i<das.length; i++)
			if (das[i] == da)
				zoom.get(i)[0] = z;
	}
	
	public void setTranslation(double x, double y){
		 setTranslation(viewer.getDrawingAreas()[0],x,y);
	}
	
	public void setTranslation(DrawingArea da, double x, double y)
	{
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=0; i<das.length; i++)
			if (das[i] == da){
				translatex.get(i)[0] = x;	
				translatey.get(i)[0] = y;}
	}
	
	public Point2D getTranslation(){
		return getTranslation(viewer.getDrawingAreas()[0]);
	}
	public Point2D getTranslation(DrawingArea da)
	{
		DrawingArea[] das = viewer.getDrawingAreas();
		for (int i=0; i<das.length; i++)
			if (das[i] == da)
				return new Point2D.Double(translatex.get(i)[0], translatey.get(i)[0]);
		return null;
	}

	@Override
	public void viewerDrawingAreasAdded(DrawingArea r) {
		super.viewerDrawingAreasAdded(r);
		zoom.add(new double[]{1});	
		translatex.add(new double[]{0});		
		translatey.add(new double[]{0});
		transform.add(new AffineTransform[]{AffineTransform.getTranslateInstance(0, 0)});
		
	}
	
	
	

}
