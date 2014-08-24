package perspectives.base;

import java.awt.geom.Point2D;
import java.util.Date;


public abstract class Animation 
{
	public abstract void step();
	public boolean done = false;
	
	public static abstract class IntegerAnimation extends Animation
	{
		int v1,v2;
		long st,et;
		public IntegerAnimation(int v1, int v2, long t)
		{
			this.v1 = v1;
			this.v2 = v2; 
			st = (new Date()).getTime();
			et = st + t;
		}

		@Override
		public void step() {
			
			double perc = ((new Date()).getTime() - st) / (double)(et-st);
			if (perc >= 1)
			{
				perc = 1;
				done = true;
			}
			int v = v1 + (int)((v2-v1)*perc);
			step(v);
			
		}
		
		public abstract void step(int v);
		
	}
	
	
	public static abstract class DoubleAnimation extends Animation
	{
		double v1,v2;
		long st,et;
		public DoubleAnimation(double v1, double v2, long t)
		{
			this.v1 = v1;
			this.v2 = v2; 
			st = (new Date()).getTime();
			et = st + t;
		}

		@Override
		public void step() {
			
			double perc = ((new Date()).getTime() - st) / (double)(et-st);
			if (perc >= 1)
			{
				perc = 1;
				done = true;
			}
			double v = v1 + ((v2-v1)*perc);
			step(v);
			
		}
		
		public abstract void step(double v);
		
	}
	
	public static abstract class PositionAnimation extends Animation
	{
		Point2D p1,p2;
		long st,et;
		public PositionAnimation(Point2D p1, Point2D p2, long t)
		{
			this.p1 = p1;
			this.p2 = p2; 
			st = (new Date()).getTime();
			et = st + t;
		}

		@Override
		public void step() {
			
			double perc = ((new Date()).getTime() - st) / (double)(et-st);
			if (perc >= 1)
			{
				perc = 1;
				done = true;
				animationComplete();
			}
			Point2D p = new Point2D.Double(p1.getX() + (p2.getX()-p1.getX())*perc, p1.getY() + (p2.getY()-p1.getY())*perc);
			step(p);
			
		}
		
		public abstract void step(Point2D p);
		public void animationComplete()
		{
			
		};
		
	}

}



