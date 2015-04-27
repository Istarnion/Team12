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
 * UserCard.java Team 12, 27. apr. 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.hist.aitel.team12.util.Text;

public class UserCard extends JPanel{

	private static final long serialVersionUID = 4688863130267581267L;
	
	public UserCard(InputField firstName, InputField lastName, InputField address, InputField zipcode, 
			InputField email, InputField telephone, InputField username, 
			InputField salary, PasswordInputField password, JLabel emptyLabel, JLabel logoLabel, JPanel westPanel, 
			JPanel eastPanel, JPanel eastBottom, BufferedImage logo) {
		super.setLayout(new BorderLayout());
		
		westPanel = new JPanel();
		eastBottom = new JPanel();
		eastPanel = new JPanel();
		westPanel.setLayout(new GridLayout(8,1));
		eastBottom.setLayout(new GridLayout(4,1));
		
		firstName = new InputField(Text.getString("firstname"), 20);
		westPanel.add(firstName);
		lastName = new InputField(Text.getString("lastname"), 20);
		westPanel.add(lastName);
		address = new InputField(Text.getString("adr"), 20);
		westPanel.add(address);
		zipcode = new InputField(Text.getString("zip"), 20);
		westPanel.add(zipcode);
		email = new InputField(Text.getString("email"), 20);
		westPanel.add(email);
		telephone = new InputField(Text.getString("tel"), 20);
		westPanel.add(telephone);
		username = new InputField(Text.getString("usr"), 20);
		westPanel.add(username);
		
		emptyLabel = new JLabel("");
		eastBottom.add(emptyLabel);
		salary = new InputField(Text.getString("sal"), 20);
		eastBottom.add(salary);
		password = new PasswordInputField(Text.getString("pwd"), 20);
		eastBottom.add(password);
		
		try {
			logo = ImageIO.read(getClass().getResource("/images/simpleLogo.png"));
		} catch (IOException e) {
			System.out.println("Failed loading language icons!");
		}
		logoLabel = new JLabel();
		logoLabel.setIcon(new ImageIcon(logo));
		logoLabel.setMaximumSize(new Dimension(2,2));
		
		eastPanel.setLayout(new BorderLayout());
		eastPanel.add(logoLabel, BorderLayout.NORTH);
		eastPanel.add(eastBottom, BorderLayout.CENTER);	
		super.add(westPanel, BorderLayout.CENTER);
		super.add(eastPanel, BorderLayout.EAST);
	}
}
