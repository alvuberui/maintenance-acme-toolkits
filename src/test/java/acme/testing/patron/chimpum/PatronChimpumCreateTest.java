package acme.testing.patron.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronChimpumCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/chimpum/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String code, final String descripcion, final String initPeriod, final String finalPeriod, final String creationMoment, final String budget,  final String link) {
		
		super.clickOnMenu("Anonymous", "Artefact List");
		super.clickOnListingRecord(0);
		final String id = super.getCurrentQuery().split("=")[1];
		
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Chimpum List");
		super.checkListingExists();

		super.clickOnButton("Create Chimpum");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", descripcion);
		super.fillInputBoxIn("initPeriod", initPeriod);
		super.fillInputBoxIn("finalPeriod", finalPeriod);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("artefactId", id);
		super.clickOnSubmit("Create Chimpum");

		super.checkErrorsExist();

		super.signOut();
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/chimpum/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveTest(final int recordIndex, final String code, final String descripcion, final String initPeriod, final String finalPeriod, final String creationMoment, final String budget,  final String link) {
		
		super.clickOnMenu("Anonymous", "Artefact List");
		super.clickOnListingRecord(0);
		final String id = super.getCurrentQuery().split("=")[1];
		
		super.signIn("patron2", "patron2");

		super.clickOnMenu("Patron", "Chimpum List");
		super.checkListingExists();

		super.clickOnButton("Create Chimpum");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", descripcion);
		super.fillInputBoxIn("initPeriod", initPeriod);
		super.fillInputBoxIn("finalPeriod", finalPeriod);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("artefactId", id);
		super.clickOnSubmit("Create Chimpum");

		super.checkNotErrorsExist();
		super.checkNotPanicExists();

		super.signOut();
	}
	
	
	@Test
	@Order(40)
	public void hackingTest() {
		super.navigate("/patron/chimpum/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/patron/chimpum/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("inventor1", "inventor1");
		super.navigate("/patron/chimpum/create");
		super.checkPanicExists();
		super.signOut();
		
		// TESTS LOS CUALES EL FRAMEWORK NO SOPORTA: 
		// A) Crear un chimpum accediendo a través de la url, poniendo la id de un "tool"
	}

}
