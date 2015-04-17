package no.hist.aitel.team12.app.test;

import no.hist.aitel.team12.app.Email;
import no.hist.aitel.team12.app.EmailAddress;

public class EmailTest {

	public static void main(String[] args) {
		Email.sendEmail("This is a test.\nIf this email is sent and recieved, it was a success.",
				new EmailAddress("hallgeir@loekken.org"));
	}
}
