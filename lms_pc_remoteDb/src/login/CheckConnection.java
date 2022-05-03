package login;

//Java program for checking Internet connectivity 
import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class CheckConnection {
	File f;
	FileWriter fw = null;
	PrintWriter pw = null;
	String uname, loginTime;
	boolean email_on;

	public CheckConnection(String uname, String loginTime) {
		f = new File("C:\\Users\\Public\\lms_pc\\Logs\\e_offline.txt");
		if (isConnected()) {
			try {
				new db.Update(uname, loginTime);
				email_on = new account.Credentials().getEmailStatus();
				if (email_on) {
					new mail.SendMail(uname, loginTime);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("Internet Not Connected");
				f = new File("C:\\Users\\Public\\lms_pc\\Logs\\e_offline.txt");
				fw = new FileWriter(f, true);
				pw = new PrintWriter(fw);
				pw.println(uname);
				pw.println(loginTime);
				fw.close();
				pw.close();
			} catch (Exception e) {
				System.out.println("error c2:" + e);
				e.printStackTrace();
			}
			// connection checking continuously
			while (!isConnected()) {
				try {
					Thread.sleep(60000); // 1 minute(s) wait
				} catch (InterruptedException e) {
					System.out.println("error c3:" + e);
					e.printStackTrace();
				}
			}
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line1, line2;
			StringBuilder sb = new StringBuilder("");
			while (((line1 = br.readLine()) != null) && ((line2 = br.readLine()) != null)) {
				// process the line.
				uname = line1;
				loginTime = line2;
				new db.Update(uname, loginTime);
				System.out.println("user:" + uname + " Login time:" + loginTime + "\n");
				br.readLine();
				sb.append("user:" + uname + " Login time:" + loginTime + "\n");
			}
			email_on = new account.Credentials().getEmailStatus();
//		    System.out.println(email_on);
			if (email_on) {
				new mail.SendMail(sb);
			}
			fw = new FileWriter(f, false);
			fw.close();
		} catch (Exception e) {
			System.out.println("error c4:" + e);
			e.printStackTrace();
		}
	}

	boolean isConnected() {
		boolean b = false;
		try {
			URL url = new URL("https://www.google.com");
			URLConnection connection = url.openConnection();
			connection.connect();
			System.out.println("Connection Successful");
			b = true;
		} catch (Exception e) {
			System.out.println(e);
			b = false;
		} finally {
			return b;
		}
	}
}
