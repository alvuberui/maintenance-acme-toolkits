package acme.features.patron.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.entities.chimpum.Chimpum;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface PatronChimpumRepository  extends AbstractRepository{
	
	@Query("Select c from Chimpum c")
	Collection<Chimpum> findAllChimpum();
	
	
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

}
