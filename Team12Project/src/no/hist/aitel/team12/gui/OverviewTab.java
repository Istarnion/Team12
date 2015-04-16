package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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

	private JLabel logoLabel, nameLabel;

	private BufferedImage logo;

	private JPanel 
		leftPanel, rightPanel, namePanel, 
		cardPanel, logoCard, centreCard, 
		buildingCard, establishmentCard;
	
	private JTextField 
		centreNameText, addressText, emailText, 
		telephoneText, openingHoursText, 
		descriptionText, buildingNameText, 
		nrOfFloorsText, areaText, estabNameText;

	private CardLayout cardLayout;

	private CentreBuffer cb;

	public OverviewTab(int userID) {
		
		// Set layout for this tab
		this.setLayout(new BorderLayout());
		
		// Starts the CentreBuffer
		cb = new CentreBuffer(2, 2, userID);
		
		// Get image of logo
		try {
			logo = ImageIO.read(getClass().getResource("/images/simpleLogo.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}		

		// Populate businessArray from CentreBuffer
		while(businessArray == null) {
			Thread.yield();
			businessArray 		= cb.getCentres();
		}


		leftPanel 			= new JPanel(new BorderLayout());
		rightPanel 			= new JPanel(new BorderLayout());
		businessList 		= new JTree(setupTree());
		cardLayout			= new CardLayout();
		cardPanel			= new JPanel(cardLayout);
		namePanel			= new JPanel();
		nameLabel			= new JLabel();
		logoLabel 			= new JLabel();
		logoCard 			= new JPanel();
		centreCard			= new JPanel();
		buildingCard		= new JPanel();
		establishmentCard	= new JPanel();
		
		centreCard.setLayout(new BoxLayout(centreCard, BoxLayout.Y_AXIS));
		buildingCard.setLayout(new BoxLayout(buildingCard, BoxLayout.Y_AXIS));
		establishmentCard.setLayout(new BoxLayout(establishmentCard, BoxLayout.Y_AXIS));

		
		centreNameText		= new JTextField("");
		addressText			= new JTextField("");
		emailText			= new JTextField("");
		telephoneText		= new JTextField("");
		openingHoursText	= new JTextField("");
		descriptionText		= new JTextField("");
		buildingNameText	= new JTextField("");
		estabNameText		= new JTextField("");
		nrOfFloorsText		= new JTextField("");
		areaText			= new JTextField("");
		
		// Setting cosmetics for TextFields
		centreNameText.setEditable(false);
		addressText.setEditable(false);
		emailText.setEditable(false);
		telephoneText.setEditable(false);
		openingHoursText.setEditable(false);
		descriptionText.setEditable(false);
		buildingNameText.setEditable(false);
		estabNameText.setEditable(false);
		nrOfFloorsText.setEditable(false);
		areaText.setEditable(false);
		
		centreNameText.setBorder(null);
		addressText.setBorder(null);
		emailText.setBorder(null);
		telephoneText.setBorder(null);
		openingHoursText.setBorder(null);;
		descriptionText.setBorder(null);;
		buildingNameText.setBorder(null);;
		estabNameText.setBorder(null);
		nrOfFloorsText.setBorder(null);;
		areaText.setBorder(null);;
		// Cosmetics done
		
		
		// Adding TextFields to cards
		centreCard.add(centreNameText);
		centreCard.add(addressText);
		centreCard.add(emailText);
		centreCard.add(telephoneText);
		centreCard.add(openingHoursText);
		centreCard.add(areaText);
		centreCard.add(descriptionText);
		
		buildingCard.add(centreNameText);
		buildingCard.add(buildingNameText);
		buildingCard.add(nrOfFloorsText);
		buildingCard.add(areaText);
		
		establishmentCard.add(centreNameText);
		establishmentCard.add(estabNameText);
		establishmentCard.add(addressText);
		establishmentCard.add(emailText);
		establishmentCard.add(telephoneText);
		establishmentCard.add(openingHoursText);
		establishmentCard.add(descriptionText);
		// TextFields adding done
		
		scrollPaneLeft 		= new JScrollPane(businessList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
		scrollPaneRight		= new JScrollPane(cardPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrollPaneRight.setBorder(null);
		namePanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));

		logoLabel.setIcon(new ImageIcon(logo));
		logoCard.add(logoLabel);
		nameLabel.setFont(nameLabel.getFont().deriveFont(Font.PLAIN, 50));
		namePanel.add(nameLabel);
		nameLabel.setText("Super Shopping Surfer");
		
		businessList.setRootVisible(false);
		
		cardPanel.add(logoCard, "logoCard");
		cardPanel.add(centreCard, "centreCard");
		cardPanel.add(buildingCard, "buildingCard");
		cardPanel.add(establishmentCard, "establishmentCard");

		
		rightPanel.add(namePanel, BorderLayout.NORTH);
		
		rightPanel.add(scrollPaneRight, BorderLayout.CENTER);

		leftPanel.setPreferredSize(new Dimension(250, 0));		

		leftPanel.add(scrollPaneLeft, BorderLayout.CENTER);
		
		add(leftPanel, BorderLayout.WEST);
		
		add(rightPanel, BorderLayout.CENTER);
		
		setUpListener();
	}






	private void updateCentreCard(ShoppingCentre centre) {
		nameLabel.setText(centre.getBusinessName());
		centreNameText.setText(centre.getBusinessName());
		addressText.setText(centre.getAddress().toString());
		emailText.setText(centre.getEmail().toString());
		telephoneText.setText(String.valueOf(centre.getTelephone()));
		openingHoursText.setText(String.valueOf(centre.getOpeningHours()));
		areaText.setText("Someone didnt implement this correctly");
		descriptionText.setText(centre.getDescription());
		
	}

	private void updateBuildingCard(Building building) {
		nameLabel.setText(building.getBuildingName());
		buildingNameText.setText(building.getBuildingName());
		nrOfFloorsText.setText(String.valueOf(building.getFloors()));
		areaText.setText("Someone didnt implement this correctly");
	}

	private void updateEstablishmentCard(Establishment establishment) {
		nameLabel.setText(establishment.getBusinessName());
		estabNameText.setText(establishment.getBusinessName());
		addressText.setText("We need to move address back to business in the db/class");
		emailText.setText(establishment.getEmail().toString());
		telephoneText.setText(String.valueOf(establishment.getTelephone()));
		openingHoursText.setText(String.valueOf(establishment.getOpeningHours()));
		descriptionText.setText("We have to move getDescription() over to Business class");
		
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
		return root;
	}

	private void setUpListener() {
		businessList.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent event) {

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) businessList.getLastSelectedPathComponent();
				
				if(node == null) {
					nameLabel.setText("Super Shopping Surfer");
					cardLayout.show(cardPanel, "logoCard");
				}

				else if(node.getUserObject() instanceof ShoppingCentre) {
					updateCentreCard((ShoppingCentre)node.getUserObject());
					cardLayout.show(cardPanel, "centreCard");
				}

				else if(node.getUserObject() instanceof Building) {
					DefaultMutableTreeNode nodeParent = (DefaultMutableTreeNode) node.getParent();
					centreNameText.setText(nodeParent.getUserObject().toString());
					System.out.println(centreNameText.getText());
					updateBuildingCard((Building)node.getUserObject());
					cardLayout.show(cardPanel, "buildingCard");					
				}

				else if(node.getUserObject() instanceof Establishment) {
					DefaultMutableTreeNode nodeParent = (DefaultMutableTreeNode) node.getParent().getParent();
					centreNameText.setText(nodeParent.getUserObject().toString());
					updateEstablishmentCard((Establishment)node.getUserObject());
					cardLayout.show(cardPanel, "establishmentCard");				
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
