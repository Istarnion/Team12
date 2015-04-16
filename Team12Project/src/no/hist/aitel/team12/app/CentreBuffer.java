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
 * CentreBuffer.java Team 12, 16 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;

/**
 * This class creates a separate thread and continously pulls data from the db, causing the ShoppingCentre array it provides
 * through <code>getCentres()</code> to generally be up to date with the db.
 * <br>
 * Between each pull from the db, the threads sleeps for a while to avoid killing the server and/or client CPU.
 * 
 * @author Hallgeir
 * @version 1.0
 */
public class CentreBuffer {

	private final long restTime;
	
	private int safePointer = -1;
	
	private int currentIndex = 0;
	
	private ShoppingCentre[][] centreBuffers;
	
	private Thread thread;
	
	private final int userID;
	
	public CentreBuffer(float restTime, int numBuffers, int userID) {
		this.userID = userID;
		if(restTime < 0.5f) {
			System.out.println("Provided restTime is too small: "+restTime+". Setting to 0.5 seconds");
			restTime = 0.5f;
		}
		
		this.restTime = (long)(restTime*1000.0f);
		
		centreBuffers = new ShoppingCentre[numBuffers][];
		
		run();
	}
	
	public CentreBuffer(float restTime, int userID) {
		this(restTime, 2, userID);
	}
	
	public CentreBuffer(int numBuffers, int userID) {
		this(1.0f, numBuffers, userID);
	}
	
	public CentreBuffer() {
		this(1.0f, 2, 1);
	}

	private void run() {
		Database db = DatabaseFactory.getDatabase();
		
		thread = new Thread() {
			@Override
			public void run() {
				while(!this.isInterrupted()) {
					centreBuffers[currentIndex++] = db.getShoppingCentres(userID);
					if(currentIndex >= centreBuffers.length) currentIndex = 0;
					safePointer++;
					if(safePointer >= centreBuffers.length) safePointer = 0;
					try {
						Thread.sleep(restTime);
					}
					catch(InterruptedException e) {
						System.out.println("Buffering thread was interrupted!\n\t"+e.getMessage());
					}
					
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
	}
	
	/**
	 * Finds the most up to date, safe buffer of shopping centres
	 * 
	 * @return An array of shopping centres, fully populated. Null if no buffer is ready yet.
	 */
	public ShoppingCentre[] getCentres() {
		if(safePointer >= 0) {
			return centreBuffers[safePointer];
		}
		else {
			return null;
		}
	}
	
	public void teardown() {
		thread.interrupt();
	}
}
