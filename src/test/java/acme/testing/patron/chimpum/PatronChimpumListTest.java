package acme.testing.patron.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronChimpumListTest extends TestHarness{

	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/chimpum/chimpum-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePatronChimpum(final int recordIndex, final String code, final String budget, final String creationMoment, final String initPeriod, final String finalPeriod, final String description, final String link, final String artefactName, final String artefactCode) {
		super.signIn("patron1", "patron1");
		super.clickOnMenu("Patron", "Chimpum List");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, initPeriod);
		super.checkColumnHasValue(recordIndex, 3, finalPeriod);
		super.checkColumnHasValue(recordIndex, 4, budget);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("initPeriod", initPeriod);
		super.checkInputBoxHasValue("finalPeriod", finalPeriod);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("ComponentName", artefactName);
		super.checkInputBoxHasValue("ComponentCode", artefactCode);

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		// TESTS LOS CUALES EL FRAMEWORK NO SOPORTA: 
		// A) Aceder a un chimpum a través de la url, poniendo la id de un "component".
		// B) Acceder al listado de chimpum con otra cuenta que no sea propietaria.
	}
}
