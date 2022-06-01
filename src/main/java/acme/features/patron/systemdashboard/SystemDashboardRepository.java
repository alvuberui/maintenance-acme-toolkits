package acme.features.patron.systemdashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;


@Repository
public interface SystemDashboardRepository extends AbstractRepository {
	
	
	@Query("Select c.budget.currency, avg(c.budget.amount) from Chimpum c group by c.budget.currency")
	List<String> averageBudgetByCurrency();
	
	@Query("Select c.budget.currency, stddev(c.budget.amount) from Chimpum c group by c.budget.currency")
	List<String> deviationBudgetByCurrency();
	
	
	@Query("Select c.budget.currency, min(c.budget.amount) from Chimpum c group by c.budget.currency")
	List<String> minBudgetByCurrency();
	
	@Query("Select c.budget.currency, max(c.budget.amount) from Chimpum c group by c.budget.currency")
	List<String> maxBudgetByCurrency();
	
	@Query("Select count(*) from Chimpum c where c.artefact.type = acme.entities.artefact.ArtefactType.TOOL and c.artefact != null")
	int allToolsWithChimpum();
	
	@Query("Select count(*) from Artefact a where a.type = acme.entities.artefact.ArtefactType.TOOL")
	int allTools();
}
