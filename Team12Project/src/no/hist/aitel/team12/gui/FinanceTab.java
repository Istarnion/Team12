package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import no.hist.aitel.team12.app.DataBuffer;
import no.hist.aitel.team12.app.PDFGenerator;
import no.hist.aitel.team12.app.Revenue;
import no.hist.aitel.team12.app.UserType;
import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.Text;

public class FinanceTab extends SSSTab {

	private static final long serialVersionUID = 2799861967861410870L;

	private JLabel info;
	
	private JPanel sidebar = new JPanel();

	private JLabel pdfLabel = new JLabel();

	private JScrollPane pdfScroll; 

	private JPanel pdfView;

	private MonthPicker regRevDate;

	private MonthPicker pdfFromDate;

	private MonthPicker pdfToDate;

	private InputField incomeAmount;

	private JButton regButton, showButton, saveButton;

	private int businessID;
	
	private String businessName = null;
	
	private String businessAddress = null;
	
	public FinanceTab(String username, int userID, UserType userType){
		
		Database db = DatabaseFactory.getDatabase();
		businessID = db.getBusinessID(userID);
		
		this.setLayout(new BorderLayout());

		GridLayout grid = new GridLayout(14, 2);	// Two columns, fourteen rows (so the bottom ones are empty)
		grid.setVgap(4);
		grid.setHgap(2);
		sidebar.setLayout(grid);

		add(sidebar, BorderLayout.WEST);

		// Selection View
		// Row 1
		info = new JLabel();
		sidebar.add(info);

		sidebar.add(new JLabel());

		// Row 2
		sidebar.add(new JLabel());
		sidebar.add(new JLabel());

		JLabel fDate = new JLabel (Text.getString("month")+": ", SwingConstants.RIGHT);
		sidebar.add(fDate);

		regRevDate = new MonthPicker();
		sidebar.add(regRevDate);

		// Row 4
		incomeAmount = new InputField(Text.getString("rev"), 10);
		sidebar.add(incomeAmount);

		regButton = new JButton(Text.getString("reg"));
		sidebar.add(regButton);

		// Row 5
		sidebar.add(new JLabel());
		sidebar.add(new JLabel());

		// Row 6
		sidebar.add(new JSeparator());
		sidebar.add(new JSeparator());

		// Row 7
		JLabel pdfFDate = new JLabel(Text.getString("fDate"), SwingConstants.RIGHT);
		sidebar.add(pdfFDate);

		pdfFromDate = new MonthPicker();
		sidebar.add(pdfFromDate);

		// Row 8
		JLabel pdfTDate = new JLabel (Text.getString("tDate"), SwingConstants.RIGHT);
		sidebar.add(pdfTDate);

		pdfToDate = new MonthPicker();
		sidebar.add(pdfToDate);

		// Row 9
		showButton = new JButton(Text.getString("spdf"));
		sidebar.add(showButton);

		saveButton = new JButton(Text.getString("svpdf"));
		sidebar.add(saveButton);

		// PDF View
		pdfView = new JPanel(new BorderLayout());
		pdfScroll = new JScrollPane(pdfView);
		pdfScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		pdfLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pdfLabel.setBorder(new LineBorder(Color.BLACK, 1));
		pdfView.add(pdfLabel, BorderLayout.CENTER);
		add(pdfScroll, BorderLayout.CENTER);

		RegisterButtonlistener  regListener = new RegisterButtonlistener();
		regButton.addActionListener(regListener);

		PdfButtonListener pdfListener = new PdfButtonListener();
		showButton.addActionListener(pdfListener);
		saveButton.addActionListener(pdfListener);

	}

	private class RegisterButtonlistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent f){
			JButton pressedButton = (JButton)f.getSource();

			if(pressedButton.equals(regButton)) {
				if(regRevDate.getSelectedDate()!=null){
					long revenue;
					Calendar calFrom = Calendar.getInstance();
					calFrom.setTime(regRevDate.getSelectedDate());

					try {
						revenue = Long.parseLong(incomeAmount.getText());

						if(Revenue.registerTurnover(calFrom.getTime(), revenue, businessID)) {
							JOptionPane.showMessageDialog(null, Text.getString("revReg"));
						}
						else {
							JOptionPane.showMessageDialog(null, Text.getString("dbErr"));
						}
					}
					catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, Text.getString("reverr"));
					}
				}
				else{
					JOptionPane.showMessageDialog(null, Text.getString("daterr"));
				}
			}
			else{
				JOptionPane.showMessageDialog(null, Text.getString("revdaterr"));
			}
		}
	}

	private class PdfButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton pressedButton = (JButton)e.getSource();

			if(pressedButton.equals(showButton)) {

				if(pdfFromDate.getSelectedDate()!=null && pdfToDate.getSelectedDate()!=null){
					Calendar pdfCalF = Calendar.getInstance();
					Calendar pdfCalT = Calendar.getInstance();
					pdfCalF.setTime(pdfFromDate.getSelectedDate());
					pdfCalT.setTime(pdfToDate.getSelectedDate());

					if(pdfCalF.before(pdfCalT)||pdfCalF.equals(pdfCalT)) {
						SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

							@Override
							protected Void doInBackground() throws Exception {

								Database db = DatabaseFactory.getDatabase();
								
								Revenue[] rs = db.getRevenue(
										businessID,
										new java.sql.Date(pdfFromDate.getSelectedDate().getTime()),
										new java.sql.Date(pdfToDate.getSelectedDate().getTime())
										);

								PDFGenerator.generatePDF(
										"budgetdoc.pdf", rs, Text.getString("rev")+":",
										businessName+"\n"+businessAddress);
								Image img = PDFGenerator.showPDF();
								pdfLabel.setIcon(new ImageIcon(img));
								pdfLabel.repaint();
								return null;
							}

							@Override
							protected void done() {
								pressedButton.setText(Text.getString("spdf"));
								pressedButton.setEnabled(true);
							}

						};
						pressedButton.setText(Text.getString("generating"));
						pressedButton.setEnabled(false);
						worker.execute();


					}
					else{
						JOptionPane.showMessageDialog(null, Text.getString("daterr"));
					}
				}
				else{
					JOptionPane.showMessageDialog(null, Text.getString("pdferr"));
				}

			}
			else if(pressedButton.equals(saveButton)) {
				if(pdfFromDate.getSelectedDate()!=null && pdfToDate.getSelectedDate()!=null) {
					Calendar pdfCalF = Calendar.getInstance();
					Calendar pdfCalT = Calendar.getInstance();
					pdfCalF.setTime(pdfFromDate.getSelectedDate());
					pdfCalT.setTime(pdfToDate.getSelectedDate());

					if(pdfCalF.before(pdfCalT)||pdfCalF.equals(pdfCalT)) {
						savePDF();
					}
					else {
						JOptionPane.showMessageDialog(null, Text.getString("daterr"));
					}
				}
				else {
					JOptionPane.showMessageDialog(null, Text.getString("pdferr"));		

				}
			}
		}

	}

	void savePDF() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF","pdf");
		chooser.setFileFilter(filter);
		chooser.setSelectedFile(new File("revenue.pdf"));
		int result = chooser.showSaveDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			Database db = DatabaseFactory.getDatabase();
			
			Revenue[] rs = db.getRevenue(
					businessID,
					new java.sql.Date(pdfFromDate.getSelectedDate().getTime()),
					new java.sql.Date(pdfToDate.getSelectedDate().getTime())
					);

			File file = chooser.getSelectedFile();
			String filePath = file.getPath();
			if (!filePath.endsWith(".pdf")){
				filePath += ".pdf";
			}
			
			PDFGenerator.generatePDF(
					filePath, rs, Text.getString("rev")+":",
					businessName+"\n"+businessAddress);
		}
	}

	@Override
	public void refresh() {
		if(businessName == null) {
			while(businessName == null) {
				businessName = DataBuffer.getCentres()[0].getBusinessName();
				businessAddress = DataBuffer.getCentres()[0].getAddress().toString();
			}
			
			info.setText(businessName);
		}
	}
}
