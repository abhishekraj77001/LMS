package net;

import java.net.URL;
import java.net.URLConnection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InternetConnection extends JFrame {
	public boolean isConnected() {
		boolean b = false;
		try {
			URL url = new URL("https://www.google.com");
			URLConnection connection = url.openConnection();
			connection.connect();
			System.out.println("Connection Successful");
			b = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Please check your internet connection");
			b = false;
		} finally {
			return b;
		}
	}
}