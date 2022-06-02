package acme.features.patron.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.entities.chimpum.Chimpum;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Patron;


@Repository
public interface PatronChimpumRepository  extends AbstractRepository{
	
	@Query("Select c from Chimpum c")
	Collection<Chimpum> findAllChimpum();
	
	
	@Query("Select c from Chimpum c where c.id = :id")
	Chimpum findChimpumById(int id);
	
	@Query("select a from Artefact a where a.published=true and a.type = acme.entities.artefact.ArtefactType.COMPONENT")
	Collection<Artefact> findAllComponents();
	
	@Query("select a from Artefact a where a.published=true and a.id = :id and a.type = acme.entities.artefact.ArtefactType.COMPONENT")
	Artefact findComponentlById(int id);
	

	@Query("Select c from SystemConfiguration c")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select currencies from SystemConfiguration c")
    String findAllCurrencies();

	@Query("Select c from Chimpum c where c.patron.id = :patronId")
	Collection<Chimpum> findChimpumByPatronId(int patronId);

	@Query("Select p from Patron p where p.id = :patronId")
	Patron findPatronById(int patronId);

	@Query("Select c from Chimpum c where c.code = :code")
	Chimpum findOneByCode(String code);

}
