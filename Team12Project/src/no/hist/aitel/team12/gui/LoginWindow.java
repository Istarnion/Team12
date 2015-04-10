package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import no.hist.aitel.team12.app.SSS;
import no.hist.aitel.team12.util.InputField;
import no.hist.aitel.team12.util.PasswordInputField;
import no.hist.aitel.team12.util.Text;

/**
 * This class contains login-window, and will handle interaction from the user from program startup to a users homepage. 
 * 
 * 
 * @author Roger
 * @version 1.0
 *
 */

public class LoginWindow {
	
	private SSS sss;
	
	private JLabel logoLabel;
	private InputField userText = new InputField(Text.getString("usr"), 20);
	private PasswordInputField passwordText = new PasswordInputField(Text.getString("pwd"), 20);
	private JButton loginButton;
	private JButton cancelButton;

	JFrame frame;
	JPanel mainPanel;
	JPanel buttonPanel;
	
	private ImageIcon no, en;
	
	private BufferedImage logo;
	
	private JComboBox<ImageIcon> languageSelection;
	
	public LoginWindow(SSS sss){
		this.sss = sss;
		try {
			no = new ImageIcon(ImageIO.read(getClass().getResource("/images/Norway-icon.png")));
			en = new ImageIcon(ImageIO.read(getClass().getResource("/images/United-Kingdom-icon.png")));
			
			logo = ImageIO.read(getClass().getResource("/images/simpleLogo.png"));
		} catch (IOException e) {
			System.out.println("Failed loading language icons!");
		}
		
		languageSelection = new JComboBox<ImageIcon>(new ImageIcon[] {no, en});
	}

	public void updateFields() {
		userText.setDefaultText(Text.getString("usr"));
		passwordText.setDefaultText(Text.getString("pwd"));
		loginButton.setText(Text.getString("login"));
		cancelButton.setText(Text.getString("cancel"));
	}

	public void showLoginWindow() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Text.setLocale(Locale.getDefault());
		if(Locale.getDefault().toString().equals(Text.ENGLISH.toString())) {
			languageSelection.setSelectedIndex(1);
		}
		else {
			languageSelection.setSelectedIndex(0);
		}
		

		languageSelection.addItemListener(
			event -> {
				JComboBox<?> cb = (JComboBox<?>)event.getSource();
				ImageIcon msg = (ImageIcon)cb.getSelectedItem();
				
				if(msg.equals(no)) {
					Text.setLocale(Text.NORWEGIAN);
					updateFields();
				}
				else if(msg.equals(en)) {
					Text.setLocale(Text.ENGLISH);
					updateFields();
				}
			}
		); 

		LoginListener loginListener = new LoginListener();
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(5, 1, 5, 2));

		logoLabel = new JLabel();
		logoLabel.setIcon(new ImageIcon(logo));
		frame.add(logoLabel, BorderLayout.NORTH);
		
		mainPanel.add(userText);
		
		mainPanel.add(passwordText);
		
		mainPanel.add(languageSelection);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2, 5, 2));
		
		loginButton = new JButton(Text.getString("login"));
		loginButton.addActionListener(loginListener);
		
		userText.addActionListener(loginListener);
		passwordText.addActionListener(loginListener);
		
		cancelButton = new JButton(Text.getString("cancel"));
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SSS.exit();
			}
		});
		
		buttonPanel.add(loginButton);
		buttonPanel.add(cancelButton);
		
		mainPanel.add(buttonPanel);
		
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	public void dispose() {
		frame.dispose();
	}
	
	private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(!sss.login(userText.getText(), new String(passwordText.getPassword()))) {
				JOptionPane.showMessageDialog(frame, Text.getString("loginfail"), Text.getString("err"), JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

