package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCommand {
	Connection con = null;

	public int executeDdlCommand(String db_name, String command) {
		int count = 0;
		try {
			con = new db.DbConnect().getDbConnection(db_name);
			if (con == null) {
				System.out.println("connection is null");
			} else {
				Statement st = con.createStatement();
				count = st.executeUpdate(command);
				System.out.println(count + " row(s) affected");
				st.close();
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return count;
	}

	public int executeDmlCommand(String db_name, String command) {
		int count = 0;
		try {
			con = new db.DbConnect().getDbConnection(db_name);
			if (con == null) {
				System.out.println("connection is null");
			} else {
				Statement st = con.createStatement();
				count = st.executeUpdate(command);
				System.out.println(count + " row(s) affected");
				st.close();
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return count;
	}

	public ResultSet executeQueryCommand(String db_name, String command) {
		ResultSet rs = null;
		try {
			con = new db.DbConnect().getDbConnection(db_name);
			if (con == null) {
				System.out.println("connection is null");
			} else {
				Statement st = con.createStatement();
				rs = st.executeQuery(command);
				st.close();
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return rs;
	}
}