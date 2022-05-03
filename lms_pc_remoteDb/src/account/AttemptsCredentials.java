package account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class AttemptsCredentials {
	Properties p;
	File file;
	FileReader fr;
	FileWriter fw;

	public AttemptsCredentials() {
		p = new Properties();
		file = new File("C:\\Users\\Public\\lms_pc\\Logs\\attempts.properties");
		boolean b = false;
		try {
			b = file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (b) {
			writeToFile();
			p.setProperty("attempts", "5");
			try {
				p.store(fw, "number of attempts");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setCredentials(String email, String password) {
		writeToFile();
		p.setProperty("attempts", "5");
		try {
			p.store(fw, "number of attempts");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeToFile() {
		try {
			fw = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void readFromFile() {
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			p.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setAttempts(int value) {
		writeToFile();
		String str = String.valueOf(value);
		p.setProperty("attempts", str);
		try {
			p.store(fw, "credentials");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getAttempts() {
		readFromFile();
		return Integer.parseInt(p.getProperty("attempts"));
	}
}
