package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.ArtefactType;
import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorChimpumListService implements AbstractListService<Inventor, Chimpum> {
	

		
		@Autowired
		protected InventorChimpumRepository repository;

		
		@Override
		public boolean authorise(final Request<Chimpum> request) {
			boolean result;
			int artefactId;
			Principal principal;
			
			
			artefactId = request.getModel().getInteger("id");
			principal = request.getPrincipal();
			final Inventor inventor = this.repository.findInventorByArtefactId(artefactId);
			
			result = principal.getActiveRoleId()==inventor.getId() && this.repository.findArtefactById(artefactId).getType() == ArtefactType.COMPONENT;
				
			return result;
		}

		@Override
		public Collection<Chimpum> findMany(final Request<Chimpum> request) {
			assert request != null;
			Collection<Chimpum> result;
			final int inventorId;
			int artefactId;
			
			artefactId = request.getModel().getInteger("id");
			inventorId = request.getPrincipal().getActiveRoleId();
			

			result = this.repository.findManyChimpusByArtefactAndInventorId(inventorId, artefactId);

			return result;
		}

		@Override
		public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
			
			assert request != null;
			assert entity != null;
			assert model != null;
			
			model.setAttribute("artefactId", request.getModel().getInteger("id"));
			
			request.unbind(entity, model, "code", "budget", "initPeriod", "finalPeriod", "description", "link");
		}
		
		

	
}
