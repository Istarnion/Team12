package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CustomerView extends JFrame {
	
	private JPanel topbar = new JPanel();
	
	private JPanel search = new JPanel();
	
	private JPanel view = new JPanel();
	
	private JPanel loginPanel = new JPanel();
	
	private JPanel searchResult = new JPanel();
	
	private JPanel resultView = new JPanel();
	
	private JLabel logo;
	
	private JLabel appName;
	
	private JButton login;
	
	private JLabel today;
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3816026786587105148L;
	
	public CustomerView(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("SuperShoppingSurfer");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		
		add(topbar);
		add(search);
		add(view);

	
		

		// TOP BAR
		topbar.setLayout(new BorderLayout());
		logo = new JLabel();
		ImageIcon logoIcon = new ImageIcon("Resources/images/logoSmall.png");
		logo.setIcon(logoIcon);
		topbar.add(logo,BorderLayout.WEST);
		appName = new JLabel();
		ImageIcon nameIcon = new ImageIcon("Resources/images/appNameSmall.png");
		appName.setIcon(nameIcon);
		topbar.add(appName, BorderLayout.CENTER);
		
		loginPanel.setLayout(new GridLayout(2,1));
		login = new JButton("Login");
		loginPanel.add(login);
		Date dateAndTime = Calendar.getInstance().getTime();
		today = new JLabel(dateAndTime.toString());
		loginPanel.add(today);
		topbar.add(loginPanel,BorderLayout.EAST);
		
		// SEARCH
		
		search.setLayout(new GridLayout(1,5));
		
		Font font = new Font("Segoe UI Symbol",Font.PLAIN,12);
		String magGlass = "\uD83D\uDD0D";
		
		InputField centerNameSearch = new InputField(magGlass+"Enter name of center",20);
		centerNameSearch.setFont(font);
		search.add(centerNameSearch);
		InputField shopNameSearch = new InputField (magGlass+"Enter name of shop",20);
		shopNameSearch.setFont(font);
		search.add(shopNameSearch);
		InputField countySearch = new InputField(magGlass +"Enter county",20);
		countySearch.setFont(font);
		search.add(countySearch);
		InputField municipalitySearch = new InputField(magGlass+"Enter municipality",20);
		municipalitySearch.setFont(font);
		search.add(municipalitySearch);
		String [] trades = {"Sport","toys","bla","bla"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox tradeSearch = new JComboBox(trades);
		search.add(tradeSearch);
		
		// VIEW
		
		view.setLayout(new BorderLayout());
		view.add(searchResult,BorderLayout.WEST);
		view.add(resultView, BorderLayout.CENTER);
		
		searchResult.setLayout(new BorderLayout());
		JList listing = new JList();
		JScrollPane scrollListing = new JScrollPane(listing);
		searchResult.add(scrollListing, BorderLayout.WEST);
		
		pack();
		
		
		
	}
	public static void main (String[]args ){
		CustomerView view = new CustomerView();
		view.setVisible(true);
	}
}
