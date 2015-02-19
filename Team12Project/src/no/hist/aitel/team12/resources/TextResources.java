package no.hist.aitel.team12.resources;

import java.util.ListResourceBundle;

public class TextResources extends ListResourceBundle {

	public TextResources() {
	}

	@Override
	protected Object[][] getContents() {
		return new Object[][] {
				{"login", "Log in"},
				{"y", "Yes"},
				{"n", "No"}
		};
	}

}
