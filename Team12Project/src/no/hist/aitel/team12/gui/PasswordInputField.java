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
 * PasswordInputField.java Team 12, 18 Feb 2015
 *******************************************************************************/

package no.hist.aitel.team12.gui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * 
 * This is the default password input for the Super Shopping Surfer application.<br>
 * It extends JPasswordField from javax.swing and retains all of its features.<br>
 * In addition, this password field shows an initial text within itself, that removes itself as the
 * user begins typing.
 * 
 * @author Hallgeir
 */
public class PasswordInputField extends JPasswordField {

	private static final long serialVersionUID = -6023239195319430046L;

	private String defaultText;

	private Color defaultColor;

	private final char defaultEcho;
	
	private boolean defaultShown = true;

	private boolean justFilled = false;

	private boolean justCleared = false;

	/**
	 * Sole constructor.<br>
	 * Identical to the constructor in JPasswordField, but with the additional default string property.
	 * Provide an empty string to make the password field behave like its superclass.
	 * 
	 * @param text		The default text this input field should display until it recieves focus.
	 * @param columns	The minimum amount of columns this text field shall be able to display.
	 */
	public PasswordInputField(String text, int columns) {
		super(columns);
		this.defaultText = text;
		this.defaultColor = super.getForeground();
		defaultEcho = super.getEchoChar();
		
		setDefaultText();

		super.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				if(defaultShown && !justCleared && getCaretPosition() != 0) {
					setCaretPosition(0);
				}
			}
			
		});
		
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
					char[] carray = getPassword();
					prepare(carray[0]);
				}
				justFilled = false;
			}

			@Override
			public void removeUpdate(DocumentEvent de) {
				if(getPassword().length == 0 && !justCleared) {
					setDefaultText();
				}
				justCleared = false;
			}
		});
	}

	public void setDefaultText(String text) {
		defaultText = text;
		if(defaultShown) {
			setDefaultText();
		}
	}
	
	private void setDefaultText() {
		if (justFilled) return;
		Runnable rnbl = new Runnable() {
			@Override
			public void run() {
				setEchoChar((char) 0);
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
		if (justCleared) return;
		Runnable rnbl = new Runnable() {
			@Override
			public void run() {
				setEchoChar(defaultEcho);
				justCleared = true;
				defaultShown = false;
				setForeground(defaultColor);
				setText(""+c);
			}
		};
		SwingUtilities.invokeLater(rnbl);
	}
	
	public boolean isDefaultShown() {
		return defaultShown;
	}
	
	@Override
	public void setText(String text) {
		if("".equals(text)) {
			setDefaultText();
		}
		else {
			super.setText(text);
		}
	}
}
