package perspectives.base;

import java.awt.BasicStroke;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyVetoException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import perspectives.properties.*;
import perspectives.base.PropertyWidgetFactory;
import perspectives.base.PropertyWidget;
import perspectives.util.*;


import perspectives.three_d.LWJGL3DRenderer;
import perspectives.two_d.JavaAwtRenderer;
import perspectives.two_d.ViewerContainer2D;
import perspectives.three_d.ViewerContainer3D;





/**
 * 
 * @author rdjianu <br>
 * 
 * Main class that regulates the global activity of Perspectives. It sets up the GUI, keeps track of Viewers, Datasources, links between them, propagates events etc. You should create one of these classes in your main function.
 * <br>
 * 
 * The Environment consists of a viewer area that behaves like a desktop. ViewerContainers can live within this space wrapping Viewer objects. ViewerContainers are windows that can be moved, minimized, maximized, and closed.
 * On the left hand side the Environment has a few controls: a menu panel in which users can manage datasources and viewers and a dedicated slot below in which properties of selected items (DataSources and Viewers) are displayed.
 * Users may draw links between Viewers displayed in the viewer area. To do this, they need to activate the Links toggle button and drag little icons that are placed under each ViewerContainer. If they release the arrow inside
 * a target ViewerContainer those two viewwers will be linked, meaning a property change in one viewer will be transmitted to the other. 
 *
 */
public class Environment extends PropertyManagerGroup implements Serializable{
	
	private boolean offline = false;
	
	private JPanel allPanel; //main gui
	private JDesktopPane viewerArea; //has a this
	private JPanel controlArea; //and this
	
	private JPanel propertyPanel; //contained in control area
	private JPanel menuPanel; //contained in control area
	
	//these give the types of datasources and viewers that can be created by users; developers can add such factories to the Environment
	private Vector<ViewerFactory> viewerFactories = new Vector<ViewerFactory>();
	private Vector<DataSourceFactory> dataFactories = new Vector<DataSourceFactory>();
	
	//this is used for links, because then links are drawn the viewer windows are minimize. ultimately they need to be restored, which is what this is for
	private Vector<Rectangle> viewerBounds = new Vector();	
	
	//viewers are contained in viewerContainers so we need to have a list of both;
	private Vector<Viewer> viewers = new Vector<Viewer>();
	private Vector<ViewerContainer> viewerContainers = new Vector<ViewerContainer>();
	private Vector<ViewerWindow> viewerWindows = new Vector<ViewerWindow>();
	
	//contains all created data sources
	private JList<String> dataList;
	
	private Vector<DataSource> dataSources = new Vector<DataSource>();
	private Vector<JInternalFrame> dataFrames = new Vector<JInternalFrame>();
	
	//used to repain the drwaing area. we need to repaint the entire drawing area because of the links we are dragging around;
	private Timer timer;
	
	private Font font = new Font("Courier", Font.PLAIN, 11);
	
	//the icon displayed for Perspectives main window
	private Icon frameicon;
	
	private Icon viewerIcon, newViewerIcon, dataIcon, newDataIcon, linksIcon, helpIcon;
	
	//the main frame
	private JFrame frame;
	
	//auto-naming integers for generating automatic names for data sources and viewers
	private int autoDataName = 1;
	
	//auto-naming integers for generating automatic names for data sources and viewers
	private int autoViewerName = 1;	
	
	private int newDataY = 50;
    private int newDataX = 20;
    
    private int defaultDataNameCounter = 1;
    
    private String localDataPath;
    
    

	
	
	// -----------------------------    Managing and drawing links -----------------------------
	
	//this is how we store connections between viewers: pairs of viewers and then a boolean indicating whether the connection is bidirectional or not.
	private Vector<Viewer> links1 = new Vector<Viewer>();
	private Vector<Viewer> links2 = new Vector<Viewer>();
	private Vector<Boolean> linksDouble = new Vector<Boolean>();
		
	public Environment(boolean offline)
	{
		this.offline = offline;
				
	     ///set default properties
	     
				this.registerNewType(new PString(""), new PropertyWidgetFactory() {
					public PropertyWidget createWidget(Property p) {
						return new PStringWidget(p);
				}});		
				
				this.registerNewType(new PDouble(0), new PropertyWidgetFactory() {
					public PropertyWidget createWidget(Property p) {
						return new PDoubleWidget(p);
				}});	
				
				this.registerNewType(new PInteger(0), new PropertyWidgetFactory() {
					public PropertyWidget createWidget(Property p) {
						return new PIntegerWidget(p);
				}});	
				
				this.registerNewType(new PBoolean(true), new PropertyWidgetFactory() {
					public PropertyWidget createWidget(Property p) {
						return new PBooleanWidget(p);
				}});		
								
				this.registerNewType(new POptions(new String[]{""}), new PropertyWidgetFactory() {
					public PropertyWidget createWidget(Property p) {
						return new POptionsWidget(p);
				}});	
									
				this.registerNewType(new PFileInput(), new PropertyWidgetFactory() {
					public PropertyWidget createWidget(Property p) {
						return new PFileInputWidget(p);
				}});
				
				this.registerNewType(new PFileOutput(), new PropertyWidgetFactory() {
					public PropertyWidget createWidget(Property p) {
						return new PFileOutputWidget(p);
				}});	
								
						
								
				this.registerNewType(new PPercent(0), new PropertyWidgetFactory() {
					public PropertyWidget createWidget(Property p) {
						return new PPercentWidget(p);
				}});		
									
				this.registerNewType(new PColor(Color.black), new PropertyWidgetFactory() {
					public PropertyWidget createWidget(Property p) {
						return new PColorWidget(p);
				}});		
							
				this.registerNewType(new PList(), new PropertyWidgetFactory() {
					public PropertyWidget createWidget(Property p) {
						return new PListWidget(p);
				}});	
				
				this.registerNewType(new PProgress(0), new PropertyWidgetFactory() {
					public PropertyWidget createWidget(Property p) {
						return new PProgressWidget(p);
				}});
				
				this.registerNewType(new PSignal(), new PropertyWidgetFactory() {
					public PropertyWidget createWidget(Property p) {
						return new PSignalWidget(p);
				}});
				
				this.registerNewType(new PText(""), new PropertyWidgetFactory() {
					public PropertyWidget createWidget(Property p) {
						return new PTextWidget(p);
				}});
		
		final Environment ev = this;
		Timer unresponsive = new Timer(1000, new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				Vector<ViewerContainer> vc = ev.getViewerContainers();
				for (int i=0; i<vc.size(); i++)
				{
					if (vc.get(i).unresponsive())
					{
						if (!ev.offline)
						{
							String title = ev.viewerWindows.get(i).getTitle();
							if (!title.endsWith("Unresponsive"))
								ev.viewerWindows.get(i).setTitle( title +  " -- Unresponsive");
						}
						vc.get(i).block(true);
						
					}
					else
					{
						if (!ev.offline)
							ev.viewerWindows.get(i).setTitle(vc.get(i).viewer.getName());
						vc.get(i).block(false);
					}
				}
				
				Vector<DataSource> ds = ev.getDataSources();
				for (int i=0; i<ds.size(); i++)
				{
					if (ds.get(i).unresponsive())
					{
						if (!ev.offline)
						{
							String title = ev.dataFrames.get(i).getTitle();
							if (!title.endsWith("Unresponsive"))
								ev.dataFrames.get(i).setTitle( title +  " -- Unresponsive");
						}
						ds.get(i).block(true);
						
					}
					else
					{
						if (!ev.offline)
						{
							ev.dataFrames.get(i).setTitle(ds.get(i).getName());
						}
						ds.get(i).block(false);
					}
				}
			}
		
		});
		unresponsive.setRepeats(true);
		unresponsive.setDelay(1000);
		unresponsive.start();
		
		if (offline)
			return;
		
		 frame = new JFrame("Perspectives");
		 
		 //maximize the window
	     GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
	     frame.setMaximizedBounds(e.getMaximumWindowBounds());
	     frame.setBounds(50, 20, 800, 600);	  
	     frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setVisible(true);	    
	        
		 
		 Toolkit tool = Toolkit.getDefaultToolkit();		 
		 Image icon = tool.getImage("images/frame_icon.png");
		 frame.setIconImage(icon);
		 
		 frameicon = new ImageIcon(icon);
		 
		 viewerIcon = new ImageIcon(tool.getImage("images/viewer.png"));
		 newViewerIcon = new ImageIcon(tool.getImage("images/new_viewer.png"));
		 dataIcon = new ImageIcon(tool.getImage("images/data.png"));
		 newDataIcon = new ImageIcon(tool.getImage("images/new_data.png"));
		 linksIcon = new ImageIcon(tool.getImage("images/links.png"));
		 helpIcon = new ImageIcon(tool.getImage("images/help.png"));
	     
	     allPanel = new JPanel(new BorderLayout());
	     frame.getContentPane().add(allPanel);  
	     
	     //sets the look and feel of th esystem (e.g. Windows)
	     try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e1) {
				e1.printStackTrace();
		}
	     
		 
	     //the viewer area instantiation 
		 
	     viewerArea = new JDesktopPane();	     
	     viewerArea.setBackground(Color.WHITE);
	     
	  
	     allPanel.add(viewerArea,BorderLayout.CENTER);	   
	       
	      JButton newViewer = new JButton();	    
	      newViewer.setIcon(newViewerIcon);
	      newViewer.setBounds(50,10,25,25);	 
	      newViewer.setToolTipText("Create a new data viewer");
	      viewerArea.add(newViewer);
	      newViewer.setVisible(true);
	    
		  newViewer.addActionListener(new ActionListener() {	    	   
		    	   public void actionPerformed(ActionEvent e) {
		    		   createNewViewer();			    		        
				      }			      
		       });    
	    
	    
	       JToggleButton linkButton = new JToggleButton();	      
	       linkButton.setIcon(linksIcon);
	       linkButton.setBounds(80,10,50,25);	     
		   viewerArea.add(linkButton);
		   linkButton.setVisible(true);
		   linkButton.setToolTipText("Link your viewers to each other");
		   
		   linkButton.addActionListener(new ActionListener() {	
			   
			   //if the links button is on, the viewer containers show up as small icons; once the button is off they are restored to their original size; sizes thus need to be stored and restored
		    	   public void actionPerformed(ActionEvent e) {
		    		   showLinksManager();		    		 
				      }			      
		       });   
	       
		  
	       
	       JButton newData = new JButton();		       
	       newData.setIcon(newDataIcon);
	       newData.setBounds(20,10,25,25);	     
		   viewerArea.add(newData);
		   newData.setVisible(true);  
		   newData.setToolTipText("Create a new data source");
	     
	       newData.addActionListener(new ActionListener() {	    	   
	    	   public void actionPerformed(ActionEvent e) {
	    		   createNewData();			    		        
			      }			      
	       });	
	       
	       
	       JButton helpButton = new JButton();		       
	       helpButton.setIcon(helpIcon);
	       helpButton.setBounds(135,10,25,25);	     
		   viewerArea.add(helpButton);
		   helpButton.setVisible(true);   
		   helpButton.setToolTipText("Help. Not yet implemented.");
	     
		   helpButton.addActionListener(new ActionListener() {	    	   
	    	   public void actionPerformed(ActionEvent e) {
	    		   createNewData();			    		        
			      }			      
	       });	
	       
	       allPanel.revalidate();       
                 
	}
	
	public String getLocalDataPath()
	{
		return this.localDataPath;
	}
	
	public void setLocalDataPath(String p)
	{
		System.out.println("environment local path set to: " + p);
		this.localDataPath = p;
	}
	
	
	public void showLinksManager()
	{
		//open a dialog box; create a viewer
		LinksManager vc = new LinksManager(this);
		vc.setVisible(true);
	}
	
	public boolean isOffline()
	{
		return offline;
	}
	
	
	/**
	 * Opens a dialog box where the user can create a new viewer: specify the type; select its datasources, name it, add it to Environment
	 */
	public void createNewViewer()
	{
		//open a dialog box; create a viewer
		ViewerCreator vc = new ViewerCreator(this, "viewer" + (autoViewerName++));
		vc.setVisible(true);
		if (vc.createdViewer != null)
		{
			Viewer v = vc.createdViewer;
			v.setPropertyManagerGroup(this);
			addViewer(v);			
		}
	}
	
	/**
	 * Opens a dialog box where the user can create a new datasource: specify the type, name it, add it to Environment
	 */
	public void createNewData()
	{
		DataCreator dc = new DataCreator(this, "Data"+(defaultDataNameCounter++));
		dc.setVisible(true);
		
		if (dc.createdDatasource != null)
			addDataSource(dc.createdDatasource);			
	}
	
	public JPanel getMenuPanel()
	{
		return menuPanel;
	}
	
	/**
	 * @return All DataSource items available in the Environment
	 */
	public Vector<DataSource> getDataSources()
	{
		return dataSources;
	}
	
	/**
	 * @return All Viewer items available in the Environment
	 */
	public Vector<Viewer> getViewers()
	{
		return viewers;
	}	

	/**
	 * Register a new Viewer factory with the system; users will then be able to create this type of Viewers in the system
	 * @param vf
	 */
	public void registerViewerFactory(ViewerFactory vf)
	{
		viewerFactories.add(vf);
	}
	
	/**
	 * 
	 * @return All ViewerFactory items available in the Environment
	 */
	public Vector<ViewerFactory> getViewerFactories()
	{
		return viewerFactories;
	}
	
	/**
	 * Register a new DataSourceFactory with the system; users will then be able to create this type of DataSources in the system
	 * @param vf
	 */	
	public void registerDataSourceFactory(DataSourceFactory df)
	{
		dataFactories.add(df);
	}	
	
	/**
	 * 
	 * @return All DataSourceFactory items available in the Environment
	 */
	public Vector<DataSourceFactory> getDataFactories()
	{
		return dataFactories;
	}
	
	/**
	 * Adds a viewer to the environment; this is generally handeld internally by Environment as a result of the createViewer function;
	 * @param v
	 */
	public void addViewer(Viewer v)
	{
		v.setPropertyManagerGroup(this);
		
		final ViewerContainer vc;
		if (v instanceof LWJGL3DRenderer)
			vc = new ViewerContainer3D(v,this,1000,700);
		else if (v instanceof JavaAwtRenderer)
			vc = new ViewerContainer2D(v,this,1000,700);
		else vc = null;
		
		viewers.add(v);
		viewerContainers.add(vc);	
		
		if (!offline)
		{
			ViewerWindow vw = new ViewerWindow(vc);
			vw.setFrameIcon(viewerIcon);
			viewerArea.add(vw);
			int offsetx = 250+viewers.size()*10;
			int offsety = 100+viewers.size()*10;
			
			vw.setBounds(offsetx,offsety,700,500);	

			viewerWindows.add(vw);
			setFocusedViewer(viewers.size()-1);	
		}

			
	}
	
	/**
	 * A user selected a different viewer to be in the foreground. Its properties are displayed in the property slot.
	 * @param v
	 */
	public void setFocusedViewer(Viewer v)
	{
		int index = viewers.indexOf(v);
		if (index >= 0)
			setFocusedViewer(index);
			
	}
	
	public void setFocusedViewer(int which)
	{		
		if (which < 0 || which >= viewers.size())
		{
					try {
						if (viewerArea.getSelectedFrame() != null)
							viewerArea.getSelectedFrame().setSelected(false);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			return;
		}		
		viewerWindows.get(which).setBorder(BorderFactory.createLineBorder(new Color(150,150,200),2));
		System.out.println("setfocusedviewer1: " + which + viewerWindows.get(which));
		System.out.println("setfocusedviewer2: " + viewerWindows.get(which));
		viewerArea.setComponentZOrder(viewerWindows.get(which), 0);
		
		try {
			viewerWindows.get(which).setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		viewerArea.repaint();
		
	}
	
	/**
	 * User deleted a viewer
	 * @param v
	 */
	public void deleteViewer(Viewer v)
	{
		int index = viewers.indexOf(v);
		
		if (index < 0) return;
		
		viewers.remove(index);
		viewerContainers.remove(index);
		if (!offline)
		{
			viewerArea.remove(viewerWindows.get(index));
			viewerWindows.get(index).dispose();
			viewerWindows.remove(index);
			
		}
		

	}
	
	public void addDataSource(DataSource d)
	{
		dataSources.add(d);
		
		if (offline)
			return;
		
		final JInternalFrame dataFrame = new JInternalFrame(d.getName());
		dataFrame.setFrameIcon(dataIcon);
		dataFrame.setBorder(BorderFactory.createLineBorder(new Color(180,180,250)));
		dataFrame.setBounds(newDataX,newDataY,200,400);
		dataFrame.setVisible(true);
		dataFrame.setClosable(true);
		dataFrame.setIconifiable(true);
		viewerArea.add(dataFrame);	
		
		dataFrames.add(dataFrame);
				
		newDataY += 30;
		newDataX += 10;
		
		viewerArea.setComponentZOrder(dataFrame, 0);
		
		PropertyManagerViewer pmv = new PropertyManagerViewer(d);
		
		PropertyManagerChangeListener pmcl = new PropertyManagerChangeListener()
		{
			public void propertyValueChanges(PropertyManager pm, Property p,	PropertyType newValue) {	}		
			public void propertyReadonlyChanges(PropertyManager pm, Property p,			boolean newReadOnly) {		}
			public void propertyVisibleChanges(PropertyManager pm, Property p,			boolean newVisible) {		}
			public void propertyPublicChanges(PropertyManager pm, Property p,		boolean newPublic) {		}
			public void propertyDisabledChanges(PropertyManager pm, Property p,					boolean enabled) {			}
			public void propertyProperyManagerChanges(PropertyManager pm,	Property p, PropertyManager newpm) {	}

			public void propertyAdded(PropertyManager pm, Property p) {	
				dataFrame.pack();	
			}

			@Override
			public void propertyRemoved(PropertyManager pm, Property p) {
				dataFrame.pack();			
			}
			
		};
		d.addChangeListener(pmcl);

		
		dataFrame.add(pmv);		
		dataFrame.pack();
		
		final DataSource fd = d;		
		
		
		
		dataFrame.addInternalFrameListener(new InternalFrameListener()
		{
			public void internalFrameActivated(InternalFrameEvent arg0) {
			}
			public void internalFrameClosed(InternalFrameEvent arg0) {
				deleteDataSource(fd);
			}
			public void internalFrameClosing(InternalFrameEvent arg0) {
			}
			public void internalFrameDeactivated(InternalFrameEvent arg0) {
			}
			public void internalFrameDeiconified(InternalFrameEvent arg0) {
			}
			public void internalFrameIconified(InternalFrameEvent arg0) {
			}
			public void internalFrameOpened(InternalFrameEvent arg0) {
			}
              
        } ); 	
		
	}
	
	public void deleteDataSource(DataSource d)
	{
		int index = dataSources.indexOf(d);
		
		if (index < 0 || index >= dataSources.size())
			return;
		
		dataSources.remove(index);
		if (!this.offline)
			dataFrames.remove(index);
	}


	@Override
	/**
	 * Overide from PropertyManagerGroup: based on the links network we propagate the the property change from the originating viewer
	 */
	public <T extends PropertyType> void broadcast(PropertyManager p, Property prop, T newvalue) {	

		for (int j=0; j<links1.size(); j++)
		{				
			Viewer v1 = links1.get(j);
			Viewer v2 = links2.get(j);
			
			boolean dob = linksDouble.get(j);
	        	
			if (v1 == p)
				v2.receivePropertyBroadcast(p, prop.getName(), newvalue);
			else if (v2 == p && dob)
				v1.receivePropertyBroadcast(p, prop.getName(), newvalue);
		}
	}
	
	public Vector<ViewerContainer> getViewerContainers()
	{
		return viewerContainers;
	}
	
	public void registerNewType(PropertyType c, PropertyWidgetFactory pwf) {	
		PropertyManager.registerType(c);
		PropertyManagerViewer.registerNewType(c, pwf);	
	}	
	
	public void linkViewers(Viewer v1, Viewer v2, boolean dir)
	{
		this.links1.add(v1);
		this.links2.add(v2);
		this.linksDouble.add(dir);
	}
	
	public void unlinkViewers(Viewer v1, Viewer v2)
	{
		for (int i=0; i<links1.size(); i++)
		{
			if ((links1.get(i) == v1 && links2.get(i) == v2) || (links2.get(i) == v1 && links1.get(i) == v2))
			{
				links1.remove(i);
				links2.remove(i);
				linksDouble.remove(i);
				i--;
			}
		}
	}
	
	public Viewer[][] getLinks()
	{
		Viewer[][] result = new Viewer[links1.size()][];
		
		for (int i=0; i<links1.size(); i++)
		{
			result[i] = new Viewer[2];
			result[i][0] = links1.get(i);
			result[i][1] = links2.get(i);
		}
		
		return result;
	}
	
	
	

}
