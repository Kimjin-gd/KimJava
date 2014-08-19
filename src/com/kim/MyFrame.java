package com.kim;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyFrame extends JFrame{
	
	public MyFrame(){
		
		getContentPane().setLayout(new BorderLayout());
		setBounds(0, 0, 800, 600);
		setVisible(true);
		setTitle("Hello Frame!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		//add(mb);
		mb.setVisible(true);
		
		JMenu mn = new JMenu("文件");
		mb.add(mn);
		//add(mn);
		//mn.setVisible(true);
		
		JMenuItem mi = new JMenuItem("打开");
		mn.add(mi);
		
	}
	
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
	}

}
