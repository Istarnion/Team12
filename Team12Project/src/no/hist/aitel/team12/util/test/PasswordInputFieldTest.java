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
 * PasswordInputFieldTest.java Team 12, 18 Feb 2015
 *******************************************************************************/

package no.hist.aitel.team12.util.test;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import no.hist.aitel.team12.gui.PasswordInputField;

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
