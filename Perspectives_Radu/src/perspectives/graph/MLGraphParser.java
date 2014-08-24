package perspectives.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import perspectives.base.Property;
import perspectives.properties.PString;

public class MLGraphParser implements GraphParser {
	
	public Graph from(InputStream is)
	{
		  try {	
			  
			  Graph g = new Graph(false);
			  
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(is);
				doc.getDocumentElement().normalize();				
				
				//load attributes/properties
				HashMap<String, Class> attributeTypes = new HashMap<String, Class>();
				HashMap<String, String> attributeNames = new HashMap<String, String>();
				
				NodeList keys = doc.getElementsByTagName("key");
				for (int i=0; i<keys.getLength(); i++)
				{
					Element e = (Element)keys.item(i);
					
					String t = e.getAttribute("attr.type");
					t = t.toUpperCase().charAt(0) + t.substring(1);
					t = "java.lang." + t;
					
					Class c = Class.forName(t);
					
					attributeTypes.put(e.getAttribute("id"), c);
					attributeNames.put(e.getAttribute("id"), e.getAttribute("attr.name"));
				}
		 			
						
				NodeList graphNode = doc.getElementsByTagName("graph");
				if (graphNode.getLength() == 0)
					return null;
				
				NodeList graphElem = graphNode.item(0).getChildNodes();
				
				for (int i=0; i<graphElem.getLength(); i++)
				{
					if (graphElem.item(i).getNodeType() == Node.ELEMENT_NODE)
					{
						Element e = (Element)graphElem.item(i);
						
						if (e.getNodeName() == "node")
						{
							String nodeId = e.getAttribute("id");							
							g.addNode(nodeId);
							
							//now get attributes
							NodeList data = e.getElementsByTagName("data");
							for (int j=0; j<data.getLength(); j++)
							{
								Element de = (Element)data.item(j);
								
								String s = de.getChildNodes().item(0).getNodeValue();
								
								String key = de.getAttribute("key");
								
								Class c = attributeTypes.get(key);
															
								Property p = new Property(attributeNames.get(key), new PString(s));							
								g.addNodeProperty(nodeId, p);
							}
						}
						else if (e.getNodeName() == "edge")
						{
							String sId = e.getAttribute("source");
							String tId = e.getAttribute("target");
							
							if (g.isEdge(tId, sId))
								g.setDirected(true);
							
							g.addEdge(sId,tId);
							
							//now get attributes
							NodeList data = e.getElementsByTagName("data");
							for (int j=0; j<data.getLength(); j++)
							{
								Element de = (Element)data.item(j);
								
								String s = de.getChildNodes().item(0).getNodeValue();
								
								String key = de.getAttribute("key");
								
								Class c = attributeTypes.get(key);
															
								Property p = new Property(attributeNames.get(key), new PString(s));							
								g.addEdgeProperty(sId, tId, p);
							}
						}
					}
				}
				
				  return g;
				
			  } catch (Exception e) {
				e.printStackTrace();
				return null;
			  }	
		
	}
	public void to(Graph g, OutputStream os)
	{
		 try {
	            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
	            Document doc = docBuilder.newDocument();

	            Element re = doc.createElement("graphml");
	            re.setAttribute("xmlns","http://graphml.graphdrawing.org/xmlns");  
	            re.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	            re.setAttribute("xsi:schemaLocation","http://graphml.graphdrawing.org/xmlns,http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd");
	            doc.appendChild(re);

	            //create child element, add an attribute, and add to root
	            Element ge = doc.createElement("graph");
	            ge.setAttribute("id", "graph");
	            ge.setAttribute("edgedefault", "undirected");
	            
	            
	            ArrayList<String> nodes = g.getNodes();
	            HashSet<String> nodeKeys = new HashSet<String>();
	            for (int i=0; i<nodes.size(); i++)
	            {
	            	String id = nodes.get(i);
	            	
	            	Element ne = doc.createElement("node");
	            	ne.setAttribute("id", id);
	            	ge.appendChild(ne);
	            	
	            	//properties
	            	ArrayList<Property> prop = g.getNodeProperties(id);
	            	for (int j=0; prop!=null && j<prop.size(); j++)
	            	{
	            		//first check for existing key
	            		if (!nodeKeys.contains(prop.get(j).getName()))
	            		{
	            			//add a key
	            			Element ke = doc.createElement("key");
	            			ke.setAttribute("id", prop.get(j).getName());
	            			ke.setAttribute("for", "node");
	            			ke.setAttribute("attr.name", prop.get(j).getName());
	            			
	            			String clas = prop.get(j).getValue().getClass().toString();
	            			clas = clas.substring(clas.lastIndexOf('.')+1);
	            			clas = clas.toLowerCase();
	            			ke.setAttribute("attr.type", clas);
	            			re.appendChild(ke);
	            			
	            			nodeKeys.add(prop.get(j).getName());
	            		}
	            		
	            		//then add the property
	            		Element pe = doc.createElement("data");
	            		pe.setAttribute("key", prop.get(j).getName());
	            		ne.appendChild(pe);
	            		
	            		Text text = doc.createTextNode(""+prop.get(j).getValue());
	                    pe.appendChild(text);	            		
	            	}	            	
	            }
	            
	            ArrayList<String> e1 = new ArrayList<String>();
	            ArrayList<String> e2 = new ArrayList<String>();
	            g.getEdges(e1,e2);
	            
	            for (int i=0; i<e1.size(); i++)
	            {
	            	String id1 = e1.get(i);
	            	String id2 = e2.get(i);
	            	
	            	Element ee = doc.createElement("edge");
	            	ee.setAttribute("source", id1);
	            	ee.setAttribute("target", id2);
	            	ge.appendChild(ee);
	            }

	            re.appendChild(ge);
	       
	            /////////////////
	            //Output the XML

	            //set up a transformer
	            TransformerFactory transfac = TransformerFactory.newInstance();
	            Transformer trans = transfac.newTransformer();
	            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	            trans.setOutputProperty(OutputKeys.INDENT, "yes");

	            //create string from xml tree
	          //  StringWriter sw = new StringWriter();
	            StreamResult result = new StreamResult(os); //was sw
	            DOMSource source = new DOMSource(doc);
	            trans.transform(source, result);
	         //   String xmlString = sw.toString();      
	         
	         
	            os.close();

	        } catch (Exception e) {
	            System.out.println(e);
	        }
	}
}
