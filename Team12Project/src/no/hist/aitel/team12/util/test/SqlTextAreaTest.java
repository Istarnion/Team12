package no.hist.aitel.team12.util.test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import no.hist.aitel.team12.util.SqlTextArea;

public class SqlTextAreaTest {

	public SqlTextAreaTest() {
		JFrame frame = new JFrame("Title");
		
		SqlTextArea sta = new SqlTextArea(20, 60);
		frame.add(sta.generateScrollPane());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		frame.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception ex) {
			System.out.println("Failed setting System laf. Reverting to Java defult.");
		}

		// Need to start the app from the EDT
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SqlTextAreaTest();
			}
		});
	}

}
