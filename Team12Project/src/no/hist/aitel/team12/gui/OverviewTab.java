package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import no.hist.aitel.team12.app.Building;
import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.app.ShoppingCentre;

public class OverviewTab extends SSSTab {

	private static final long serialVersionUID = 7700803662193994900L;

	private ShoppingCentre[] businessArray;

	private JPanel leftPanel;

	private JPanel rightPanel;

	private JTree businessList;

	private JScrollPane scrollPane;

	private JLabel logoLabel;

	private BufferedImage logo;

	private JPanel logoCard, centreCard, buildingCard, establishmentCard;

	private CardLayout cardLayout;

	public OverviewTab() {

		try {
			logo = ImageIO.read(getClass().getResource("/images/simpleLogo.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}		

		businessArray 		= ShoppingCentre.getPopulatedShoppingCentres();
		leftPanel 			= new JPanel(new BorderLayout());
		cardLayout			= new CardLayout();
		rightPanel 			= new JPanel(cardLayout);
		logoLabel 			= new JLabel();
		logoCard 			= new JPanel();
		centreCard			= new JPanel();
		buildingCard		= new JPanel();
		establishmentCard	= new JPanel();

		

		logoLabel.setIcon(new ImageIcon(logo));
		logoCard.add(logoLabel);
		rightPanel.add(logoCard, "logoCard");
		rightPanel.add(centreCard, "centreCard");
		rightPanel.add(buildingCard, "buildingCard");
		rightPanel.add(establishmentCard, "establishmentCard");
		
		



		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Centres");
		DefaultMutableTreeNode centreNode;
		DefaultMutableTreeNode buildingNode;
		for(ShoppingCentre c : businessArray) {
			centreNode = new DefaultMutableTreeNode(c);
			for(Building b : c.getBuildings()) {
				buildingNode = new DefaultMutableTreeNode(b);
				for(Establishment e : b.getEstablishments()) {
					buildingNode.add(new DefaultMutableTreeNode(e));
				}
				centreNode.add(buildingNode);
			}
			root.add(centreNode);
		}

		businessList = new JTree(root);
		businessList.setRootVisible(false);
		

		scrollPane = new JScrollPane(businessList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		this.setLayout(new BorderLayout());
		
		leftPanel.setPreferredSize(new Dimension(250, 0));		

		leftPanel.add(scrollPane, BorderLayout.CENTER);
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.CENTER);
		setUpListener();
	}



	public void updateCentreCard(ShoppingCentre centre) {

	}
	
	public void updateBuildingCard(Building building) {
		
	}
	
	public void updateEstablishmentCard(Establishment establishment) {
		
	}

	
	public void setUpListener() {
		businessList.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent event) {

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) businessList.getLastSelectedPathComponent();

				if(node == null) {
					cardLayout.show(rightPanel, "logoCard");
				}

				else if(node.getUserObject() instanceof ShoppingCentre) {
					updateCentreCard((ShoppingCentre)node.getUserObject());
					cardLayout.show(rightPanel, "centreCard");
				}

				else if(node.getUserObject() instanceof Building) {
					updateBuildingCard((Building)node.getUserObject());
					cardLayout.show(rightPanel, "buildingCard");					
				}

				else if(node.getUserObject() instanceof Establishment) {
					updateEstablishmentCard((Establishment)node.getUserObject());
					cardLayout.show(rightPanel, "establishmentCard");				
				}
				else {
					// log error
				}
				refresh();
			}
		});
	}
	
	@Override
	public void refresh() {
		rightPanel.repaint();
	}
}
