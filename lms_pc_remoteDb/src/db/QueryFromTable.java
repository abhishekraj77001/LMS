package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryFromTable {
	ResultSet rs = null;
	Connection con = null;

	public ResultSet getResult(String db_name, String table_name) {
		con = new DbConnect().getDbConnection(db_name);
		if (con == null) {
			System.out.println("connection is null");
		} else {
			String query = "select * from " + table_name;
			try {
				Statement st = con.createStatement();
				rs = st.executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs;
	}
}