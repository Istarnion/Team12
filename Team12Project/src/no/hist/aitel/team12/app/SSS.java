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
 * SSS.java Team 12, 8 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.gui.FinanceTab;
import no.hist.aitel.team12.gui.LoginWindow;
import no.hist.aitel.team12.gui.MessageTab;
import no.hist.aitel.team12.gui.OverviewTab;
import no.hist.aitel.team12.gui.SSSWindow;
import no.hist.aitel.team12.gui.SplashScreen;
import no.hist.aitel.team12.gui.SqlTab;
import no.hist.aitel.team12.gui.TicketTab;
import no.hist.aitel.team12.gui.UserTab;
import no.hist.aitel.team12.util.PasswordManager;
import no.hist.aitel.team12.util.Text;

/**
 * This is the main class of the SSS application. It handles the main system flow, mainly startup and shutdown.
 * 
 * 
 * @author Hallgeir
 *
 */
public class SSS {

	public final static long MIN_SPLASH_TIME = 1500L;	// How long, minimum, the splash screen will be shown.
	
	LoginWindow login;
	
	SSSWindow sssWindow;
	
	SplashScreen splash;
	
	private static SSS sss;
	
	/**
	 * The constructor first shows the splash screen, then attempts to connect to the database.
	 * This is done in a safe way, so if the connection fails, we will exit cleanly with an error message.
	 */
	public SSS() {
		File loginInfoFile = new File("login.ser");
		if(!loginInfoFile.isFile()) {
			String[] loginInfo = new String[3];
			loginInfo[0] = JOptionPane.showInputDialog(null, "Please input the database URL:", "localhost");
			if(loginInfo[0] == null) System.exit(0);
			loginInfo[1] = JOptionPane.showInputDialog("Please input the username for "+loginInfo[0]);
			if(loginInfo[1] == null) System.exit(0);
			loginInfo[2] = JOptionPane.showInputDialog("Please input the password for "+loginInfo[1]);
			if(loginInfo[2] == null) System.exit(0);
			
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(loginInfoFile))) {
				oos.writeObject(loginInfo);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Splash Screen
		splash = new SplashScreen();
		splash.createSplash();
		long timestamp = System.currentTimeMillis();
		
		if(!DatabaseFactory.setup()) {
			exitWithError("Failed to connect to the Database.\nPlease contact system administrator.");
		}
		else {
			System.out.println("Everything went nicely with the database connection.");
		}
		
		while(System.currentTimeMillis() - timestamp < MIN_SPLASH_TIME) {
			Thread.yield();
		}
		splash.setSplashVisible(false);
		
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			
			// Set System L&F
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception ex) {
			System.out.println("Failed setting System laf. Reverting to Java defult.");
		}
		
		// Login
		login = new LoginWindow(this);
		login.showLoginWindow();
		
		sss = this;
	}
	
	public static void main(String[] args) {
		new SSS();
	}
	
	/**
	 * Makes an attempt to log in as the user indicated by the provided username.
	 * For security reasons, the password should actually be handled as a char[],
	 * but a String was chosen for this application for speed of development.
	 * 
	 * @param username	The username of the user attempting to log in
	 * @param password	The password of the user
	 * @return True if the username and password match. This is needed for the login window to know how it should react
	 */
	public boolean login(String username, String password) {
		Database db = DatabaseFactory.getDatabase();
		
		int id = db.getUserID(username);
		
		boolean ok = PasswordManager.validatePasswordMatch(password, db.getPasswordHash(id));
		
		if(ok) {
			splash.setSplashVisible(true);
			
			if(login != null) login.dispose();
			UserType type = DatabaseFactory.getDatabase().getUserType(id);
			DataBuffer.setup(2, 2, id, id, type);
			
			setupWindow(id, username);
		}
		
		return ok;
	}
	
	/**
	 * Sets up the main window, creating the neccesary tabs for the user in question.
	 * For instance, a system admin will be given tabs for overview and editing, managing of users,
	 * his/hers message system, and a tab for executing arbitary SQL towards the database.
	 * Naturally, a customer service employee should not be given those oppurtunities, but is instead
	 * given a tab to respond to support tickets from customers, in addition to their message system.
	 * 
	 * @param userId	The employee number of the user trying to log in
	 * @param username	The textual username of the user
	 */
	private void setupWindow(int userId, String username) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				UserType type = DatabaseFactory.getDatabase().getUserType(userId);
				sssWindow = new SSSWindow();
				
				switch(type) {
					case SYS_ADMIN:
					{
						sssWindow.addTab(Text.getString("overview"),	new OverviewTab(userId, type));
						sssWindow.addTab(Text.getString("usrs"),		new UserTab(userId, type));
						sssWindow.addTab(Text.getString("msgs"),		new MessageTab(username));
						sssWindow.addTab(Text.getString("sql"),			new SqlTab());
					} break;
					
					case CENTRE_MANAGER:
					{
						sssWindow.addTab(Text.getString("overview"),	new OverviewTab(userId, type));
						sssWindow.addTab(Text.getString("msgs"),		new MessageTab(username));
						sssWindow.addTab(Text.getString("usrs"),		new UserTab(userId, type));
						sssWindow.addTab(Text.getString("finance"),		new FinanceTab(username, userId, type));
					} break;
					
					case SHOP_OWNER:
					{
						sssWindow.addTab(Text.getString("overview"),	new OverviewTab(userId, type));
						sssWindow.addTab(Text.getString("msgs"),		new MessageTab(username));
						sssWindow.addTab(Text.getString("finance"),		new FinanceTab(username, userId, type));
					} break;
					
					case CUSTOMER_SERVICE:
					{
						sssWindow.addTab(Text.getString("msgs"),		new MessageTab(username));
						sssWindow.addTab(Text.getString("tckts"),		new TicketTab());
					} break;
					default:
					{
						System.out.println("Invalid user type attempted login. User type: "+type);
						exit();
					} break;
				}
				
				sssWindow.showWindow();
				splash.removeSplash();
			}
		});
	}
	
	/**
	 * A clean exit for the SSSApplication.
	 * The database connection is taken down, and all windows are disposed of.
	 */
	public static void exit() {
		DatabaseFactory.getDatabase().teardown();
		if(sss != null && sss.login != null) sss.login.dispose();
		if(sss != null && sss.sssWindow != null) sss.sssWindow.dispose();
		System.out.println("Successful exit.");
		System.exit(0);
	}
	
	/**
	 * In case of a fatal error, this method will display an error message as the application is taken down.
	 * 
	 * @param errMsg	The text that will be displayed in the error message
	 */
	public static void exitWithError(String errMsg) {
		DatabaseFactory.getDatabase().teardown();
		if(sss.login != null) sss.login.dispose();
		if(sss.sssWindow != null) sss.sssWindow.dispose();
		JOptionPane.showMessageDialog(null, errMsg, Text.getString("err"), JOptionPane.ERROR_MESSAGE);
		System.exit(1);
	}
}
