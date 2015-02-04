package no.hist.aitel.team12.util.test;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import no.hist.aitel.team12.util.PasswordInputField;

public class PasswordInputFieldTest {

	public PasswordInputFieldTest() {
		JFrame frame = new JFrame("Title");
		PasswordInputField ifld = new PasswordInputField("Password..", 20);
		frame.add(ifld, BorderLayout.NORTH);
		JButton btn = new JButton("Button");
		frame.add(btn, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		btn.requestFocus();

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
				new PasswordInputFieldTest();
			}
		});
	}
}
