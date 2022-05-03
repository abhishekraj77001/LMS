package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AccountGUI extends JFrame implements ActionListener {
	Container c;
	JButton reset, change;
	JLabel l_email, l_user_email;
	Font f1, f2;

	public AccountGUI() {
		c = this.getContentPane();
		c.setLayout(null);
		reset = new JButton("Reset Account");
		change = new JButton("Change Email");
		f1 = new Font("Arial", Font.BOLD, 15);
		l_email = new JLabel("Email :- ");
		l_email.setFont(f1);
		f2 = new Font("Segoe Script", Font.BOLD, 15);
		l_user_email = new JLabel(new account.Credentials().getEmail());
		l_user_email.setFont(f2);
		windowSetter();
		boundSetter();
		componentsAdder();
		eventSetter();
	}

	void boundSetter() {
		change.setBounds(25, 200, 125, 50);
		reset.setBounds(350, 200, 125, 50);
		l_email.setBounds(25, 50, 125, 50);
		l_user_email.setBounds(100, 50, 300, 50);
	}

	void windowSetter() {
		setTitle("change Monitor Software");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 525, 300);
		setResizable(false);
	}

	void componentsAdder() {
		c.add(reset);
		c.add(change);
		c.add(l_email);
		c.add(l_user_email);
	}

	void eventSetter() {
		reset.addActionListener(this);
		change.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == reset) {
			int output = JOptionPane.showConfirmDialog(this,
					"The program will reset!! Do you really " + "want to reset?", "Warning!!",
					JOptionPane.YES_NO_OPTION);
			if (output == JOptionPane.YES_OPTION) {
				new account.ResetProgram();
				dispose();
			}
		} else if (e.getSource() == change) {
			new ChangeEmail().setVisible(true);
			dispose();
		}
	}

	public static void main(String[] args) {
		AccountGUI d = new AccountGUI();
	}
}