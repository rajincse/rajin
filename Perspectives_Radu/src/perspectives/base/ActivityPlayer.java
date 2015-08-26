package perspectives.base;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ActivityPlayer extends JDialog{
	
	private String filePath;
	
	Viewer viewer;
	
	boolean playing = false;

	public ActivityPlayer(JFrame parent, Environment e){
		super(parent);
		this.setUndecorated(true);
		this.setSize(220,20);
		getContentPane().setLayout(
			    new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS)
			);
		final JButton play = new JButton("PLAY");
		play.setEnabled(false);
		
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
		    	 play.setEnabled(true);
		      }
		    };	
		viewerCombo.addActionListener(listener);
		
		listener = new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  playing = !playing;
		    	 if (playing) play.setText("||");
		    	 else play.setText("PLAY");
		    	 
		    	 if(playing)
		    	 {
		    		 startReplay();
		    	 }
		    	 else
		    	 {
		    		 stopReplay();
		    	 }
		      }
		    };	
		play.addActionListener(listener);
		
		this.getContentPane().add(Box.createHorizontalGlue());		
		this.getContentPane().add(file);
		this.getContentPane().add(Box.createHorizontalGlue());
		this.getContentPane().add(viewerCombo);
		this.getContentPane().add(Box.createHorizontalGlue());
		this.getContentPane().add(play);
		this.getContentPane().add(Box.createHorizontalGlue());
	}
	
	
	public void viewerResize(Viewer v){
		
	}

	
	public void mousePressed(Viewer v, int x, int y, int button){
		v.getContainer().mousePressed(x, y, button);
	}
	
	public void mouseReleased(Viewer v, int x, int y, int button){
		v.getContainer().mouseReleased(x, y, button);
	}
	
	public void mouseMoved(Viewer v, int x, int y){
		v.getContainer().mouseMoved(x, y);
	}
	
	public void mouseDragged(Viewer v, int x, int y){
		v.getContainer().mouseDragged(x, y);
		
	}
	//-----REPLAYING START -----//
	
		// Used to indicate the first instruction
		public static final int INVALID =-1;
		// Saves the last instruction time
		protected long lastInstructionTime = INVALID;
		//Thread to perform the replay
		protected Thread replayThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				replay();
			}
		}, "ReplayThread");
		
		
		
		
		/**
		 * Starts the thread
		 */
		protected void startReplay()
		{
			this.replayThread.start();
		}
		/**
		 * Stops the thread
		 */
		protected void stopReplay()
		{
			try {
				this.replayThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Performs replaying
		 */
		protected void replay()
		{
			ArrayList<String> instructionList = getInstructionList(filePath); // Reads all the instructions
			
			for(String instruction: instructionList)
			{
				performInstruction(instruction); // performs the instructions one by one
			}
			lastInstructionTime = INVALID; // makes the process replayable
		}
		
		/**
		 * Peform a single instruction. 
		 * @param instruction
		 */
		protected void performInstruction(String instruction)
		{
			String[] split = instruction.split("\t");
			if(split.length > 2)
			{
				String anchor = split[0]; // Anchor at the first column
				long time = Long.parseLong(split[1]); // time at second
				
				if(lastInstructionTime== INVALID)
				{
					lastInstructionTime = time; // only for the first the instruction
				}
				long timelapse = time - lastInstructionTime; // the timelapse required
				
				lastInstructionTime = time;
				
				//sleep for the timelapse
				try {
					Thread.sleep(timelapse);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(anchor.equals(ActivityRecorder.EVENT_ANCHOR_MOUSE_DRAGGED))
				{
					int x = Integer.parseInt(split[2]);
					int y = Integer.parseInt(split[3]);
					this.mouseDragged(viewer, x, y);
				}
				else if(anchor.equals(ActivityRecorder.EVENT_ANCHOR_MOUSE_MOVED))
				{
					int x = Integer.parseInt(split[2]);
					int y = Integer.parseInt(split[3]);
					this.mouseMoved(viewer, x, y);
				}
				else if(anchor.equals(ActivityRecorder.EVENT_ANCHOR_MOUSE_PRESSED))
				{
					int x = Integer.parseInt(split[2]);
					int y = Integer.parseInt(split[3]);
					int button = Integer.parseInt(split[4]);
					this.mousePressed(viewer, x, y, button);
				}
				else if(anchor.equals(ActivityRecorder.EVENT_ANCHOR_MOUSE_RELEASED))
				{
					int x = Integer.parseInt(split[2]);
					int y = Integer.parseInt(split[3]);
					int button = Integer.parseInt(split[4]);
					this.mouseReleased(viewer, x, y, button);
				}
				else if(anchor.equals(ActivityRecorder.EVENT_ANCHOR_PROPERTY_VALUE_CHANGED))
				{
					String propertyName = split[2];
					Property p = this.viewer.getProperty(propertyName);
					String propertyTypeName = split[3];
					String serializedPropertyType = "";
					if(split.length> 4)
					{
						serializedPropertyType =split[4];
					}
					PropertyType newvalue = p.getValue().deserialize(serializedPropertyType);
					p.setValue(newvalue);
				}
				else if(anchor.equals(ActivityRecorder.EVENT_ANCHOR_PROPERTY_DISABILITY_CHANGED))				
				{
					String propertyName = split[2];
					Property p = this.viewer.getProperty(propertyName);
					String propertyTypeName = split[3];
					boolean isDisabled = Boolean.parseBoolean(split[4]);
					p.setDisabled(isDisabled);
				}
				else if(anchor.equals(ActivityRecorder.EVENT_ANCHOR_PROPERTY_VISIBILITY_CHANGED))				
				{
					String propertyName = split[2];
					Property p = this.viewer.getProperty(propertyName);
					String propertyTypeName = split[3];
					boolean isVisible = Boolean.parseBoolean(split[4]);
					p.setVisible(isVisible);
				}
				else if(anchor.equals(ActivityRecorder.EVENT_ANCHOR_SCREEN_SIZE))				
				{
					int x  = Integer.parseInt(split[2]);
					int y = Integer.parseInt(split[3]);
					int width = Integer.parseInt(split[4]);
					int height = Integer.parseInt(split[5]);
					
					
					viewer.getContainer().window.setBounds(x, y, width, height);
					this.viewerResize(viewer);
				}
				
			}
		}
		
		/**
		 * Reading the instruction from the file
		 * @param filePath
		 * @return
		 */
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
		//-----REPLAYING END -----//
		
		//-----------REPLAY FEATURE END---------------
	
}