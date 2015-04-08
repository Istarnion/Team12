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

import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.gui.LoginWindow;
import no.hist.aitel.team12.gui.SplashScreen;

public class SSS {

	private final static long MIN_SPLASH_TIME = 2000L;	// How long, minimum, the splash screen will be shown.
	
	public static void main(String[] args) {
		// Splash Screen
		SplashScreen splash = new SplashScreen();
		splash.createSplash();
		long timestamp = System.currentTimeMillis();
		DatabaseFactory.setup();
		while(System.currentTimeMillis() - timestamp < MIN_SPLASH_TIME) {
			Thread.yield();
		}
		splash.removeSplash();
		
		// Login
		LoginWindow loginWindow = new LoginWindow();
		
		// Depending on the user, setup the GUI
				
	}
}
