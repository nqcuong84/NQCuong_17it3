package sict.copy;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;

public class CopyContent extends JFrame {

	private JPanel contentPane;
	private JTextArea txtMain;
	private JFileChooser chooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CopyContent frame = new CopyContent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CopyContent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtMain = new JTextArea();
		txtMain.setBounds(10, 11, 414, 185);
		contentPane.add(txtMain);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooser= new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Open file as..");
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				String filename= chooser.getSelectedFile().getAbsolutePath();
				openFile(filename);
			}
			}

			private void openFile(String filename) {
				// TODO Auto-generated method stub
				FileReader fr= null; 
				try {
					
					fr =new FileReader(filename);
					String content= "";
					int data=fr.read();
					while(data!=-1) {
						content+= (char) data;
						data=fr.read();
					}
					txtMain.setText(content);
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				finally {
					try {
						fr.close();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnOpen.setBounds(39, 207, 89, 23);
		contentPane.add(btnOpen);
		
		JButton btnSaveAs = new JButton("Save As");
		btnSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				chooser.setDialogTitle("Save File");
				if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
					String filename= chooser.getSelectedFile().getAbsolutePath();
					saveFile(filename);
				}
						
						
			}

			private void saveFile(String filename) {
				// TODO Auto-generated method stub
				FileWriter fw= null; 
				try {
					fw =new FileWriter(filename);
					String content= txtMain.getText();
					fw.write(content);
					fw.flush();
					
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				finally {
					try {
						fw.close();
						JOptionPane.showMessageDialog(null, "File "+filename +" đã được lưu lại");
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		btnSaveAs.setBounds(293, 207, 89, 23);
		contentPane.add(btnSaveAs);
	}
}
