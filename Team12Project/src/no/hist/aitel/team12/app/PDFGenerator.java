package no.hist.aitel.team12.app;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;




import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;




/**
 * This class takes the information from the Report class and generate a .pdf file
 * with that information. The class uses the the iText library to create the .pdf file.
 * 
 * @author Andreas 
 * @version 1.0
 *
 */


public class PDFGenerator {


	public static void generatePDF(){


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
			paragraph.add("This is a test 1");
			document.add(paragraph);
			document.close();
		} catch (Exception e){
			e.printStackTrace();
		}


	}

	public static java.awt.Image showPDF() throws IOException{
		
		File file = new File ("budgetdoc.pdf");
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		FileChannel channel = raf.getChannel();
		ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
		PDFFile pdffile = new PDFFile(buf);

		PDFPage page = pdffile.getPage(0);
		
		
		Rectangle rect = new Rectangle(0,0,
				(int)page.getBBox().getWidth(),
				(int)page.getBBox().getHeight());

		java.awt.Image img = page.getImage(
				rect.width, rect.height, //width & height
				rect, // clip rect
				null, // null for the ImageObserver
				true, // fill background with white
				true  // block until drawing is done
				);
		channel.close();
		return img;

	}
}
