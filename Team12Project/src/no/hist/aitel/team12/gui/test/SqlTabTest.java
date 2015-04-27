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
 * SqlTabTest.java Team 12, 27. apr. 2015
 *******************************************************************************/
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
