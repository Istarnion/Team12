/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * CustomerView.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import no.hist.aitel.team12.app.Building;
import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.app.SSS;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.app.Trade;
import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.DoubleMetaphoneUtils;
import no.hist.aitel.team12.util.Text;

/**
 * This is the main class for the customer view part of the SSS system
 * This class handles the flow of this application, and sets up the main GUI
 * 
 * @author Hallgeir
 *
 */
public class CustomerView {

	private ShoppingCentre [] centers;
	
	private JPanel basePanel = new JPanel();	
	
	private JPanel mainPanel = new JPanel();
	
	private JPanel topbar = new JPanel();
	
	private JPanel search = new JPanel();
	
	private JPanel view = new JPanel();
	
	private JPanel loginPanel = new JPanel();
	
	private JPanel searchResult = new JPanel();
	
	private JPanel resultView = new JPanel();
	
	private CardLayout cardLayout;	
	
	private CardLayout bigCardLayout;
	
	private CentreView cView;	
	
	private BuildingView bView;
	
	private EstablishmentView eView;	
	
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
	
	private boolean valueChanged = true;
	
	private CustomerCSView csView;
	
	private ImageIcon logoIcon, nameIcon;

	
	/**
	 * The constructor creates and sets up the GUI
	 */
	public CustomerView(){
		super();

		bigCardLayout = new CardLayout();
		mainPanel.setLayout(bigCardLayout);
		
		basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));
		basePanel.setPreferredSize(new Dimension(1200, 675));

		// TOP BAR
		topbar.setLayout(new BorderLayout());
		topbar.setPreferredSize(new Dimension((int)topbar.getPreferredSize().getWidth(),100));
		logo = new JLabel();
		try {
			logoIcon = new ImageIcon(ImageIO.read(getClass().getResource("/images/logoSmall.png")));
			nameIcon = new ImageIcon(ImageIO.read(getClass().getResource("/images/appNameVert.png")));

		} catch (IOException e1) {
			logoIcon = null;
			nameIcon = null;
			e1.printStackTrace();
		}
		logo.setIcon(logoIcon);
		topbar.add(logo,BorderLayout.WEST);
		appName = new JLabel();
		appName.setIcon(nameIcon);
		topbar.add(appName, BorderLayout.CENTER);

		BorderLayout gLayout = new BorderLayout();
		loginPanel.setLayout(gLayout);
		today = new JLabel(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(new Date()));
		loginPanel.add(today, BorderLayout.SOUTH);
		topbar.add(loginPanel,BorderLayout.EAST);
		topbar.setMaximumSize(new Dimension(topbar.getMaximumSize().width, 100));

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
		trades.add(0, new Trade(0, "ALLTRADES"));
		JComboBox<Trade> tradeSearch = new JComboBox<Trade>(trades.toArray(new Trade[trades.size()]));
		search.add(tradeSearch);

		Database Db = DatabaseFactory.getDatabase();
		while(centers == null) {
			centers = Db.getShoppingCentres(1);
		}

		// VIEW
		cardLayout = new CardLayout();
		resultView.setLayout(cardLayout);
		
		cView = new CentreView(this);
		bView = new BuildingView();
		eView = new EstablishmentView();
		
		resultView.add(new LogoCard(), "logoCard");
		resultView.add(cView, "centreCard");
		resultView.add(bView, "buildingCard");
		resultView.add(eView, "estabCard");
		
		view.setLayout(new BorderLayout());
		view.add(searchResult,BorderLayout.WEST);
		view.add(resultView, BorderLayout.CENTER);

		searchResult.setLayout(new BorderLayout());
		searchResult.setPreferredSize(new Dimension(200,(int)searchResult.getPreferredSize().getHeight()));
		scrollTree 	= new JScrollPane(resultTree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		searchResult.add(scrollTree, BorderLayout.CENTER);

		resultTree.setRootVisible(false);

		resultTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) resultTree.getLastSelectedPathComponent();
				if(node == null) {
					cardLayout.show(resultView, "logoCard");
				}
				else {
					Object o = node.getUserObject();
					if(o instanceof ShoppingCentre) {
						cView.updateCard((ShoppingCentre)o);
						cardLayout.show(resultView, "centreCard");
					}
					else if(o instanceof Building) {
						bView.updateCard((Building)o);
						cardLayout.show(resultView, "buildingCard");
					}
					else if(o instanceof Establishment) {
						eView.updateCard((Establishment)o);
						cardLayout.show(resultView, "estabCard");
					}
				}
			}
		});
		
		basePanel.add(topbar);
		basePanel.add(search);
		basePanel.add(view);

		SearchListener fieldListener = new SearchListener();
		shopNameSearch.addKeyListener(fieldListener);
		centerNameSearch.addKeyListener(fieldListener);
		countySearch.addKeyListener(fieldListener);
		municipalitySearch.addKeyListener(fieldListener);

		TradeSearchListener tsl = new TradeSearchListener();
		tradeSearch.addActionListener(tsl);

		csView = new CustomerCSView(this);
		
		mainPanel.add(basePanel, "mainView");
		mainPanel.add(csView, "csView");
		
		Timer t = new Timer(666,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent f) {
				today.setText(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(new Date()));
				
				if(valueChanged){

					String centreName = centerNameSearch.isDefaultShown()?"":centerNameSearch.getText();
					String muni = municipalitySearch.isDefaultShown()?"":municipalitySearch.getText();
					String shopName = shopNameSearch.isDefaultShown()?"":shopNameSearch.getText();
					String county = countySearch.isDefaultShown()?"":countySearch.getText();
					Trade trade = tradeSearch.getItemAt(tradeSearch.getSelectedIndex());

					DefaultMutableTreeNode root = new DefaultMutableTreeNode("Centres");
					DefaultMutableTreeNode centreNode;
					DefaultMutableTreeNode buildingNode;

					boolean centreOK = false, buildingOK = false;;

					for(ShoppingCentre centre : centers) {
						if(
								(centreName.isEmpty() || DoubleMetaphoneUtils.isBeginningMetaphoneEqual(centreName, centre.getBusinessName())) &&
								(county.isEmpty() || DoubleMetaphoneUtils.isBeginningMetaphoneEqual(county, centre.getAddress().getCounty())) &&
								(muni.isEmpty() || DoubleMetaphoneUtils.isBeginningMetaphoneEqual(muni, centre.getAddress().getMunicipality()))
								) {
							centreNode = new DefaultMutableTreeNode(centre);

							for(Building building : centre.getBuildings()) {
								if(building != null) {
									buildingNode = new DefaultMutableTreeNode(building);

									for(Establishment estab : building.getEstablishments()) {
										if(estab != null) {
											if(shopName.isEmpty() || DoubleMetaphoneUtils.isBeginningMetaphoneEqual(shopName, estab.getBusinessName())) {
												boolean ok = false;
												if(trade.getTradeId() == 0) {
													ok = true;
												}
												else {
													for(Trade t : estab.getSelectedTrades()) {
														if(t.equals(trade)) {
															ok = true;
															break;
														}
													}
												}

												if(ok) {
													buildingOK = true;
													buildingNode.add(new DefaultMutableTreeNode(estab));
												}
											}
										}
									}

									if(buildingOK) {
										centreNode.add(buildingNode);
										centreOK = true;
										buildingOK = false;
									}
								}
							}

							if(centreOK) {
								root.add(centreNode);
								centreOK = false;
							}
						}

						resultTree.setModel(new DefaultTreeModel(root));
						for (int i = 0; i < resultTree.getRowCount(); i++) {
							resultTree.expandRow(i);
						}
					}
				}
				valueChanged = false;
			}
		});
		t.start();
	}

	/**
	 * Flips the GUI to show the customer service view
	 * 
	 * @param centreID		The ID of the centre we will show the GUI for
	 * @param centreName	The name of the centre, so the CS view can display it without looking it up from the db
	 */
	public void gotoCustomerCSView(int centreID, String centreName) {
		csView.updateView(centreID, centreName);
		bigCardLayout.show(mainPanel, "csView");
	}

	/**
	 * Flips the GUI to show the main view
	 */
	public void gotoMainView() {
		bigCardLayout.show(mainPanel, "mainView");
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

	/**
	 * The main method sets up the database connection, and initializes the window
	 * @param args No command line arguments are used in this application
	 */
	public static void main (String[]args ){
		File loginInfoFile = new File("login.ser");
		if(!loginInfoFile.isFile()) {
			String[] loginInfo = new String[3];
			loginInfo[0] = JOptionPane.showInputDialog(null, "Please input the database URL:", "localhost");
			if(loginInfo[0] == null) System.exit(0);
			loginInfo[1] = JOptionPane.showInputDialog("Please input the username for "+loginInfo[0]);
			if(loginInfo[1] == null) System.exit(0);
			loginInfo[2] = JOptionPane.showInputDialog("Please input the password for "+loginInfo[1]);
			if(loginInfo[2] == null) System.exit(0);
			
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(loginInfoFile))) {
				oos.writeObject(loginInfo);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Splash Screen
		SplashScreen splash = new SplashScreen();
		splash.createSplash();
		long timestamp = System.currentTimeMillis();

		if(DatabaseFactory.setup()) {
			System.out.println("Everything went nicely with the database connection.");
		}
		else {
			SSS.exitWithError("Failed connecting to the database.\nPlease contact system administrator.");
		}

		while(System.currentTimeMillis() - timestamp < SSS.MIN_SPLASH_TIME) {
			Thread.yield();
		}

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		}
		catch (Exception ex) {
			System.out.println("Failed setting JTattoo laf. Reverting to System defult.");
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch(Exception e) {
				System.out.println("Failed setting System laf. Reverting to Java defult.");
			}
		}

		SSSWindow frame = new SSSWindow();
		CustomerView cv = new CustomerView();
		frame.add(cv.mainPanel);
		frame.showWindow();
		
		splash.removeSplash();
	}
}
