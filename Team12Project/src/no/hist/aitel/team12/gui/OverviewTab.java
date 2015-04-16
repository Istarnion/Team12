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
import no.hist.aitel.team12.app.CentreBuffer;
import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.app.ShoppingCentre;

public class OverviewTab extends SSSTab {

	private static final long serialVersionUID = 7700803662193994900L;

	private ShoppingCentre[] businessArray;

	private JTree businessList;

	private JScrollPane scrollPaneLeft, scrollPaneRight;

	private JLabel logoLabel;

	private BufferedImage logo;

	private JPanel leftPanel, rightPanel, namePanel, logoCard, centreCard, buildingCard, establishmentCard;

	private CardLayout cardLayout;

	private CentreBuffer cb;

	public OverviewTab(int userID) {

		cb = new CentreBuffer(2, 2, userID);
		
		try {
			logo = ImageIO.read(getClass().getResource("/images/simpleLogo.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}		


		while(businessArray == null) {
			Thread.yield();
			businessArray 		= cb.getCentres();
		}
		
		leftPanel 			= new JPanel(new BorderLayout());
		cardLayout			= new CardLayout();
		rightPanel 			= new JPanel(new BorderLayout());
		
		namePanel			= new JPanel();
		logoLabel 			= new JLabel();
		logoCard 			= new JPanel();
		centreCard			= new JPanel();
		buildingCard		= new JPanel();
		establishmentCard	= new JPanel();



		logoLabel.setIcon(new ImageIcon(logo));
		logoCard.add(logoLabel);
		



		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Centres");
		DefaultMutableTreeNode centreNode;
		DefaultMutableTreeNode buildingNode;
		for(ShoppingCentre c : businessArray) {
			centreNode = new DefaultMutableTreeNode(c);
			if(c != null && c.getBuildings() != null) {
				for(Building b : c.getBuildings()) {
					buildingNode = new DefaultMutableTreeNode(b);
					if(b != null && b.getEstablishments() != null) {
						for(Establishment e : b.getEstablishments()) {
							if(e != null) buildingNode.add(new DefaultMutableTreeNode(e));
						}
						centreNode.add(buildingNode);
					}
				}
				root.add(centreNode);
			}
		}

		businessList = new JTree(root);
		businessList.setRootVisible(false);


		scrollPaneLeft = new JScrollPane(businessList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		this.setLayout(new BorderLayout());

		leftPanel.setPreferredSize(new Dimension(250, 0));		

		leftPanel.add(scrollPaneLeft, BorderLayout.CENTER);
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
