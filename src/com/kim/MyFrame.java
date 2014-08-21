package com.kim;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyFrame extends JFrame implements ActionListener{
	
	JTextArea ta;
	
	public MyFrame(){
		
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		mb.setVisible(true);
		
		getContentPane().setLayout(new BorderLayout());
		setBounds(0, 0, 800, 600);
		setVisible(true);
		setTitle("Hello Frame!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
		JMenu mn = new JMenu("文件");
		mb.add(mn);
		//add(mn);
		//mn.setVisible(true);
		
		JMenuItem mi = new JMenuItem("打开");
		mn.add(mi);
		
		ta = new JTextArea(100,100);
		//ta.setVisible(true);
		//getContentPane().add(ta);
		//getContentPane().add(ta, BorderLayout.CENTER);
		//ta.setAutoscrolls(true);
		ta.setEditable(true);
		ta.setText("XX");
		
		JScrollPane sp = new JScrollPane(ta);
		getContentPane().add(sp, BorderLayout.CENTER);
		sp.setVisible(true);
		
		
		mi.addActionListener(this);
		

		//ta.setText("XX");
		
	}
	
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		FileDialog fd = new FileDialog(this, "Open File", FileDialog.LOAD);
		fd.setVisible(true);
		
		if (fd.getFile() == null){
			ta.setText("No file!");
			return;
		}
			
		
		try {
			FileReader fr = new FileReader(fd.getDirectory()+fd.getFile());
			BufferedReader isr = new BufferedReader(fr );
			String ts;
			StringBuilder sb = new StringBuilder();
			while ( (ts = isr.readLine()) != null){
				sb.append(ts + "\n");
			}
			
			ta.setText(sb.toString());
			
			isr.close();
			fr.close();
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
