package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

import no.hist.aitel.team12.app.PDFGenerator;
import no.hist.aitel.team12.app.Revenue;
import no.hist.aitel.team12.util.Text;

import org.jdesktop.swingx.JXDatePicker;

public class FinanceTab extends SSSTab {

	private static final long serialVersionUID = 2799861967861410870L;

	private JPanel sidebar = new JPanel();

	private JLabel pdfLabel = new JLabel();

	private JScrollPane pdfScroll; 

	private  JPanel pdfView;

	private JXDatePicker regRevDate;

	private JXDatePicker pdfFromDate;

	private JXDatePicker pdfToDate;

	private InputField incomeAmount;

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
		sidebar.add(new JLabel());
		sidebar.add(new JLabel());
		
		JLabel fDate = new JLabel (Text.getString("fDate"), SwingConstants.RIGHT);
		sidebar.add(fDate);

		regRevDate = new JXDatePicker();
		regRevDate.setDate(Calendar.getInstance().getTime());
		regRevDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		sidebar.add(regRevDate);

		
	/*	// Row 3
		//	JLabel tDate = new JLabel (Text.getString("tDate"), SwingConstants.RIGHT);
		sidebar.add(new JLabel());

		//	toDate = new JXDatePicker();
		//	toDate.setDate(Calendar.getInstance().getTime());
		//	toDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		sidebar.add(new JLabel());
*/
		// Row 4
		incomeAmount = new InputField(Text.getString("rev"), 10);
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

		pdfFromDate = new JXDatePicker();
		pdfFromDate.setDate(Calendar.getInstance().getTime());
		pdfFromDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		sidebar.add(pdfFromDate);

		// Row 8
		JLabel pdfTDate = new JLabel (Text.getString("tDate"), SwingConstants.RIGHT);
		sidebar.add(pdfTDate);

		pdfToDate = new JXDatePicker();
		pdfToDate.setDate(Calendar.getInstance().getTime());
		pdfToDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		sidebar.add(pdfToDate);

		// Row 9
		JButton showPdf = new JButton(Text.getString("spdf"));
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

				if(pdfFromDate.getDate()!=null && pdfToDate.getDate()!=null){
					Calendar pdfCalF = Calendar.getInstance();
					Calendar pdfCalT = Calendar.getInstance();
					pdfCalF.setTime(pdfFromDate.getDate());
					pdfCalT.setTime(pdfToDate.getDate());

					if(pdfCalF.before(pdfCalT)||pdfCalF.equals(pdfCalT)){
						SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

							@Override
							protected Void doInBackground() throws Exception {

								Revenue[] rs = new Revenue[12];
								for(int i=0; i<rs.length; i++) {
									rs[i] = new Revenue(new Date(1000000000000L), i*500+1000);
								}

								PDFGenerator.generatePDF("budgetdoc.pdf", rs, "Chart");
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


					}else{
						JOptionPane.showMessageDialog(null, Text.getString("daterr"));
					}
				}else{
					JOptionPane.showMessageDialog(null, Text.getString("pdferr"));
				}

			}
			else if(buttonName.equals(Text.getString("reg"))){
				if(regRevDate.getDate()!=null){
					long revenue;
					Calendar calFrom = Calendar.getInstance();
					calFrom.setTime(regRevDate.getDate());

					try{
						revenue = Long.parseLong(incomeAmount.getText());
						int yearFrom = calFrom.get(Calendar.YEAR);
						String monthFrom  = calFrom.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.getDefault());

						System.out.println("Register Revenue button pressed"+"\n"+ "Dates selected"+ "From date: "+ monthFrom +yearFrom + " To date: "+ revenue);


					}catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, Text.getString("reverr"));
					}
				}else{
					JOptionPane.showMessageDialog(null, Text.getString("daterr"));
				}
			}else{
				JOptionPane.showMessageDialog(null, Text.getString("revdaterr"));
			}

		
			if(pdfFromDate.getDate()!=null && pdfToDate.getDate()!=null){
				Calendar pdfCalF = Calendar.getInstance();
				Calendar pdfCalT = Calendar.getInstance();
				pdfCalF.setTime(pdfFromDate.getDate());
				pdfCalT.setTime(pdfToDate.getDate());

				if(pdfCalF.before(pdfCalT)||pdfCalF.equals(pdfCalT)){
					savePDF();
				}else{
					JOptionPane.showMessageDialog(null, Text.getString("daterr"));
				}
			}else{
				JOptionPane.showMessageDialog(null, Text.getString("pdferr"));		

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
			Revenue[] rs = new Revenue[12];
			for(int i=0; i<rs.length; i++) {
				rs[i] = new Revenue(new Date(1000000000000L), i*500+1000);
			}

			PDFGenerator.generatePDF(chooser.getSelectedFile().getPath(), rs, "Chart");
			File file = chooser.getSelectedFile();
			String file_name = file.toString();
			if (!file_name.endsWith(".pdf")){
				file_name += ".pdf";
			}
		}
	}



@Override
public void refresh() {
	// TODO Auto-generated method stub

}
}
