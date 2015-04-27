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
 * SSSTab.java Team 12, 27. apr. 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import javax.swing.JPanel;

public abstract class SSSTab extends JPanel {

	private static final long serialVersionUID = 1819301448571520953L;

	protected boolean active = false;
	
	public abstract void refresh();
	
	void setActive(boolean active) {
		this.active = active;
	}
}
