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
	
	@Query("select a from Artefact a where a.published=true and a.type = acme.entities.artefact.ArtefactType.COMPONENT")
	Collection<Artefact> findAllComponents();
	
	@Query("select a from Artefact a where a.published=true and a.id = :id and a.type = acme.entities.artefact.ArtefactType.COMPONENT")
	Artefact findComponentById(int id);
	

	@Query("Select c from SystemConfiguration c")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select currencies from SystemConfiguration c")
    String findAllCurrencies();

	
	@Query("select a from Artefact a where a.id = :id")
	Artefact findArtefactById(int id);

	@Query("select i from Inventor i where i.id = :id")
	Inventor findOneInventorById(int id);
	
	@Query("select c from Chimpum c where c.code = :code")
    Chimpum findOneByCode(String code);
	
	
	@Query("Select a.inventor from Artefact a where a.id = :artefactId")
	Inventor findInventorByArtefactId(int artefactId);


	@Query("select distinct c from Chimpum c join Artefact a on c.artefact.id = a.id join Inventor i on i.id = a.inventor.id where c.artefact.id = :artefactId and i.id = :inventorId ")
	Collection<Chimpum> findManyChimpusByArtefactAndInventorId(int inventorId, int artefactId);

	@Query("Select a.inventor from Chimpum c join Artefact a on c.artefact.id= a.id where c.id = :chimpumId")
	Inventor findInventorByChimpum(int chimpumId);


	

}
