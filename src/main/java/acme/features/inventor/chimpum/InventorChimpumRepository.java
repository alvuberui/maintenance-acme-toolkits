package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.entities.chimpum.Chimpum;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorChimpumRepository  extends AbstractRepository{
	
	@Query("Select c from Chimpum c where c.inventor = inventorId")
	Collection<Chimpum> findAllChimpumFromInventor(Integer inventorId);
	
	
	@Query("Select c from Chimpum c where c.id = :id")
	Chimpum findChimpumById(int id);
	
	@Query("select a from Artefact a where a.published=true and a.type = acme.entities.artefact.ArtefactType.TOOL")
	Collection<Artefact> findAllTools();
	
	@Query("select a from Artefact a where a.published=true and a.id = :id and a.type = acme.entities.artefact.ArtefactType.TOOL")
	Artefact findToolById(int id);
	

	@Query("Select c from SystemConfiguration c")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select currencies from SystemConfiguration c")
    String findAllCurrencies();


	
	
	
	
	@Query("Select a.inventor from Artefact a where a.id = :artefactId")
	Inventor findInventorByArtefactId(int artefactId);


	@Query("select distinct c from Chimpum c join Artefact a on c.artefact.id = a.id join Inventor i on i.id = a.inventor.id where c.artefact.id = :artefactId and i.id = :inventorId ")
	Collection<Chimpum> findManyChimpusByArtefactAndInventorId(int inventorId, int artefactId);


	

}