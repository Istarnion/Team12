package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import no.hist.aitel.team12.app.PDFGenerator;
import no.hist.aitel.team12.util.Text;

import org.jdesktop.swingx.JXDatePicker;

public class FinanceTab extends SSSTab {

	private static final long serialVersionUID = 2799861967861410870L;
	
	private JPanel sidebar = new JPanel();
	
	private JLabel pdfLabel = new JLabel();
	
	private JScrollPane pdfScroll; 
	
	private  JPanel pdfView;
	
	public FinanceTab(String username){
		
		this.setLayout(new BorderLayout());
		
		GridLayout grid = new GridLayout(14, 2);	// Two columns, fourteen rows (so the bottom ones are empty)
		grid.setVgap(4);
		grid.setHgap(2);
		sidebar.setLayout(grid);
		
		add(sidebar, BorderLayout.WEST);
		
		
		// Selection View
		// Row 1
		JLabel info = new JLabel(Text.getString("storeTxt")+Text.getString("cntrTxt"));
		sidebar.add(info);
		
		sidebar.add(new JLabel());
		
		// Row 2
		JLabel fDate = new JLabel (Text.getString("fDate"), SwingConstants.RIGHT);
		sidebar.add(fDate);
		
		JXDatePicker fromDate = new JXDatePicker();
		fromDate.setDate(Calendar.getInstance().getTime());
        fromDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        sidebar.add(fromDate);
		
        // Row 3
    	JLabel tDate = new JLabel (Text.getString("tDate"), SwingConstants.RIGHT);
		sidebar.add(tDate);
        
        JXDatePicker toDate = new JXDatePicker();
		toDate.setDate(Calendar.getInstance().getTime());
        toDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        sidebar.add(toDate);
             
        // Row 4
		InputField incomeAmount = new InputField("Revenue..", 10);
		sidebar.add(incomeAmount);
		
		JButton reg = new JButton(Text.getString("reg"));
		sidebar.add(reg);
		
		// Row 5
		sidebar.add(new JLabel());
		sidebar.add(new JLabel());
		
		// Row 6
		sidebar.add(new JSeparator());
		sidebar.add(new JSeparator());
		
		// Row 7
		JLabel pdfFDate = new JLabel(Text.getString("fDate"), SwingConstants.RIGHT);
		sidebar.add(pdfFDate);
		
		JXDatePicker pdfFromDate = new JXDatePicker();
		fromDate.setDate(Calendar.getInstance().getTime());
        fromDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        sidebar.add(pdfFromDate);
		
        // Row 8
    	JLabel pdfTDate = new JLabel (Text.getString("tDate"), SwingConstants.RIGHT);
		sidebar.add(pdfTDate);
        
        JXDatePicker pdfToDate = new JXDatePicker();
		toDate.setDate(Calendar.getInstance().getTime());
        toDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        sidebar.add(pdfToDate);
             
        // Row 9
		JButton showPdf = new JButton(Text.getString("spdf"));
		//showPdf.addActionListener(new Buttonlistener());
		sidebar.add(showPdf);
		
		JButton savePdf = new JButton(Text.getString("svpdf"));
		sidebar.add(savePdf);
		
		// PDF View
		pdfView = new JPanel(new BorderLayout());
		pdfScroll = new JScrollPane(pdfView);
		pdfScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		pdfLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pdfLabel.setBorder(new LineBorder(Color.BLACK, 1));
		pdfView.add(pdfLabel, BorderLayout.CENTER);
		add(pdfScroll, BorderLayout.CENTER);

		//add(pdfView, BorderLayout.CENTER);
		
		Buttonlistener  ButtonListener = new Buttonlistener();
		showPdf.addActionListener(ButtonListener);
		reg.addActionListener(ButtonListener);
		savePdf.addActionListener(ButtonListener);
		
		
		
	}
	
	

	
	private class Buttonlistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent f){
			JButton pressedButton = (JButton)f.getSource();
			String buttonName = pressedButton.getText();
			
			if(buttonName.equals(Text.getString("spdf"))){
				PDFGenerator.generatePDF();
				Image img = PDFGenerator.showPDF();
				pdfLabel.setIcon(new ImageIcon(img));
				pdfLabel.repaint();
				System.out.println("Show PDF trykket");
			}
			else if(buttonName.equals(Text.getString("reg"))){
				
				System.out.println("Register Revenue button pressed");
			}
			else{
				JFileChooser fileSaver = new JFileChooser(); 
				int returnVal = fileSaver.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					
				}
				System.out.println("Save pdf button pressed");
			}
			 
		}
		
	}
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
