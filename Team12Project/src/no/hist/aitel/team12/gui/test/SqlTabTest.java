package no.hist.aitel.team12.gui.test;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import no.hist.aitel.team12.gui.SqlTab;

public class SqlTabTest {

	public SqlTabTest() {
		JFrame frame = new JFrame("SqlTab Test");
		
		JTabbedPane jtp = new JTabbedPane();
		jtp.setPreferredSize(new Dimension(800, 600));
		
		jtp.addTab("SQL", new SqlTab());
		
		frame.add(jtp);
		frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new SqlTabTest();
	}
}
