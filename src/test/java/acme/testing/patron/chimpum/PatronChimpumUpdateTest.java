package acme.testing.patron.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronChimpumUpdateTest  extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/chimpum/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveTest(final int recordIndex, final String code, final String descripcion, final String initPeriod, final String finalPeriod, final String creationMoment, final String budget,  final String link) {
		
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Chimpum List");
		super.checkListingExists();
		super.clickOnListingRecord(0);
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
	@CsvFileSource(resources = "/patron/chimpum/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void negativeTest(final int recordIndex, final String code, final String descripcion, final String initPeriod, final String finalPeriod, final String creationMoment, final String budget,  final String link) {
		
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Chimpum List");
		super.checkListingExists();
		super.clickOnListingRecord(0);
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
