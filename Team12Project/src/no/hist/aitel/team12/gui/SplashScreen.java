/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * SplashScreen.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

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

	private static JLabel splashImage;

	/**
	 * Displays the splash screen
	 */
	public void createSplash(){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if(splashImage == null) {
					splashImage = new JLabel(new ImageIcon(getClass().getResource("/images/Splashscreen.gif")));
				}
				frame = new JFrame();
				try {
					List<BufferedImage> icons = new ArrayList<BufferedImage>(3);
					icons.add(ImageIO.read(getClass().getResource("/images/micro.png")));
					icons.add(ImageIO.read(getClass().getResource("/images/tiny.png")));
					icons.add(ImageIO.read(getClass().getResource("/images/medium.png")));

					frame.setIconImages(icons);
				}
				catch(Exception e) {
					System.out.println("Failed loading the frame icon images. Reverting to default\n\t"+
							"The icons are expected to be named: 'micro.png', 'tiny.png' and 'medium.png'");
				}

				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				frame.setUndecorated(true);
				frame.setBackground(new Color(1, 1, 1, 0));
				frame.add(splashImage);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Takes the splash screen down, disposing resources
	 */
	public void removeSplash(){
		frame.dispose();
	}

	/**
	 * Toggles the visibility of the splash screen
	 * 
	 * @param b	if true, make the splash visible, if false, hide it
	 */
	public void setSplashVisible(boolean b) {
		Thread t = new Thread() {
			@Override
			public void run() {
				frame.setVisible(b);
			}
		};
		t.start();
	}
}
