package com.kim;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class MyFrame extends JFrame implements ActionListener{
	
	private JTextArea ta;
	private String sFilePath;
	
	public JTextArea getTa() {
		return ta;
	}

	public void setTa(JTextArea ta) {
		this.ta = ta;
	}

	public String getsFilePath() {
		return sFilePath;
	}

	public void setsFilePath(String sFilePath) {
		this.sFilePath = sFilePath;
	}

	public MyFrame(){
		
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		mb.setVisible(true);
		
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		getContentPane().setLayout(borderLayout);
		setBounds(0, 0, 800, 600);
		setVisible(true);
		setTitle("Hello Frame!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
		JMenu mn = new JMenu("文件");
		mn.setMnemonic('F');
		mb.add(mn);
		
		JMenuItem openMi = new JMenuItem("打开");
		openMi.setDisplayedMnemonicIndex(82);
		openMi.setMnemonic(KeyEvent.VK_X);
		openMi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mn.add(openMi);		
		
		JSeparator separator = new JSeparator();
		mn.add(separator);
		
		//mn.();
		
		JMenuItem saveMi = new JMenuItem("保存");
		mn.add(saveMi);		

		JScrollPane sp = new JScrollPane();
		getContentPane().add(sp, BorderLayout.CENTER);
		
		ta = new JTextArea();
		sp.setViewportView(ta);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(ta, popupMenu);
		
		JMenuItem popOpenMi = new JMenuItem("\u6253\u5F00");
		popupMenu.add(popOpenMi);
		
		JSeparator separator_1 = new JSeparator();
		popupMenu.add(separator_1);
		
		JMenuItem popSaveMi = new JMenuItem("\u4FDD\u5B58");
		popupMenu.add(popSaveMi);
		
		
		openMi.addActionListener(this);
		saveMi.addActionListener(this);
		popSaveMi.addActionListener(this);
		popOpenMi.addActionListener(this);
		
	}
	
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//System.out.println(e.getActionCommand());
		
		if (e.getActionCommand().equals("打开")){
			openFile();
		}
		else if (e.getActionCommand().equals("保存")){
			saveFile();
				
		}		
	}
	
	private void openFile(){
		
		FileDialog fd = new FileDialog(this, "Open File", FileDialog.LOAD);
		fd.setVisible(true);
		
		if (fd.getFile() == null){
			getTa().setText("No file!");
			setsFilePath(null);
			return;
		}
			
		
		try {
			String sfp = fd.getDirectory()+fd.getFile();
			FileReader fr = new FileReader(fd.getDirectory()+fd.getFile());
			BufferedReader isr = new BufferedReader(fr );
			String ts;
			StringBuilder sb = new StringBuilder();
			while ( (ts = isr.readLine()) != null){
				sb.append(ts + "\n");
			}
			
			setsFilePath(sfp);
			getTa().setText(sb.toString());
			
			isr.close();
			fr.close();
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void saveFile(){
		
		if (getsFilePath() == null){
			return;
		}
		
		//System.out.println(getTa().getText());
		
		try {
			FileWriter fw = new FileWriter(getsFilePath());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(getTa().getText());
			bw.close();
			fw.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
