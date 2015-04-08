package no.hist.aitel.team12.app;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;


public class PDFGenerator {

	public static void main (String[]args){
		
		Document document = new Document();
		document.setPageSize(PageSize.A4);
		
		try{
		PdfWriter.getInstance(document, new FileOutputStream("budgetdoc.pdf"));
		
		document.open();
		//Watermark
		Image image1 = Image.getInstance("Resources/images/watermark1.png");
		image1.scaleToFit(PageSize.A4);
		document.add(image1);
		// adds report generated in Report class 
		Paragraph paragraph = new Paragraph();
		paragraph.add("This is a test");
		document.add(paragraph);
		document.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	
	
	}
	
}
