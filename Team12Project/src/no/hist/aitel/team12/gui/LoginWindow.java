package no.hist.aitel.team12.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LoginWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private String username;                // variabel så vi kan skifte språk
    private String password;                // -||-

    private JLabel info = new JLabel("Super Shopping Surfer");
    private JLabel userLabel = new JLabel(username);
    private JLabel passwordLabel = new JLabel(password);
    private JTextField userText = new JTextField(20);
    private JTextField passwordText = new JTextField(20);
    private JButton loginbutton = new JButton();

    public LoginWindow(){
        setTitle("SSS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel chooseLanguage = new JPanel();
        chooseLanguage.add(new JLabel("Choose Language"));
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Norwegian");
        model.addElement("English");
        model.addElement("Chinese");
        JComboBox comboBox = new JComboBox(model);

        LayoutManager layout = new FlowLayout();
        setLayout(layout);

        add(info);
        add(comboBox);
        add(userLabel);
        add(passwordLabel);
        add(userText);
        add(passwordText);
        add(loginbutton);
        pack();

        Buttonlistener listener = new Buttonlistener();
        loginbutton.addActionListener(listener);
    }

    private class Buttonlistener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            JButton button = (JButton) event.getSource();
            if(button == loginbutton){
                // logg inn
            }
        }
    }

    private class DropdownListener implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent event){
            int index1 = event.getFirstIndex();
            if (event.getValueIsAdjusting() == false){

            }
        }
        public void actionPerformed(ActionEvent event){
            JList listed = (JList) event.getSource();
        }
    }

}
class Language{
    private String navn;

    public Language(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }
}

class LoginWindowTest{
    public static void main(String[] args){
        LoginWindow oneWindow = new LoginWindow();
        oneWindow.setVisible(true);
    }
}
