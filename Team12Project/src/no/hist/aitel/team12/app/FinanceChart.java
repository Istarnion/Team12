package no.hist.aitel.team12.app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import no.hist.aitel.team12.util.Text;


public class FinanceChart {
	
	private final static int margin = 30;
	
	private final static long defaultHeight = 5000L;
	
	private final static long defaultMinHeight = 0;
	
	private final static int defaultBarWidth = 100;
	
	private final static Color mainColor = Color.BLACK;
	
	private final static Color borderColor = Color.ORANGE;
	
	private final static DecimalFormat format = new DecimalFormat("0.#");
	
	public static BufferedImage createBarGraph(String chartName, Revenue[] revenues, int width, int height) {
		
		
		long maxHeight = defaultHeight;
		long minHeight = defaultMinHeight;
		for(Revenue r : revenues) {
			if(r.getTurnover() > maxHeight) maxHeight = r.getTurnover();
			else if(r.getTurnover() < minHeight) minHeight = r.getTurnover();
		}
		int barHeight = height-margin*2;
		int barWidth = defaultBarWidth;
		if(revenues.length * defaultBarWidth > width+margin*3) {
			barWidth = (width-margin*3)/revenues.length;
		}
		
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		
		g.setStroke(new BasicStroke(3));
		
		g.setColor(Color.black);
		g.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		g.drawString(chartName, (int)(margin*0.5), margin-5);
		
		int h;
		for(int i=0; i<revenues.length; i++) {
			h = (int)(barHeight*normalize(minHeight, maxHeight, revenues[i].getTurnover()));
			g.setColor(mainColor);
			g.fillRect((margin+1)+i*barWidth, (height-margin)-h, barWidth, h);
			g.setColor(borderColor);
			g.drawRect((margin+1)+i*barWidth, (height-margin)-h, barWidth, h);
		}
		
		g.setColor(mainColor);
		g.drawLine(margin, margin, margin, height-margin); // y-axis
		g.drawLine(margin, height-margin, width-margin, height-margin);
		
		g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 10));
		g.drawString(format.format((maxHeight/1000.0))+Text.getString("$"), 0, margin*2);
		g.drawString(format.format((minHeight/1000.0))+Text.getString("$"), 0, height-margin);
		
		g.drawString(revenues[0].getMonth().toString(), 10, height-10);
		g.drawString(revenues[revenues.length-1].getMonth().toString(), width-margin*2, height-10);
		
		g.dispose();
		
		return img;
	}
	
	private static double normalize(long min, long max, long val) {
		return val/((double)max-(double)min);
	}
}
