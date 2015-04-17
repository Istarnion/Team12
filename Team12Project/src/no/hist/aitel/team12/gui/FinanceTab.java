package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

	private JXDatePicker fromDate;

	private JXDatePicker toDate;

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
		JLabel fDate = new JLabel (Text.getString("fDate"), SwingConstants.RIGHT);
		sidebar.add(fDate);

		fromDate = new JXDatePicker();
		fromDate.setDate(Calendar.getInstance().getTime());
		fromDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		sidebar.add(fromDate);

		// Row 3
		JLabel tDate = new JLabel (Text.getString("tDate"), SwingConstants.RIGHT);
		sidebar.add(tDate);

		toDate = new JXDatePicker();
		toDate.setDate(Calendar.getInstance().getTime());
		toDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		sidebar.add(toDate);

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
		fromDate.setDate(Calendar.getInstance().getTime());
		fromDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		sidebar.add(pdfFromDate);

		// Row 8
		JLabel pdfTDate = new JLabel (Text.getString("tDate"), SwingConstants.RIGHT);
		sidebar.add(pdfTDate);

		pdfToDate = new JXDatePicker();
		toDate.setDate(Calendar.getInstance().getTime());
		toDate.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
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
						PDFGenerator.generatePDF();
						Image img = PDFGenerator.showPDF();
						pdfLabel.setIcon(new ImageIcon(img));
						pdfLabel.repaint();
						System.out.println("Show PDF trykket");
					}else{
						JOptionPane.showMessageDialog(null, Text.getString("daterr"));
					}
				}else{
					JOptionPane.showMessageDialog(null, Text.getString("pdfdaterr"));
				}

			}
			else if(buttonName.equals(Text.getString("reg"))){
				if(fromDate.getDate()!=null && toDate.getDate()!=null){
					long revenue;
					Calendar calFrom = Calendar.getInstance();
					calFrom.setTime(fromDate.getDate());
					Calendar  calTo = Calendar.getInstance();
					calTo.setTime(toDate.getDate());

					if(calFrom.before(calTo)||calFrom.equals(calTo)){

						try{
							revenue = Long.parseLong(incomeAmount.getText());
							int yearFrom = calFrom.get(Calendar.YEAR);
							String monthFrom  = calFrom.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.getDefault());

							int yearTo = calTo.get(Calendar.YEAR);
							String monthTo = calTo.getDisplayName(Calendar.MONTH,Calendar.LONG_FORMAT,Locale.getDefault());

							System.out.println("Register Revenue button pressed"+"\n"+ "Dates selected"+ "From date: "+ monthFrom +yearFrom + " To date: "+ monthTo + yearTo + revenue);

						}catch(NumberFormatException nfe){
							JOptionPane.showMessageDialog(null, Text.getString("reverr"));
						}
					}else{
						JOptionPane.showMessageDialog(null, Text.getString("daterr"));
					}
				}else{
					JOptionPane.showMessageDialog(null, Text.getString("ravdaterr"));
				}

			}
			else{
				JFileChooser fileSaver = new JFileChooser(); 
				int returnVal = fileSaver.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File outFile = fileSaver.getSelectedFile();
					if(outFile!=null){
						
					}
				
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
