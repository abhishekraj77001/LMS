package account;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class ResetPassword extends JFrame implements ActionListener {
	Container c;
	JLabel l_email;
	JTextField email;
	JButton ok, cancel;

	public ResetPassword(String passedEmail) {
		c = this.getContentPane();
		c.setLayout(null);
		l_email = new JLabel("Email:");
		email = new JTextField(passedEmail);
		ok = new JButton("Ok");
		cancel = new JButton("Cancel");
		windowSetter();
		boundSetter();
		componentsAdder();
		eventSetter();
	}

	void boundSetter() {
		l_email.setBounds(20, 10, 200, 25);
		email.setBounds(150, 10, 250, 25);
		ok.setBounds(200, 50, 100, 40);
		cancel.setBounds(320, 50, 100, 40);
	}

	void windowSetter() {
		setTitle("Login Monitor Software");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 300, 450, 150);
		setResizable(false);
	}

	void componentsAdder() {
		c.add(l_email);
		c.add(email);
		c.add(ok);
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
		ok.addActionListener(this);
		cancel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			db.VerifyExistence ve = new db.VerifyExistence();
			int result = ve.doesExists("id", "id_table", email.getText(), "");
			if (result == 2) {
				JOptionPane.showMessageDialog(this, "Entered email doesn't exists in database.." + "Please try again");
			} else if (result == 0 || result == 1) {
//				JOptionPane.showMessageDialog(this, "Entered email does exists in database..");
				this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				if (isEmailVerified()) {
					new CreatePass(email.getText());
					dispose();
				}
			}
		} else if (e.getSource() == cancel) {
			dispose();
		}
	}

	boolean isEmailVerified() {
		int generatedOTP, enteredOTP;
		generatedOTP = new otp.OTPGenerator().generateOTP();
		System.out.println(generatedOTP);
		new otp.MailOTP(email.getText(), generatedOTP);
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
}
