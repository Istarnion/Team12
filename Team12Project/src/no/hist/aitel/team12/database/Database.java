package no.hist.aitel.team12.database;

import java.sql.ResultSet;

public interface Database {
	public ResultSet executeSQL(String SQL);
	public boolean executeUpdate(String SQL);

}
