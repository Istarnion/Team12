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
 * SSSWindow.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import no.hist.aitel.team12.app.SSS;

public class SSSWindow extends JFrame {

	private static final long serialVersionUID = -6075431975477953906L;

	private JTabbedPane tabbedPane;

	private SSSTab currTab;
	
	public SSSWindow() {
		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				SSS.exit();
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}
		});
		
		try {
			List<BufferedImage> icons = new ArrayList<BufferedImage>(3);
			icons.add(ImageIO.read(getClass().getResource("/images/micro.png")));
			icons.add(ImageIO.read(getClass().getResource("/images/tiny.png")));
			icons.add(ImageIO.read(getClass().getResource("/images/medium.png")));
			
			super.setIconImages(icons);
		}
		catch(Exception e) {
			System.out.println("Failed loading the frame icon images. Reverting to default\n\t"+
					"The icons are expected to be named: 'micro.png', 'tiny.png' and 'medium.png'");
		}
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(1200, 670));
		
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if(currTab != null) currTab.setActive(false);
				currTab = (SSSTab)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
				currTab.setActive(true);
				currTab.refresh();
			}
		});
		
		add(tabbedPane);
	}
	
	/**
	 * Adds a new tab to the window
	 * @param tabName	The name that shall be shown on the tab
	 * @param tab		The tab to be added
	 */
	public void addTab(String tabName, SSSTab tab) {
		tabbedPane.add("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>" + tabName + "</body></html>", tab);
	}
	
	/**
	 * Sets the window visible
	 */
	public void showWindow() {
		pack();
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
}
