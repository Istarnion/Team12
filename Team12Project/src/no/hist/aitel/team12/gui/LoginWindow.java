package no.hist.aitel.team12.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.*;

public class LoginWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private static String username = "Username";                // variabel så vi kan skifte språk
    private static String password = "Password";                // -||-
    private static String login = "Login";

    private JLabel info = new JLabel("Super Shopping Surfer");
    private JLabel userLabel = new JLabel(username);
    private JLabel passwordLabel = new JLabel(password);
    private JTextField userText = new JTextField(20);
    private JTextField passwordText = new JTextField(20);
    private JButton loginButton = new JButton(login);

    private static String[] languages = {"Norwegian", "English"};
    private JComboBox<?> comboBox;

    public LoginWindow(){
        super("SSS");
        //setTitle("SSS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        comboBox = new JComboBox(languages);

        comboBox.addItemListener(
                event -> {      // Hvis et språk er selektert
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        if (event.getItem() == "English") {
                            // Skift språk på teksten
                            username = "Username";
                            password = "Password";
                        } else if (event.getItem() == "Norwegian"){
                            username = "Brukernavn";
                            password = "Passord";
                        }
                    }
                }
        );

       setLayout(new SpringLayout());
        
        //GridBagConstraints constraints = new GridBagConstraints();

        add(new TopText(), SpringLayout.NORTH);
        add(new InputPanel(), SpringLayout.SOUTH);
        pack();

        Buttonlistener listener = new Buttonlistener();
        loginButton.addActionListener(listener);
    }
    
    private class TopText extends JPanel{
    	public TopText(){
    		setLayout(new BorderLayout());
    		add(info, BorderLayout.CENTER);
    		add(comboBox, BorderLayout.SOUTH);
    		
    	}
    }

    private class TextPanel extends JPanel{
        public TextPanel(){
            setLayout(new BorderLayout());
            add(userLabel, BorderLayout.WEST);
            add(userText, BorderLayout.EAST);
        }
    }

    private class PasswordPanel extends JPanel{
        public PasswordPanel(){
            setLayout(new BorderLayout());
            add(passwordLabel, BorderLayout.WEST);
            add(passwordText, BorderLayout.EAST);
        }
    }
    
    private class InputPanel extends JPanel{
    	public InputPanel(){
    		setLayout(new BorderLayout());
    		add(new TextPanel(), BorderLayout.NORTH);
    		add(new PasswordPanel(), BorderLayout.CENTER);
    		add(loginButton, BorderLayout.SOUTH);
    	}
    }
    
    
    
    
    
    // GUI \end

    private class Buttonlistener implements ActionListener {			// Hjelpemetode for knapp
        public void actionPerformed(ActionEvent event){
            JButton button = (JButton) event.getSource();
            if(button == loginButton){
                // logg inn
            }
        }
    }
    public static void main(String[] args){
        LoginWindow oneWindow = new LoginWindow();
        oneWindow.setSize(500,500);
        oneWindow.setVisible(true);
    }

    /*
    private class DropdownListener implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent event){
            int index1 = event.getFirstIndex();
            //Language chosenLanguage = (Language) comboBox.
            if (event.getValueIsAdjusting() == false){

            }
        }
        public void actionPerformed(ActionEvent event){
            JList listed = (JList) event.getSource();
        }
    }
    */

}


class Language{							// Hjelpeklasse, n�dvendig?
    private String navn;

    public Language(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }
}

