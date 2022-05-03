package login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * main class of the lms_pc.jar file this class is called on loading the jar
 * file
 */
public class LTime {
	/**
	 * Main() method
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Process p;
		File f = null;
		FileWriter fw = null;
		StringBuilder sb = null;
		PrintWriter pw = null;
		BufferedReader reader = null;
		try {
			sb = new StringBuilder();
			f = new File("C:\\Users\\Public\\lms_pc\\Logs\\login_info.txt");
			fw = new FileWriter(f, true);
			pw = new PrintWriter(fw);
			p = Runtime.getRuntime().exec("quser");
			p.waitFor();
			reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			String[] vals = sb.toString().split(" ");
			String uname = System.getProperty("user.name");
			String loginTime = vals[vals.length - 3] + " " + vals[vals.length - 2] + " " + vals[vals.length - 1];
			System.out.println(uname);
			System.out.println(loginTime);
			pw.println("username:" + uname);
			pw.println("Login time:" + loginTime);
			account.AttemptsCredentials ac = new account.AttemptsCredentials();
			ac.setAttempts(5);
			new CheckConnection(uname, loginTime);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null)
				fw.close();
		}
	}
}
