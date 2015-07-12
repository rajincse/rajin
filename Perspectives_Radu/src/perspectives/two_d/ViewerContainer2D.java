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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
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
	private double zoom = 1;
	private double translatex = 0;
	private double translatey = 0;
	
	//keeping track of dragging; 
	public boolean rightButtonDown = false;
	public int dragPrevX = 0;
	public int dragPrevY = 0;
	
	public int zoomOriginX = 0;
	public int zoomOriginY = 0;
	
	public AffineTransform transform = AffineTransform.getTranslateInstance(0, 0);
		
	JPanel drawArea;
	
	boolean antialiasingSet = false;
	
	public ViewerContainer2D(Viewer v, Environment env, int width, int height)
	{
		super(v,env,width,height);
			
		zoom = 1;
		v.requestRender();
	
	}
	
	
	public void render()
	{
		BufferedImage image = getRenderBuffer();
		Graphics2D gc = (Graphics2D)image.getGraphics();		
	

		gc.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

		gc.setColor(((JavaAwtRenderer)viewer).getBackgroundColor());
		gc.fillRect(0, 0, this.getWidth(), this.getHeight()); // fill in background
           		
		gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		gc.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


		zoomOriginX = this.getWidth()/2;
		zoomOriginY = this.getHeight()/2;

		//sets the proper transformation for the Graphics context that will passed into the rendering function	
		gc.setTransform(AffineTransform.getTranslateInstance(zoomOriginX, zoomOriginY));	          
		gc.transform(AffineTransform.getScaleInstance(zoom,zoom));		           
		gc.transform(AffineTransform.getTranslateInstance(translatex-zoomOriginX, translatey-zoomOriginY));
		
		transform = gc.getTransform();
		
		((JavaAwtRenderer)getViewer()).render(gc);

	     gc.setTransform(AffineTransform.getTranslateInstance(0, 0));
         //render tooltip
        
	     if (viewer.getToolTipText().length() > 0) 
                  viewer.renderTooltip(gc);             
		   
		 this.setViewerImage(image);	
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
	protected void keyPressed(String code, String modifiers)
	{
		if (code == "UP")
		{	translatey++; this.render();	}
		else if (code == "DOWN")
		{	translatey--;this.render();	}
		else if (code == "LEFT")
		{	translatex--;this.render();	}
		else if (code == "RIGHT")
		{	translatex++;this.render();	}
		else if (code == "PLUS")
		{	zoom = zoom + 0.1;this.render();	}
		else if (code == "MINUS")
		{
			zoom = zoom - 0.1;
			if (zoom < 0.1)
				zoom = 0.1;
			this.render();
		}
		
		((JavaAwtRenderer)viewer).keyPressed(code, modifiers);
		addResult(EVENT_ANCHOR_KEY_PRESSED, code, modifiers);
	}

	protected void keyReleased(String code, String modifiers) {
		((JavaAwtRenderer)viewer).keyReleased(code, modifiers);
		addResult(EVENT_ANCHOR_KEY_RELEASED, code, modifiers);
	}
	

	//we add listeners both to send those events to the 2D viewer but also to implement zooming an panning; there's a 25pixel offset on the y-axes because of the title bar of ViewerContainers; for zooming and panning we use
	//the raw coordinates; when passing the coordinates to the viewer they need to be transformed so they are in the Viewer's space, hence the operations based on translate and zoom.
	 
	public void mouseEntered(int ex, int ey) {
	}
	public void mouseExited(int ex, int ey) {
	}
	@Override
	protected void mousePressed(int ex, int ey, int button) {
		
		try{
			Point2D.Double tp = new Point2D.Double();
			transform.inverseTransform(new Point2D.Double(ex,ey), tp);
			int x = (int)tp.x;
			int y = (int)tp.y;
			
			((JavaAwtRenderer)viewer).mousepressed(x,y, button);
			
			
			dragPrevX = ex;
			dragPrevY = ey;
			
			if (button == MouseEvent.BUTTON3)
			{
				rightButtonDown = true;
				zoomOriginX = dragPrevX;
				zoomOriginY = dragPrevY;
			}
                        
                        this.render();
            addResult(EVENT_ANCHOR_MOUSE_PRESSED, x, y, button);
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
			
		}
	}
	
	protected void mouseReleased(int ex, int ey, int button)
	{
		try{
			Point2D.Double tp = new Point2D.Double();
			transform.inverseTransform(new Point2D.Double(ex, ey), tp);
			int x = (int)tp.x;
			int y = (int)tp.y;
		
		
			((JavaAwtRenderer)viewer).mousereleased(x,y, button);
			
			if (button == MouseEvent.BUTTON3){
                            rightButtonDown = false;
                        }
				
                        this.render();
            addResult(EVENT_ANCHOR_MOUSE_RELEASED, x, y, button);
		}
		catch(Exception ee)
		{
			
		}
	}	
	
	protected void mouseDragged(int ex, int ey) {
		
		viewer.setToolTipCoordinates(ex,ey);
		
		try{
			Point2D.Double tp = new Point2D.Double();
			transform.inverseTransform(new Point2D.Double(ex, ey), tp);
			int x = (int)tp.x;
			int y = (int)tp.y;
			
			transform.inverseTransform(new Point2D.Double(dragPrevX, dragPrevY), tp);
			int px = (int)tp.x;
			int py = (int)tp.y;
		
		if (!((JavaAwtRenderer)viewer).mousedragged(x,y, px, py))
		{
			if (rightButtonDown) //zoom
			{
				if (Math.abs(ex-dragPrevX) > Math.abs(ey-0 - dragPrevY))
					zoom *= (1+(ex-dragPrevX)/100.);
				else
					zoom *= (1+(ey-0-dragPrevY)/100.);
				if (zoom <0.01)
					zoom = 0.01;
			}
			else
			{
				translatex += (ex-dragPrevX)/zoom;
				translatey += (ey-dragPrevY)/zoom;
			}
			
			viewer.viewChanged();
			//this.render();
		}
                
       this.render();
                
		dragPrevX = ex;
		dragPrevY = ey;
			addResult(EVENT_ANCHOR_MOUSE_DRAGGED, ex, ey);
		}
		catch(Exception ee)
		{
			
			
		}
	}
	protected void mouseMoved(int ex, int ey)
	{
		if (viewer == null)
			return;
		
		viewer.setToolTipCoordinates(ex,ey);
		
		try{
			Point2D.Double tp = new Point2D.Double();
			transform.inverseTransform(new Point2D.Double(ex,ey), tp);
			int x = (int)tp.x;
			int y = (int)tp.y;	

		((JavaAwtRenderer)viewer).mousemoved(x,y);
		
		//if (viewer.getToolTipText() != "")                
        // this.render();
			addResult(EVENT_ANCHOR_MOUSE_MOVED, ex, ey);     
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}
	
	public Point modelToScreen(Point modelPoint)
	{
		Point2D p = transform.transform(modelPoint, null);
		return new Point((int)p.getX(), (int)p.getY());
	}
	
	public Point screenToModel(Point screenPoint)
	{
		Point2D p;
		try {
			p = transform.inverseTransform(screenPoint, null);
			return new Point((int)p.getX(), (int)p.getY());
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public double getZoom()
	{
		return zoom;
	}
	
	public void setZoom(double z)
	{
		zoom = z;
		addResult(EVENT_ANCHOR_ZOOM, z);
	}
	
	public void setTranslation(double x, double y)
	{
		this.translatex = x;
		this.translatey = y;
		addResult(EVENT_ANCHOR_TRANSLATION, x,y);
	}
	
	public Point2D getTranslation()
	{
		return new Point2D.Double(translatex, translatey);
	}
	

	//-----------REPLAY FEATURE---------------
	public static final String INTERACTION_FILE_PATH ="C:\\work\\interaction.txt";
	public static final String EVENT_ANCHOR_MOUSE_MOVED ="MouseMoved";
	public static final String EVENT_ANCHOR_MOUSE_DRAGGED ="MouseDragged";
	public static final String EVENT_ANCHOR_MOUSE_PRESSED ="MousePressed";
	public static final String EVENT_ANCHOR_MOUSE_RELEASED ="MouseReleased";
	public static final String EVENT_ANCHOR_KEY_PRESSED ="KeyPressed";
	public static final String EVENT_ANCHOR_KEY_RELEASED ="KeyReleased";
	public static final String EVENT_ANCHOR_ZOOM ="Zoom";
	public static final String EVENT_ANCHOR_TRANSLATION ="Translation";
	public static final String EVENT_ANCHOR_GAZE ="Gaze";
	
	protected boolean recordingOn = false;
	protected boolean replayingOn = false;
	

	//-----RECORDING-----//
	protected StringBuffer resultText = new StringBuffer();
	protected Timer eventTimer = new Timer("InteractionTimer");
	
	protected void startTimer()
	{
		this.eventTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int length = 0;
				synchronized (this) {
					length = resultText.length();
				}
				if(length > 0)
				{
					saveResultToFile();
				}
				
			}
		}, 0, 2000);		
	}
	protected void stopTimer()
	{
		this.eventTimer.cancel();
		this.eventTimer = new Timer("InteractionTimer");
	}
	public boolean isRecordingOn() {
		return recordingOn;
	}


	public void setRecordingOn(boolean recordingOn) {
		this.recordingOn = recordingOn;
		
		if(recordingOn)
		{
			startTimer();
		}
		else
		{
			stopTimer();
		}
	}
	
	


	protected void addResult(String anchor, int x, int y)
	{
		if(recordingOn && !replayingOn)
		{
			long time = System.currentTimeMillis();
			String data = anchor+"\t"+time+"\t"+x+"\t"+y+"\r\n";
			synchronized (this) {
				this.resultText.append(data);
			}
		}
	}
	protected void addResult(String anchor, int x, int y, int button)
	{
		if(recordingOn && !replayingOn)
		{
			long time = System.currentTimeMillis();
			String data = anchor+"\t"+time+"\t"+x+"\t"+y+"\t"+button+"\r\n";
			synchronized (this) {
				this.resultText.append(data);
			}
		}
	}
	protected void addResult(String anchor, String code, String modifiers)
	{
		if(recordingOn && !replayingOn)
		{
			long time = System.currentTimeMillis();
			String data = anchor+"\t"+time+"\t"+code+"\t"+modifiers+"\r\n";
			synchronized (this) {
				this.resultText.append(data);
			}
		}		
	}
	protected void addResult(String anchor, double value)
	{
		if(recordingOn && !replayingOn)
		{
			long time = System.currentTimeMillis();
			String data = anchor+"\t"+time+"\t"+value+"\r\n";
			synchronized (this) {
				this.resultText.append(data);
			}
		}
	}
	protected void addResult(String anchor, double x, double y)
	{
		if(recordingOn && !replayingOn)
		{
			long time = System.currentTimeMillis();
			String data = anchor+"\t"+time+"\t"+x+"\t"+y+"\r\n";
			synchronized (this) {
				this.resultText.append(data);
			}		
		}
	}
	protected void saveResultToFile()
	{
		try {
			

			FileWriter fstream = new FileWriter(new File(INTERACTION_FILE_PATH), true);
			BufferedWriter br = new BufferedWriter(fstream);

			br.write(resultText.toString());

			br.close();
			synchronized (this) {
				resultText.setLength(0);
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//-----REPLAYING -----
	public static final int INVALID =-1;
	protected long lastInstructionTime = INVALID;
	protected Thread replayThread = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			replay();
		}
	}, "ReplayThread");
	
	
	
	public boolean isReplayingOn() {
		return replayingOn;
	}


	public void setReplayingOn(boolean replayingOn) {
		this.replayingOn = replayingOn;
		if(replayingOn)
		{
			setRecordingOn(false);
			replay();
		}
	}
	
	protected void startReplay()
	{
		this.replayThread.start();
	}
	
	protected void stopReplay()
	{
		try {
			this.replayThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void replay()
	{
		ArrayList<String> instructionList = getInstructionList(INTERACTION_FILE_PATH);
		
		for(String instruction: instructionList)
		{
			performInstruction(instruction);
		}
		lastInstructionTime = INVALID;
	}
	
	protected void performInstruction(String instruction)
	{
		System.out.println("instruct:\t"+instruction);
		String[] split = instruction.split("\t");
		if(split.length > 2)
		{
			String anchor = split[0];
			long time = Long.parseLong(split[1]);
			
			if(lastInstructionTime== INVALID)
			{
				lastInstructionTime = time;
			}
			long timelapse = time - lastInstructionTime;
			
			lastInstructionTime = time;
			
			try {
				Thread.sleep(timelapse);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(anchor.equals(EVENT_ANCHOR_KEY_PRESSED))
			{
				String code = split[2];
				String modifiers = split[3];
				
				this.keyPressed(code, modifiers);
			}
			else if(anchor.equals(EVENT_ANCHOR_KEY_RELEASED))
			{
				String code = split[2];
				String modifiers = split[3];
				
				this.keyPressed(code, modifiers);
			}
			else if(anchor.equals(EVENT_ANCHOR_MOUSE_DRAGGED))
			{
				int x = Integer.parseInt(split[2]);
				int y = Integer.parseInt(split[3]);
				this.mouseDragged(x, y);
			}
			else if(anchor.equals(EVENT_ANCHOR_MOUSE_MOVED))
			{
				int x = Integer.parseInt(split[2]);
				int y = Integer.parseInt(split[3]);
				this.mouseMoved(x, y);
			}
			else if(anchor.equals(EVENT_ANCHOR_MOUSE_PRESSED))
			{
				int x = Integer.parseInt(split[2]);
				int y = Integer.parseInt(split[3]);
				int button = Integer.parseInt(split[4]);
				this.mousePressed(x, y, button);
			}
			else if(anchor.equals(EVENT_ANCHOR_MOUSE_RELEASED))
			{
				int x = Integer.parseInt(split[2]);
				int y = Integer.parseInt(split[3]);
				int button = Integer.parseInt(split[4]);
				this.mouseReleased(x, y, button);
			}
//			else if(anchor.equals(EVENT_ANCHOR_TRANSLATION))
//			{
//				double x = Double.parseDouble(split[2]);
//				double y = Double.parseDouble(split[3]);
//				this.setTranslation(x, y);
//			}
//			else if(anchor.equals(EVENT_ANCHOR_ZOOM))
//			{
//				double zoom= Double.parseDouble(split[2]);
//				this.setZoom(zoom);
//			}
			
			
		}
	}
	
	protected ArrayList<String> getInstructionList(String filePath)
	{
		ArrayList<String> instructionList = new ArrayList<String>();
		try {
			File file = new File(filePath);
		
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String instruction = bufferedReader.readLine();
			while(instruction != null)
			{
				instructionList.add(instruction);
				instruction = bufferedReader.readLine();
			}
			
			bufferedReader.close();
			fileReader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instructionList;
	}
	
	
	//-----------REPLAY FEATURE---------------
}
