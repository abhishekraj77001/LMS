package db;

import java.sql.*;

public class Update {
	Connection con = null;

	public Update(String uname, String loginTime) {
		String email = new account.Credentials().getModifiedEmail();
		String db_name = "login";
		String table_name = email;
		String query = "insert into " + table_name + " values (?,?)";
		try {
			con = new db.DbConnect().getDbConnection(db_name);
			if (con == null) {
				System.out.println("connection is null");
			} else {
				PreparedStatement st = con.prepareStatement(query);
				st.setString(1, uname);
				st.setString(2, loginTime);
				int count = st.executeUpdate();
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
	}
}