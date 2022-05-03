package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Loader {
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("Test");
		ImageIcon loading = new ImageIcon("loader.gif");
		frame.add(new JLabel(loading, JLabel.CENTER));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setVisible(true);
	}
}
