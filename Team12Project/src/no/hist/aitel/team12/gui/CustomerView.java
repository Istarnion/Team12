package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import no.hist.aitel.team12.util.Text;

public class CustomerView extends SSSTab {

	private JPanel basePanel = new JPanel();

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
	
	private String magGlass = "\uD83D\uDD0D";
	
	private InputField shopNameSearch = new InputField (magGlass+Text.getString("cvsShpNam"),20);

	private	InputField centerNameSearch = new InputField(magGlass+Text.getString("cvsCntrNam"),20);
	
	private InputField countySearch = new InputField(magGlass +Text.getString("cvsCounty"),20);

	private InputField municipalitySearch = new InputField(magGlass+Text.getString("cvsMunici"),20);
	
	private ArrayList<String> shops= new ArrayList<String>();
	
	private JLabel test = new JLabel();
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3816026786587105148L;

	public CustomerView(){
		super();
		
		basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));
		basePanel.setPreferredSize(new Dimension(1200, 675));

		add(basePanel, BorderLayout.CENTER);


		// TOP BAR
		topbar.setLayout(new BorderLayout());
		topbar.setPreferredSize(new Dimension(0,100));
		logo = new JLabel();
		ImageIcon logoIcon = new ImageIcon("Resources/images/logoSmall.png");
		logo.setIcon(logoIcon);
		topbar.add(logo,BorderLayout.WEST);
		appName = new JLabel();
		ImageIcon nameIcon = new ImageIcon("Resources/images/appNameVert.png");
		appName.setIcon(nameIcon);
		topbar.add(appName, BorderLayout.CENTER);

		GridLayout gLayout = new GridLayout(2,1, 2, 10);
		loginPanel.setLayout(gLayout);
		//loginPanel.setPreferredSize(new Dimension(400,100));
		login = new JButton("Login");
		loginPanel.add(login);
		Calendar dateAndTime = Calendar.getInstance();
		today = new JLabel(dateAndTime.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), JLabel.CENTER);
		loginPanel.add(today);
		topbar.add(loginPanel,BorderLayout.EAST);
		topbar.setMaximumSize(new Dimension(topbar.getMaximumSize().width, 100));
		
		System.out.println("Topbar done");

		// SEARCH

		search.setLayout(new GridLayout(1,5));
		
	
		Font font = new Font("Segoe UI Symbol",Font.PLAIN,12);
		centerNameSearch.setFont(font);
		search.add(centerNameSearch);
		shopNameSearch.setFont(font);
		search.add(shopNameSearch); 
		countySearch.setFont(font);
		search.add(countySearch); 
		municipalitySearch.setFont(font);
		search.add(municipalitySearch); 
		String [] trades = {"Sport","toys","bla","bla"};
		JComboBox<String> tradeSearch = new JComboBox<String>(trades);
		search.add(tradeSearch);
		
		
		System.out.println("Search panel done");

		// VIEW

		view.setLayout(new BorderLayout());
		view.add(searchResult,BorderLayout.WEST);
		view.add(resultView, BorderLayout.CENTER);

		searchResult.setLayout(new BorderLayout());
		
	//	JList <String>listing = new JList<String>();
	//	JScrollPane scrollListing = new JScrollPane(listing);
		
		searchResult.add(test, BorderLayout.WEST);

		
		
		basePanel.add(topbar);
		basePanel.add(search);
		basePanel.add(view);
		
		System.out.println("View panel done");

		ButtonListener btnListener = new ButtonListener();
		login.addActionListener(btnListener);
		
		SearchListener fieldListener = new SearchListener();
		shopNameSearch.addActionListener(fieldListener);
		
	
		shops.add("Narvesen");
		shops.add("HM");
		shops.add("BigBite");

		
			
		}
	
	


	
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("open login window");
			
		}
		
		}
	private class SearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent f) {
			// TODO Auto-generated method stub
			String selected = shopNameSearch.getText();
			
			for(String shp : shops){
				if(selected.contains(shp)){
					test.setText(selected);
				}
			}
		
			
				
			}
			
		}
		

		
	
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	public static void main (String[]args ){
		SSSWindow cv = new SSSWindow();
		System.out.println("SSSWindow added");
		cv.addTab("This is a tab ",new CustomerView());
		System.out.println("tab added to window");
		cv.showWindow();
		System.out.println("Visible");
	}


}
