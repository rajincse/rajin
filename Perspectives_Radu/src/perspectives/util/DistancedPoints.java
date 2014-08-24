package perspectives.util;

public interface DistancedPoints 
{
	public abstract int getCount();
	public abstract float getDistance(int index1, int index2);
	
	public abstract String getPointId(int index);
	public abstract int getPointIndex(String id);
	
	public abstract void normalize();

}
