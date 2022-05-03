package blocker;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Blocks programs. programs - The array of process names. timeout - The time
 * between blocks, in milliseconds. This parameter should not be set below 100,
 * to avoid slowdown.
 */
public class ProgramsBlocker {
	ArrayList<String> programs;
	int timeout;
	String fileName;

	public ProgramsBlocker() {
		try {
			timeout = 1000;
			programs = new ArrayList<String>();
			blockPrograms();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public void blockPrograms() {
		// Get OS name
		String OS = System.getProperty("os.name").toLowerCase();
		// Identify correct blocking command for OS
		String command = "";
		if ((OS.indexOf("win") >= 0)) {
			command = "taskkill /f /im ";
			fileName = "C:\\Users\\Public\\lms_pc\\Logs\\pb.txt";
		} else if ((OS.indexOf("mac") >= 0) || (OS.indexOf("nux") >= 0)) {
			command = "killall ";
		} else {
			// Handle error when platform is not Windows, Mac, or Linux
			System.err.println("Sorry, but your OS doesn't support blocking.");
			System.exit(0);
		}
		gui.LogOperations lo = new gui.LogOperations();
		ArrayList<String> str = lo.readFromFile(fileName);
		for (String s : str) {
			programs.add(s);
		}
		if (programs.size() == 0) {
			System.exit(0);
		}
		// Start blocking!
		while (true) {
			// Cycle through programs list
			for (int i = 0; i < programs.size(); i++) {
				// Block program
				try {
					Runtime.getRuntime().exec(command + programs.get(i));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// Timeout
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) throws Exception {
		ProgramsBlocker pb = new ProgramsBlocker();
		pb.blockPrograms();
	}
}