package no.hist.aitel.team12.app;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYBarDataset;


public class FinanceChart {
	
	public static BufferedImage createBarGraph(String chartName, Revenue[] revenues) {
		
		final DefaultXYDataset dataset = new DefaultXYDataset(); 
		
		double[][] data = new double[2][revenues.length];
		double barWidth = 500.0/revenues.length;
		double x = 0;
		
		int index = 0;
		for(Revenue r : revenues) {
			data[0][index] = x;
			x+=barWidth;
			data[1][index] = r.getTurnover();
			
			index++;
		}
		
		dataset.addSeries(new Integer(1), data);
		
		final XYBarDataset ds = new XYBarDataset(dataset, (500.0/revenues.length));
		
		JFreeChart chart = ChartFactory.createXYBarChart(chartName, "Month", false, "Revenue", ds);
		chart.removeLegend();
		
		
		
		return chart.createBufferedImage(500, 500);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		
		Revenue[] rs = new Revenue[12];
		for(int i=0; i<rs.length; i++) {
			rs[i] = new Revenue(new Date(1000000000000L), i*500+1000);
		}
		
		JLabel label = new JLabel(new ImageIcon(createBarGraph("Test", rs)));
		panel.add(label);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}





