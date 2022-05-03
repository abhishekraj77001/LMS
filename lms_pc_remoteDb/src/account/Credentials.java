package account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Credentials {
	Properties p;
	File file;
	FileReader fr;
	FileWriter fw;

	public Credentials() {
		p = new Properties();
		file = new File("C:\\Users\\Public\\lms_pc\\Logs\\credentials.properties");
		boolean b = false;
		try {
			b = file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (b) {
//			System.out.println("File has been created.");
			writeToFile();
			p.setProperty("fresh", "true");
			try {
				p.store(fw, "credentials");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
//			System.out.println("File already exists.");
		}
	}

	public void setCredentials(String email, String password) {
		writeToFile();
		p.setProperty("email", email);
		p.setProperty("password", password);
		p.setProperty("fresh", "false");
		boolean b = new webcam.DetectWebCam().webCamExists();
		String str = String.valueOf(b);
		p.setProperty("webCam", str);
		p.setProperty("useWebCam", str);
		p.setProperty("useEmail", "true");
		p.setProperty("pb_key", "true");
		try {
			p.store(fw, "credentials");
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

	public void setEmail(String email) {
		writeToFile();
		p.setProperty("email", email);
		try {
			p.store(fw, "credentials");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getEmail() {
		readFromFile();
		return p.getProperty("email");
	}

	public void setPassword(String password) {
		writeToFile();
		p.setProperty("password", password);
		try {
			p.store(fw, "credentials");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPassword() {
		readFromFile();
		return p.getProperty("password");
	}

	public void setFresh(boolean b) {
		writeToFile();
		String str = String.valueOf(b);
		p.setProperty("fresh", str);
		try {
			p.store(fw, "credentials");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isFresh() {
		readFromFile();
		return Boolean.parseBoolean(p.getProperty("fresh"));
	}

	public void setWebCam(boolean b) {
		writeToFile();
		String str = String.valueOf(b);
		p.setProperty("webCam", str);
		try {
			p.store(fw, "credentials");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isWebCam() {
		readFromFile();
		return Boolean.parseBoolean(p.getProperty("webCam"));
	}

	public void setWebCamUsable(boolean b) {
		writeToFile();
		String str = String.valueOf(b);
		p.setProperty("useWebCam", str);
		try {
			p.store(fw, "credentials");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean canUseWebCam() {
		readFromFile();
		return Boolean.parseBoolean(p.getProperty("useWebCam"));
	}

	public void setEmailUsable(boolean b) {
		writeToFile();
		String str = String.valueOf(b);
		p.setProperty("useEmail", str);
		try {
			p.store(fw, "credentials");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean getEmailStatus() {
		readFromFile();
		return Boolean.parseBoolean(p.getProperty("useEmail"));
	}

	public void setPBKey(boolean b) {
		writeToFile();
		String str = String.valueOf(b);
		p.setProperty("pb_key", str);
		try {
			p.store(fw, "credentials");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean getPBKey() {
		readFromFile();
		return Boolean.parseBoolean(p.getProperty("pb_key"));
	}

	public void clearCredentials() {
		// p.clear();
		writeToFile();
		p.remove("email");
		p.remove("password");
//		p.remove("fresh");
		p.setProperty("fresh", "true");
		p.remove("webCam");
		p.remove("useWebCam");
		p.remove("useEmail");
		p.remove("pb_key");
//		p.clear();
		try {
			p.store(fw, "credentials");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getModifiedEmail() {
		String mEmail = getEmail();
		mEmail = mEmail.toLowerCase();
		mEmail = removeSpecialChars(mEmail);
		return mEmail;
	}

	static String removeSpecialChars(String email) {
		String[] arrOfStr = email.split("[@.]");
		StringBuilder modified = new StringBuilder();
		for (String a : arrOfStr) {
			System.out.println(a);
			modified.append(a);
		}
		return new String(modified);
	}

	public void resetSettings() {
		writeToFile();
		boolean b = new webcam.DetectWebCam().webCamExists();
		String str = String.valueOf(b);
		p.setProperty("webCam", str);
		p.setProperty("useWebCam", str);
		p.setProperty("useEmail", "true");

		p.setProperty("pb_key", "true");
		try {
			p.store(fw, "credentials");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Credentials c = new Credentials();
		c.clearCredentials();

//		c.setCredentials("prashantkumar67625@gmail.com", "Pacific");
		// c.setFresh(false);
		// c.setFresh(true);
		// c.setWebCam(new webcam.DetectWebCam().webCamExists());
		// c.setWebCam(true);
		// c.setWebCam(false);
		// c.setWebCamUsable(true);
		// c.setWebCamUsable(false);
		// c.setEmailUsable(true);
		// c.setEmailUsable(false);

		System.out.println("email=" + c.getEmail());
//		System.out.println("Modified email="+c.getModifiedEmail());
		System.out.println("password=" + c.getPassword());
		System.out.println("isFresh=" + c.isFresh());
		System.out.println("isWebCam=" + c.isWebCam());
		System.out.println("canUseWebcam=" + c.canUseWebCam());
		System.out.println("EmailStatus=" + c.getEmailStatus());
		System.out.println("pb_key=" + c.getPBKey());
	}
}
