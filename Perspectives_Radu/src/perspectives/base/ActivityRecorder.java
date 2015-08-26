package perspectives.base;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import perspectives.properties.PFileOutput;
import perspectives.properties.POptions;

public class ActivityRecorder extends JDialog{
	
	private String filePath;
	
	Viewer viewer;
	
	boolean recording = false;

	public ActivityRecorder(JFrame parent, Environment e){
		super(parent);
		this.setUndecorated(true);
		this.setSize(220,20);
		getContentPane().setLayout(
			    new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS)
			);
		final JButton rec = new JButton("REC");
		rec.setEnabled(false);
		
		final JComboBox viewerCombo = new JComboBox();
		viewerCombo.setEnabled(false);
	
		
		final JDialog th = this;
		
		final Environment env = e;
		
		JButton file = new JButton("File", new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/Save16.gif")));			
		ActionListener listener = new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  
		    	  JFileChooser fc = new JFileChooser();	  
		    	  			    	  
		    	  int returnVal  = fc.showSaveDialog(th);
		    	
		    	  if (returnVal == JFileChooser.APPROVE_OPTION) {
		    		
		    		  filePath = fc.getSelectedFile().getAbsolutePath();	
		    		  viewerCombo.setEnabled(true);
		    		  
		    		  viewerCombo.removeAllItems();
		    		  viewerCombo.addItem("?");
		    		  Vector<Viewer> viewers = env.getViewers();
		    			for (int i=0; i<viewers.size(); i++)
		    				viewerCombo.addItem(viewers.get(i).name);
		    	  }
		      }
		    };
		file.addActionListener(listener);
		
		
		
		listener = new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	 if (viewerCombo.getSelectedIndex() == 0) return;
		    	 viewer = env.getViewers().get(viewerCombo.getSelectedIndex()-1);		    	 
		    	 viewer.addChangeListener(propertyManagerChangeListener);
		    	 rec.setEnabled(true);
		      }
		    };	
		viewerCombo.addActionListener(listener);
		
		listener = new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	 recording = !recording;
		    	 if (recording) rec.setText("||");
		    	 else rec.setText("REC");
		    	 
		    	 if(recording)
		 		{
		 			startTimer();
		 		}
		 		else
		 		{
		 			stopTimer();
		 		}
		      }
		    };	
		rec.addActionListener(listener);
		
		this.getContentPane().add(Box.createHorizontalGlue());		
		this.getContentPane().add(file);
		this.getContentPane().add(Box.createHorizontalGlue());
		this.getContentPane().add(viewerCombo);
		this.getContentPane().add(Box.createHorizontalGlue());
		this.getContentPane().add(rec);
		this.getContentPane().add(Box.createHorizontalGlue());
	}
	
	
	public void viewerResize(Viewer v){
		this.addResult(EVENT_ANCHOR_SCREEN_SIZE,v.getContainer().window.getLocation().x, v.getContainer().window.getLocation().y, v.getContainer().getWidth(), v.getContainer().getHeight());
	}

	
	public void mousePressed(Viewer v, int x, int y, int button){
		addResult(EVENT_ANCHOR_MOUSE_PRESSED, x, y, button);
	}
	
	public void mouseReleased(Viewer v, int x, int y, int button){
		addResult(EVENT_ANCHOR_MOUSE_RELEASED, x, y, button);
	}
	
	public void mouseMoved(Viewer v, int x, int y){
		addResult(EVENT_ANCHOR_MOUSE_MOVED, x, y);  
	}
	
	public void mouseDragged(Viewer v, int x, int y){
		addResult(EVENT_ANCHOR_MOUSE_DRAGGED, x, y);
	}
	//-----------PROPERTY RECORDINGS START-----------------

	PropertyManagerChangeListener propertyManagerChangeListener = new PropertyManagerChangeListener() {
		
		@Override
		public void propertyVisibleChanges(PropertyManager pm, Property p,
				boolean newVisible) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void propertyValueChanges(PropertyManager pm, Property p,
				PropertyType newValue) {
			// TODO Auto-generated method stub
			addResult(EVENT_ANCHOR_PROPERTY_VALUE_CHANGED, p.getName(),  p.getValue().typeName(), newValue.serialize());
		}
		
		@Override
		public void propertyRemoved(PropertyManager pm, Property p) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void propertyReadonlyChanges(PropertyManager pm, Property p,
				boolean newReadOnly) {
			// TODO Auto-generated method stub
			addResult(EVENT_ANCHOR_PROPERTY_VALUE_CHANGED, p.getName(),  p.getValue().typeName(), newReadOnly);
		}
		
		@Override
		public void propertyPublicChanges(PropertyManager pm, Property p,
				boolean newPublic) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void propertyDisabledChanges(PropertyManager pm, Property p,
				boolean enabled) {
			// TODO Auto-generated method stub
			addResult(EVENT_ANCHOR_PROPERTY_VALUE_CHANGED, p.getName(), p.getValue().typeName(), enabled);
		}
		
		@Override
		public void propertyAdded(PropertyManager pm, Property p) {
			// TODO Auto-generated method stub
			
		}
	};
	//-----------PROPERTY RECORDINGS END-----------------
	// The anchor text to identify the event 
	public static final String EVENT_ANCHOR_MOUSE_MOVED ="MouseMoved";
	public static final String EVENT_ANCHOR_MOUSE_DRAGGED ="MouseDragged";
	public static final String EVENT_ANCHOR_MOUSE_PRESSED ="MousePressed";
	public static final String EVENT_ANCHOR_MOUSE_RELEASED ="MouseReleased";
	public static final String EVENT_ANCHOR_PROPERTY_VALUE_CHANGED ="PropertyValueChanged"; 
	public static final String EVENT_ANCHOR_PROPERTY_VISIBILITY_CHANGED ="PropertyVisibilityChanged";
	public static final String EVENT_ANCHOR_PROPERTY_DISABILITY_CHANGED ="PropertyDisabilityChanged";
	public static final String EVENT_ANCHOR_SCREEN_SIZE ="ScreenSize";
	
	//The string buffer to store all the instruction strings to record. 
	protected StringBuffer resultText = new StringBuffer();
	// timer to save the results
	protected Timer eventTimer = new Timer("InteractionTimer");
	
	/**
	 * Every 2 sec the timer will trigger to save the recorded instructions to the file
	 */
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
	/**
	 * Invokes to stop the timer
	 */
	protected void stopTimer()
	{
		this.eventTimer.cancel();
		this.eventTimer = new Timer("InteractionTimer");
	}
	/**
	 * Save the events to the file and empties the buffer
	 */
	protected void saveResultToFile()
	{
		try {
			

			FileWriter fstream = new FileWriter(new File(filePath), true);
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
	protected void addResult(String anchor, String propertyName, String propertyTypeName, String propertyNewValue)
	{
		if(recording) // only record when recording is on and replaying is not
		{
			long time = System.currentTimeMillis();
			String data = anchor+"\t"+time+"\t"+propertyName+"\t"+propertyTypeName+"\t"+propertyNewValue+"\r\n";
			synchronized (this) {
				this.resultText.append(data);
			}
		}
	}
	
	protected void addResult(String anchor, String propertyName, String propertyTypeName, boolean value)
	{
		if(recording) // only record when recording is on and replaying is not
		{
			long time = System.currentTimeMillis();
			String data = anchor+"\t"+time+"\t"+propertyName+"\t"+propertyTypeName+"\t"+value+"\r\n";
			synchronized (this) {
				this.resultText.append(data);
			}
		}
	}
	protected void addResult(String anchor, int x, int y, int width, int height)
	{
		if(recording) // only record when recording is on and replaying is not
		{
			long time = System.currentTimeMillis();
			String data = anchor+"\t"+time+"\t"+x+"\t"+y+"\t"+width+"\t"+height+"\r\n";
			synchronized (this) {
				this.resultText.append(data);
			}
		}
	}
	/**
	 * Adding results for 2 param methods: Mouse Moved, Mouse Dragged 
	 * @param anchor
	 * @param x
	 * @param y
	 */
	protected void addResult(String anchor, int x, int y)
	{
		if(recording) // only record when recording is on and replaying is not
		{
			long time = System.currentTimeMillis();
			String data = anchor+"\t"+time+"\t"+x+"\t"+y+"\r\n";
			synchronized (this) {
				this.resultText.append(data);
			}
		}
	}
	/**
	 * Adding results for 3 param methods: Mouse Press, Mouse Released 
	 * @param anchor
	 * @param x
	 * @param y
	 * @param button
	 */
	protected void addResult(String anchor, int x, int y, int button)
	{
		if(recording) // only record when recording is on and replaying is not
		{
			long time = System.currentTimeMillis();
			String data = anchor+"\t"+time+"\t"+x+"\t"+y+"\t"+button+"\r\n";
			synchronized (this) {
				this.resultText.append(data);
			}
		}
	}
	/**
	 * Adding result for Key Press and Release methods
	 * @param anchor
	 * @param code
	 * @param modifiers
	 */
	protected void addResult(String anchor, String code, String modifiers)
	{
		if(recording) // only record when recording is on and replaying is not
		{
			long time = System.currentTimeMillis();
			String data = anchor+"\t"+time+"\t"+code+"\t"+modifiers+"\r\n";
			synchronized (this) {
				this.resultText.append(data);
			}
		}		
	}
	/**
	 * Adding result for Zoom value
	 * @param anchor
	 * @param value
	 */
	protected void addResult(String anchor, double value)
	{
		if(recording) // only record when recording is on and replaying is not
		{
			long time = System.currentTimeMillis();
			String data = anchor+"\t"+time+"\t"+value+"\r\n";
			synchronized (this) {
				this.resultText.append(data);
			}
		}
	}
	/**
	 * Adding result for translation 
	 * @param anchor
	 * @param x
	 * @param y
	 */
	protected void addResult(String anchor, double x, double y)
	{
		if(recording) // only record when recording is on and replaying is not
		{
			long time = System.currentTimeMillis();
			String data = anchor+"\t"+time+"\t"+x+"\t"+y+"\r\n";
			synchronized (this) {
				this.resultText.append(data);
			}		
		}
	}
}
