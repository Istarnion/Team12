package no.hist.aitel.team12.gui;

import javax.swing.*;
import java.awt.*;

/**
 * 
 * @author Andreas Laptop
 *
 * Class creates a transparent JFrame that contains a JLabel 
 * with a transparent image (Splashscreen.gif)located in Resources/images.
 */



public class SplashScreen extends JFrame {

	public SplashScreen(){
		createSplash();
	}

	private void createSplash(){

		JLabel splashImage = new JLabel(new ImageIcon(getClass().getResource("/images/Splashscreen.gif")));
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(new BorderLayout());
	    frame.setUndecorated(true);
	    frame.setBackground(new Color(1, 1, 1, 0));
	    frame.add(splashImage);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}

public static void main(String[] args) {
    new SplashScreen();

}
}