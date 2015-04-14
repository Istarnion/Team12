package no.hist.aitel.team12.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import no.hist.aitel.team12.app.SSS;

public class SSSWindow extends JFrame {

	private static final long serialVersionUID = -6075431975477953906L;

	private JTabbedPane tabbedPane;
	
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
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(1200, 675));
		
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				((SSSTab)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex())).refresh();
			}
			
		});
		
		add(tabbedPane);
	}
	
	
	public void addTab(String tabName, SSSTab tab) {
		tabbedPane.add("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>" + tabName + "</body></html>", tab);
	}
	
	public void showWindow() {
		pack();
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
}
