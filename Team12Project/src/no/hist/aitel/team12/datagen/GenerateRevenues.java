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
 * GenerateRevenues.java Team 12, 27. apr. 2015
 *******************************************************************************/
package no.hist.aitel.team12.datagen;

import java.util.Calendar;
import java.util.Random;

import no.hist.aitel.team12.app.Building;
import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;

public class GenerateRevenues {

	public static void main(String[] args) {
		DatabaseFactory.setup();
		Database db = DatabaseFactory.getDatabase();
		System.out.println("Database ready");

		Noise noise = new Noise();

		long minCentreRev = 11240845;
		long minEstabRev  = 234555;

		int numMonths = 12*20;

		double scale = 10.0;

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1995);

		Establishment[] estabs;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO revenue (business_id, month, turnover_month) VALUES ");

		java.sql.Date sqlDate;

		ShoppingCentre[] centres = db.getShoppingCentres(1);
		
		Random rnd = new Random();
		
		try {
		for(int i=0; i<numMonths; i++) {
			sqlDate = new java.sql.Date(cal.getTime().getTime());
			for(int j=0; j<centres.length; j++) {
				if(centres[j] != null) {

					for(Building b : centres[j].getBuildings()) {
						if(b != null) {
							estabs = b.getEstablishments();
							if(estabs != null) {
								for(int k=0; k<estabs.length; k++) {
									if(estabs[k] != null) {
										builder.append(
												"("+estabs[k].getBusinessId()+", '"+sqlDate+
												"', "+(long)((rnd.nextDouble()+Math.sin(k)+1.0+
														noise.eval((200*j)/scale, (100*k)/scale))*minEstabRev)+"), ");
									}
								}
							}
						}
					}
					builder.append(
							"("+centres[j].getBusinessId()+", '"+sqlDate+
							"', "+(long)((rnd.nextDouble()+Math.cos(j)+1.0+
									noise.eval((i*100)/scale, (100*j)/scale))*minCentreRev)+"), ");
				}
			}

			cal.add(Calendar.MONTH, 1);
			System.out.println("Progress: "+(i+1)+"/"+numMonths);
		}

		String sqlStatement = builder.toString().trim();
		sqlStatement = sqlStatement.substring(0, sqlStatement.length()-1);

		db.executePreparedStatement(sqlStatement+" ON DUPLICATE KEY UPDATE turnover_month = VALUES(turnover_month)");
		}
		catch(Exception e) {
			System.out.println("Something went wrong: "+e.getMessage());
		}
		finally {
			db.teardown();
			System.out.println("Successful exit");
		}
	}
}
