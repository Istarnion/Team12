package no.hist.aitel.team12.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.jdesktop.swingx.JXDatePicker;

import no.hist.aitel.team12.app.PDFGenerator;
import no.hist.aitel.team12.util.Text;

public class FinanceTab extends SSSTab {

	private static final long serialVersionUID = 2799861967861410870L;
	
	private JPanel financeView = new JPanel();
	
	private JLabel showPDF = new JLabel();
	
	private JScrollPane pdfScroll; 
	
	
	
	public FinanceTab(String username){
		
		financeView.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		// Colors for Debugging
		
		financeView.setBackground(Color.BLUE);
		
		
		// Selection View
		// Row 1
		JLabel info = new JLabel(Text.getString("storeTxt")+Text.getString("cntrTxt"));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		//gbc.anchor = GridBagConstraints.WEST;
		financeView.add(info, gbc);
		
		// Row 2
		JLabel fDate = new JLabel (Text.getString("fDate"));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		//gbc.anchor = GridBagConstraints.WEST;
		financeView.add(fDate,gbc);
		
		JXDatePicker fromDate = new JXDatePicker();
		fromDate.setDate(Calendar.getInstance().getTime());
        fromDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.30;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        financeView.add(fromDate,gbc);
		
    	JLabel tDate = new JLabel (Text.getString("tDate"));
    	gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		financeView.add(tDate,gbc);
        
        JXDatePicker toDate = new JXDatePicker();
		toDate.setDate(Calendar.getInstance().getTime());
        toDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        financeView.add(toDate,gbc);
             
		JTextField incomeAmount = new JTextField();
		gbc.gridx = 4;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.15;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		incomeAmount.setEditable(true);
		financeView.add(incomeAmount, gbc);
		
		JButton reg = new JButton(Text.getString("reg"));
		gbc.gridx = 5;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.05;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		financeView.add(reg, gbc);
		
		// Row 3
		
		JLabel pdfFDate = new JLabel(Text.getString("fDate"));
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		//gbc.anchor = GridBagConstraints.WEST;
		financeView.add(pdfFDate,gbc);
		
		JXDatePicker pdfFromDate = new JXDatePicker();
		fromDate.setDate(Calendar.getInstance().getTime());
        fromDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        financeView.add(pdfFromDate,gbc);
		
    	JLabel pdfTDate = new JLabel (Text.getString("tDate"));
    	gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		financeView.add(pdfTDate,gbc);
        
        JXDatePicker pdfToDate = new JXDatePicker();
		toDate.setDate(Calendar.getInstance().getTime());
        toDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        financeView.add(pdfToDate,gbc);
             
		JButton showPdf = new JButton(Text.getString("spdf"));
		showPdf.addActionListener(new Buttonlistener());
		gbc.gridx = 5;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.2;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		financeView.add(showPdf, gbc);
		
		// PDF View
		
		
		pdfScroll = new JScrollPane(showPDF);
		pdfScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		//gbc.anchor = GridBagConstraints.WEST;
		financeView.add(pdfScroll,gbc);
		
		JButton savePdf = new JButton(Text.getString("svpdf"));
		gbc.gridx = 5;
		gbc.gridy = 5;
		gbc.weightx = 1;
		gbc.weighty = 1;
		//gbc.anchor  = GridBagConstraints.EAST;
		financeView.add(savePdf, gbc);

		add(financeView);
		
	
		
		
		
		
	}
	
	private class Buttonlistener implements ActionListener{
		public void actionPerformed(ActionEvent f){
			PDFGenerator.generatePDF();
			try {
				Image img = PDFGenerator.showPDF();
				showPDF.setIcon(new ImageIcon(img));
				showPDF.repaint();
				System.out.println("Show PDF trykket");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
		
	}
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
