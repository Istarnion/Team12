package no.hist.aitel.team12.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.*;

import no.hist.aitel.team12.util.InputField;
import no.hist.aitel.team12.util.PasswordInputField;

import org.omg.CORBA.PUBLIC_MEMBER;

public class LoginWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private static String username = "Username";
    private static String password = "Password";            
    private static String login = "Login";

    private JLabel info = new JLabel("Super Shopping Surfer");
    private JLabel userLabel = new JLabel(username);
    private JLabel passwordLabel = new JLabel(password);
    private JTextField userText = new InputField("Username", 20);
    private JPasswordField passwordText = new PasswordInputField("Password", 20);
    private JButton loginButton = new JButton(login);
    
    JFrame frame = new JFrame("SSS");
    JPanel panel = new JPanel();

    private static String[] languages = {"Norwegian", "English"};
    
    @SuppressWarnings("rawtypes")
	private JComboBox comboBox;
    @SuppressWarnings({ "unchecked", "rawtypes" })
    
	public LoginWindow(){
        comboBox = new JComboBox(languages);

        comboBox.addItemListener(
                event -> {      											// Listens to dropdownbox with languages
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                    	String english = "English";
                    	String norwegian = "Norwegian";
                        if (event.getItem().equals(english)) {				// Changes language to the chosen one
                        	
                        	LoginWindow.username = "Username";
                        	LoginWindow.password = "Password";
                        } else if (event.getItem().equals(norwegian)){
                        	LoginWindow.username = "Brukernavn";
                        	LoginWindow.password = "Passord";
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
            setLayout(new GridLayout(2,4));
            add(userLabel);
            add(userText);
            add(passwordLabel);
            add(passwordText);
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
}

/*				Språk-klasse
class Language{															// Hjelpeklasse, nødvendig?
    private String navn;

    public Language(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }
}
*/

