package cli;

import java.io.*;

public class MainInstaller {
	public void installLms_pc() {
		try {
			FileInputStream fis, fisWin, fisWinPB, fisWinGUI, fisWinHosts;
			FileOutputStream fos, fosWin, fosWinPB, fosWinGUI, fosWinHosts;
			// Get OS name
			String OS = System.getProperty("os.name").toLowerCase();
			/*
			 * If file doesnot exist FileInputStream throws FileNotFoundException and read()
			 * write() throws IOException if I/O error occurs
			 */
			fis = new FileInputStream("lms_pc.jar");
			/*
			 * read() will readonly next int so we used while loop here in order to read
			 * upto end of file and keep writing the read int into dest file
			 */
			if ((OS.indexOf("win") >= 0)) {
				// create path for setup
				new File("C:\\Program Files\\lms_pc\\LMS").mkdirs();
				new File("C:\\Program Files\\lms_pc\\sb").mkdirs();
				new File("C:\\Users\\Public\\lms_pc\\Logs").mkdirs();
				new File("C:\\Users\\Public\\lms_pc\\Logs\\pics").mkdirs();
				fisWin = new FileInputStream("starter.bat");
				fisWinPB = new FileInputStream("start_pb.bat");
				fisWinGUI = new FileInputStream("lms_pc_GUI.bat");
				fisWinHosts = new FileInputStream("C:\\Windows\\System32\\drivers\\etc\\hosts");
				fos = new FileOutputStream("C:\\Program Files\\lms_pc\\LMS\\lms_pc.jar");
				fosWin = new FileOutputStream("C:\\Program Files\\lms_pc\\LMS\\starter.bat");
				fosWinPB = new FileOutputStream("C:\\Program Files\\lms_pc\\LMS\\start_pb.bat");
				fosWinGUI = new FileOutputStream("C:\\Program Files\\lms_pc\\LMS\\lms_pc_GUI.bat");
				fosWinHosts = new FileOutputStream("C:\\Program Files\\lms_pc\\sb\\hosts");
				int b;
				while ((b = fis.read()) != -1)
					fos.write(b);
				while ((b = fisWin.read()) != -1)
					fosWin.write(b);
				while ((b = fisWinPB.read()) != -1)
					fosWinPB.write(b);
				while ((b = fisWinGUI.read()) != -1)
					fosWinGUI.write(b);
				while ((b = fisWinHosts.read()) != -1)
					fosWinHosts.write(b);
				fisWin.close();
				fisWinPB.close();
				fisWinGUI.close();
				fisWinHosts.close();
				fosWin.close();
				fosWinPB.close();
				fosWinGUI.close();
				fosWinHosts.close();
				fos.close();
				ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "Admin.bat");
				Process p = pb.start();
			} else if ((OS.indexOf("mac") >= 0)) {
			} else if ((OS.indexOf("nux") >= 0)) {
			} else {
				// Handle error when platform is not Windows, Mac, or Linux
				System.err.println("Sorry, your os isn't supported");
				System.exit(0);
			}
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}