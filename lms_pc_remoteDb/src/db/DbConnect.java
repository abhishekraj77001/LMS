package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
	Connection con = null;
	String user, pass;

	public Connection getDbConnection(String db_name) {
		if (db_name.equals("id")) {
			db_name = "MogrfKq1wm";
			user = "MogrfKq1wm";
			pass = "dGYkFf0fyI";
		} else if (db_name.equals("login")) {
			db_name = "uaUuh6GUC2";
			user = "uaUuh6GUC2";
			pass = "FdNPsrLhYH";
		}
		String url = "jdbc:mysql://remotemysql.com:3306/" + db_name;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return con;
	}
}