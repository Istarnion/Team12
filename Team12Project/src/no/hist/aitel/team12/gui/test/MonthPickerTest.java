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
 * MonthPickerTest.java Team 12, 27. apr. 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui.test;

import java.awt.BorderLayout;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

import no.hist.aitel.team12.gui.MonthPicker;

public class MonthPickerTest {

	public MonthPickerTest() {
		JFrame frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		MonthPicker monthPicker = new MonthPicker("jan 2003");
		
		panel.add(monthPicker);
		frame.add(panel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		monthPicker.setDate(Calendar.getInstance());
	}

	public static void main(String[] args) {
		
		System.out.println((-1/12.0f));
		new MonthPickerTest();
	}
}
