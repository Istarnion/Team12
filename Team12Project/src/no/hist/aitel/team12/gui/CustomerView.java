package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerView extends JFrame {
	
	private JPanel shortcut = new JPanel();
	
	private JPanel topbar = new JPanel();
	
	private JPanel view = new JPanel();
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3816026786587105148L;
	
	public CustomerView(){
		setTitle("SuperShoppingSurfer");
		setPreferredSize(new Dimension(1200, 675));
		
		setLayout(new GridLayout(3,1));
		
		
		add(topbar, BorderLayout.NORTH);
		add(shortcut,BorderLayout.CENTER);
		add(view, BorderLayout.SOUTH);

	
		

		// TOP BAR
		
		
		
		topbar.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		ImageIcon icon = new ImageIcon("Resources/images/simpleLogo.png"); 
		JLabel logo = new JLabel(icon);
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.weightx = 0.3;
		gbc.gridheight=3;
		gbc.fill=GridBagConstraints.VERTICAL;
		topbar.add(logo, gbc);
		
		JTextField search = new JTextField("Search SSS");
		search.setEditable(true);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 0.5;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		topbar.add(search,gbc);
		
		JButton login = new JButton("Login");
		gbc.gridx=2;
		gbc.gridy=0;
		gbc.weightx = 0.2;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		topbar.add(login,gbc);
		
		JLabel today = new JLabel("Date: is today. the time is:");
		gbc.gridx=2;
		gbc.gridy=1;
		gbc.weightx =0.2;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		topbar.add(today,gbc);
		
		
		
		
		shortcut.setLayout(new FlowLayout());
		JButton center = new JButton("centers");
		shortcut.add(center);
		JButton shops = new JButton("shops");
		shortcut.add(shops);
		JButton county = new JButton("county");
		shortcut.add(county);
		JButton city = new JButton ("city");
		shortcut.add(city);
		
		view.setLayout(new BorderLayout());
		JLabel info = new JLabel("Information about selected");
		view.add(info,BorderLayout.NORTH);
			
		
		
	}
	public static void main (String[]args ){
		CustomerView view = new CustomerView();
		view.setVisible(true);
	}
}
