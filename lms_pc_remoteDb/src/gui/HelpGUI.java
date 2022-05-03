package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HelpGUI extends JFrame implements ActionListener {
	Container c;
	JButton read, feedback;

	public HelpGUI() {
		c = this.getContentPane();
		c.setLayout(null);
		read = new JButton("Read Documentation");
		feedback = new JButton("Send Us Feedback/Ask");
		windowSetter();
		boundSetter();
		componentsAdder();
		eventSetter();
	}

	void boundSetter() {
		read.setBounds(25, 200, 200, 100);
		feedback.setBounds(250, 200, 200, 100);
	}

	void windowSetter() {
		setTitle("HELP");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 500, 500);
		setResizable(false);
	}

	void componentsAdder() {
		c.add(read);
		c.add(feedback);
	}

	void eventSetter() {
		read.addActionListener(this);
		feedback.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == read) {
			HelpGUI.openWebpage("https://drive.google.com/open?id=13ZMBOXhSc28xKXlfPPshSQqoQKuUbl3E");
//			dispose();
		} else if (e.getSource() == feedback) {
			JOptionPane.showMessageDialog(this, "Email us at lms.pc.software@gmail.com");
			dispose();
		}
	}
	
	public static boolean openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}
	
	public static boolean openWebpage(String str) {
	    try {
	    	URL url=new URL(str);
	        return openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return false;
	}

	public static void main(String[] args) {
		HelpGUI d = new HelpGUI();
	}
}