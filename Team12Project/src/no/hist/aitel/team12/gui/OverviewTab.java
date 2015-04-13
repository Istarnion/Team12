package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
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
	
	private JPanel infoPanel;
	
	private JLabel logoLabel;
	
	private BufferedImage logo;

	public OverviewTab() {

		try {
			logo = ImageIO.read(getClass().getResource("/images/simpleLogo.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
		
		businessArray 		= ShoppingCentre.getPopulatedShoppingCentres();
		leftPanel 			= new JPanel(new BorderLayout());
		rightPanel 			= new JPanel(new BorderLayout());
		logoLabel 			= new JLabel();
		infoPanel			= new JPanel(new BorderLayout());
		logoLabel.setIcon(new ImageIcon(logo));
		
		

		
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
		businessList.setToggleClickCount(1);
		businessList.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent event) {
				rightPanel.remove(logoLabel);
				
				if(((DefaultMutableTreeNode) businessList.getLastSelectedPathComponent()).getUserObject() instanceof ShoppingCentre) {
					
				}

				if(((DefaultMutableTreeNode) businessList.getLastSelectedPathComponent()).getUserObject() instanceof Building) {
					
				}
				
				if(((DefaultMutableTreeNode) businessList.getLastSelectedPathComponent()).getUserObject() instanceof Establishment) {
				}
				refresh();
			}


			
		});
		
		scrollPane = new JScrollPane(businessList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		this.setLayout(new BorderLayout());
		leftPanel.setPreferredSize(new Dimension(250, 0));		
		
		leftPanel.add(scrollPane, BorderLayout.CENTER);
		rightPanel.add(logoLabel);
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.CENTER);	
	}


	@Override
	public void refresh() {
		rightPanel.repaint();
	}
	

}
