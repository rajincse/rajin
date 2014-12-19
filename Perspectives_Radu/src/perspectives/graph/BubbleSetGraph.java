package perspectives.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import perspectives.base.Property;
import perspectives.properties.PBoolean;
import perspectives.properties.PDouble;
import perspectives.properties.PFileInput;
import perspectives.properties.PInteger;
import perspectives.properties.PSignal;
import perspectives.properties.PString;
import perspectives.base.PropertyType;
import perspectives.util.BubbleSets;
import perspectives.util.Util;

public class BubbleSetGraph extends ClusterGraphViewer {
	
	Object bigGridCompute = new Object();
	BubbleSets bubbleSets = null;	
	BufferedImage bigGrid;
	int bigGridSX, bigGridSY;
	
	Path2D[] bubbles = null;
	
	private int splitClusters =0;

	public BubbleSetGraph(String name, GraphData g) {
		super(name, g);
		try {			
			
			Property<PInteger> p44 = new Property<PInteger>("Cluster Bubbles", new PInteger(0))
					{
						@Override
		    			public boolean updating(PInteger newvalue)
		    			{
							createBubbles();
		    				return true;
		    			}         
					};			
			this.addProperty(p44);	
			
			Property<PInteger> p4 = new Property<PInteger>("Bubble",  new PInteger(0))
					{
				@Override
    			public boolean updating(PInteger newvalue)
    			{
					createBubbles();
    				return true;
    			}         
			};	
			this.addProperty(p4);	
			
			Property<PInteger> p5 = new Property<PInteger>("BubbleCellSize",  new PInteger(10))
			{
				@Override
    			public boolean updating(PInteger newvalue)
    			{
					createBubbles();
    				return true;
    			}         
			};	
			this.addProperty(p5);	
			
			Property<PInteger> p6 = new Property<PInteger>("BubbleR1",  new PInteger(100))
			{
				@Override
    			public boolean updating(PInteger newvalue)
    			{
					createBubbles();
    				return true;
    			}         
			};	
			this.addProperty(p6);	
			
			Property<PDouble> p8 = new Property<PDouble>("BubbleThresh",  new PDouble(0.25))
					{
				@Override
    			public boolean updating(PDouble newvalue)
    			{
					createBubbles();
    				return true;
    			}         
			};	
			this.addProperty(p8);
			
			Property<PSignal> p9 = new Property<PSignal>("Smaller", new PSignal())
					{
				
				@Override
    			public boolean updating(PSignal newvalue)
    			{
					clusterTypes.add("newCluster" + splitClusters++);
					
					Color[] newcolor = new Color[clusterTypes.size()];
					for (int i=0; i<clusterColor.length; i++)
						newcolor[i] = clusterColor[i];
					newcolor[clusterTypes.size()-1] =  new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
					
					clusterColor = newcolor;
					
					String sel = ((PString)getProperty("Selected").getValue()).stringValue();					
					ArrayList<String> nodes = graph.getNodes();							
					String[] split = sel.split("\t");
					for (int i=0; i<split.length; i++)
					{
						int index = nodes.indexOf(split[i]);
						if (index < 0) continue;
						clusters[index] = clusterTypes.size()-1;
						
					}
    				return true;
    			} 
				
					};
			this.addProperty(p9);	
			
			
			Property<PSignal> p10 = new Property<PSignal>("Recolor", new PSignal())
					{
				
				@Override
    			public boolean updating(PSignal newvalue)
    			{
					for (int i=0; i<clusterTypes.size(); i++)
						clusterColor[i] = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());

    				return true;
    			} 
				
					};
			this.addProperty(p10);	
			
			
			Property<PBoolean> p11 = new Property<PBoolean>("Fill",  new PBoolean(true));
			this.addProperty(p11);
			
			Property<PBoolean> p111 = new Property<PBoolean>("Contours",  new PBoolean(true));
			this.addProperty(p111);
			
			Property<PBoolean> p12 = new Property<PBoolean>("Edges",  new PBoolean(true));
			this.addProperty(p12);
			

			
			
			}
			catch (Exception e) {		
				e.printStackTrace();
			}
	}
	
	
	@Override
	public void simulate() {
		// TODO Auto-generated method stub
		super.simulate();
		if (((PBoolean)getProperty("Simulation.Simulate").getValue()).boolValue())
			createBubbles();
			
	}

	@Override
	public void render(Graphics2D g) {
	//	if (this.bubbleSets != null)
		//	bubbleSets.debugRender(g);
		
		boolean fill = ((PBoolean)getProperty("Fill").getValue()).boolValue();
		boolean cont = ((PBoolean)getProperty("Contours").getValue()).boolValue();
			
		if (bubbles != null)
		{
			
			for (int i=0; i<bubbles.length && fill; i++)
			{				
				Color c = new Color(clusterColor[i].getRed(), clusterColor[i].getGreen(), clusterColor[i].getBlue(),100);
				g.setColor(c);
				g.fill(bubbles[i]);
			}
			
			for (int i=0; i<bubbles.length && cont; i++)
			{			
				
				g.setStroke(new BasicStroke(3));
				Color c = new Color(clusterColor[i].getRed(), clusterColor[i].getGreen(), clusterColor[i].getBlue(),200);
				g.setColor(c);
				g.draw(bubbles[i]);
			}
			
		}

		super.render(g);
	}
	
	@Override
    public void renderEdge(int p1, int p2, int edgeIndex, boolean selected, boolean hovered, Graphics2D g) {
		
		boolean edges = ((PBoolean)getProperty("Edges").getValue()).boolValue();
		
		if (!edges) return;
		
        int x1 = (int) drawer.getX(p1);
        int y1 = (int) drawer.getY(p1);
        int x2 = (int) drawer.getX(p2);
        int y2 = (int) drawer.getY(p2);
        
        g.setStroke(new BasicStroke(2));

        if (selected) {
            g.setColor(new Color(255, 0, 0, 150));
        } else if (hovered) {
            g.setColor(new Color(255, 100, 100, 150));
        } else {
            g.setColor(new Color(50, 50, 50, 100));
        }



        g.drawLine(x1, y1, x2, y2);
    }
	
	@Override
	public void renderNode(int i, boolean selected, boolean hovered,
			Graphics2D g) {	
		if (selected)
			ovals.get(i).setColor(Color.red);			
		else if (hovered)
			ovals.get(i).setColor(Color.pink);	
		else
		{
			if (clusters != null)
				ovals.get(i).setColor(Color.GRAY);		
		}
		ovals.get(i).render(g);
		
	//	g.setColor(Color.black);
		//g.drawOval((int)ovals.get(i).x-2, (int)ovals.get(i).y-2, 4,4);
	}
	
	
	public void createBubbles()
	{
		if (clusters == null)
			return;
		
		
		
		synchronized(this.bigGridCompute)
		{			
			ArrayList<String> nodes = graph.getNodes();	
			
			Rectangle2D.Double[] points = new Rectangle2D.Double[nodes.size()];
			
		int[][] sets = new int[clusterTypes.size()][];
		for (int j=0; j<clusterTypes.size(); j++)
		{
			int ct = 0;
			for (int i=0; i<nodes.size(); i++)
				if (j == clusters[i])
					ct++;
				
			
			int[] s1 = new int[ct];
			
			ct = 0;
			for (int i=0; i<nodes.size(); i++)
			{			
				
				if (j == clusters[i])
				{
					points[i] =  new Rectangle2D.Double(this.ovals.get(i).x-this.ovals.get(i).w/2, this.ovals.get(i).y-this.ovals.get(i).h/2, this.ovals.get(i).w*1, this.ovals.get(i).h*1);
					s1[ct] = i;
					ct++;
				}
				
			}				
			
			sets[j] = s1;
		}
				
			
			BubbleSets bs = new BubbleSets(points,sets);
			
			
			int r1 = ((PInteger)this.getProperty("BubbleR1").getValue()).intValue();
			int r0 = 3;
			int cellSize = ((PInteger)this.getProperty("BubbleCellSize").getValue()).intValue();			
			double contThresh = ((PDouble)this.getProperty("BubbleThresh").getValue()).doubleValue();						

			bubbles  = bs.computeAll(cellSize, r1, r0, contThresh);
			
			bubbleSets = bs;
		
		
		
	}
	}

}
