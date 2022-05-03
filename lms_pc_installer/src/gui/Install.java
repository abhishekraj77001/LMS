package gui;

import javax.swing.*;
import cli.MainInstaller;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Install extends JFrame implements ActionListener {
	Container c;
	JLabel l_install;
	JProgressBar pb;
	Timer t;
	int i;

	public Install() {
		c = this.getContentPane();
		c.setLayout(null);
		l_install = new JLabel("Installing..");
		pb = new JProgressBar(0, 100);
		pb.setStringPainted(true);
		i = 0;
		t = new Timer(400, this);
		new cli.MainInstaller().installLms_pc();
		t.start();
		windowSetter();
		boundSetter();
		componentsAdder();
		eventSetter();
	}

	void boundSetter() {
		l_install.setBounds(20, 150, 200, 25);
		pb.setBounds(20, 250, 450, 50);
	}

	void windowSetter() {
		setTitle("Login Monitor Software");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 500, 500);
		setResizable(false);
	}

	void componentsAdder() {
		c.add(l_install);
		c.add(pb);
	}

	void eventSetter() {
		t.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == t) {
			if (i == 100) {
				t.stop();
				JOptionPane.showMessageDialog(this, "Installation successful!");
				try {
					ProcessBuilder pb = new ProcessBuilder("cmd", "/c",
							"C:\\Program Files\\lms_pc\\LMS\\lms_pc_gui.bat");
					Process p = pb.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			} else {
				pb.setValue(++i);
			}
		}
	}
}