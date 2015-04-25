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

public class SSS {

	public final static long MIN_SPLASH_TIME = 1500L;	// How long, minimum, the splash screen will be shown.
	
	LoginWindow login;
	
	SSSWindow sssWindow;
	
	private static SSS sss;
	
	public SSS() {
		// Splash Screen
		SplashScreen splash = new SplashScreen();
		splash.createSplash();
		long timestamp = System.currentTimeMillis();
		
		if(!DatabaseFactory.setup()) {
			JOptionPane.showMessageDialog(
					null,
					"Failed connecting to the database.\nPlease contact system administrator.",
					"Connection failed",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		else {
			System.out.println("Everything went nicely with the database connection.");
		}
		
		while(System.currentTimeMillis() - timestamp < MIN_SPLASH_TIME) {
			Thread.yield();
		}
		splash.removeSplash();
		
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
	
	public boolean login(String username, String password) {
		Database db = DatabaseFactory.getDatabase();
		
		int id = db.getUserID(username);
		
		boolean ok = PasswordManager.validatePasswordMatch(password, db.getPasswordHash(id));
		
		if(ok) {
			if(login != null) login.dispose();
			UserType type = DatabaseFactory.getDatabase().getUserType(id);
			DataBuffer.setup(2, 2, id, id, type);
			
			setupWindow(id, username);
		}
		
		return ok;
	}
	
	private void setupWindow(int userId, String username) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				SplashScreen splash = new SplashScreen();
				splash.createSplash();
				
				while(sssWindow == null || !sssWindow.isVisible()) {
					Thread.yield();
				}
				
				splash.removeSplash();
			}
		};
		thread.start();
		
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
						sssWindow.addTab(Text.getString("overview"),	new OverviewTab(userId));
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
