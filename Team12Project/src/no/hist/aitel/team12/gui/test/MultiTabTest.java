package no.hist.aitel.team12.gui.test;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import no.hist.aitel.team12.gui.MessageTab;
import no.hist.aitel.team12.gui.OverviewTab;
import no.hist.aitel.team12.gui.SqlTab;

public class MultiTabTest {

	public MultiTabTest() {
		JFrame frame = new JFrame("MultiTab Test");
		
		JTabbedPane jtp = new JTabbedPane();
		jtp.setPreferredSize(new Dimension(800, 600));
		
		jtp.addTab("SQL", new SqlTab());
		jtp.addTab("Messages", new MessageTab());
		jtp.addTab("Overview", new OverviewTab());
		frame.add(jtp);
		frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MultiTabTest();
	}
}
