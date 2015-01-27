package no.hist.aitel.team12.util;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * An extension of JTextField to support a default text that goes away when the field gets focus.
 * @author Hallgeir
 */
public class InputField extends JTextField {

	private static final long serialVersionUID = -7278393682230959467L;

	private String defaultText;
	
	private Color defaultColor;
	
	private boolean defaultShown = true;
	
	private boolean justFilled = false;
	
	private boolean justCleared = false;
	
	/**
	 * @param text		The default text this input field should display until it recieves focus.
	 * @param columns	The minimum amount of columns this text field shall be able to display.
	 */
	public InputField(String text, int columns) {
		super(columns);
		this.defaultText = text;
		this.defaultColor = super.getForeground();
		
		setDefaultText();
		
		/* This commented out block defines the focus-behaviour */
//		super.addFocusListener(new FocusListener() {
//
//			@Override
//			public void focusGained(FocusEvent e) {
//				if(defaultShown) {
//					prepare();
//				}
//			}
//
//			@Override
//			public void focusLost(FocusEvent e) {
//				if(getText().equals("")) {
//					setDefaultText();
//				}
//			}
//		});
		
		/* This block of code defines the inputting-behaviour */
		
		super.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent fe) {
				if(defaultShown) {
					setCaretPosition(0);
				}
			}

			@Override
			public void focusLost(FocusEvent fe) {}
		});
		
		super.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent de) {}

			@Override
			public void insertUpdate(DocumentEvent de) {
				if(defaultShown && !justFilled) {
					char[] carray = getText().toCharArray();
					prepare(carray[0]);
				}
				justFilled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent de) {
				if(getText().length() == 0 && !justCleared) {
					setDefaultText();
				}
				justCleared = false;
			}
		});
	}

	private void setDefaultText() {
		Runnable rnbl = new Runnable() {
			@Override
			public void run() {
				justFilled = true;
				defaultShown = true;
				setForeground(Color.GRAY);
				setText(defaultText);
				setCaretPosition(0);
			}
		};
		SwingUtilities.invokeLater(rnbl);
	}
	
	private void prepare(final char c) {
		Runnable rnbl = new Runnable() {
			@Override
			public void run() {
				justCleared = true;
				defaultShown = false;
				setForeground(defaultColor);
				setText(""+c);
			}
		};
		SwingUtilities.invokeLater(rnbl);
	}
}
