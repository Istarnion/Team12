package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.hist.aitel.team12.util.Text;

public class NewUserCard extends JPanel {

	private static final long serialVersionUID = 4688863130267581267L;
	
	private InputField firstName, lastName, address, zipcode, email, telephone, username, company, position, salary;
	
	private PasswordInputField password;
	
	private JButton saveButton, cancelButton;
	
	private JLabel emptyLabel, logoLabel;
	
	private JPanel buttonPanel, westPanel, eastPanel, eastBottom;
	
	private BufferedImage logo;
	
	public NewUserCard() {
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
		company = new InputField(Text.getString("cmp"), 20);
		westPanel.add(company);
		username = new InputField(Text.getString("usr"), 20);
		westPanel.add(username);
		
		emptyLabel = new JLabel("");
		eastBottom.add(emptyLabel);
		position = new InputField(Text.getString("pos"), 20);
		eastBottom.add(position);
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
		
		eastPanel.setLayout(new BorderLayout());
		eastPanel.add(logoLabel, BorderLayout.NORTH);
		eastPanel.add(eastBottom, BorderLayout.CENTER);	
		super.add(westPanel, BorderLayout.CENTER);
		super.add(eastPanel, BorderLayout.EAST);
		
		saveButton = new JButton(Text.getString("save"));
		cancelButton = new JButton(Text.getString("cancel"));
		buttonPanel = new JPanel();
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		
		super.add(buttonPanel, BorderLayout.SOUTH);
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
			}
		});
	}

}
