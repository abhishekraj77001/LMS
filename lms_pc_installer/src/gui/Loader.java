package gui;

import java.awt.Color;
import java.awt.Container;
import java.util.concurrent.ExecutionException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class Loader extends JFrame {
	JLabel jl;

	public Loader() {
		ImageIcon loading;
		try {
			loading = new ImageIcon("loader.gif");
			jl = new JLabel(loading, JLabel.CENTER);
			add(jl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Login Monitor Software Installer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 500, 500);
		setVisible(true);
		setResizable(false);
		SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				// Simulate doing something useful.
				new Install();
				// Here we can return some object of whatever type
				// we specified for the first template parameter.
				// (in this case we're auto-boxing 'true').
				return true;
			}

			// Can safely update the GUI from this method.
			protected void done() {
				boolean status;
				try {
					// Retrieve the return value of doInBackground.
					status = get();
					if (status == true) {
						disposeLoader();
					}
				} catch (InterruptedException e) {
					// This is thrown if the thread's interrupted.
					e.printStackTrace();
				} catch (ExecutionException e) {
					// This is thrown if we throw an exception
					// from doInBackground.
					e.printStackTrace();
				}
			}
		};
		worker.execute();
	}

	public void disposeLoader() {
		dispose();
	}

	public static void main(String[] args) {
		Loader ld = new Loader();
	}
}