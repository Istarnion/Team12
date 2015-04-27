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
 * CustomerView.java Team 12, 27. apr. 2015
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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

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
		ImageIcon logoIcon = new ImageIcon("Resources/images/logoSmall.png");
		logo.setIcon(logoIcon);
		topbar.add(logo,BorderLayout.WEST);
		appName = new JLabel();
		ImageIcon nameIcon = new ImageIcon("Resources/images/appNameVert.png");
		appName.setIcon(nameIcon);
		topbar.add(appName, BorderLayout.CENTER);

		BorderLayout gLayout = new BorderLayout();
		loginPanel.setLayout(gLayout);
		today = new JLabel(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(new Date()));
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
		trades.add(0, new Trade(0, "ALLTRADES"));
		JComboBox<Trade> tradeSearch = new JComboBox<Trade>(trades.toArray(new Trade[trades.size()]));
		search.add(tradeSearch);

		Database Db = DatabaseFactory.getDatabase();
		while(centers == null) {
			centers = Db.getShoppingCentres(1);
		}

		System.out.println("Search panel done");

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

		System.out.println("View panel done");

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

	public void gotoCustomerCSView(int centreID, String centreName) {
		csView.updateView(centreID, centreName);
		bigCardLayout.show(mainPanel, "csView");
	}

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

	public static void main (String[]args ){
		// Splash Screen
		SplashScreen splash = new SplashScreen();
		splash.createSplash();
		long timestamp = System.currentTimeMillis();

		if(!DatabaseFactory.setup()) {
			JOptionPane.showMessageDialog(
					null,
					"Failed connecting to the database.\nPlease contact system administrator.",
					"Connection failed",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		else {
			System.out.println("Everything went nicely with the database connection.");
		}

		while(System.currentTimeMillis() - timestamp < SSS.MIN_SPLASH_TIME) {
			Thread.yield();
		}

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			// Set System L&F
			//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception ex) {
			System.out.println("Failed setting System laf. Reverting to Java defult.");
		}

		SSSWindow frame = new SSSWindow();
		System.out.println("SSSWindow added");
		CustomerView cv = new CustomerView();
		frame.add(cv.mainPanel);
		System.out.println("tab added to window");
		frame.showWindow();
		System.out.println("Visible");
		
		splash.removeSplash();
	}
}
