package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;


@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	@Query("select count(p) from Patronages p where p.status = acme.entities.patonages.PatronageStatus.PROPOSED")
	int numberOfProposedPatronages();
	@Query("select count(p) from Patronages p where p.status = acme.entities.patonages.PatronageStatus.ACCEPTED")
	int numberOfAcceptedPatronages();
	@Query("select count(p) from Patronages p where p.status = acme.entities.patonages.PatronageStatus.DENIED")
	int numberOfDeniedPatronages();
	@Query("select count(i) from Artefact i where i.type = acme.entities.artefact.ArtefactType.TOOL")
	int numberOfTools();	
	@Query("select count(i) from Artefact i where i.type = acme.entities.artefact.ArtefactType.COMPONENT")
	int numberOfComponents();	
	
	@Query("select i.retailPrice.currency, avg(i.retailPrice.amount), i.type from Artefact i where i.type=acme.entities.artefact.ArtefactType.TOOL group by i.retailPrice.currency")
	List<String> avgRetailPriceOfTools();
	@Query("select i.retailPrice.currency, stddev(i.retailPrice.amount), i.type from Artefact i where i.type=acme.entities.artefact.ArtefactType.TOOL group by i.retailPrice.currency")
	List<String> deviationRetailPriceOfTools();
	@Query("select i.retailPrice.currency, min(i.retailPrice.amount), i.type from Artefact i where i.type=acme.entities.artefact.ArtefactType.TOOL group by i.retailPrice.currency")
	List<String> minRetailPriceOfTools();
	@Query("select i.retailPrice.currency, min(i.retailPrice.amount), i.type from Artefact i where i.type=acme.entities.artefact.ArtefactType.TOOL group by i.retailPrice.currency")
	List<String> maxRetailPriceOfTools();
	
	@Query("select i.retailPrice.currency, i.technology, avg(i.retailPrice.amount), i.type from Artefact i where i.type=acme.entities.artefact.ArtefactType.COMPONENT group by i.retailPrice.currency, i.technology")
	List<String> avgRetailPriceOfComponents();
	@Query("select i.retailPrice.currency, i.technology, stddev(i.retailPrice.amount), i.type from Artefact i where i.type=acme.entities.artefact.ArtefactType.COMPONENT group by i.retailPrice.currency, i.technology")
	List<String> deviationRetailPriceOfComponents();
	@Query("select i.retailPrice.currency, i.technology, min(i.retailPrice.amount), i.type from Artefact i where i.type=acme.entities.artefact.ArtefactType.COMPONENT group by i.retailPrice.currency, i.technology")
	List<String> minRetailPriceOfComponents();
	@Query("select i.retailPrice.currency, i.technology, max(i.retailPrice.amount), i.type from Artefact i where i.type=acme.entities.artefact.ArtefactType.COMPONENT group by i.retailPrice.currency, i.technology")
	List<String> maxRetailPriceOfComponents();
	
	@Query("select p.budget.currency, avg(p.budget.amount), p.status from Patronages p group by p.status")
	List<String> avgBudgetByStatus();
	@Query("select p.budget.currency, stddev(p.budget.amount), p.status from Patronages p group by p.status")
	List<String> deviationBudgetByStatus();
	@Query("select p.budget.currency, min(p.budget.amount), p.status from Patronages p group by p.status")
	List<String> minBudgetByStatus();
	@Query("select p.budget.currency, min(p.budget.amount), p.status from Patronages p group by p.status")
	List<String> maxBudgetByStatus();
}
