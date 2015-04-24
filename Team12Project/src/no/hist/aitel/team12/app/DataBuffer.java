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
 * This class creates a separate thread and continously pulls data from the db, causing the arrays it provides
 * through <code>getCentres()</code> and <code>getMessages()</code> to generally be up to date with the db.
 * <br>
 * Between each pull from the db, the threads sleeps for a while to avoid killing the server and/or client CPU.
 * 
 * @author Hallgeir
 * @version 1.0
 */
public class DataBuffer {

	private final long restTime;
	
	private int safePointer = -1;
	
	private int currentIndex = 0;
	
	private ShoppingCentre[][] centreBuffers;
	
	private Message[][] messageBuffers;
	
	private Person[][] personBuffers;
	
	private Ticket[][] ticketBuffer;
	
	private Thread thread;
	
	private final int centreUserID, messageUserID;
	
	private UserType userType;
	
	private static DataBuffer dataBuffer;
	
	private DataBuffer(float restTime, int numBuffers, int userID, int messageUserID, UserType userType) {
		this.userType = userType;
		this.centreUserID = userID;
		this.messageUserID = messageUserID;
		if(restTime < 0.5f) {
			System.out.println("Provided restTime is too small: "+restTime+". Setting to 0.5 seconds");
			restTime = 0.5f;
		}
		
		this.restTime = (long)(restTime*1000.0f);
		
		centreBuffers = new ShoppingCentre[numBuffers][];
		messageBuffers = new Message[numBuffers][];
		personBuffers = new Person[numBuffers][];
		
		if(userType == UserType.CUSTOMER_SERVICE) {
			ticketBuffer = new Ticket[numBuffers][];
		}
		
		run();
	}
	
	public static void setup(float restTime, int numBuffers, int userID, int messageUserID, UserType userType) {
		dataBuffer = new DataBuffer(restTime, numBuffers, userID, messageUserID, userType);
	}
	
	private void run() {
		Database db = DatabaseFactory.getDatabase();
		
		thread = new Thread("DataBufferThread") {
			@Override
			public void run() {
				ShoppingCentre[] carray;
				Message[] marray;
				Person[] parray;
				Ticket[] tarray;
				
				String username = db.getUsername(messageUserID);
				while(!this.isInterrupted()) {
					carray = db.getShoppingCentres(centreUserID);
					if(carray == null) break;
					centreBuffers[currentIndex] = carray;
					
					marray = db.getMessages(username);
					if(marray == null) break;
					messageBuffers[currentIndex] = marray;
					
					if(userType != UserType.CUSTOMER_SERVICE) {
						parray = db.getPersons(centreUserID);
						if(parray == null) break;
						personBuffers[currentIndex] = parray;
					}
					else {
						tarray = db.getTickets(centreUserID);
						if(tarray == null) break;
						ticketBuffer[currentIndex] = tarray; 
					}
					
					currentIndex++;
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
	public static ShoppingCentre[] getCentres() {
		if(dataBuffer == null) return null;
		if(dataBuffer.safePointer >= 0) {
			return dataBuffer.centreBuffers[dataBuffer.safePointer];
		}
		else {
			return null;
		}
	}
	
	/**
	 * Finds the most up to date, safe buffer of shopping centres
	 * 
	 * @return An array of shopping centres, fully populated. Null if no buffer is ready yet.
	 */
	public static Message[] getMessages() {
		if(dataBuffer == null) return null;
		if(dataBuffer.safePointer >= 0) {
			return dataBuffer.messageBuffers[dataBuffer.safePointer];
		}
		else {
			return null;
		}
	}
	
	/**
	 * Finds the most up to date, safe buffer of persons
	 * 
	 * @return An array of persons in the db
	 */
	public static Person[] getPersons() {
		if(dataBuffer == null) return null;
		if(dataBuffer.safePointer >= 0) {
			return dataBuffer.personBuffers[dataBuffer.safePointer];
		}
		else {
			return null;
		}
	}
	
	/**
	 * 
	 * @return An array of tickets
	 */
	public static Ticket[] getTickets() {
		if(dataBuffer == null) return null;
		if(dataBuffer.safePointer >= 0) {
			return dataBuffer.ticketBuffer[dataBuffer.safePointer];
		}
		else {
			return null;
		}
	}
	
	public void teardown() {
		thread.interrupt();
	}
}
