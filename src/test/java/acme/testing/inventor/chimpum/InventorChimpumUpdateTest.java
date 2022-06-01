package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumUpdateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String descripcion, final String initPeriod, final String finalPeriod, final String creationMoment, final String budget,  final String link) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "Own artefacts");
		super.checkListingExists();
		super.clickOnListingRecord(1);
		super.clickOnButton("List Chimpum");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnButton("Update");


		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", descripcion);
		super.fillInputBoxIn("initPeriod", initPeriod);
		super.fillInputBoxIn("finalPeriod", finalPeriod);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
		
		super.checkNotErrorsExist();
		super.checkNotPanicExists();

		

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void negativeTest(final int recordIndex, final String code, final String descripcion, final String initPeriod, final String finalPeriod, final String creationMoment, final String budget,  final String link) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "Own artefacts");
		super.checkListingExists();
		super.clickOnListingRecord(1);
		super.clickOnButton("List Chimpum");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnButton("Update");

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", descripcion);
		super.fillInputBoxIn("initPeriod", initPeriod);
		super.fillInputBoxIn("finalPeriod", finalPeriod);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
		

		

		super.signOut();
	}

}