package account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreatePass extends JFrame implements ActionListener {
	Container c;
	JLabel l_pass, l_cpass;
	JPasswordField pass, cpass;
	JButton next, cancel;
	String passText, cpassText, emailText;

	public CreatePass(String email) {
		emailText = email;
		c = this.getContentPane();
		c.setLayout(null);
		l_pass = new JLabel("Create Password");
		l_cpass = new JLabel("Confirm Password");
		pass = new JPasswordField();
		cpass = new JPasswordField();
		next = new JButton("Next");
		cancel = new JButton("Cancel");
		windowSetter();
		boundSetter();
		componentsAdder();
		eventSetter();
	}

	void boundSetter() {
		l_pass.setBounds(10, 200, 200, 25);
		l_cpass.setBounds(10, 250, 200, 25);
		pass.setBounds(250, 200, 200, 25);
		cpass.setBounds(250, 250, 200, 25);
		next.setBounds(225, 400, 100, 50);
		cancel.setBounds(350, 400, 100, 50);
	}

	void windowSetter() {
		setTitle("Login Monitor Software");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 500, 500);
		setResizable(false);
	}

	void componentsAdder() {
		c.add(l_pass);
		c.add(l_cpass);
		c.add(pass);
		c.add(cpass);
		c.add(next);
		c.add(cancel);
	}

	void eventSetter() {
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				pass.requestFocus();
			}
			public void windowActivated(WindowEvent e) {
				pass.requestFocus();
			}
		});
		next.addActionListener(this);
		cancel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
			passText = new String(pass.getPassword());
			cpassText = new String(cpass.getPassword());
			int i;
			i = verify();
			if (i == 1) {
				JOptionPane.showMessageDialog(this, "Invalid: Empty field(s)..Please fill all the fields");
			} else if (i == 2) {
				JOptionPane.showMessageDialog(this, "Invalid: Passwords didn't match!");
			} else {
				int count = new db.DbCommand().executeDmlCommand("id",
						"update id_table set password=" + "'" + passText + "' where email='" + emailText + "'");
				if (count == 1) {
					JOptionPane.showMessageDialog(this, "Password created successfully!! Please login to complete"
							+ " the process and save the changes");
					dispose();
					new Login();
				}
			}
		} else if (e.getSource() == cancel) {
			System.exit(2);
		}
	}

	int verify() {
		if (isFieldBlank()) {
			return 1;
		}
		if (!isPassValid(passText, cpassText)) {
			return 2;
		}
		return 0;
	}

	boolean isFieldBlank() {
		if (passText.isEmpty() || cpassText.isEmpty()) {
			return true;
		}
		return false;
	}

	boolean isPassValid(String p, String cp) {
		if (p.contentEquals(cp)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		CreatePass c = new CreatePass("prashantkumar67625@gmail.com");
	}
}
