package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import no.hist.aitel.team12.app.Building;
import no.hist.aitel.team12.app.Business;
import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.database.DatabaseFactory;

public class OverviewTab extends SSSTab {

	private static final long serialVersionUID = 7700803662193994900L;
	
	private ShoppingCentre[] businessArray;
	
	private JPanel leftPanel;
	
	private JPanel rightPanel;
	
	private JTree businessList;
	
	private JScrollPane scrollPane;

	public OverviewTab() {
		
		DatabaseFactory.getDatabase();
		businessArray 		= ShoppingCentre.getPopulatedShoppingCentres();
		leftPanel 			= new JPanel(new BorderLayout());
		rightPanel 			= new JPanel(new BorderLayout());
		
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
		businessList.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent event) {
				if(businessList.getLastSelectedPathComponent() instanceof Business) {
					
					
				}
				System.out.println(businessList.getLastSelectedPathComponent().getClass());
				System.out.println(((DefaultMutableTreeNode)businessList.getLastSelectedPathComponent()).getUserObject().getClass());
			}
			
		});
		
		
//		for (int i = 0; i < businessList.getRowCount(); i++) {
//		    businessList.expandRow(i);
//		}
		
		scrollPane = new JScrollPane(businessList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		this.setLayout(new BorderLayout());
		leftPanel.setPreferredSize(new Dimension(250, 0));		
		
		leftPanel.add(scrollPane, BorderLayout.CENTER);
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.CENTER);	
	}


	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
