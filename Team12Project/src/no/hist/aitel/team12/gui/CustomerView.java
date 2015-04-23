package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.app.Trade;
import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.Text;

public class CustomerView extends SSSTab {
	
	private ShoppingCentre [] centers ;

	private JPanel basePanel = new JPanel();

	private JPanel topbar = new JPanel();

	private JPanel search = new JPanel();

	private JPanel view = new JPanel();

	private JPanel loginPanel = new JPanel();

	private JPanel searchResult = new JPanel();

	private JPanel resultView = new JPanel();

	private JLabel logo;

	private JLabel appName;

	private JLabel today;

	private String magGlass = "\uD83D\uDD0D";

	private InputField shopNameSearch = new InputField (magGlass+Text.getString("cvsShpNam"),20);

	private	InputField centerNameSearch = new InputField(magGlass+Text.getString("cvsCntrNam"),20);

	private InputField countySearch = new InputField(magGlass +Text.getString("cvsCounty"),20);

	private InputField municipalitySearch = new InputField(magGlass+Text.getString("cvsMunici"),20);

	private JTree resultTree = new JTree();

	private JScrollPane scrollTree;

	private boolean valueChanged = false;

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
		topbar.setPreferredSize(new Dimension((int)topbar.getPreferredSize().getWidth(),100));
		logo = new JLabel();
		ImageIcon logoIcon = new ImageIcon("Resources/images/logoSmall.png");
		logo.setIcon(logoIcon);
		topbar.add(logo,BorderLayout.WEST);
		appName = new JLabel();
		ImageIcon nameIcon = new ImageIcon("Resources/images/appNameVert.png");
		appName.setIcon(nameIcon);
		topbar.add(appName, BorderLayout.CENTER);

		BorderLayout gLayout = new BorderLayout();
		loginPanel.setLayout(gLayout);
		Calendar dateAndTime = Calendar.getInstance();
		today = new JLabel(dateAndTime.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), JLabel.CENTER);
		loginPanel.add(today, BorderLayout.SOUTH);
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
		ArrayList <Trade> trades = Trade.getAllTrades();		
		JComboBox<Trade> tradeSearch = new JComboBox<Trade>(trades.toArray(new Trade[trades.size()]));
		search.add(tradeSearch);
		
		Database Db = DatabaseFactory.getDatabase();
		centers = Db.getShoppingCentres(1);


		System.out.println("Search panel done");

		// VIEW

		view.setLayout(new BorderLayout());
		view.add(searchResult,BorderLayout.WEST);
		view.add(resultView, BorderLayout.CENTER);

		searchResult.setLayout(new BorderLayout());
		searchResult.setPreferredSize(new Dimension(200,(int)searchResult.getPreferredSize().getHeight()));
		scrollTree 	= new JScrollPane(resultTree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		searchResult.add(scrollTree, BorderLayout.CENTER);




		basePanel.add(topbar);
		basePanel.add(search);
		basePanel.add(view);

		System.out.println("View panel done");


		SearchListener fieldListener = new SearchListener();
		shopNameSearch.addKeyListener(fieldListener);
		centerNameSearch.addKeyListener(fieldListener);
		countySearch.addKeyListener(fieldListener);
		municipalitySearch.addKeyListener(fieldListener);
		
		
		TradeSearchListener tsl = new TradeSearchListener();
		tradeSearch.addActionListener(tsl);
		
		
		Timer t = new Timer(1000,new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent f) {
				if(valueChanged){
					for(int i=0; i<centers.length; i++){
						System.out.println(centers[i].getCentreId());
					}
					
			
				}
				valueChanged =false;
			}
			
			
		});





	}






	private class SearchListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent a) {
			valueChanged = true;
			
		}

		@Override
		public void keyReleased(KeyEvent b) {
			valueChanged = true;
			
		}

		@Override
		public void keyTyped(KeyEvent c) {
			valueChanged = true;
			
		}

	}
	
	private class TradeSearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			valueChanged = true;
			
		}
		
	}





	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	public static void main (String[]args ){
		DatabaseFactory.setup();
		SSSWindow cv = new SSSWindow();
		System.out.println("SSSWindow added");
		cv.addTab("This is a tab ",new CustomerView());
		System.out.println("tab added to window");
		cv.showWindow();
		System.out.println("Visible");
	}


}
