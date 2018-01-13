package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  Creates a graphic user interface with JFrame.
 *  Provides ability to draw on a Java GUI and optional animations.
 	@author Dr. Sayeed S. Alam
 */
public class GUIBuilder
{
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel drawPanel;

	private Drawable drawable;

	/**
     * <p>
     *      Constructs a default java GUI.
     * </p>
	 *
	 * E.g.
     * <code>
     *     GUIBuilder builder = new GUIBuilder("SayeedGUI", 500, 500, new Drawable() <br>
     *              {<br>
     *                  public void draw(Graphics g) {<br>
     *                      //Draw Something<br>
     *                  }<br>
     *              }<br>
     * </code>
	 * <p>
     *     This will create a drawable GUI with 500 X 500 dimension and drawing can be performed in the draw method
	 * </p>
	 * @param title Title/Header of the window.
	 * @param width Width in pixels of the window.
	 * @param height Height in pixels of the window.
	 * @param drawable Drawable interface
	 */
	public GUIBuilder(String title, int width, int height, Drawable drawable)
	{
		this.drawable = drawable;
		frame=new JFrame(title);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(width,height));
		
		mainPanel=new JPanel(new BorderLayout());
		initDrawPanel();
		mainPanel.add(drawPanel, BorderLayout.CENTER);
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
	}

	private void initDrawPanel()
	{
		this.drawPanel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawable.draw(g);
			}
		};
	}

	/**
	 * Provides ability to animate. The draw method will be reinvoked after given interval
	 * @param interval Interval in milliseconds
	 */
	public void setTimer(int interval)
	{
		Timer timer = new Timer(interval, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPanel.repaint();
			}
		});
		timer.start();
	}


}