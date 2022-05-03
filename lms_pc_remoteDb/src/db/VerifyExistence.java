package db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerifyExistence {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	public int doesExists(String db, String table_name, String email, String password) {
		String db_name = db;
		String query = "Select * from " + table_name;
		try {
			con = new db.DbConnect().getDbConnection(db_name);
			if (con == null) {
				System.out.println("connection is null");
			} else {
				st = (Statement) con.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					String e, p;
					e = rs.getString(1);
					p = rs.getString(2);
					if (email.equalsIgnoreCase(e)) {
						if (password.equals(p)) {
							return 0; // means email & password both match
						} else {
							return 1; // means only email match not password
						}
					}
				}
				st.close();
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 2; // either email or both email & password didn't match
	}

	public static void main(String[] args) {
		VerifyExistence ve = new VerifyExistence();
		System.out.println(ve.doesExists("id", "id_table", "prashantkumar67625@gmail.com", "Pacific"));
	}
}
