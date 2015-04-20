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
 * TextResources_no.java Team 12, 19 Feb 2015
 *******************************************************************************/

package no.hist.aitel.team12.resources;

import java.util.ListResourceBundle;

/**
 * This class contains the Norwegian translation of all string resources to be used.
 * Note that version number is not updated for each addition to the list.
 * 
 * @author Hallgeir
 * @version 1.0
 */
public class TextResources_no extends ListResourceBundle {

	public TextResources_no() {
	}

	@Override
	protected Object[][] getContents() {
		return new Object[][] {
				{"login", "Logg inn"},
				{"cancel", "Avbryt"},
				{"y", "Ja"},
				{"n", "Nei"},
				{"sss", "Super Shopping Surfer"},
				{"pwd", "Passord"},
				{"usr", "Brukernavn"},
				{"loginfail", "Innloging feilet.\nUgyldig brukernavn/passord."},
				{"err", "Feil"},
				{"send", "Send"},
				{"reply", "Svar"},
				{"from", "Fra"},
				{"to", "Til"},
				{"sub", "Emne"},
				{"exe", "Kjør"},
				{"overview", "Oversikt"},
				{"msgs", "Meldinger"},
				{"usrs", "Brukere"},
				{"sql", "SQL"},
				{"rport", "Rapport"},
				{"tckts", "Hendvendelser"},
				{"finance", "Finans"},
				{"reg","Registrer"},
				{"cntrTxt"," På: "},
				{"storeTxt"," Finans rapportering for: "},
				{"fDate","Fra dato: "},
				{"tDate","Til dato: "},
				{"spdf", "Vis PDF"},
				{"svpdf", " Lagre PDF"},
				{"newuser", "Ny bruker"},
				{"edituser", "Endre bruker"},
				{"regsub", "SuperShoppingSurfer - Ny bruker registrert "},
				{"msgConfirmation", "Meldingen er sendt"},
				{"businessName", "Foretningsnavn"},
				{"email", "E-post"},
				{"openingHrs", "Åpningstider"},
				{"textDescription", "Beskrivelse"},
				{"edit", "Rediger"},
				{"save", "Lagre"},
				{"nrOfFloors", "Antall etasjer"},
				{"area", "Area"},
				{"firstname","Fornavn"},
				{"lastname","Etternavn"},
				{"adr","Adresse"},
				{"zip","Postboks"},
				{"tel","Telefon"},
				{"sal","Lønn"},
				{"reverr", " Ingen fortjeneste er skrevet inn"},
				{"rev", "Fortjeneste"},
				{"revdaterr", "Kan ikke registrere fortjeneste.\nDato fra/til mangler."},
				{"pdferr", "Kan ikke vise finansrapport.\nDato fra/til er ikke valgt"},
				{"daterr", "Fra dato må være før til dato"},
				{"createuser","Lagre bruker"},
				{"generating", "Genererer ..."},
				{"newTrade", "Legg til bransje"},
				{"trades", "Bransjer"},
				{"personnel", "Personell"},
				{"name", "Navn"},
				{"title", "Tittel"},
				{"centre", "Senter"},
				{"building", "Bygg"},
				{"shop", "Butikk"},
				{"cmp","Firma"},
				{"pos","Posisjon"},
				{"invalidEmail", "Ugyldig epost-addresse, prøv igjen"},
				{"invalidTel", "Ugyldig telefonnummer, oppgi 8 siffer"}
		};
	}
}
