package no.hist.aitel.team12.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import no.hist.aitel.team12.util.InputField;
import no.hist.aitel.team12.util.PasswordInputField;
//import no.hist.aitel.team12.util.Text;
//import no.hist.aitel.team12.app.PasswordManager;

/**
 * This class contains login-window, and will handle interaction from the user from program startup to a users homepage. 
 * 
 * 
 * @author Roger
 * @version 1.0
 *
 */

public class LoginWindow extends JFrame{
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
    
    // Example:
    private String[] languages = {"Norwegian", "English"};
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JComboBox comboBox = new JComboBox(languages);
	
	Connection con;
	java.sql.Statement st;
	ResultSet rs;
	
	
	public void setUser(String user){this.user = user;}
	public void setPass(String pass){this.pass = pass;}
	public void setLogin(String login){this.login = login;}
    
	public LoginWindow(){
		
		comboBox.setSelectedIndex(1);
		connect();
		
        comboBox.addItemListener(
                event -> {      																		// Listener for dropdownbox with languages
                    if (event.getSource() == comboBox) {
						JComboBox<?> cb = (JComboBox<?>)event.getSource();
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
								
	
							default:													// No language selected
								languageInfo.setText("Error, no language selected");
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
		private static final long serialVersionUID = 1180991892671688640L;
		public TopText(){
    		setLayout(new FlowLayout());
    		add(info);
    	}
    }
    private class ChooserPanel extends JPanel{
		private static final long serialVersionUID = -1720583423271090517L;
		public ChooserPanel(){
    		setLayout(new FlowLayout());
    		add(comboBox);
    	}
    }
    private class TextPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public TextPanel(){
            setLayout(new GridLayout(3,1));
            add(userText);
            add(passwordText);
            add(languageInfo);
        }
    }
    private class ButtonPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public ButtonPanel(){
    		setLayout(new FlowLayout());
    		add(loginButton);
    	}
    }   
    // GUI-helpclasses \end

    private class Buttonlistener implements ActionListener {			// Buttonlistener for login-functionality, example
        public void actionPerformed(ActionEvent event){
            try {
	            //JButton button = (JButton) event.getSource();
	            
	            String user = userText.getText().trim();
	            @SuppressWarnings("deprecation")
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
    
    public void connect() {											// Connecting to database !EXAMPLE!
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
    
    public int showLoginWindow(){
    	return 1;					//?
    }
}

