package account;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ResetProgram extends JFrame {
	Credentials ac;

	public ResetProgram() {
		ac = new Credentials();
		resetDB();
		resetPB();
		ac.clearCredentials();
		new blocker.ResetRunAsAdmin();
	}

	public void resetDB() {
		String drop_query, delete_query;
		String email = ac.getEmail();
		String m_email = ac.getModifiedEmail();
		drop_query = "drop table " + m_email;
		delete_query = "delete from id_table where email='" + email + "'";
		new db.DbCommand().executeDdlCommand("login", drop_query);
		new db.DbCommand().executeDmlCommand("id", delete_query);
	}

	public void resetPB() {
		String fileName = "C:\\Users\\Public\\lms_pc\\Logs\\pb.txt";
		new blocker.StopProgramsBlocker().stopPB();
		File f = new File(fileName);
		try {
			FileWriter fw = new FileWriter(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ResetProgram rp = new ResetProgram();
//		new gui.PassWindow();
	}
}