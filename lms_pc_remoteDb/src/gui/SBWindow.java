package gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class SBFrame extends JFrame implements ActionListener, ListSelectionListener {
	Container c;
	JLabel l_sb, l_blockList;
	JPanel p1;
	JButton block, unblock, unblockAll, apply, cancel;
	JScrollPane sp;
	JList<String> list;
	DefaultListModel<String> l1;
	String fileName;
	LogOperations lo;

	public SBFrame() {
		c = this.getContentPane();
		c.setLayout(null);
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBounds(0, 0, 800, 50);
		p1.setBackground(Color.blue);
		l_sb = new JLabel("SITE BLOCKER");
		l_sb.setBounds(270, 5, 300, 20);
		l_sb.setFont(new Font("Courier New", Font.BOLD, 25));
		p1.add(l_sb);
		l_blockList = new JLabel("Block List");
		l_blockList.setFont(new Font("Courier New", Font.BOLD, 20));
		l1 = new DefaultListModel<>(); // list
		fileName = "C:\\Users\\Public\\lms_pc\\Logs\\sb.txt";
		lo = new LogOperations();
		ArrayList<String> str = lo.readFromFile(fileName);
		for (String s : str) {
			l1.addElement(s);
		}
		list = new JList<>(l1);
		list.setBounds(100, 100, 250, 300);
		sp = new JScrollPane(list); // scrollpane
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setBounds(100, 100, 250, 300);
		c.add(sp);
		block = new JButton("Add to Block List");
		unblock = new JButton("UnBlock");
		unblockAll = new JButton("UnBlock All");
		apply = new JButton("Apply");
		cancel = new JButton("Cancel");
		unblock.setEnabled(false);
		apply.setEnabled(false);
		cancel.setEnabled(false);
		windowSetter();
		boundSetter();
		componentsAdder();
		eventSetter();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == block) {
			String input = JOptionPane.showInputDialog(this, "Enter the Website Address:\nExample: www.example.com");
			if (input != null && !input.isEmpty()) {
				l1.addElement(input);
				apply.setEnabled(true);
				cancel.setEnabled(true);
				block.setEnabled(true);
			}
		} else if (e.getSource() == unblock) {
			l1.removeElementAt(list.getSelectedIndex());
			apply.setEnabled(true);
			cancel.setEnabled(true);
			block.setEnabled(true);
		} else if (e.getSource() == unblockAll) {
			l1.clear();
			apply.setEnabled(true);
			cancel.setEnabled(true);
			block.setEnabled(true);
		} else if (e.getSource() == apply) {
			String[] urls = new String[l1.getSize()];
			for (int i = 0; i < l1.getSize(); i++) {
				urls[i] = l1.getElementAt(i);
			}
			new blocker.SiteBlocker(urls);
			lo.writeToFile(fileName, urls);
			JOptionPane.showMessageDialog(this, "done!");
			dispose();
		} else if (e.getSource() == cancel) {
			dispose();
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == list) {
			unblock.setEnabled(true);
			block.setEnabled(false);
		}
	}

	void boundSetter() {
		block.setBounds(500, 100, 200, 70);
		l_blockList.setBounds(100, 50, 200, 50);
		unblock.setBounds(500, 200, 200, 70);
		unblockAll.setBounds(500, 300, 200, 70);
		apply.setBounds(150, 430, 200, 70);
		cancel.setBounds(450, 430, 200, 70);
	}

	void windowSetter() {
		setTitle("Login Monitor Software");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 70, 800, 600);
		setResizable(false);
	}

	void componentsAdder() {
		c.add(p1);
		c.add(l_blockList);
		c.add(block);
		c.add(unblock);
		c.add(unblockAll);
		c.add(apply);
		c.add(cancel);
	}

	void eventSetter() {
		block.addActionListener(this);
		unblock.addActionListener(this);
		unblockAll.addActionListener(this);
		apply.addActionListener(this);
		cancel.addActionListener(this);
		list.addListSelectionListener(this);
	}
}

public class SBWindow {
	public static void main(String[] args) {
		SBFrame ob = new SBFrame();
	}
}