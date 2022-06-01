package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String code, final String descripcion, final String initPeriod, final String finalPeriod, final String creationMoment, final String budget,  final String link) {
		
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "Own artefacts");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.clickOnButton("Create Chimpum");

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", descripcion);
		super.fillInputBoxIn("initPeriod", initPeriod);
		super.fillInputBoxIn("finalPeriod", finalPeriod);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveTest(final int recordIndex, final String code, final String descripcion, final String initPeriod, final String finalPeriod, final String creationMoment, final String budget,  final String link) {

		
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "Own artefacts");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.clickOnButton("Create Chimpum");

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", descripcion);
		super.fillInputBoxIn("initPeriod", initPeriod);
		super.fillInputBoxIn("finalPeriod", finalPeriod);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");

		super.checkNotErrorsExist();
		super.checkNotPanicExists();

		super.signOut();
	}
	
	
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.navigate("/inventor/chimpum/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/inventor/chimpum/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron1", "patron1");
		super.navigate("/inventor/chimpum/create");
		super.checkPanicExists();
		super.signOut();
	}

}