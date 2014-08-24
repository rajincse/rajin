package perspectives.web;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.keypoint.PngEncoder;

import perspectives.base.DataSource;
import perspectives.base.Environment;
import perspectives.base.Property;
import perspectives.base.Viewer;
import perspectives.base.ViewerFactory;
import perspectives.d3.D3Renderer;


public class ViewerManagement extends HttpServlet {
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
	
	
	   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        PrintWriter out;
	        String outResponse = null;
	        HttpSession session = request.getSession();
	        String message = "";

	        Environment e = null;
	        //get the environment from the session or create a new one if the session doesn't have the environment initialized
	        if (session.getAttribute("environment") == null) {
	         

	        } else {
	            e = (Environment) session.getAttribute("environment");
	            message = "Environment already exists";
	       





	            try {
	        	
		           if (request.getParameter("page").equals("getViewerFactories")) {
		                //get viewer factories
		                System.out.println("get Viewer Fact....");
		                outResponse = getViewerFact(e);
		            } 
		           
		           else if (request.getParameter("page").equals("getCurrentViewers")) {
		                //Request to get Current Viewers from Perspectives
		                outResponse = getCurrViewers(e);
		            } 
		           
		           else if (request.getParameter("page").equals("linkViewers") || request.getParameter("page").equals("unlinkViewers")) {
		              
		        	   boolean how = request.getParameter("page").equals("linkViewers");		        	   
		        	   String v1s = request.getParameter("viewer1");
		        	   String v2s = request.getParameter("viewer2");
		        	   
		        	   Viewer v1 = this.getViewer(e, v1s);
		        	   Viewer v2 = this.getViewer(e, v2s);
		        	   
		        	   if (v1 != null && v2 != null)
		        	   {
		        		   if (how)
		        			   e.linkViewers(v1,v2, true);
		        		   else
		        			   e.unlinkViewers(v1,v2);
		        	   }  
		               outResponse = "";
		            }
		           
		           else if (request.getParameter("page").equals("getViewerLinks")) {
		        	   
		        	  Viewer[][] links =  e.getLinks();
		        	  
		        	  outResponse = "";
		        	  
		        	  for (int i=0; i<links.length; i++)
		        	  {
		        		  if (i != 0) outResponse += "\t";		        		  
		        		  outResponse += links[i][0].getName() + "\t" + links[i][1].getName();
		        	  }       	   
		        	 
		             
		            }
		           
		           else if (request.getParameter("page").equals("createViewer")) {
		                //Request to Create Viewer              
		                System.out.println("create Viewer....");
		                String type = request.getParameter("type");
		                String data = request.getParameter("data");
		                String viewerName = request.getParameter("viewerName");
		                String error = createViewer(e, type, viewerName, data);
		                if (error.length() == 0)
		                	outResponse = getCurrViewers(e);
		                else
		                	outResponse = error;
		                session.setAttribute("environment", e);
	
		            } else if (request.getParameter("page").equals("deleteViewer")) {
		                //Request to Delete Viewer                
		                String viewer = request.getParameter("viewer");
		                deleteViewer(e,viewer);
		                outResponse = getCurrViewers(e);
		            } 
		            else if (request.getParameter("page").equals("launchViewer")) {
		                //Request to Launch Viewer Page               
		                String viewerName = request.getParameter("viewerName");
	
		                Viewer viewer = getViewer(e, viewerName);
		               
		                if (viewer instanceof D3Renderer) {
		                    System.out.println("D3-VIEWER");
		                    outResponse = "d3viewer.html" + ";" + viewerName;
		                } else {
		                    outResponse = "viewer.html" + ";" + viewerName;
		                }
	
		            } else if (request.getParameter("page").equals("d3viewer")) {

                        String viewerName = request.getParameter("viewerName");

                        //Request to get Initial Properties    
                        String method = request.getParameter("method");
                        if (method.equals("readViewerData")) {
                            loadD3Viewer(e, viewerName, request, response);
                        } 
                        else if (method.equals("getImage"))
                        {
                        	String imageId = request.getParameter("viewerName");
                        	Viewer viewer = getViewer(e, viewerName);
                        	BufferedImage image = ((D3Renderer)viewer).getImage(imageId);
                        	
                            response.setContentType("image/png");
                            response.setHeader("Cache-control", "no-cache, no-store");
                            response.setHeader("Pragma", "no-cache");
                            response.setHeader("Expires", "-1");
                            
                            PngEncoder p = new PngEncoder(image, true);	                        
				             p.setCompressionLevel(8);
				             byte[] bs = p.pngEncode(true);

                            response.getOutputStream().write(bs); 
                        }
                        else if (method.equals("readCreatorType")) {

                        	
                        
                            Viewer viewer = getViewer(e, viewerName);                            
                         
                           
                            outResponse = viewer.getClass().getName();
                        }
                    }
            
		            
		            
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
	
		        session.setAttribute("environment", e); //reset the environment session
	
		        if (outResponse != null) {
		        	
		        	System.out.println("ViewerManagement: " + outResponse);
		            out = response.getWriter();
		            out.write(outResponse);
		            out.flush();
		            out.close();
		        }
	        }




	    }
	   
	   
	    //Method to get available viewer factories
	    public String getViewerFact(Environment e) {

	        String allViewers = "";
	        Vector<ViewerFactory> viewers = e.getViewerFactories();
	        for (int i = 0; i < viewers.size(); i++) {
	            if (allViewers.equals("")) {
	                allViewers = viewers.get(i).creatorType();
	            } else {
	                allViewers += "," + viewers.get(i).creatorType();
	            }
	        }
	        return allViewers;
	    }
	    
	    
	    //Method to get a list of current viewers
	    public String getCurrViewers(Environment e) {

	        String allCurrViewers = "";
	        try {
	            Vector<Viewer> viewers = e.getViewers();

	            for (int i = 0; i < viewers.size(); i++) {
	                if (allCurrViewers.equals("")) {
	                    allCurrViewers = viewers.get(i).getName();
	                } else {
	                    allCurrViewers += ";" +viewers.get(i).getName();
	                }
	            }

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        if (allCurrViewers.equals("")) {
	            return "";
	        } else {
	            return allCurrViewers;
	        }
	    }
	    
	    private DataSource getDataSource(Environment e, String d)
	    {
	    	Vector<DataSource> ds = e.getDataSources();
	    	for (int i=0; i<ds.size(); i++)
	    		if (ds.get(i).getName().equals(d))
	    			return ds.get(i);
	    	return null;
	    }
	    
	    private ViewerFactory getFactory(Environment e, String d)
	    {
	    	Vector<ViewerFactory> ds = e.getViewerFactories();
	    	for (int i=0; i<ds.size(); i++)
	    		if (ds.get(i).creatorType().equals(d))
	    			return ds.get(i);
	    	return null;
	    }
	    
	    
	    public String createViewer(Environment e, String type, String name, String data) {

	    	String[] dataSourceNames = data.split(";");
	    	DataSource[] dataSources = new DataSource[dataSourceNames.length];
	    	for (int i=0; i<dataSources.length; i++)
	    		dataSources[i] = getDataSource(e, dataSourceNames[i]);
	    	
	    	
	        Viewer v = null;
	       
	        //find the ViewerFactory
	        ViewerFactory factory = getFactory(e, type);
	        
	        if (factory == null)
	        	return "Error: no such viewer type";          
	        
	        factory.clearData();
	        for (int i = 0; i < dataSources.length; i++) {
	                     factory.addDataSource(dataSources[i]);
	                     break;
	          }
	        
	        if (factory.isAllDataPresent())
	        {
	     	   //create the viewer
	             v = factory.create(name);
	             v.setPropertyManagerGroup(e);
	             e.addViewer(v);	
	        }
	        
	         return "";
	     

	    }
	    
	    public void deleteViewer(Environment e, String v)
	    {
	    	Viewer viewer = getViewer(e,v);
	    	if (v != null)
	    		e.deleteViewer(viewer);
	
	    }
	    
	    public Viewer getViewer(Environment e, String v)
	    {
	    	Vector<Viewer> viewers = e.getViewers();
	    	for (int i=0; i<viewers.size(); i++)
	    		if (viewers.get(i).getName().equals(v))
	    			return viewers.get(i);
	    	return null;
	    		
	    }
	    public void loadD3Viewer(Environment e, String viewerName, HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	    	System.out.println("ViewerManag: loadD3Viewer 1");

	        HttpSession session = request.getSession();

	        if (request.getParameter("isinitcall") != null
	                && request.getParameter("isinitcall").equalsIgnoreCase("True")) {

	        	System.out.println("ViewerManag: loadD3Viewer 2");
	            response.setContentType("application/json;charset=UTF-8");
	            response.setHeader("Cache-control", "no-cache, no-store");
	            response.setHeader("Pragma", "no-cache");
	            response.setHeader("Expires", "-1");

	            D3Renderer viewer = (D3Renderer) this.getViewer(e, viewerName);
	            PrintWriter out = response.getWriter();
	            out.println(viewer.renderToData());
	            out.flush();
	            out.close();

	        	System.out.println("ViewerManag: loadD3Viewer 3");

	            session.setAttribute("environment", e); //reset the environment session
	        } else {
	        	System.out.println("ViewerManag: loadD3Viewer 4");
	            response.setContentType("application/json;charset=UTF-8");
	            response.setHeader("Cache-control", "no-cache, no-store");
	            response.setHeader("Pragma", "no-cache");
	            response.setHeader("Expires", "-1");

	            D3Renderer viewer = (D3Renderer) this.getViewer(e, viewerName);
	            PrintWriter out = response.getWriter();
	            out.println(viewer.renderToData());
	            out.flush();
	            out.close();

	            session.setAttribute("environment", e); //reset the environment session
	        	System.out.println("ViewerManag: loadD3Viewer 5");
	        }
	    }
	    
}
