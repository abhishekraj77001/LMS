package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SystemInfoGUI extends JFrame {
	JTextArea jt;
	JScrollPane scrollPane;

	public SystemInfoGUI() {
		jt = new JTextArea(new String(new sys.WinSysInfo().getInfo()));
		jt.setEditable(false);
		scrollPane = new JScrollPane(jt);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane);
		windowSetter();
		makeFrameFullSize(this);
	}

	private void makeFrameFullSize(JFrame aFrame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		aFrame.setSize(screenSize.width, screenSize.height);
//	    aFrame.setSize(screenSize.width/2, screenSize.height/2);
	}

	void windowSetter() {
		setTitle("Login Monitor Software");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
	}

	public static void main(String[] args) {
		SystemInfoGUI w = new SystemInfoGUI();
	}
}