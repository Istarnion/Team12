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
 * LoginWindow.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import no.hist.aitel.team12.app.SSS;
import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.PasswordManager;
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
	private PasswordInputField passwordText 	= new PasswordInputField(Text.getString("pwd"), 20);
	private InputField userText 				= new InputField(Text.getString("usr"), 20);
	private InputField resetUserField 			= new InputField(Text.getString("usr"), 20);
	private PasswordInputField resetOldPwd 		= new PasswordInputField(Text.getString("oldPwd"), 20);
	private PasswordInputField resetNewPwd 		= new PasswordInputField(Text.getString("newPwd"), 20);
	private PasswordInputField resetRepNewPwd 	= new PasswordInputField(Text.getString("repNewPwd"), 20);
	private JButton loginButton;
	private JButton cancelButton;
	private JButton saveButton = new JButton(Text.getString("save"));
	private JDialog resetPwdDialog = new JDialog();
	private JPanel resetPanel = new JPanel();

	private JButton resetpasswordButton;

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
		resetpasswordButton.setText(Text.getString("changePwd"));

		userText.repaint();
		passwordText.repaint();
		loginButton.repaint();
		cancelButton.repaint();
		resetpasswordButton.repaint();
	}

	public void showLoginWindow() {
		frame = new JFrame();
		try {
			List<BufferedImage> icons = new ArrayList<BufferedImage>(3);
			icons.add(ImageIO.read(getClass().getResource("/images/micro.png")));
			icons.add(ImageIO.read(getClass().getResource("/images/tiny.png")));
			icons.add(ImageIO.read(getClass().getResource("/images/medium.png")));

			frame.setIconImages(icons);
		}
		catch(Exception e) {
			System.out.println("Failed loading the frame icon images. Reverting to default\n\t"+
					"The icons are expected to be named: 'micro.png', 'tiny.png' and 'medium.png'");
		}

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.setUndecorated(true);
		frame.getRootPane().setBorder(new LineBorder(Color.BLACK));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Text.setLocale(Locale.getDefault());
		if(Locale.getDefault().toString().startsWith(Text.ENGLISH.toString())) {
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
					}
					else if(msg.equals(en)) {
						Text.setLocale(Text.ENGLISH);
					}
					updateFields();
				}
				); 

		LoginListener loginListener = new LoginListener();

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(6, 1, 5, 2));

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

		resetpasswordButton = new JButton(Text.getString("changePwd"));
		mainPanel.add(resetpasswordButton);


		resetPwdDialog.setLocationRelativeTo(null);
		resetPwdDialog.add(resetPanel);
		resetPwdDialog.setResizable(false);
		resetPanel.setLayout((new BoxLayout(resetPanel, BoxLayout.Y_AXIS)));
		resetPwdDialog.setMinimumSize(new Dimension(200,200));
		resetPanel.add(resetUserField);
		resetPanel.add(resetOldPwd);
		resetPanel.add(resetNewPwd);
		resetPanel.add(resetRepNewPwd);
		resetPanel.add(saveButton);
		resetPanel.setBorder(new EmptyBorder(10,10,10,10));
		saveButton.setAlignmentX(JButton.CENTER_ALIGNMENT);;
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {



				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					boolean OK = false;
					String newHash = PasswordManager.generatePasswordHash(new String(resetNewPwd.getPassword()));
					@Override
					protected Void doInBackground() throws Exception {

						Database db = DatabaseFactory.getDatabase();
						int id = db.getUserID(resetUserField.getText());
						if(id==-1) {
							JOptionPane.showMessageDialog(null, Text.getString("usrNotFound"));
							OK = false;
							return null;
						}
						if(!PasswordManager.validatePasswordMatch(new String(resetOldPwd.getPassword()), db.getPasswordHash(id))) {
							JOptionPane.showMessageDialog(null, Text.getString("loginfail"));
							OK = false;
							return null;
						}
						if(!(new String(resetNewPwd.getPassword()).equals(new String(resetRepNewPwd.getPassword())))) {
							JOptionPane.showMessageDialog(null, Text.getString("newPwdFail"));
							OK = false;
							return null;
						}
						if(!db.executePreparedStatement("UPDATE user SET password_hash = ? WHERE employee_number = ?", newHash, id)) {
							JOptionPane.showMessageDialog(null, Text.getString("dbErr"));
							OK = false;
						} else {
							OK = true;
						}
						return null;
					}
					@Override
					protected void done() {
						if(OK) {
							JOptionPane.showMessageDialog(null, Text.getString("pwdChanged"));
							resetPwdDialog.setVisible(false);
							saveButton.setText(Text.getString("save"));
							saveButton.setEnabled(true);
						}
					}

				};
				saveButton.setText(Text.getString("saving"));
				saveButton.setEnabled(false);
				worker.execute();
			}

		});
		resetpasswordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetUserField.requestFocus();
				resetUserField.setText("");
				resetOldPwd.setText("");
				resetNewPwd.setText("");
				resetRepNewPwd.setText("");
				resetPwdDialog.setVisible(true);
			}
		});

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

