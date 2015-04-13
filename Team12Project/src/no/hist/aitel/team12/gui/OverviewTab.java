package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.LineBorder;

import no.hist.aitel.team12.app.Business;
import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;

public class OverviewTab extends SSSTab {

	private static final long serialVersionUID = 7700803662193994900L;
	
	private Database db;
	
	private Business[] businessArray;
	
	private JPanel leftPanel;
	
	private JPanel rightPanel;
	
	private JTree businessList;

	public OverviewTab() {
		
		db 					= DatabaseFactory.getDatabase();
		businessArray 		= db.getShoppingCentreData();
		leftPanel 			= new JPanel(new BorderLayout());
		rightPanel 			= new JPanel(new BorderLayout());
		businessList 		= new JTree(businessArray);
		
		for(Business b : businessArray) {
			System.out.println(b);
		}
		this.setLayout(new BorderLayout());
		leftPanel.setPreferredSize(new Dimension(250, 0));		
		businessList.setBorder(new LineBorder(Color.black));
		
		leftPanel.add(businessList, BorderLayout.CENTER);
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.CENTER);	
	}


	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
