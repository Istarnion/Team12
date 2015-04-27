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
 * JTextFieldLimit.java Team 12, 27. apr. 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends JTextField {

	private static final long serialVersionUID = 1708597896073847975L;
	private int limit;

    public JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    @Override
    protected Document createDefaultModel() {
        return new LimitDocument();
    }

    private class LimitDocument extends PlainDocument {

		private static final long serialVersionUID = 8478838301284786492L;

		@Override
        public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if (str == null) return;
            
            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }       

    }

}