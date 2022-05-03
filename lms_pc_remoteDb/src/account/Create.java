package account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class Create extends JFrame implements ActionListener {
	Container c;
	JLabel l_title, l_name, l_email, l_pass, l_cpass;
	JTextField name, email;
	JPasswordField pass, cpass;
	JButton back, next, cancel;
	String nameText, emailText, passText, cpassText;

	public Create() {
		c = this.getContentPane();
		c.setLayout(null);
		l_title = new JLabel("Create your account");
		l_name = new JLabel("Name");
		l_email = new JLabel("Email");
		l_pass = new JLabel("Create Password");
		l_cpass = new JLabel("Confirm Password");
		name = new JTextField();
		email = new JTextField();
		pass = new JPasswordField();
		cpass = new JPasswordField();
		back = new JButton("Back");
		next = new JButton("Next");
		cancel = new JButton("Cancel");
		windowSetter();
		boundSetter();
		componentsAdder();
		eventSetter();
	}

	void boundSetter() {
		l_title.setBounds(10, 10, 200, 50);
		l_name.setBounds(10, 100, 200, 25);
		l_email.setBounds(10, 150, 200, 25);
		l_pass.setBounds(10, 200, 200, 25);
		l_cpass.setBounds(10, 250, 200, 25);
		name.setBounds(250, 100, 200, 25);
		email.setBounds(250, 150, 200, 25);
		pass.setBounds(250, 200, 200, 25);
		cpass.setBounds(250, 250, 200, 25);
		back.setBounds(100, 400, 100, 50);
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
		c.add(l_title);
		c.add(l_name);
		c.add(l_email);
		c.add(l_pass);
		c.add(l_cpass);
		c.add(name);
		c.add(email);
		c.add(pass);
		c.add(cpass);
		c.add(back);
		c.add(next);
		c.add(cancel);
	}

	void eventSetter() {
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				name.requestFocus();
			}
			public void windowActivated(WindowEvent e) {
				name.requestFocus();
			}
		});
		back.addActionListener(this);
		next.addActionListener(this);
		cancel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			new Welcome();
			dispose();
		} else if (e.getSource() == next) {
			nameText = name.getText();
			emailText = email.getText().toLowerCase();
			passText = new String(pass.getPassword());
			cpassText = new String(cpass.getPassword());
			int i;
			i = verify();
			if (i == 1) {
				JOptionPane.showMessageDialog(this, "Invalid: Empty field(s)..Please fill all the fields");
			} else if (i == 2) {
				JOptionPane.showMessageDialog(this, "Invalid: Email is invalid!");
			} else if (i == 3) {
				JOptionPane.showMessageDialog(this, "Invalid: Passwords didn't match!");
			} else {
				db.VerifyExistence ve = new db.VerifyExistence();
				int result = ve.doesExists("id", "id_table", emailText, passText);
				if (result == 0 || result == 1) {
					JOptionPane.showMessageDialog(this, "Sorry.. Entered email already exists in database."
							+ "Please login with that email or create account with some other email");
				} else {
					this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
					if (isEmailVerified()) {
						new db.IdTable(emailText, passText, name.getText());
						new db.CreateNew(emailText);
						new Credentials().setCredentials(emailText, passText);
						new AttemptsCredentials().setAttempts(5);

						JOptionPane.showMessageDialog(this, "Account creation successful!");
						dispose();
					}
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
		if (!isEmailValid(emailText)) {
			return 2;
		}
		if (!isPassValid(passText, cpassText)) {
			return 3;
		}
		return 0;
	}

	boolean isFieldBlank() {
		if (nameText.isEmpty() || emailText.isEmpty() || passText.isEmpty() || cpassText.isEmpty()) {
			return true;
		}
		return false;
	}

	boolean isEmailValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	boolean isPassValid(String p, String cp) {
		if (p.contentEquals(cp)) {
			return true;
		}
		return false;
	}

	boolean isEmailVerified() {
		int generatedOTP, enteredOTP;
		generatedOTP = new otp.OTPGenerator().generateOTP();
		new otp.MailOTP(emailText, generatedOTP);
		String input = JOptionPane.showInputDialog(this, "Enter OTP sent to your mail:");
		try {
			if (input != null) {
				enteredOTP = Integer.parseInt(input);
				if (generatedOTP == enteredOTP) {
					return true;
				} else {
					JOptionPane.showMessageDialog(this, "Entered OTP invalid!! Please check and try again");
					this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		Create c = new Create();
	}
}
