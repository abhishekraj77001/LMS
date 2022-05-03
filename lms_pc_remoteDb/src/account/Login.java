package account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class Login extends JFrame implements ActionListener {
	Container c;
	JLabel l_title, l_email, l_pass, l_forgot;
	JTextField email;
	JPasswordField pass;
	JButton back, login, cancel;
	String emailText, passText;

	public Login() {
		c = this.getContentPane();
		c.setLayout(null);
		l_title = new JLabel("Login");
		l_email = new JLabel("Enter your Email");
		l_pass = new JLabel("Enter Password");
		email = new JTextField();
		pass = new JPasswordField();
		l_forgot = new JLabel("<HTML><U>Forgot Password?</U></HTML>");
		l_forgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back = new JButton("Back");
		login = new JButton("Login");
		cancel = new JButton("Cancel");
		windowSetter();
		boundSetter();
		componentsAdder();
		eventSetter();
	}

	void boundSetter() {
		l_title.setBounds(10, 10, 200, 50);
		l_email.setBounds(10, 150, 200, 25);
		l_pass.setBounds(10, 200, 200, 25);
		l_forgot.setBounds(250, 250, 125, 50);
		email.setBounds(250, 150, 200, 25);
		pass.setBounds(250, 200, 200, 25);
		back.setBounds(100, 400, 100, 50);
		login.setBounds(225, 400, 100, 50);
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
		c.add(l_email);
		c.add(l_pass);
		c.add(l_forgot);
		c.add(email);
		c.add(pass);
		c.add(back);
		c.add(login);
		c.add(cancel);
	}

	void eventSetter() {
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				email.requestFocus();
			}
			public void windowActivated(WindowEvent e) {
				email.requestFocus();
			}
		});
		l_forgot.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new ResetPassword(email.getText());
				dispose();
			}
		});
		back.addActionListener(this);
		login.addActionListener(this);
		cancel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			new Welcome();
			dispose();
		} else if (e.getSource() == login) {
			emailText = email.getText().toLowerCase();
			passText = new String(pass.getPassword());
			int i;
			i = verify();
			if (i == 1) {
				JOptionPane.showMessageDialog(this, "Invalid: Empty field(s)..Please fill all the fields");
			} else if (i == 2) {
				JOptionPane.showMessageDialog(this, "Invalid: Email is invalid!");
			} else {
				db.VerifyExistence ve = new db.VerifyExistence();
				int result = ve.doesExists("id", "id_table", emailText, passText);
				if (result == 1) {
					JOptionPane.showMessageDialog(this,
							"Entered Password didn't match with the password " + "in database");
				} else if (result == 2) {
					JOptionPane.showMessageDialog(this,
							"Entered email doesn't exists in database.." + "Please try again");
				} else {
					this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
					if (isEmailVerified()) {
						new account.Credentials().setCredentials(emailText, passText);
						new AttemptsCredentials().setAttempts(5);
//						System.out.println(new account.Credentials().getEmail());
						JOptionPane.showMessageDialog(this, "Login successful!");
						dispose();
					}
				}
			}
		} else if (e.getSource() == cancel) {
			System.exit(3);
		}
	}

	// modify here
	int verify() {
		if (isFieldBlank()) {
			return 1;
		}
		if (!isEmailValid(emailText)) {
			return 2;
		}
		return 0;
	}

	boolean isFieldBlank() {
		if (emailText.isEmpty() || passText.isEmpty()) {
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

	boolean isEmailVerified() {
		int generatedOTP, enteredOTP;
		generatedOTP = new otp.OTPGenerator().generateOTP();
		System.out.println(generatedOTP);
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
			System.out.println(e);
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		Login l = new Login();
	}
}
