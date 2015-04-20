package no.hist.aitel.team12.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.hist.aitel.team12.util.Text;

public class LogoCard extends JPanel {

	private static final long serialVersionUID = -8151653104927709218L;
	
	private BufferedImage logo;
	
	private JLabel logoLabel;

	public LogoCard() {
		logoLabel = new JLabel();
		try {
			logo = ImageIO.read(getClass().getResource("/images/simpleLogo.png"));
			logoLabel.setIcon(new ImageIcon(logo));
		} catch (IOException e1) {
			e1.printStackTrace();
			logoLabel.setText(Text.getString("logoFailed"));
		}
		this.add(logoLabel);
		
	}
}
