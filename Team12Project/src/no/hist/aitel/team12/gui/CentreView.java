package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.jfree.io.IOUtils;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import netscape.javascript.JSObject;
import no.hist.aitel.team12.app.MapGson;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.util.Text;

public class CentreView extends JPanel {

	private Box	mainBox;
	private Box cAdrBox;
	private Box cNameBox;
	private Box cOpenBox;
	private Box cParkBox;
	private Box cAreaBox;
	private Box cEmailBox;
	private Box cPhoneBox;
	private Box cDescriptBox;
	private Box contactCSBox;
	private Box mapBox;

	private JLabel cAdrLabel;
	private JLabel cAdr;
	private JLabel cNameLabel;
	private JLabel cName;
	private JLabel cOpenLabel;
	private JLabel cOpen;
	private JLabel cPark;
	private JLabel cParkLabel;
	private JLabel cAreaLabel;
	private JLabel cArea;
	private JLabel cEmailLabel;
	private JLabel cEmail;
	private JLabel cPhoneLabel;
	private JLabel cPhone;
	private JLabel cDescriptLabel;
	private JLabel contactCSLabel;

	private JTextArea cDescript;

	private JScrollPane scroll;

	private JButton contactCS;

	private JMapViewer map;
	private static final long serialVersionUID = -4615331862168657390L;

	private CustomerView cv;

	private int centreID = 0;

	private String centreName = null;

	private ShoppingCentre centre;
	public CentreView(CustomerView cv) {


		
		
		this.cv = cv;

		cNameBox = new Box(BoxLayout.X_AXIS);
		cNameLabel = new JLabel(Text.getString("name")+":");
		cNameLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		cNameLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cName = new JLabel();
		cNameBox.add(cNameLabel);
		cNameBox.add(Box.createGlue());
		cNameBox.add(cName);

		cAdrBox = new Box(BoxLayout.X_AXIS);
		cAdrLabel = new JLabel((Text.getString("adr")+":"));
		cAdrLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		cAdrLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cAdr = new JLabel();
		cAdr.setBackground(Color.RED);
		cAdrBox.add(cAdrLabel);
		cAdrBox.add(Box.createGlue());
		cAdrBox.add(cAdr);

		cOpenBox = new Box(BoxLayout.X_AXIS);
		cOpenLabel = new JLabel(Text.getString("openingHrs")+":");
		cOpenLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		cOpenLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cOpen = new JLabel();
		cOpenBox.add(cOpenLabel);
		cOpenBox.add(Box.createGlue());
		cOpenBox.add(cOpen);

		cParkBox = new Box(BoxLayout.X_AXIS);
		cParkLabel = new JLabel(Text.getString("park")+":");
		cParkLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		cParkLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cPark = new JLabel();
		cParkBox.add(cParkLabel);
		cParkBox.add(Box.createGlue());
		cParkBox.add(cPark);

		cAreaBox = new Box(BoxLayout.X_AXIS);
		cAreaLabel = new JLabel(Text.getString("area")+":");
		cAreaLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		cAreaLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cArea = new JLabel();
		cAreaBox.add(cAreaLabel);
		cAreaBox.add(Box.createGlue());
		cAreaBox.add(cArea);

		cEmailBox = new Box(BoxLayout.X_AXIS);
		cEmailLabel = new JLabel(Text.getString("email")+":");
		cEmailLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		cEmailLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cEmail = new JLabel();
		cEmailBox.add(cEmailLabel);
		cEmailBox.add(Box.createGlue());
		cEmailBox.add(cEmail);

		cPhoneBox = new Box(BoxLayout.X_AXIS);
		cPhoneLabel = new JLabel(Text.getString("tel")+":");
		cPhoneLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		cPhoneLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cPhone = new JLabel();
		cPhoneBox.add(cPhoneLabel);
		cPhoneBox.add(Box.createGlue());
		cPhoneBox.add(cPhone);

		cDescriptBox = new Box(BoxLayout.X_AXIS);
		cDescriptLabel = new JLabel(Text.getString("textDescription")+":");
		cDescriptLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		cDescriptLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cDescript = new JTextArea(10,65);
		cDescript.setLineWrap(true);
		cDescript.setWrapStyleWord(true);
		cDescript.setEditable(false);
		cDescript.setBorder(new EmptyBorder(5, 5, 5 ,5));
		scroll = new JScrollPane(cDescript);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		cDescriptBox.add(cDescriptLabel);
		cDescriptBox.add(Box.createGlue());
		cDescriptBox.add(scroll);

		contactCSBox = new Box(BoxLayout.X_AXIS);
		contactCSLabel = new JLabel();
		contactCSLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		contactCS = new JButton(Text.getString("contactCS"));
		contactCSBox.add(contactCSLabel);
		contactCSBox.add(Box.createGlue());
		contactCSBox.add(contactCS);

		
		mainBox = new Box(BoxLayout.Y_AXIS);
		mainBox.add(cNameBox);
		mainBox.add(cAdrBox);
		mainBox.add(cOpenBox);
		mainBox.add(cParkBox);
		mainBox.add(cAreaBox);
		mainBox.add(cEmailBox);
		mainBox.add(cPhoneBox);
		mainBox.add(cDescriptBox);
		mainBox.add(contactCSBox);

		// Creating and adding map
		map = new JMapViewer();
		map.setPreferredSize(new Dimension(10, 300));
//		map.setBorder(new EmptyBorder(20, 120, 20 ,20));
		mapBox = new Box(BoxLayout.X_AXIS);
		mapBox.add(map);
		mainBox.add(mapBox);


		setLayout(new BorderLayout());
		add(mainBox, BorderLayout.WEST);

		Buttonlistener pushed = new Buttonlistener();
		contactCS.addActionListener(pushed);
	}

	private class Buttonlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			cv.gotoCustomerCSView(centreID, centreName);
		}
	}

	public void updateCard(ShoppingCentre centre) {
		this.centre = centre;
		centreID = centre.getCentreId();
		centreName = centre.getBusinessName();

		cName.setText(centre.getBusinessName());
		cAdr.setText(centre.getAddress().toShortString());

		if(centre.getOpeningHours() != null){

			String openingHours =centre.getOpeningHours();
			cOpen.setText("("+openingHours.substring(0,2)+"-"+openingHours.substring(2,4)+"("+openingHours.substring(4,6)+"-"+openingHours.substring(6,8)+"))");
		}

		cPark.setText(""+ centre.getParkingSpaces());
		cArea.setText(""+centre.getArea());
		cEmail.setText(""+centre.getEmail().getEmailAddress());
		cPhone.setText(""+centre.getTelephone());
		cDescript.setText(centre.getDescription());
		mapSetup();

	}

	public boolean mapSetup() {
		double lat = 0.0;
		double lon = 0.0;

		try {
			
			String argument = centre.getMapString();
			String cUrl = String.format("http://nominatim.openstreetmap.org/search/%s?format=json&addressdetails=1&limit=1", argument);
			URL url = new URL(cUrl);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = reader.readLine();
			JsonParser parser = new JsonParser();
			JsonArray o = (JsonArray)parser.parse(s);
			Gson resGson = new Gson();
			MapGson[] enums = resGson.fromJson(s, MapGson[].class);
			MapGson test = enums[0];
			lat = Double.parseDouble(test.getLat());
			lon = Double.parseDouble(test.getLon());
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		map.setDisplayPosition(new Coordinate(lat, lon), 15);
		return true;
	}
}
