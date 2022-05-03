package blocker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ResetSB {
	gui.LogOperations lo;

	public ResetSB() {
		File f = new File("C:\\Users\\Public\\lms_pc\\Logs\\sb.txt");
		try {
			FileWriter fw = new FileWriter(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new blocker.SiteBlocker("");
		System.exit(1);
	}

	public static void main(String[] args) {
		ResetSB rsb = new ResetSB();
	}
}
