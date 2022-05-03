package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IdTable {
	Connection con = null;

	public IdTable(String email, String password, String name) {
		String db_name = "id";
		String query = "insert into id_table values (?,?,?,?)";
		String hostName = new String(new sys.HostName().getHostName());
		try {
			con = new db.DbConnect().getDbConnection(db_name);
			if (con == null) {
				System.out.println("connection is null");
			} else {
				PreparedStatement st = con.prepareStatement(query);
				st.setString(1, email);
				st.setString(2, password);
				st.setString(3, name);
				st.setString(4, hostName);
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