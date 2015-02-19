package no.hist.aitel.team12.resources;

import java.util.ListResourceBundle;

public class TextResources_no extends ListResourceBundle {

	public TextResources_no() {
	}

	@Override
	protected Object[][] getContents() {
		return new Object[][] {
				{"login", "Logg inn"},
				{"y", "Ja"},
				{"n", "Nei"}
		};
	}

}
