package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginInfoTable extends JFrame implements ActionListener {
	JTable jt;
	JScrollPane jsp;
	DefaultTableModel tm;
	String db_name;

	public LoginInfoTable(String tableName) {
		db_name = "login";
		tm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;// This causes all cells to be not editable
			}
		};
		jt = new JTable(tm);
		tm.addColumn("Username");
		tm.addColumn("Login Time");
		ResultSet rs = new db.QueryFromTable().getResult(db_name, tableName);
		try {
			while (rs.next()) {
				tm.addRow(new Object[] { rs.getString(1), rs.getString(2) });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jsp = new JScrollPane(jt);
		windowSetter();
		boundSetter();
		componentsAdder();
		eventSetter();
	}

	void boundSetter() {
		jt.setBounds(30, 40, 200, 300);
	}

	void windowSetter() {
		setTitle("LMS");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 500, 500);
//		setSize(500,200);
		setResizable(false);
	}

	void componentsAdder() {
		add(jsp);
	}

	void eventSetter() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.out.println("window closed");
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
	}

	public static void main(String[] args) {
		LoginInfoTable lit = new LoginInfoTable("prashantkumar67625gmailcom");
	}
}