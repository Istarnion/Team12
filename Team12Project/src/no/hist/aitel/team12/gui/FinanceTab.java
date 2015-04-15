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
	
	private  JPanel pdfView;
	
	
	public FinanceTab(String username){
		
		financeView.setLayout(new GridBagLayout());
		GridBagConstraints slctcons = new GridBagConstraints();
		add(financeView);
		
		// Colors for Debugging
		
		financeView.setBackground(Color.BLUE);
		
		
		// Selection View
		// Row 1
		JLabel info = new JLabel(Text.getString("storeTxt")+Text.getString("cntrTxt"));
		slctcons.gridx = 0;
		slctcons.gridy = 1;
		slctcons.gridwidth = 5;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		financeView.add(info, slctcons);
		
		// Row 2
		JLabel fDate = new JLabel (Text.getString("fDate"));
		slctcons.gridx = 0;
		slctcons.gridy = 2;
		slctcons.gridwidth = 1;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		financeView.add(fDate,slctcons);
		
		JXDatePicker fromDate = new JXDatePicker();
		fromDate.setDate(Calendar.getInstance().getTime());
        fromDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        slctcons.gridx = 1;
        slctcons.gridy = 2;
        slctcons.gridwidth = 1;
        slctcons.gridheight = 1;
        slctcons.weightx = 1;
        slctcons.weighty = .025;
        slctcons.fill = GridBagConstraints.HORIZONTAL;
        financeView.add(fromDate,slctcons);
		
    	JLabel tDate = new JLabel (Text.getString("tDate"));
    	slctcons.gridx = 2;
		slctcons.gridy = 2;
		slctcons.gridwidth = 1;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		financeView.add(tDate,slctcons);
        
        JXDatePicker toDate = new JXDatePicker();
		toDate.setDate(Calendar.getInstance().getTime());
        toDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        slctcons.gridx = 3;
        slctcons.gridy = 2;
        slctcons.gridwidth = 1;
        slctcons.gridheight = 1;
        slctcons.weightx = 1;
        slctcons.weighty = .025;
        slctcons.fill = GridBagConstraints.HORIZONTAL;
        financeView.add(toDate,slctcons);
             
		JTextField incomeAmount = new JTextField();
		slctcons.gridx = 4;
		slctcons.gridy = 2;
		slctcons.gridwidth = 1;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		incomeAmount.setEditable(true);
		financeView.add(incomeAmount, slctcons);
		
		JButton reg = new JButton(Text.getString("reg"));
		slctcons.gridx = 5;
		slctcons.gridy = 2;
		slctcons.gridwidth = 1;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		financeView.add(reg, slctcons);
		
		// Row 3
		
		JLabel pdfFDate = new JLabel(Text.getString("fDate"));
		slctcons.gridx = 0;
		slctcons.gridy = 3;
		slctcons.gridwidth = 1;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		financeView.add(pdfFDate,slctcons);
		
		JXDatePicker pdfFromDate = new JXDatePicker();
		fromDate.setDate(Calendar.getInstance().getTime());
        fromDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        slctcons.gridx = 1;
        slctcons.gridy = 3;
        slctcons.gridwidth = 1;
        slctcons.gridheight = 1;
        slctcons.weightx = 1;
        slctcons.weighty = .025;
        slctcons.fill = GridBagConstraints.HORIZONTAL;
        financeView.add(pdfFromDate,slctcons);
		
    	JLabel pdfTDate = new JLabel (Text.getString("tDate"));
    	slctcons.gridx = 2;
		slctcons.gridy = 3;
		slctcons.gridwidth = 1;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		financeView.add(pdfTDate,slctcons);
        
        JXDatePicker pdfToDate = new JXDatePicker();
		toDate.setDate(Calendar.getInstance().getTime());
        toDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        slctcons.gridx = 3;
        slctcons.gridy = 3;
        slctcons.gridwidth = 1;
        slctcons.gridheight = 1;
        slctcons.weightx = 1;
        slctcons.weighty = .025;
        slctcons.fill = GridBagConstraints.HORIZONTAL;
        financeView.add(pdfToDate,slctcons);
             
		JButton showPdf = new JButton(Text.getString("spdf"));
		showPdf.addActionListener(new Buttonlistener());
		slctcons.gridx = 5;
		slctcons.gridy = 3;
		slctcons.gridwidth = 1;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		financeView.add(showPdf, slctcons);
		
		// PDF View
		
		
		pdfScroll = new JScrollPane(showPDF);
		pdfScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		slctcons.gridx = 0;
		slctcons.gridy = 4;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.BOTH;
		financeView.add(pdfScroll,slctcons);
		
		JButton savePdf = new JButton(Text.getString("svpdf"));
		slctcons.gridx = 5;
		slctcons.gridy = 5;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		financeView.add(savePdf, slctcons);

	
		
	
		
		
		
		
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
