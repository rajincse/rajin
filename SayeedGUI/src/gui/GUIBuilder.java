package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIBuilder
{
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel drawPanel;

	private Drawable drawable;
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
	public void setTimer(int delay)
	{
		Timer timer = new Timer(delay, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPanel.repaint();
			}
		});
		timer.start();
	}


}