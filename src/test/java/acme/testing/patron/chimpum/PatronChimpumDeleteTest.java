package acme.testing.patron.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronChimpumDeleteTest extends TestHarness{

	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/chimpum/chimpum-update-component.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePatronChimpum(final String artefactName, final String artefactCode) {
		super.signIn("patron2", "patron2");
		super.clickOnMenu("Patron", "Chimpum List");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		
		super.clickOnMenu("Patron", "Chimpum List");
		super.checkListingEmpty();
		
		
		super.signOut();
	}
}
