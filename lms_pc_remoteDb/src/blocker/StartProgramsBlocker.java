package blocker;

import java.io.*;

public class StartProgramsBlocker {
	private String command;
	Process p;

	public StartProgramsBlocker() {
		startPB();
	}

	public void startPB() {
		// Get OS name
		String OS = System.getProperty("os.name").toLowerCase();
		if ((OS.indexOf("win") >= 0)) {
//			when run from ide
//			command="java -cp .\\bin blocker.ProgramsBlocker";
//			when run from jar file
			command = "java -cp lms_pc.jar blocker.ProgramsBlocker";
		} else if ((OS.indexOf("mac") >= 0)) {

		} else if ((OS.indexOf("nux") >= 0)) {
		} else {
			// Handle error when platform is not Windows, Mac, or Linux
			System.err.println("Sorry, but your OS doesn't support blocking.");
			System.exit(0);
		}
		try {
			p = Runtime.getRuntime().exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		StartProgramsBlocker r = new StartProgramsBlocker();
		r.startPB();
	}
}
