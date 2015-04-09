package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Class creates a transparent JFrame that contains a JLabel 
 * with a transparent image (Splashscreen.gif)located in Resources/images.
 * 
 * @author Andreas
 * @version 1.0
 *
 * 
 */



public class SplashScreen{
	
	private JFrame frame;

	/**
	 * 
	 */
	public void createSplash(){
		JLabel splashImage = new JLabel(new ImageIcon(getClass().getResource("/images/Splashscreen.gif")));
		frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setLayout(new BorderLayout());
	    frame.setUndecorated(true);
	    frame.setBackground(new Color(1, 1, 1, 0));
	    frame.add(splashImage);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
	/**
	 * 
	 */
	public void removeSplash(){
		frame.dispose();
	}
}
