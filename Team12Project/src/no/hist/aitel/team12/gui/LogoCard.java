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
 * LogoCard.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.hist.aitel.team12.util.Text;

/**
 * This card is used several places where a place holder card is needed, to show something before the user has selected anything
 * 
 * @author Team12
 */
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
