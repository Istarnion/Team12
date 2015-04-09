package no.hist.aitel.team12.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

	private JLabel info = new JLabel(Text.getString("sss"));
	private InputField userText = new InputField(Text.getString("usr"), 20);
	private PasswordInputField passwordText = new PasswordInputField(Text.getString("pwd"), 20);
	private JButton loginButton = new JButton(Text.getString("login"));

	JFrame frame = new JFrame("SSS");
	JPanel panel = new JPanel();

	private String[] languages = {"Norwegian", "English"};

	private JComboBox<String> comboBox = new JComboBox<String>(languages);

	private int user = -2;
	
	public LoginWindow(){

	}

	public void updateFields() {
		userText.setDefaultText(Text.getString("usr"));
		passwordText.setDefaultText(Text.getString("pwd"));
		loginButton.setText(Text.getString("login"));
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

	private class Buttonlistener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event){

			//JButton button = (JButton) event.getSource();

			String username = userText.getText().trim();

			String pass = new String(passwordText.getPassword());	

			user = 0;
			
		}
	}

	public int showLoginWindow() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		comboBox.setSelectedIndex(1);

		comboBox.addItemListener(
			event -> {      												// Listener for dropdownbox with languages
				if (event.getSource() == comboBox) {
					JComboBox<?> cb = (JComboBox<?>)event.getSource();
					String msg = (String)cb.getSelectedItem();
					switch (msg) {
						case "Norwegian":
							Text.setLocale(Text.NORWEGIAN);
							updateFields();
							break;
						case "English":
							Text.setLocale(Text.ENGLISH);
							updateFields();
							break;
						default:
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
		frame.setLocationRelativeTo(null);

		Buttonlistener listener = new Buttonlistener();
		loginButton.addActionListener(listener);
		
		while(user < -1) {
			Thread.yield();
		}
		
		frame.dispose();
		return user;
	}
}

