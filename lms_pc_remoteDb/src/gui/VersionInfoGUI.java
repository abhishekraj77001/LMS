package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VersionInfoGUI extends JFrame implements ActionListener
{	
	Container c;
	ImageIcon icon;
	JLabel l_img,l_version,l_lms_pc,l_gnu;
	JTextArea jt;
	JScrollPane scrollPane;
	JButton ok;
	String license;
	
	public VersionInfoGUI()
	{
		c=this.getContentPane();
		c.setLayout(null);
		icon=new ImageIcon(getClass().getResource("/icons/lms.png"));
		l_img=new JLabel();
		l_img.setIcon(icon);	
		Font vfont=new Font("Arial",Font.BOLD,25);
		l_version=new JLabel("<html><u>version: 1.0</u></html>");
		l_version.setFont(vfont);
		l_lms_pc=new JLabel("<html><u>developed by:	Prashant,Abhishek,Ram</u></html>");
		l_lms_pc.setFont(vfont);
		l_gnu=new JLabel("<html><u>***GNU General Public License***</u></html>");
		license="This program is free software; \nyou can redistribute it and/or modify it "
				+ "\nunder the terms of the \nGNU General Public License \nas published by the "
				+ "Free Software Foundation;"
				+ " \neither version 2 of the License, \nor (at your option) any later version."
				+ "\n\nThis program is distributed in the hope \nthat it will be useful, "
				+ "\nbut WITHOUT ANY WARRANTY; \nwithout even the implied warranty of \nMERCHANTABILITY or FITNESS"
				+ "\nFOR A PARTICULAR PURPOSE.\nSee the GNU General Public License \nfor more details. ";
		jt=new JTextArea(5,20);
		jt.setText(license);
		Font font = new Font("Segoe Script", Font.BOLD, 20);
		l_gnu.setFont(font);
        jt.setFont(font);
		jt.setEditable(false);
		scrollPane= new JScrollPane(jt);   
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		ok=new JButton("OK");
		windowSetter();
		boundSetter();
		componentsAdder();
		eventSetter();
	}
	
	void boundSetter()
	{
		l_img.setBounds(10, 50, icon.getIconWidth(), icon.getIconHeight());
		l_version.setBounds(275,25,400,50);
		l_lms_pc.setBounds(275,75,400,100);
		l_gnu.setBounds(125, 200, 400, 50);
		ok.setBounds(225,500 ,150 ,50 );
		scrollPane.setBounds(10, 250, 575, 225);
	}
	
	void windowSetter()
	{
		setTitle("Login Monitor Software");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500,200,600,600);
		setResizable(false);
	}
	
	void componentsAdder()
	{
		c.add(l_img);
		c.add(l_version);
		c.add(l_lms_pc);
		c.add(l_gnu);
		c.add(scrollPane);
		c.add(ok);
	}
	
	void eventSetter()
	{
		ok.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==ok)
		{
			dispose();
		}
	}
	
	public static void main(String[] args) 
	{
		VersionInfoGUI d=new VersionInfoGUI();
	}
}