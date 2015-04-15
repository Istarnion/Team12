package no.hist.aitel.team12.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import no.hist.aitel.team12.util.Text;

public class FinanceTab extends SSSTab {

	private static final long serialVersionUID = 2799861967861410870L;
	
	private JPanel financeView = new JPanel();
	
	private JPanel selection = new JPanel(); 
	
	private JPanel pdfView = new JPanel();
	
	private JTextArea pdfText;
	
	
	
	
	public FinanceTab(String username){
		
		financeView.setLayout(new GridLayout(2,1));
		financeView.add(selection);
		financeView.add(pdfView);
		add(financeView);
		
		// Colors for Debugging
		
		financeView.setBackground(Color.BLUE);
		pdfView.setBackground(Color.GREEN);
		selection.setBackground(Color.RED);
		
		
		selection.setLayout(new GridBagLayout());
		GridBagConstraints slctcons = new GridBagConstraints();
		
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
		selection.add(info, slctcons);
		
		// Row 2
		JLabel fDate = new JLabel (Text.getString("fDate"));
		slctcons.gridx = 0;
		slctcons.gridy = 2;
		slctcons.gridwidth = 1;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		selection.add(fDate,slctcons);
		
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
        selection.add(fromDate,slctcons);
		
    	JLabel tDate = new JLabel (Text.getString("tDate"));
    	slctcons.gridx = 2;
		slctcons.gridy = 2;
		slctcons.gridwidth = 1;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		selection.add(tDate,slctcons);
        
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
        selection.add(toDate,slctcons);
             
		JTextField incomeAmount = new JTextField();
		slctcons.gridx = 4;
		slctcons.gridy = 2;
		slctcons.gridwidth = 1;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		incomeAmount.setEditable(true);
		selection.add(incomeAmount, slctcons);
		
		JButton reg = new JButton(Text.getString("reg"));
		slctcons.gridx = 5;
		slctcons.gridy = 2;
		slctcons.gridwidth = 1;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		selection.add(reg, slctcons);
		
		// Row 3
		
		JLabel pdfFDate = new JLabel(Text.getString("fDate"));
		slctcons.gridx = 0;
		slctcons.gridy = 3;
		slctcons.gridwidth = 1;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		selection.add(pdfFDate,slctcons);
		
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
        selection.add(pdfFromDate,slctcons);
		
    	JLabel pdfTDate = new JLabel (Text.getString("tDate"));
    	slctcons.gridx = 2;
		slctcons.gridy = 3;
		slctcons.gridwidth = 1;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		selection.add(pdfTDate,slctcons);
        
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
        selection.add(pdfToDate,slctcons);
             
		JButton showPdf = new JButton(Text.getString("spdf"));
		slctcons.gridx = 5;
		slctcons.gridy = 3;
		slctcons.gridwidth = 1;
		slctcons.gridheight = 1;
		slctcons.weightx = 1;
		slctcons.weighty = .025;
		slctcons.fill = GridBagConstraints.HORIZONTAL;
		selection.add(showPdf, slctcons);
		
		
		
		// PDF View
		pdfView.setLayout(new GridBagLayout());
		GridBagConstraints pvc = new GridBagConstraints();
		
		pdfText = new JTextArea();
		pvc.gridx = 0;
		pvc.gridy = 0;
		pvc.weightx = 1;
		pvc.weighty = .025;
		pvc.fill = GridBagConstraints.BOTH;
		pdfView.add(pdfText, pvc);
		
		JButton savePdf = new JButton(Text.getString("svpdf"));
		pvc.gridx = 1;
		pvc.gridy = 1;
		pvc.weightx = 1;
		pvc.weighty = .025;
		pdfView.add(savePdf, pvc);

	
		
	
		
		
		
		
	}
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
