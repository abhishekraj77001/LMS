package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

class WFrame extends JFrame implements ActionListener {
	Container c;
	JLabel l_title, l_wel, l_next, l_note;
	JButton read, next, cancel;

	public WFrame() {
		c = this.getContentPane();
		c.setLayout(null);
		l_title = new JLabel("Login Monitor Software Installer");
		l_wel = new JLabel("Welcome");
		l_next = new JLabel("click next to proceed");
		l_note = new JLabel("Note:- Internet connection is required");
		read = new JButton("Read Doc");
		next = new JButton("Next");
		cancel = new JButton("Cancel");
		windowSetter();
		boundSetter();
		componentsAdder();
		eventSetter();
	}

	void boundSetter() {
		l_title.setBounds(10, 10, 200, 50);
		l_wel.setBounds(10, 100, 200, 25);
		l_next.setBounds(10, 150, 200, 25);
		l_note.setBounds(10, 200, 300, 25);
		read.setBounds(10, 400, 100, 50);
		next.setBounds(250, 400, 100, 50);
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
		c.add(l_wel);
		c.add(l_next);
		c.add(l_note);
		c.add(read);
		c.add(next);
		c.add(cancel);
	}

	void eventSetter() {
		read.addActionListener(this);
		next.addActionListener(this);
		cancel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == read) {
			WFrame.openWebpage("https://drive.google.com/open?id=13ZMBOXhSc28xKXlfPPshSQqoQKuUbl3E");
		} else if (e.getSource() == next) {
			new Loader();
			dispose();
		} else if (e.getSource() == cancel) {
			System.exit(1);
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
}

public class Installer {
	public static void main(String[] args) {
		WFrame frame = new WFrame();
	}
}