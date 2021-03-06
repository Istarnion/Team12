/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * PDFGenerator.java Team 12, 17 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

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

	/**
	 * Creates and writes to disk a pdf file with a graph generated based on the given <code>Revenue[]</code>
	 * 
	 * @param path		The path noting where to save the file
	 * @param revenues	The revenues from wich to generate the graph
	 * @param chartName	The name of the chart
	 * @param info		The info that should be added above the chart
	 */
	public static void generatePDF(String path, Revenue[] revenues, String chartName, String info){

		Document document = new Document();
		document.setPageSize(PageSize.A4);

		try(FileOutputStream fos = new FileOutputStream(path)) {
			PdfWriter writer = PdfWriter.getInstance(document, fos);

			document.open();
			//Watermark
			Image image = Image.getInstance(ImageIO.read(new PDFGenerator().getClass().getResource("/images/watermark.png")), null, false);
			image.scaleToFit(PageSize.A4);
			document.add(image);
			
			Paragraph infoParagraph = new Paragraph(info);
			
			// adds report generated in Report class 
			Paragraph paragraph = new Paragraph();
			Image img = Image.getInstance(FinanceChart.createBarGraph(chartName, revenues, 540, 540), null);
			
			
			paragraph.add(img);
			document.add(infoParagraph);
			document.add(paragraph);
			
			document.close();
			writer.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Reads the temporary budgetdoc.pdf, generates an image of it, and then deletes it.
	 * This means that <code>generatePDF()</code> should be called right before this method, so that
	 * we can be sure the file exists on disk.
	 * The deletion ensures the filesystem remains clean
	 * 
	 * @return A <code>BufferedImage</code> containing the rendered PDF document
	 */
	public static java.awt.Image showPDF() {
		File file = new File ("budgetdoc.pdf");
		Rectangle rect = null;
		int numPages = 0;
		java.awt.Image[] pages = null;

		try(
				RandomAccessFile raf = new RandomAccessFile(file, "r");
				FileChannel channel = raf.getChannel();
				) {

			ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			PDFFile pdffile = new PDFFile(buf);

			numPages = pdffile.getNumPages();
			pages = new java.awt.Image[numPages];
			PDFPage page;
			rect = null;
			for(int i=0; i<numPages; i++) {
				page = pdffile.getPage(i);
				rect = new Rectangle(
						0,0,
						(int)page.getBBox().getWidth(),
						(int)page.getBBox().getHeight());
				pages[i] = page.getImage(
						rect.width, rect.height, //width & height
						rect, // clip rect
						null, // null for the ImageObserver
						true, // fill background with white
						true  // block until drawing is done
						);
			}
			pdffile.stop(pdffile.getNumPages()-1);
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		System.gc();
		file.delete();
		
		int spacing = 5;
		BufferedImage img = new BufferedImage(rect.width, (rect.height+spacing)*numPages-spacing, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		for(int i=0; i<numPages; i++) {
			g.drawImage(pages[i], 0, i*(rect.height+spacing), rect.width, rect.height, null);
		}
		g.dispose();
		
		return img;

	}
}
