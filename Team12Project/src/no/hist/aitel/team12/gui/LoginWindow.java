package no.hist.aitel.team12.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.beans.Statement;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import no.hist.aitel.team12.util.InputField;
import no.hist.aitel.team12.util.PasswordInputField;

import org.omg.CORBA.PUBLIC_MEMBER;

public class LoginWindow extends JFrame{
    private static final long serialVersionUID = 1L;   
    private String user = "Username";
    private String pass = "Password";
    private String login = "Login";
    private JLabel languageInfo = new JLabel();
    
	Connection con;
	java.sql.Statement st;
	ResultSet rs;

    private JLabel info = new JLabel("Super Shopping Surfer");
    private JTextField userText = new InputField(user, 20);
    private JPasswordField passwordText = new PasswordInputField(pass, 20);
    private JButton loginButton = new JButton(login);
    
    JFrame frame = new JFrame("SSS");
    JPanel panel = new JPanel();

    private String[] languages = {"Norwegian", "English", "Russian", "Chinese", "Spanish", "German", "French"};
    
	private JComboBox comboBox = new JComboBox(languages);
	
	public void setUser(String user){this.user = user;}
	public void setPass(String pass){this.pass = pass;}
	public void setLogin(String login){this.login = login;}
    
	public LoginWindow(){
		
		comboBox.setSelectedIndex(1);
		connect();
		
        comboBox.addItemListener(
                event -> {      																		// Listener for dropdownbox with languages
                    if (event.getSource() == comboBox) {
                    	JComboBox cb = (JComboBox)event.getSource();
                    	String msg = (String)cb.getSelectedItem();
                    	switch (msg) {
							case "Norwegian": 				 		// system.setLanguage = norwegian - Select language Norwegian
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
								
							case "Russian":
								languageInfo.setText("Russian selected");
								break;
								
							case "Chinese":
								languageInfo.setText("Chinese selected");
								break;
								
							case "Spanish":
								languageInfo.setText("Spanish selected");
								break;
								
							case "German":
								languageInfo.setText("German selected");
								break;
								
							case "French":
								languageInfo.setText("French selected");
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
            try {
	            //JButton button = (JButton) event.getSource();
	            
	            String user = userText.getText().trim();
	            String pass = passwordText.getText().trim();
	            
	            String sql = "select user, pass from _Tablename_ where user = '"+user+"'and pass = '"+pass+"' ";
				rs = ((java.sql.Statement) st).executeQuery(sql);
					
	            int count = 0;
	            while(rs.next()){
	            	count++;
	            }
	            if (count == 1) {
					JOptionPane.showMessageDialog(null, "User found, Access Granted!");
				}else if (count > 1) {
					JOptionPane.showMessageDialog(null, "Duplicate User, Access Denied");
				}else {
					JOptionPane.showMessageDialog(null, "User not Found");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }
    
    public void connect() {											// Connecting to database (Missing driver- and database address)
		try {
			String driver = "x.x.x.X";
			getClass();
			Class.forName(driver);
			
			String db = "x:x:x";
			con = DriverManager.getConnection(db);
			st = con.createStatement();
			
		} catch(Exception e){
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
}

