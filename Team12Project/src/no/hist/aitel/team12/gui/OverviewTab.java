package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.LineBorder;

import no.hist.aitel.team12.app.Business;

public class OverviewTab extends JPanel {

	private static final long serialVersionUID = 7700803662193994900L;
	
	private JPanel leftPanel;
	
	private JPanel rightPanel;
	
	private JTree businessList;
	
	private Business[] businessArray;
	
	public OverviewTab() {
		
		this.setLayout(new BorderLayout());
		
		
		
		leftPanel = new JPanel(new BorderLayout());
		rightPanel = new JPanel(new BorderLayout());
		businessList = new JTree();
		leftPanel.setPreferredSize(new Dimension(250, 0));		
		businessList.setBorder(new LineBorder(Color.black));
		
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.CENTER);
		
		leftPanel.add(businessList, BorderLayout.CENTER);
	}
}
