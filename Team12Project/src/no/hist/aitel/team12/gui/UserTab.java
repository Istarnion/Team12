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
 * UserTab.java Team 12, 20 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.hist.aitel.team12.app.DataBuffer;
import no.hist.aitel.team12.app.Person;
import no.hist.aitel.team12.database.DatabaseConnection;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.Text;

/**
 * 
 * @author Roger
 *
 */

public class UserTab extends SSSTab {

	private static final long serialVersionUID = -445246322816259272L;

	private JButton newUser = new JButton(Text.getString("newuser"));	
	private JButton editUser = new JButton(Text.getString("edituser"));

	private JPanel buttonPanel;

	private JList<Person> personTable;

	private JPanel mainPanel;

	public UserTab() {
		this.setLayout(new BorderLayout());

		mainPanel = new JPanel();
		mainPanel.setLayout(new CardLayout());

		
		/* get the parameters: firstName, lastName, address, zipcode, email, telephone, username, 
		 * company, logoLabel, westPanel, eastPanel, eastBottom, logo
		*/
		mainPanel.add(new UserCard(null, null, null, null, null, null, null, null, null, null, null, null, null, buttonPanel, buttonPanel, buttonPanel, null), "usercard");
		
		
		mainPanel.add(new LogoCard(), "logoCard");
		mainPanel.add(new NewUserCard(null, null, null, null, null, null, null, null, null, null, null, null, null, buttonPanel, buttonPanel, buttonPanel, null), "newUserCard");
		
		
		/*---------------------------- set editable! --------------------------- */
		mainPanel.add(new EditUserCard(null, null, null, null, null, null, null, null, null, null, null, null, null, buttonPanel, buttonPanel, buttonPanel, null), "editUserCard");
		
		this.add(mainPanel, BorderLayout.CENTER);

		JPanel userPanel = new JPanel();
		userPanel.setLayout(new BorderLayout());
		
		Person[] parray = null;
		while(parray == null) {
			parray = DataBuffer.getPersons();
		}
		personTable = new JList<Person>(parray);
		JScrollPane resultTablePane = new JScrollPane(personTable);
		resultTablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		userPanel.add(resultTablePane, BorderLayout.CENTER);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(newUser);		
		buttonPanel.add(editUser);
		ButtonListener buttonListener = new ButtonListener();
		editUser.addActionListener(buttonListener);
		newUser.addActionListener(buttonListener);
		userPanel.add(buttonPanel, BorderLayout.SOUTH);

		this.add(userPanel, BorderLayout.WEST);
	}

	public void setUserTable(DatabaseConnection databaseConnection, int userID){

		String statement = databaseConnection.getUserStatement(userID);
		String[][] output = DatabaseFactory.getDatabase().executeQuery(statement);
		String[][] content = new String[output.length-1][output[0].length];		
		for(int row=0; row<content.length; row++) {			
			for(int col=0; col<content[0].length; col++) {
				content[row][col] = output[row+1][col];
			}


		}
	}

	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {

			JButton button = (JButton) event.getSource();

			if (button == newUser) {
			/* ------------------------------Create new user------------------------------------ */
				/*---------------------------- cast NewUserCard --------------------- */
			}
			else if (button == editUser) {
			/* ------------------------------Edit existing user--------------------------------- */
				/*---------------------------- cast EditUserCard --------------------- */
			}
		}

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}
}