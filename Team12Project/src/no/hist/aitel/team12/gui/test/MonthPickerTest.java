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
