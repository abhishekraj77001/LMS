package blocker;

import java.io.*;

public class StopProgramsBlocker {
	private String[] command;
	Process p;

	public StopProgramsBlocker() {
		stopPB();
	}

	public void stopPB() {
		// Get OS name
		String OS = System.getProperty("os.name").toLowerCase();
		if ((OS.indexOf("win") >= 0)) {
			command = new String[] { "cmd.exe", "/c",
					"for /f \"tokens=1\" %i in ('jps -m ^| find \"ProgramsBlocker\"') do ( taskkill /F /PID %i )" };
		} else if ((OS.indexOf("mac") >= 0)) {

		} else if ((OS.indexOf("nux") >= 0)) {
		} else {
			// Handle error when platform is not Windows, Mac, or Linux
			System.err.println("Sorry, but your OS doesn't support blocking.");
			System.exit(0);
		}
		try {
			p = Runtime.getRuntime().exec(command);
			System.out.println(p.waitFor());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		StopProgramsBlocker r = new StopProgramsBlocker();
		r.stopPB();
	}
}
