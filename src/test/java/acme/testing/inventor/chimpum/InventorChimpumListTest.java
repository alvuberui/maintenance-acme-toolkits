package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumListTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/chimpum-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePatronChimpum(final int recordIndex, final String code, final String budget, final String creationMoment, final String initPeriod, final String finalPeriod, final String description, final String link) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "Own artefacts");
		super.checkListingExists();
		super.clickOnListingRecord(2);
		super.clickOnButton("List Chimpum");
		
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
