package no.hist.aitel.team12.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.*;

import no.hist.aitel.team12.util.InputField;
import no.hist.aitel.team12.util.PasswordInputField;

import org.omg.CORBA.PUBLIC_MEMBER;

public class LoginWindow extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;   
    private String user = "Username";
    private String pass = "Password";
    private String login = "Login";
    private JLabel languageInfo = new JLabel();

    private JLabel info = new JLabel("Super Shopping Surfer");
    private JTextField userText = new InputField(user, 20);
    private JPasswordField passwordText = new PasswordInputField(pass, 20);
    private JButton loginButton = new JButton(login);
    
    JFrame frame = new JFrame("SSS");
    JPanel panel = new JPanel();

    private String[] languages = {"Norwegian", "English"};
    
	private JComboBox comboBox = new JComboBox(languages);
	
	public void setUser(String user){this.user = user;}
	public void setPass(String pass){this.pass = pass;}
	public void setLogin(String login){this.login = login;}
    
	public LoginWindow(){
		
		comboBox.setSelectedIndex(1);
		comboBox.addActionListener(this);
		
		
        comboBox.addItemListener(
                event -> {      																		// Listens to dropdownbox with languages
                    if (event.getSource() == comboBox) {
                    	JComboBox cb = (JComboBox)event.getSource();
                    	String msg = (String)cb.getSelectedItem();
                    	switch (msg) {
							case "Norwegian": 				 	// system.setLanguage = norwegian - Select language Norwegian
								setUser("Brukernavn");
								setPass("Passord");
								setLogin("Logg inn");
								languageInfo.setText("Norwegian selected");
								break;
                    	
							case "English": 						//system.setLanguage = english	- Select language english
								setUser("Username");
								setPass("Password");
								setLogin("Login");
								languageInfo.setText("English selected");
								break;
								
	
							default:
								setUser("Username");
								setPass("Password");
								setLogin("Login");															// No language selected
								languageInfo.setText("No language selected");
								break;
						}
                    }
                }
                
        ); 

        panel.setLayout(new GridLayout(4, 1));
        
        panel.add(new TopText());
        panel.add(new ChooserPanel());
        panel.add(new TextPanel());
		panel.add(new ButtonPanel());
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

        Buttonlistener listener = new Buttonlistener();
        loginButton.addActionListener(listener);
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	/*
	public void actionPerformed(ActionEvent event){
		                    if (event.getSource() == comboBox) {
                    	JComboBox cb = (JComboBox)event.getSource();
                    	String msg = (String)cb.getSelectedItem();
                    	switch (msg) {
							case "Norwegian": 				 	// system.setLanguage = norwegian - Select language Norwegian
								setUser("Brukernavn");
								setPass("Passord");
								setLogin("Logg inn");
								languageInfo.setText("Norwegian selected");
								break;
                    	
							case "English": 						//system.setLanguage = english	- Select language english
								setUser("Username");
								setPass("Password");
								setLogin("Login");
								languageInfo.setText("English selected");
								break;
								
	
							default:
								setUser("Username");
								setPass("Password");
								setLogin("Login");															// No language selected
								languageInfo.setText("No language selected");
								break;
								
                    	}
		                    }
	}*/
	
	
    
    //Gui-helpclasses
    private class TopText extends JPanel{
    	public TopText(){
    		setLayout(new FlowLayout());
    		add(info);
    	}
    }
    private class ChooserPanel extends JPanel{
    	public ChooserPanel(){
    		setLayout(new FlowLayout());
    		add(comboBox);
    	}
    }
    private class TextPanel extends JPanel{
        public TextPanel(){
            setLayout(new GridLayout(3,1));
            add(userText);
            add(passwordText);
            add(languageInfo);
        }
    }
    private class ButtonPanel extends JPanel{
    	public ButtonPanel(){
    		setLayout(new FlowLayout());
    		add(loginButton);
    	}
    }
    
    // GUI-helpclasses \end

    private class Buttonlistener implements ActionListener {			// Buttonlistener-login
        public void actionPerformed(ActionEvent event){
            JButton button = (JButton) event.getSource();
            if(button == loginButton){
                														// Insert login-functionality
            }
        }
    }
    public static void main(String[] args){								// Main-method, just runs the code
    	SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new LoginWindow();
				
			}
		});
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

