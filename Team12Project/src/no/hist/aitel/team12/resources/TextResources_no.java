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
				{"exe", "Kj�r"},
				{"overview", "Oversikt"},
				{"msgs", "Meldinger"},
				{"usrs", "Brukere"},
				{"sql", "SQL"},
				{"rport", "Rapport"},
				{"tckts", "Hendvendelser"},
				{"finance", "Finans"},
				{"reg","Registrer"},
				{"cntrTxt"," P�: "},
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
				{"openingHrs", "�pningstider"},
				{"textDescription", "Beskrivelse"},
				{"edit", "Rediger"},
				{"save", "Lagre"},
				{"nrOfFloors", "Antall etasjer"},
				{"area", "Areal  (m�)"},
				{"firstname","Fornavn"},
				{"lastname","Etternavn"},
				{"adr","Adresse"},
				{"zip","Postnummer"},
				{"tel","Telefon"},
				{"sal","L�nn"},
				{"reverr", " Ingen fortjeneste er skrevet inn"},
				{"rev", "Fortjeneste"},
				{"revdaterr", "Kan ikke registrere fortjeneste.\nDato fra/til mangler."},
				{"pdferr", "Kan ikke vise finansrapport.\nDato fra/til er ikke valgt"},
				{"daterr", "Fra dato m� v�re f�r til dato"},
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
				{"invalidEmail", "Ugyldig epost-addresse, pr�v igjen"},
				{"invalidTel", "Ugyldig telefonnummer, oppgi 8 siffer"},
				{"delMsg", "Slett melding"},
				{"dbErr", "Noe gikk galt i databaseforbindelsen."},
				{"sysadmin", "Systemadministrator"},
				{"cntrmanager", "Senterleder"},
				{"shopowner", "Bedriftseier"},
				{"customerservice", "Kundeservice"},
				{"personnel", "Personell"},
				{"invalidZip", "Ugyldig postnummer"},
				{"usertypeTry", "Pr�ver � opprette brukertype: "},
				{"whatUsertype", "Hvilken type bruker skal opprettes?"},
				{"noEmpty", "Feltet kan ikke v�re tomt."},
				{"usertype", "brukertype"},
				{"invalidHrs", "Ugyldig format p� �pningstid\nDet kreves to tall i hvert av de 4 feltene\nog man m� �pne f�r man stenger"},
				{"invalidInt", "Ugyldig format, oppgi heltall"},
				{"shce","Kj�pesenter"},
				{"store","Butikk"},
				{"cvsCntrNam","Skriv inn navn p� senter"},
				{"cvsShpNam","Skriv inn navn p� butikk"},
				{"cvsCounty","Skriv inn fylke"},
				{"cvsMunici","Skriv inn kommune"},
				{"newBuilding", "Ny bygning"},
				{"store","Butikk"},		
				{"frnamelong","- Fornavn er for langt. Maks 30 bokstaver."},
				{"lsnamelong","- Etternavn er for langt. Maks 30 bokstaver."},
				{"adrlong","- Adressen er for lang. Maks 30 bokstaver."},
				{"zipfour","- Postnummeret m� ha fire siffer."},
				{"zipnr","- Postnummer kan bare ha nummer, og m� v�re fire siffer langt."},
				{"emailinv","- Epostadressen er ikke gyldig."},
				{"tlplong","- Telefonnummeret m� ha 8 siffer."},
				{"tlpnr","- Telefonnummer m� ha kun nummer, og v�re 8 siffer langt."},
				{"salnr","- L�nn m� v�re oppgitt i nummer."},
				{"inputerr","Det var en feil i skjemaet ditt:"},
				{"centerlong", "- Kj�pesenternavnet er for langt. Maks 30 bokstaver."},
				{"userlong", "- Brukernavnet er for langt. Maks 30 bokstaver."},
				{"floor", "Etasje"},
				{"park","Parkeringsplasser"},
				{"contactCS","Kontakt kundeservice"},
				{"tradeType","Bransje"},
				{"floorPlur","Etasje(r)"},
				{"resetPwd", "Tilbakestill Passord"},
				{"manager", "Manager"},
				{"EROTICA", "Erotikk"},
				{"CLOTHING", "Kl�r"},
				{"SHOES", "Sko"},
				{"TRAVELGOODS", "Reiseeffekter"},
				{"HOUSEANDHOME", "Hus og hjem"},
				{"TRAVELCOMPANY", "Reiseselskap"},
				{"BAKERY", "Bakeri"},
				{"GROCERIES", "Dagligvarer"},
				{"ELECTRONICS", "Elektronikk"},
				{"WELLBEING", "Vellv�re"},
				{"SPORT", "Sport"},
				{"JEWELER", "Gullsmed"},
				{"LIQUORSTORE", "Vinmonopol"},
				{"OPTICIAN", "Optikker"},
				{"MAKEUP", "Sminke"},
				{"FLORIST", "Blomsterhandler"},
				{"PHARMACY", "Apotek"},
				{"TOYSHOP", "Leket�ysbutikk"},
				{"BOOKS", "B�ker"},
				{"MUSIC", "Musikk"},
				{"GAMES", "Spill og underholdning"},
				{"PETSHOP", "Dyrebutikk"},
				{"HAIRDRESSING", "Fris�r"},
				{"HVAC", "VVS"},
				{"FURNITURE", "M�bler"},
				{"TEXTILES", "Tekstil"},
				{"GIFTSHOP", "Gavebutikk"},
				{"LOCKSMITH", "L�sesmed"},
				{"POSTALSERVICES", "Posttjenester"},
				{"CINEMA", "Kino"},
				{"OFFICESUPPLIES", "Kontorrekvisita"},
				{"BUILDINGMATERIALS", "Byggevarer"},
				{"RESTAURANT", "Restaurant"},
				{"CAFE", "Kaf�"},
				{"changePwd", "Endre passord"},
				{"manager", "Manager"},
				{"oldPwd", "Gammelt passord"},
				{"newPwd", "Nytt passord"},
				{"repNewPwd", "Gjenta nytt passord"},
				{"usrNotFound", "Finner ikke bruker"},
				{"usrAllreadyExists", "Brukernavn opptatt"},
				{"newPwdFail", "Sjekk at nytt passord gjentas korrekt"},
				{"pwdChanged", "Passord endret"},
				{"saving", "Lagrer ..."},
				{"TAILOR", "Skredder"},
				{"ALLTRADES", "Alle bransjer"},
				{"LINGERIE", "Undert�y"},
				{"month", "M�ned"},
				{"jan", "Januar"},
				{"feb", "Februar"},
				{"mar", "Mars"},
				{"apr", "April"},
				{"may", "Mai"},
				{"jun", "Juni"},
				{"jul", "Juli"},
				{"aug", "August"},
				{"sep", "September"},
				{"oct", "Oktober"},
				{"nov", "November"},
				{"des", "Desember"},
				{"shopNameLong", "- Butikknavn for langt. Maks 30 karakterer"},
				{"buildingCreated", "Byggning opprettet"},
				{"selectBuildingErr", "Et bygg m� velges."},
				{"noCentreName", "- Senteret m� ha et navn"},
				{"shopFloorError", "- Etasje m� oppgis som heltall"},
				{"shoptlpnr", "- Butikkens telefonnummer ugyldig, oppgi 8 heltall"},
				{"shopInvEmail", "- Butikkens epost er ugyldig"},
				{"shopInvZip", "- Butikkens postkode er ugyldig"},
				{"shopAdrLong", "- Butikkens adresse er for lang. Maks 30 karakterer"},
				{"shopNameLong", "- Butikkens navn er for langt. Maks 30 karakterer"},
				{"frnameMissing", "- Brukers fornavn m� fylles ut"},
				{"lsnameMissing", "- Brukers etternavn m� fylles ut"},
				{"usrNameMissing", "- Brukernavn m� fylles ut"},
				{"titleMissing", "- Brukers tittel m� fylles ut"},
				{"titleLong", "- Brukers tittel er for lang. Maks 30 karakterer"},
				{"adrMissing", "- Adresse mangler"},
				{"usrCreated", "- Bruker opprettet"},
				{"shopNameMissing", "- Butikkens navn m� fylles ut"},
				{"revReg", "Fortjeneste er registrert"},
				{"needTo", "- Du m� fylle inn en mottaker"},
				{"needSub", "- Du m� fylle inn et emne"},
				{"$", "mrd."}
		};
	}
}
