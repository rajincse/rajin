package gui;

import java.awt.*;

/**
 * Drawable Interface to attach with GUIBuilder.
 * @author Dr. Sayeed S. Alam
 */
public interface Drawable {
    /**
     * This method will be invoked when the GUI is inovked.
     * @param g Graphics object to Draw
     */
    public void draw(Graphics g);
}
