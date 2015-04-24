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
		tabbedPane.setPreferredSize(new Dimension(1200, 730));
		
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
	
	
	public void addTab(String tabName, SSSTab tab) {
		tabbedPane.add("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>" + tabName + "</body></html>", tab);
	}
	
	public void showWindow() {
		System.out.println("Before pack");
		pack();
		System.out.println("After pack");
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
}
