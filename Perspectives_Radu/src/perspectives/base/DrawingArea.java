package perspectives.base;

public class DrawingArea {
	public int x, y, w, h;
	ViewerContainer listener;
	String name;
	
	public DrawingArea(String name, int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.name = name;
		this.listener = listener;
	}
	
	public String getName(){
		return name;
	}
	
	public void resize(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		if (listener != null)
			listener.drawingAreaChanged(this);
	}
	
	public boolean contains(int x, int y){
		if (x >= this.x && x <= this.x + w)
			if (y>= this.y  && y <= this.y +h)
				return true;
		return false;
	}

}
