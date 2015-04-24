package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import no.hist.aitel.team12.app.Building;
import no.hist.aitel.team12.app.DataBuffer;
import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.util.Text;

public class OverviewTab extends SSSTab {

	private static final long serialVersionUID = 7700803662193994900L;

	private ShoppingCentre[] businessArray;

	private JTree businessList;

	private JScrollPane scrollPaneLeft, scrollPaneRight;

	private JLabel managerLabel, nameLabel;

	private JPanel 
		leftPanel, rightPanel, 
		cardPanel;
	private Box nameBox, nameWrapBox;
	
	private EstablishmentCard estabCard;
	
	private BuildingCard buildingCard;
	
	private CentreCard centreCard;
	
	private CardLayout cardLayout;
	
	private LogoCard logoCard;

	public OverviewTab(int userID) {
		
		// Set layout for this tab
		this.setLayout(new BorderLayout());
		

		// Populate businessArray from CentreBuffer
		while(businessArray == null) {
			Thread.yield();
			businessArray 		= DataBuffer.getCentres();
		}


		leftPanel 		= new JPanel(new BorderLayout());
		rightPanel 		= new JPanel(new BorderLayout());
		businessList 	= new JTree();
		cardLayout		= new CardLayout();
		cardPanel		= new JPanel(cardLayout);
		nameWrapBox		= new Box(BoxLayout.X_AXIS);
		nameBox			= new Box(BoxLayout.Y_AXIS);
		nameLabel		= new JLabel("Super Shopping Surfer", SwingConstants.LEFT);
		estabCard		= new EstablishmentCard(userID);
		buildingCard	= new BuildingCard(userID);
		centreCard		= new CentreCard(userID);
		logoCard 		= new LogoCard();
		managerLabel	= new JLabel("");
		
		scrollPaneLeft 	= new JScrollPane(businessList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
		scrollPaneRight	= new JScrollPane(cardPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		Font currentFont = businessList.getFont();
		
		nameWrapBox.add(Box.createGlue());
		nameWrapBox.add(nameBox);
		nameWrapBox.add(Box.createGlue());
		
		businessList.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), currentFont.getSize() + 3));
		scrollPaneRight.setBorder(null);
		nameBox.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));

		nameLabel.setFont(nameLabel.getFont().deriveFont(Font.PLAIN, 50));

		nameBox.add(Box.createHorizontalStrut(10));
		nameBox.add(nameLabel);
		nameBox.add(managerLabel);

		nameLabel.setAlignmentX(LEFT_ALIGNMENT);
		businessList.setRootVisible(false);
		
		cardPanel.add(logoCard, "logoCard");
		cardPanel.add(centreCard, "centreCard");
		cardPanel.add(buildingCard, "buildingCard");
		cardPanel.add(estabCard, "estabCard");

		
		rightPanel.add(nameBox, BorderLayout.NORTH);
		rightPanel.add(scrollPaneRight, BorderLayout.CENTER);
		leftPanel.setPreferredSize(new Dimension(200, 0));		
		leftPanel.add(scrollPaneLeft, BorderLayout.CENTER);
		
		this.add(leftPanel, BorderLayout.WEST);
		this.add(rightPanel, BorderLayout.CENTER);
		
		
		setUpListener();
		
		
	}

	private DefaultMutableTreeNode setupTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Centres");
		DefaultMutableTreeNode centreNode;
		DefaultMutableTreeNode buildingNode;
		
		for(ShoppingCentre c : businessArray) {
			centreNode = new DefaultMutableTreeNode(c);
			if(c != null && c.getBuildings() != null) {
				for(Building b : c.getBuildings()) {
					buildingNode = new DefaultMutableTreeNode(b);
					if(b != null) {
						if(b.getEstablishments() != null) {
							for(Establishment e : b.getEstablishments()) {
								if(e != null) buildingNode.add(new DefaultMutableTreeNode(e));
							}
						}
						centreNode.add(buildingNode);
					}
				}
			}
			root.add(centreNode);
		}
		return root;
	}

	private void setUpListener() {
		businessList.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent event) {

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) businessList.getLastSelectedPathComponent();
				
				if(node == null) {
					nameLabel.setText("Super Shopping Surfer");
					managerLabel.setText("");
					cardLayout.show(cardPanel, "logoCard");
				}

				else if(node.getUserObject() instanceof ShoppingCentre) {
					ShoppingCentre centre = (ShoppingCentre) node.getUserObject();
					nameLabel.setText(Text.getString("centre") + ": " + centre.getBusinessName());
					managerLabel.setText(Text.getString("manager") + ": " + centre.getOwnerName());
					centreCard.updateCard(centre);
					cardLayout.show(cardPanel, "centreCard");
					scrollPaneRight.getVerticalScrollBar().setValue(0);
				}

				else if(node.getUserObject() instanceof Building) {
					Building building = (Building) node.getUserObject();
					nameLabel.setText(Text.getString("building") + ": " + building.getBuildingName());
					managerLabel.setText("");
					buildingCard.updateCard(building);
					cardLayout.show(cardPanel, "buildingCard");
					scrollPaneRight.getVerticalScrollBar().setValue(0);
				}

				else if(node.getUserObject() instanceof Establishment) {
					Establishment estab = (Establishment) node.getUserObject();
					nameLabel.setText(Text.getString("shop") + ": " + estab.getBusinessName());
					managerLabel.setText(Text.getString("manager") + ": " + estab.getOwnerName());
					estabCard.updateCard(estab);
					cardLayout.show(cardPanel, "estabCard");
					scrollPaneRight.getVerticalScrollBar().setValue(0);
				}
				else {
					// log error
				}
			}
		});
	}

	@Override
	public void refresh() {
		businessArray = null;
		while(businessArray == null) {
			Thread.yield();
			businessArray 		= DataBuffer.getCentres();
		}
		businessList.setModel(new DefaultTreeModel(setupTree()));
		for (int i = businessList.getRowCount() - 1; i >= 0; i--) {
	         businessList.expandRow(i);
	}

	}
}
