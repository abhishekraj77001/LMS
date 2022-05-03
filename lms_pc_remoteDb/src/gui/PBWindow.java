package gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class PBFrame extends JFrame implements ActionListener, ListSelectionListener {
	Container c;
	JLabel l_pb, l_programsList, l_blockList;
	JPanel p1;
	JButton add, remove, removeAll, apply, cancel;
	JScrollPane sp1, sp2;
	JList<String> list1, list2;
	DefaultListModel<String> l1, l2;
	String fileName;
	LogOperations lo;

	public PBFrame() {
		c = this.getContentPane();
		c.setLayout(null);
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(Color.blue);
		l_pb = new JLabel("PROGRAM BLOCKER");
		l_pb.setFont(new Font("Courier New", Font.BOLD, 25));
		p1.add(l_pb);
		l_programsList = new JLabel("Installed Programs");
		l_programsList.setFont(new Font("Courier New", Font.BOLD, 20));
		l1 = new DefaultListModel<>(); // list1
		ArrayList<String> str1 = new blocker.InstalledPrograms().getInstalledPrograms();
		for (int i = 3; i < str1.size(); i++) {
			l1.addElement(str1.get(i));
		}
		list1 = new JList<>(l1);
		list1.setBounds(50, 100, 250, 450);
		sp1 = new JScrollPane(list1); // scrollpane
		// sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp1.setBounds(50, 100, 250, 450);
		c.add(sp1);
		l_blockList = new JLabel("Block List");
		l_blockList.setFont(new Font("Courier New", Font.BOLD, 20));
		l2 = new DefaultListModel<>(); // list2
		fileName = "C:\\Users\\Public\\lms_pc\\Logs\\pb.txt";
		lo = new LogOperations();
		ArrayList<String> str2 = lo.readFromFile(fileName);
		for (String s : str2) {
			l2.addElement(s);
		}
		list2 = new JList<>(l2);
		list2.setBounds(500, 100, 250, 450);
		sp2 = new JScrollPane(list2); // scrollpane
		// sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp2.setBounds(500, 100, 250, 450);
		c.add(sp2);
		add = new JButton("Add to Block list");
		remove = new JButton("Remove");
		removeAll = new JButton("Remove All");
		apply = new JButton("Apply");
		cancel = new JButton("Cancel");
		add.setEnabled(false);
		remove.setEnabled(false);
		apply.setEnabled(false);
		cancel.setEnabled(false);
		windowSetter();
		boundSetter();
		componentsAdder();
		eventSetter();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add) {
			List list = list1.getSelectedValuesList();
			for (Object obj : list) {
				String s = obj.toString();
				if (!isAlreadyPresent(l2, s)) {
					l2.addElement(s);
				}
			}
			apply.setEnabled(true);
			cancel.setEnabled(true);
		} else if (e.getSource() == remove) {
			List list = list2.getSelectedValuesList();
			for (Object obj : list) {
				String s = obj.toString();
				l2.removeElement(obj);
			}
			apply.setEnabled(true);
			cancel.setEnabled(true);
		} else if (e.getSource() == removeAll) {
			l2.clear();
			apply.setEnabled(true);
			cancel.setEnabled(true);
			remove.setEnabled(false);
		} else if (e.getSource() == apply) {
			new blocker.StopProgramsBlocker().stopPB();
			String[] programs = new String[l2.getSize()];
			for (int i = 0; i < l2.getSize(); i++) {
				programs[i] = l2.getElementAt(i);
			}
			lo.writeToFile(fileName, programs);
			JOptionPane.showMessageDialog(this, "done!");
			dispose();
			new blocker.StartProgramsBlocker();
		} else if (e.getSource() == cancel) {
			dispose();
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == list1) {
			add.setEnabled(true);
		}
		if (e.getSource() == list2) {
			remove.setEnabled(true);
			add.setEnabled(false);
		}
	}

	void boundSetter() {
		p1.setBounds(0, 0, 1000, 50);
		l_pb.setBounds(350, 5, 300, 30);
		l_programsList.setBounds(50, 50, 300, 50);
		l_blockList.setBounds(500, 50, 300, 50);
		add.setBounds(325, 275, 150, 50);
		remove.setBounds(800, 225, 150, 50);
		removeAll.setBounds(800, 325, 150, 50);
		apply.setBounds(400, 600, 200, 50);
		cancel.setBounds(650, 600, 200, 50);
	}

	void windowSetter() {
		setTitle("Login Monitor Software");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 25, 1000, 700);
		setResizable(false);
	}

	void componentsAdder() {
		c.add(p1);
		c.add(l_programsList);
		c.add(l_blockList);
		c.add(add);
		c.add(remove);
		c.add(removeAll);
		c.add(apply);
		c.add(cancel);
	}

	void eventSetter() {
		list1.addListSelectionListener(this);
		list2.addListSelectionListener(this);
		add.addActionListener(this);
		remove.addActionListener(this);
		removeAll.addActionListener(this);
		apply.addActionListener(this);
		cancel.addActionListener(this);
	}

	boolean isAlreadyPresent(DefaultListModel<String> list, String element) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equalsIgnoreCase(element)) {
				return true;
			}
		}
		return false;
	}
}

public class PBWindow {
	public PBWindow() {
		PBFrame ob = new PBFrame();
	}
	
	public static void main(String[] args) {
		PBFrame ob = new PBFrame();
	}
}
