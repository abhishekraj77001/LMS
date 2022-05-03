package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

class PassFrame extends JFrame implements ActionListener {
	Container c;
	JLabel l_pass, l_forgot;
	JPasswordField pass;
	JButton ok, cancel;
	String enteredPass, originalPass;
	account.Credentials ac;
	account.AttemptsCredentials ac_attempts;
	int attempts;

	public PassFrame() {
		c = this.getContentPane();
		c.setLayout(null);
		l_pass = new JLabel("Enter Password:");
		l_forgot = new JLabel("<HTML><U>Forgot Password?</U></HTML>");
		l_forgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pass = new JPasswordField();
		ok = new JButton("Ok");
		cancel = new JButton("Cancel");
		ac = new account.Credentials();
		originalPass = ac.getPassword();
		ac_attempts = new account.AttemptsCredentials();
		attempts = ac_attempts.getAttempts();
		if (attempts <= 0) {
			JOptionPane.showMessageDialog(this,
					"Max password entry attempts allowed over!! " + "Please restart your pc to try again",
					"Login failed", JOptionPane.WARNING_MESSAGE);
			System.exit(2);
		}
		windowSetter();
		boundSetter();
		componentsAdder();
		eventSetter();
	}

	void boundSetter() {
		l_pass.setBounds(20, 10, 200, 25);
		l_forgot.setBounds(30, 50, 125, 50);
		pass.setBounds(150, 10, 250, 25);
		ok.setBounds(200, 50, 100, 40);
		cancel.setBounds(320, 50, 100, 40);
	}

	void windowSetter() {
		setTitle("Login Monitor Software");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 450, 150);
		setResizable(false);
	}

	void componentsAdder() {
		c.add(l_pass);
		c.add(l_forgot);
		c.add(pass);
		c.add(ok);
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
		l_forgot.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String email = "";
				new account.ResetPassword(email);
				dispose();
			}
		});
		ok.addActionListener(this);
		cancel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			attempts--;
			ac_attempts.setAttempts(attempts);
			enteredPass = new String(pass.getPassword());
			if (enteredPass.equals(originalPass)) {
				ac_attempts.setAttempts(5);
				new MainWin();
				dispose();
			} else if (attempts == 0) {
				boolean b1 = ac.isWebCam();
				boolean b2 = ac.canUseWebCam();
				if (b1 == true && b2 == true) {
					String dt;
					dt = new date.CurrentDateTime().getCurrentDateTime();
					new webcam.TakePicture(new File("C:\\Users\\Public\\lms_pc\\Logs\\pics\\" + dt + ".png"));
				}
				System.exit(1);
			} else {
				JOptionPane.showMessageDialog(this, "Invalid Password!! Attempts remaining=" + attempts);
				pass.setText("");
			}
		} else if (e.getSource() == cancel) {
			System.exit(2);
		}
	}
}

public class PassWindow extends JFrame {
	public PassWindow() {
		Boolean b;
		b = new account.Credentials().isFresh();
		if (b == true) {
			boolean isConnected = new net.InternetConnection().isConnected();
			if (isConnected) {
				new account.Welcome();
			} else {
				System.exit(1);
			}
		} else if (b == false) {
			PassFrame pf = new PassFrame();
		}
	}

	public static void main(String[] args) {
		PassWindow pw = new PassWindow();
	}
}