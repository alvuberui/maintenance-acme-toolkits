package acme.testing.patron.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronChimpumUpdateTool extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/chimpum/chimpum-update-tool.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePatronChimpum(final String artefactName, final String artefactCode) {
		super.signIn("patron1", "patron1");
		super.clickOnMenu("Authenticated", "Artefact List");
		super.sortListing(1, "asc");
		super.clickOnListingRecord(0);
		final String [] currentQuery = super.getCurrentQuery().split("=");
		final String id = currentQuery[1];

		super.clickOnMenu("Patron", "Chimpum List");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnButton("Change Tool");
		super.fillInputBoxIn("artefactId", id);
		super.clickOnSubmit("Change Tool");
		
		super.clickOnMenu("Patron", "Chimpum List");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("toolName", artefactName);
		super.checkInputBoxHasValue("toolCode", artefactCode);
		
		
		super.signOut();
	}
}
