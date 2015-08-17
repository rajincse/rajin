package perspectives.base;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
		    	 rec.setEnabled(true);
		      }
		    };	
		viewerCombo.addActionListener(listener);
		
		listener = new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	 recording = !recording;
		    	 if (recording) rec.setText("||");
		    	 else rec.setText("REC");
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
		
	}

	
	public void mousePressed(Viewer v, int x, int y, int button){
		
	}
	
	public void mouseReleased(Viewer v, int x, int y, int button){
		
	}
	
	public void mouseMoved(Viewer v, int x, int y){
		
	}
	
	public void mouseDragged(Viewer v, int x, int y){
		
	}
	
}
