import gui.Drawable;

import java.awt.*;
import java.util.Random;

/**
 * @author Dr. Sayeed S. Alam
 */
public class MyDrawable implements Drawable {
    int x =0;
    int y =0;
    @Override
    public void draw(Graphics g) {
        Random rand = new Random();
        g.setColor(Color.red);
        x = (x+10)%500;
        y = (y+10)%500;
        g.fillRect(x,y,100,100);
    }
}
