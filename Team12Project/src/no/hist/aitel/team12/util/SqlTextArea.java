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
 * SqlTextArea.java Team 12, 18 Feb 2015
 *******************************************************************************/

package no.hist.aitel.team12.util;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 * <p>
 * Utility class for setting up a SQL editor area with syntax highlighting and line numbers.<br>
 * This class extends org.fife.ui.rsyntaxtextarea.RSyntaxTextArea, which in turn extends JTextArea.<br>
 * That means it can be used by the application just as a normal JTextArea would, but this class also provides
 * the utility method generateScrollPane(), that returns a JScrollPane with proper scroll bars and line numbers added,
 * ready to be applied to the container.
 * </p>
 * Example:<br>
 * JPanel panel = new JPanel();<br>
 * SqlTextArea sta = new SqlTextArea(20, 60);<br>
 * panel.add(sta.generateScrollPane());<br>
 * 
 * @author Hallgeir
 * @version 1.0
 * @see org.fife.ui.rsyntaxtextarea.RSyntaxTextArea
 */
public class SqlTextArea extends RSyntaxTextArea {

	private static final long serialVersionUID = 7054078016927918605L;

	private JTextArea lines;
	
	/**
	 * Sole constructor<br>
	 * Note that this constructor adds support for line numbers, but the JTextArea itself does not have them.
	 * Use generateScrollPane if you want the SqlTextArea to have line numbers.
	 * As an utility, you can skip this constructor all together, and simply call the static factory method
	 * createSqlTextArea(int rows, int cols)
	 * 
	 * @param rows The number of rows in the SqlTextArea
	 * @param cols The number of columns in the SqlTextArea
	 */
	public SqlTextArea(int rows, int cols) {
		super(rows, cols-3);
		super.setSyntaxEditingStyle(SYNTAX_STYLE_SQL);
		
		lines = new JTextArea("001");
		lines.setEditable(false);
		lines.setBackground(Color.LIGHT_GRAY);
		lines.setFont(this.getFont());
		
		SqlTextArea sta = this;
		this.getDocument().addDocumentListener(new DocumentListener(){
			public String getText(){
				int caretPosition = sta.getDocument().getLength();
				Element root = sta.getDocument().getDefaultRootElement();
				String text = "001" + System.getProperty("line.separator");
				for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
					text += String.format("%03d", i) + System.getProperty("line.separator");
				}
				return text;
			}
			@Override
			public void changedUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
			@Override
			public void insertUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
			@Override
			public void removeUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
		});
	}

	/**
	 * Generates a JScrollPane, ready for use.
	 * See constructor for example of use.
	 * 
	 * @return This object contained in a JScrollPane, with line numbers added.
	 */
	public JScrollPane generateScrollPane() {
		JScrollPane jsPane = new JScrollPane(this);
		jsPane.setRowHeaderView(lines);
		jsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		return jsPane;
	}
	
	/**
	 * Generates a JScrollPane with a SqlTextArea ready to use within.
	 * This method simply calls the constructor and generateScrollPane() internally
	 * 
	 * @param rows The number of rows in the SqlTextArea
	 * @param cols The number of columns in the SqlTextArea
	 * @return a SqlTextArea object contained in a JScrollPane, with line numbers added.
	 */
	public static JScrollPane createSqlTextArea(int rows, int cols) {
		SqlTextArea sta = new SqlTextArea(rows, cols);
		return sta.generateScrollPane();
	}
}
