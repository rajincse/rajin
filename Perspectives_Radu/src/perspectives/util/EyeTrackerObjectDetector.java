package perspectives.util;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;


class GazeServer implements Runnable
{
	public static final int PORT = 9876;
	EyeTrackerObjectDetector fgv;
	
    private BufferedReader mIn;
    private String message;
    private String decoded = null;
	
	Socket socket;
	private ServerSocket serverSocket;
	public GazeServer(EyeTrackerObjectDetector fgv)
	{
		this.fgv = fgv;		
		
		this.serverSocket = null;
		    try {
		       serverSocket = new ServerSocket(GazeServer.PORT);}
		    catch (IOException se) 
		    {
		       System.err.println("Can not start listening on port " + GazeServer.PORT);
		       se.printStackTrace();
		       System.exit(-1);
		    }	 
	}
	
	@Override
	public void run() {

		if(this.serverSocket != null)
		{
	       try  
	       {
	           socket = serverSocket.accept();
	           mIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	       }
	       catch (IOException ioe) 
	       {
	           ioe.printStackTrace();
	       }
		}
        
        while (true) {
            try {
                message = mIn.readLine();
              
                if (message == null)
                {
                	continue;
                }
                    
                try {
                    decoded = URLDecoder.decode(message, "UTF-8");
                    
                    String[] split = decoded.split(",");
                                              		
        			int x = Integer.parseInt(split[0]);
        			int y = Integer.parseInt(split[1]);
        			
        			 fgv.processGaze(new Point(x,y));
        			
        			
                } catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
                catch(Exception ex)
                {
                	ex.printStackTrace();
                }
            } 
            catch (IOException e) {
                break;
            }

        }

      
    }	
	
}

public class EyeTrackerObjectDetector {
	
	Point[][] curves;
	Point[] points;
	
	private Point[][] curveControl;
	private double[][] curveControlScore;	
	private double[] curveScoresShort;
	private double[] curveScoresLong;
	private double[] curveLengths;
	
	private double[] pointControlScore;
	
	HashMap<String,Integer> curveIds;
	HashMap<String, Integer> pointIds;
	
	private Point offset;
	
	
	////params
	double curveSegmentDecay = 0.04;
	double curveSegThreshold = 75;
	double maxCurveLength = 400;
	
	double curveShortInc = 10.; // s/curveShortInc get added to curveShort
	double curveShortDec = 0.004;
	
	double curveLongInc = 200.; // curveShort/curveShortInc get added to curveShort
	double curveLongDec = 0.001;
	
	
	public EyeTrackerObjectDetector()
	{
		curves = new Point[0][];
		points = new Point[0];
		
		curveControl = new Point[0][];
		curveControlScore = new double[0][];
		pointControlScore = new double[0];
		
		curveScoresShort = new double[0];
		curveScoresLong = new double[0];
		
		curveLengths = new double[0];
		
		curveIds = new HashMap<String,Integer>();
		pointIds = new HashMap<String,Integer>();
		
		offset = new Point(0,0);
	}
	
	public void setOffset(Point p)
	{
		offset = p;
	}
	
	public void processGaze(Point gaze)
	{
		int scx = (int)this.offset.getX();
		int scy = (int)this.offset.getY();
		
		Point processingPoint = new Point((int)gaze.getX()-scx,(int)gaze.getY()-scy);
		
		gaze = processingPoint;
		
		for (int i=0; i<curves.length; i++)
			if (curves[i] != null)
				processCurveGaze(i,gaze);
		
		for (int i=0; i<points.length; i++)
			if (points[i] != null)
				processPointGaze(i,gaze);
				
	}
	
	public double getCurveScore(String id)
	{
		int index = curveIds.get(id);
		return this.curveScoresLong[index];
	}
	
	
	private void processPointGaze(int curve, Point gaze)
	{
		
	}
		
	public void registerCurve(String id, Point[] curve)
	{
		Point[][] curves2 = new Point[curves.length+1][];
		Point[][] curveControl2 = new Point[curveControl.length+1][];
		double[][] curveControlScore2 = new double[curveControlScore.length+1][];
		double[] curveScoresShort2 = new double[curveScoresShort.length+1];
		double[] curveScoresLong2 = new double[curveScoresLong.length+1];
		double[] curveLengths2 = new double[this.curveLengths.length+1];
		
		for (int i=0; i<curves.length; i++)
		{
			curves2[i] = curves[i];
			curveControl2[i] = curveControl[i];
			curveControlScore2[i] = curveControlScore[i]; 
			curveScoresShort2[i] = curveScoresShort[i];
			curveScoresLong2[i] = curveScoresLong[i];
			curveLengths2[i] = curveLengths[i];
		}
		curves2[curves.length] = curve;
		curveScoresShort2[curveScoresShort.length] = 0;
		curveScoresLong2[curveScoresLong.length] = 0;
		
		
		Point[] cc = segmentCurve(curve);
		double[] ccs = new double[cc.length];
		for (int i=0; i<ccs.length; i++)
			ccs[i] = 0;
		
		curveControl2[curveControl.length] = cc;
		curveControlScore2[curveControlScore.length] = ccs;
		
		
		curves = curves2;
		curveScoresShort = curveScoresShort2;
		curveScoresLong = curveScoresLong2;
		curveControl = curveControl2;
		curveControlScore = curveControlScore2;
		curveLengths = curveLengths2;
		curveIds.put(id, curves.length-1);
		
		double length = 0;		
		for (int i=0; i<cc.length-1; i++)
			length += cc[i].distance(cc[i+1]);
		curveLengths[curveLengths.length-1] = length;
	}
	
	public void deleteCurve(String id)
	{
		int index = curveIds.get(id);
		curves[index] = null;
	}
	
	public void reshapeCurve(String id, Point[] curve)
	{		
		int index = curveIds.get(id);
		curves[index] = curve;
		
		Point[] cc = segmentCurve(curve);
		double[] ccs = new double[cc.length];
		for (int i=0; i<ccs.length; i++)
			ccs[i] = 0;
		
		curveControl[index] = cc;
		curveControlScore[index] = ccs;
		
		double length = 0;		
		for (int i=0; i<cc.length-1; i++)
			length += cc[i].distance(cc[i+1]);
		curveLengths[index] = length;
	}
	
	public void registerPoint(String id, Point p)
	{
		Point[] points2 = new Point[points.length+1];
		double[] pointControlScore2 = new double[pointControlScore.length+1];
		
		for (int i=0; i<points.length; i++)
		{
			points2[i] = points[i];
			pointControlScore2[i] = pointControlScore[i];
		}
		
		points2[points.length] = p;
		pointControlScore2[pointControlScore.length] = 0;
		
		points = points2;		
		pointControlScore = pointControlScore2;
		pointIds.put(id, points.length-1);
	}
	
	public void deletePoint(String id)
	{
		int index = pointIds.get(id);
		points[index] = null;
	}
	
	public void reshapePoint(String id, Point p)
	{
		int index = pointIds.get(id);
		points[index] = p;
	}
	
	public double getScore(String id)
	{
		return 0;
	}
	
	private Point[] segmentCurve(Point[] curve)
	{
		if (curve.length == 0)
			return new Point[]{};
		
		double length = 0;		
		for (int i=0; i<curve.length-1; i++)
			length += curve[i].distance(curve[i+1]);
		
		int nrSeg = (int)Math.ceil(length/25.);
		double segLength = length / (double)nrSeg;
		
		Point[] ret = new Point[nrSeg+1];
		
		ret[0] = curve[0];
		
		
		double l = segLength;
		int cindex = 0;
		int sindex = 1;
		double remainder = 0;
		while(cindex != curve.length-1)
		{			
			double d = curve[cindex].distance(curve[cindex+1]);
						
			while (l <= d)
			{
				int vx = curve[cindex+1].x - curve[cindex].x;
				int vy = curve[cindex+1].y - curve[cindex].y;
				
				double len = Math.sqrt(vx*vx + vy*vy);
				
				vx /= len;
				vy /= len;
				
				Point p = new Point((int)(curve[cindex].x + vx*l), (int)(curve[cindex].y + vy*l));				
			
				ret[sindex++] = p;
				
				l += segLength;
			}
			l = l - d;
			cindex++;
		}
		
		if (ret[ret.length-1] == null) ret[ret.length-1] = curve[curve.length-1];
		
		return ret;
	}
	
	private void processCurveGaze(int curve, Point gaze)
	{
		//decay
		for (int i=0; i<curveControlScore[curve].length; i++)
		{				
			curveControlScore[curve][i] -= (curveSegmentDecay / Math.sqrt(curveControlScore[curve].length));
				if (curveControlScore[curve][i] < 0) curveControlScore[curve][i] = 0;	
		}
		
		addGazeToEdge(gaze, curveControl[curve], this.curveControlScore[curve], (int)curveSegThreshold);
		
		double d = this.computeBars(curve,curveControl[curve], this.curveControlScore[curve]);
				
				
		double s = Math.min(1,d/maxCurveLength);
		
		if (s > curveScoresShort[curve])
			curveScoresShort[curve] = Math.min(1, curveScoresShort[curve]+s/curveShortInc);
		else 
			curveScoresShort[curve] = Math.max(0, curveScoresShort[curve]-curveShortDec) ;
		
		
			
			
		if (curveScoresShort[curve] > curveScoresLong[curve])
			curveScoresLong[curve] += curveScoresShort[curve]/curveLongInc;
			else
				curveScoresLong[curve] -= curveLongDec;
			
		if (curveScoresLong[curve] > 1) curveScoresLong[curve] = 1;
		if (curveScoresLong[curve] < 0) curveScoresLong[curve] = 0;
		
		System.out.println(d + "\t" + curveScoresShort[curve] + "\t" + curveScoresLong[curve]);
	}
	
	public void addGazeToEdge(Point gaze, Point[] seg, double[] segV, int threshold)
	{	
	
		double bestDist = Double.MAX_VALUE;
		int bestIndex = -1;
	
		for (int i=0; i<seg.length; i++)
		{
			double d = gaze.distance(seg[i]);
			if (d < bestDist)
			{
				bestDist = d;
				bestIndex = i;
			}
		}
		
		if (bestIndex >= 0 && bestDist < threshold)
		{
			double v = 1-bestDist/threshold;
	
			int index = bestIndex;
			double max = 0;
			for (int i=bestIndex-2; i<=bestIndex+2; i++)
			{
				if (i < 0 || i>=seg.length)
					continue;
	
				if (segV[i] > max)
				{
					index = i;
					max = segV[i];
				}
			}
	
			segV[index] = segV[index] + v;
			if (segV[index] > 1) segV[index] = 1;
		}	
	}
	
	
	public double computeBars(int index, Point[] seg, double[] segV)
	{
		if (seg.length <= 1) return 0;	
		
		/*double ret = 0;
		for (int i=0; i<seg.length; i++)
			ret += segV[i];
		
		ret = ret / segV.length;
		
		return ret;*/
		
		
	
		double[] barsR = new double[segV.length];
		double[] barsL = new double[segV.length];	
	
		
		double len = curveLengths[index];
		double segL = len/(seg.length-1);
	
		
		for (int i=0; i<seg.length; i++)
		{
			double val = 3*segV[i];
			if (val == 0) continue;
			
			barsL[i] = val; 
			barsR[i] = val;
	
			if (val > i)
				barsR[i] += (val-i);
			if (val+i > seg.length-1)
				barsL[i] += (val+i - seg.length+1);
	
			if (barsL[i] > i)
				barsL[i] = i;
			if (barsR[i] + i > seg.length-1)
				barsR[i] = seg.length-1 - i;	
		}
	
		double min = -1;
		double max = -1;
		double d = 0;
		for (int i=0; i<seg.length; i++)
		{
			if (segV[i] <= 0) continue;
	
			double l = i*segL - barsL[i]*segL;
			double r = i*segL + barsR[i]*segL;	
	
			if (min < 0)
			{
				min = l; 
				max = r; 	
			}
			else
			{
				if (l > max)
				{	
					d = d + (max-min);
					min = l;
					max = r;	
				}
				else
				{	
					if (l < min) min = l;
					if (r > max) max = r;
				}
			}	
		}
		if (max > 0 && min>=0)
			d = d + (max-min);
		
	return d;
	}
	
}
