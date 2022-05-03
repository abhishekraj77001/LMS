package blocker;

import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ResetRunAsAdmin extends JFrame {
	private String command;
	Process p;

	public ResetRunAsAdmin() {
		getRights();
	}

	public void getRights() {
		// Get OS name
		String OS = System.getProperty("os.name").toLowerCase();

		if ((OS.indexOf("win") >= 0)) {
//			command="powershell.exe Start-Process \"java \'-classpath .\\bin blocker.ResetSB\'\" -verb RunAs";

//			when run from jar file
			command = "powershell.exe Start-Process \"java \'-cp lms_pc.jar blocker.ResetSB\'\" -verb RunAs";
		} else if ((OS.indexOf("mac") >= 0)) {

		} else if ((OS.indexOf("nux") >= 0)) {
		} else {
			// Handle error when platform is not Windows, Mac, or Linux
			System.err.println("Sorry, but your OS doesn't support blocking.");
			System.exit(0);
		}
		try {
			p = Runtime.getRuntime().exec(command);
			int n = p.waitFor();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "program has been reset.Please relaunch program");
			System.exit(2);
		} catch (Exception e) {
			System.out.println("error");
		}
	}

	public static void main(String args[]) {
		ResetRunAsAdmin r = new ResetRunAsAdmin();
//		r.getRights();
	}
}
