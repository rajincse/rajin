package perspectives.two_d;

import java.awt.Color;
import java.awt.Graphics2D;

public interface JavaAwtRenderer {
	
	public void render(Graphics2D g);
	
	public Color getBackgroundColor();

	public boolean mousepressed(int x, int y, int button);
	public boolean mousereleased(int x, int y, int button);
	public boolean mousemoved(int x, int y);
	public boolean mousedragged(int currentx, int currenty, int oldx, int oldy);
	public void keyPressed(String key, String modifiers);
	public void keyReleased(String key, String modifiers);
}
