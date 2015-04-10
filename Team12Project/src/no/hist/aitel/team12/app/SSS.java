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
import javax.swing.UIManager;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.gui.LoginWindow;
import no.hist.aitel.team12.gui.MessageTab;
import no.hist.aitel.team12.gui.OverviewTab;
import no.hist.aitel.team12.gui.SSSWindow;
import no.hist.aitel.team12.gui.SplashScreen;
import no.hist.aitel.team12.gui.SqlTab;
import no.hist.aitel.team12.util.PasswordManager;

public class SSS {

	private final static long MIN_SPLASH_TIME = 1500L;	// How long, minimum, the splash screen will be shown.
	
	LoginWindow login;
	
	SSSWindow sssWindow;
	
	public SSS() {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception ex) {
			System.out.println("Failed setting System laf. Reverting to Java defult.");
		}
		
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
		
		// Login
		login = new LoginWindow(this);
		login.showLoginWindow();
	}
	
	public static void main(String[] args) {
		new SSS();
	}
	
	public boolean login(String username, String password) {
		Database db = DatabaseFactory.getDatabase();
		
		int id = db.getUserID(username);
		
		boolean ok = PasswordManager.validatePasswordMatch(password, db.getPasswordHash(id));
		
		if(ok) {
			login.dispose();
			
			setupWindow(id);
		}
		
		return ok;
	}
	
	private void setupWindow(int userId) {
		UserType type = DatabaseFactory.getDatabase().getUserType(userId);
		
		sssWindow = new SSSWindow();
		switch(type) {
			case SYS_ADMIN:
			{
				sssWindow.addTab("Overview", new OverviewTab());
				sssWindow.addTab("Messages", new MessageTab());
				sssWindow.addTab("SQL", new SqlTab());
			} break;
			
			case CENTRE_MANAGER:
			{
				System.out.println("Centre manager attempted login");
				exit();
			} break;
			
			case SHOP_OWNER:
			{
				System.out.println("Shop owner attempted login");
				exit();
			} break;
			
			case CUSTOMER_SERVICE:
			{
				System.out.println("Customer service attempted login");
				exit();
			} break;
			default:
			{
				System.out.println("Invalid user type attempted login. User type: "+type);
				exit();
			} break;
		}
		
		sssWindow.showWindow();
	}
	
	public static void exit() {
		DatabaseFactory.getDatabase().teardown();
		System.out.println("Successful exit.");
		System.exit(0);
	}
}
