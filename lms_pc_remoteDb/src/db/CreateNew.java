package db;

import java.sql.*;

public class CreateNew {
	Connection con = null;

	public CreateNew(String email) {
		email = email.toLowerCase();
		email = removeSpecialChars(email);
		String db_name = "login";
		String table_name = email;
		String query = "create table " + table_name + "(user varchar(20) NOT NULL," + "login_time varchar(20) NOT NULL)";
		try {
			con = new db.DbConnect().getDbConnection(db_name);
			if (con == null) {
				System.out.println("connection is null");
			} else {
				Statement st = con.createStatement();
				st.executeUpdate(query);
				st.close();
				con.close();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	static String removeSpecialChars(String email) {
		String[] arrOfStr = email.split("[@.]");
		StringBuilder modified = new StringBuilder();
		for (String a : arrOfStr) {
			System.out.println(a);
			modified.append(a);
		}
		return new String(modified);
	}
}
