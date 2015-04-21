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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import no.hist.aitel.team12.app.DataBuffer;
import no.hist.aitel.team12.app.Person;
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

	private CardLayout cards;
	
	private EditUserCard editUserCard;
	
	public UserTab() {
		this.setLayout(new BorderLayout());

		mainPanel = new JPanel();
		cards = new CardLayout();
		mainPanel.setLayout(cards);		
		
		mainPanel.add(new LogoCard(), "logoCard");
		mainPanel.add(new NewUserCard(), "newUserCard");
		editUserCard = new EditUserCard();
		mainPanel.add(editUserCard, "editUserCard");
		
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
		userPanel.add(buttonPanel, BorderLayout.SOUTH);

		this.add(userPanel, BorderLayout.WEST);
		
		personTable.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				cards.show(mainPanel, "editUserCard");
				editUserCard.updateCard(personTable.getSelectedValue());
			}
		});
		
		editUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!personTable.isSelectionEmpty()) {
					cards.show(mainPanel, "editUserCard");
					editUserCard.setEditable(true);
				}
			}
		});
	}

	@Override
	public void refresh() {
		DefaultListModel<Person> model = new DefaultListModel<Person>();
		Person[] parray = null;
		while(parray == null) {
			parray = DataBuffer.getPersons();
		}
		for(Person p : parray) {
			model.addElement(p);
		}
		personTable.setModel(model);
	}
}