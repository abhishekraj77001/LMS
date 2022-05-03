package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class SettingsGUI extends JFrame implements ActionListener {
	Container c;
	JTabbedPane tp;
	JButton apply, cancel, reset;
	account.Credentials sp;

	public SettingsGUI() {
		c = this.getContentPane();
		c.setLayout(null);
		tp = new JTabbedPane();
		apply = new JButton("Apply");
		cancel = new JButton("Cancel");
		reset = new JButton("Reset Settings");
		apply.setEnabled(false);
		cancel.setEnabled(false);
		sp = new account.Credentials();
		windowSetter();
		boundSetter();
		componentsAdder();
		eventSetter();
	}

	void boundSetter() {
		tp.setSize(625, 500);
		reset.setBounds(50, 525, 150, 75);
		apply.setBounds(250, 525, 150, 75);
		cancel.setBounds(450, 525, 150, 75);
	}

	void windowSetter() {
		setTitle("Settings");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 650, 650);
		setResizable(false);
	}

	void componentsAdder() {
		c.add(tp);
		c.add(reset);
		c.add(apply);
		c.add(cancel);
		tp.add("General", new P_general(this, sp));
		tp.add("Email", new P_email(this, sp));
		tp.add("Webcam", new P_webcam(this, sp));
		boolean b = new account.Credentials().isWebCam();
		if (b) {
			tp.setEnabledAt(2, true);
		} else {
			tp.setEnabledAt(2, false);
		}
	}

	void eventSetter() {
		reset.addActionListener(this);
		apply.addActionListener(this);
		cancel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == reset) {
			int op = JOptionPane.showConfirmDialog(this, "Are you sure to Reset Settings?", "Confirmation Message",
					JOptionPane.YES_NO_OPTION);
			if (op == JOptionPane.YES_OPTION) {
				sp.resetSettings();
				JOptionPane.showMessageDialog(this, "Settings have been reset");
				dispose();
				new SettingsGUI();
			}
		} else if (e.getSource() == apply) {
			if (P_general.cb_pb.isSelected()) {
				new blocker.StartProgramsBlocker().startPB();
				sp.setPBKey(true);
			} else {
				new blocker.StopProgramsBlocker().stopPB();
				sp.setPBKey(false);
			}
			if (P_general.cb_del.isSelected()) {
				String table_name = new account.Credentials().getModifiedEmail();
				String command = "delete from " + table_name;
				new db.DbCommand().executeDmlCommand("login", command);
			}
			if (P_email.cb_email.isSelected()) {
				sp.setEmailUsable(true);
				;
			} else {
				sp.setEmailUsable(false);
			}
			if (P_webcam.cb_webcam.isSelected()) {
				sp.setWebCamUsable(true);
			} else {
				sp.setWebCamUsable(false);
			}
			apply.setEnabled(false);
			cancel.setEnabled(false);
			JOptionPane.showMessageDialog(this, "Settings have been applied");
		} else if (e.getSource() == cancel) {
			dispose();
		}
	}

	public static void main(String[] args) {
		SettingsGUI d = new SettingsGUI();
	}
}

class P_general extends JPanel {
	SettingsGUI obj;
	public static JCheckBox cb_pb, cb_del;

	P_general(SettingsGUI obj, account.Credentials sp) {
		this.obj = obj;
		setLayout(null);
		cb_pb = new JCheckBox("Turn on program blocker");
		cb_pb.setSelected(sp.getPBKey());
		cb_del = new JCheckBox("Delete login history");
		cb_del.setSelected(false);
		cb_pb.setBounds(25, 25, 300, 100);
		cb_del.setBounds(25, 100, 300, 100);
		add(cb_pb);
		add(cb_del);
		eventSetter();
	}

	void eventSetter() {
		cb_pb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				obj.apply.setEnabled(true);
				obj.cancel.setEnabled(true);
			}
		});
		cb_del.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				obj.apply.setEnabled(true);
				obj.cancel.setEnabled(true);
			}
		});
	}
}

class P_email extends JPanel {
	SettingsGUI obj;
	public static JCheckBox cb_email;

	P_email(SettingsGUI obj, account.Credentials sp) {
		this.obj = obj;
		setLayout(null);
		cb_email = new JCheckBox("Turn on email facility");
		cb_email.setSelected(sp.getEmailStatus());
		cb_email.setBounds(25, 25, 200, 100);
		add(cb_email);
		eventSetter();
	}

	void eventSetter() {
		cb_email.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				obj.apply.setEnabled(true);
				obj.cancel.setEnabled(true);
			}
		});
	}
}

class P_webcam extends JPanel {
	SettingsGUI obj;
	public static JCheckBox cb_webcam;

	P_webcam(SettingsGUI obj, account.Credentials sp) {
		this.obj = obj;
		setLayout(null);
		cb_webcam = new JCheckBox("Turn on Webcam facility");
		cb_webcam.setSelected(sp.canUseWebCam());
		cb_webcam.setBounds(25, 25, 200, 100);
		add(cb_webcam);
		eventSetter();
	}

	void eventSetter() {
		cb_webcam.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				obj.apply.setEnabled(true);
				obj.cancel.setEnabled(true);
			}
		});
	}
}