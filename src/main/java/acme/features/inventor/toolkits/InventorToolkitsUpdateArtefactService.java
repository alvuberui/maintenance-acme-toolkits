

package acme.features.inventor.toolkits;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.entities.artefact.ArtefactType;
import acme.entities.artefact.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;


@Service
public class InventorToolkitsUpdateArtefactService implements AbstractUpdateService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitsRepository repository;
	
	@Override
	public boolean authorise(Request<Toolkit> request) {
		assert request != null;
		
		
		Toolkit result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(id);
		
		
		
		Collection<Toolkit> toolkits = this.repository.findToolkitsByInventorId(request.getPrincipal().getActiveRoleId());
		boolean isMine = toolkits.stream().anyMatch(x -> x.getId() == request.getModel().getInteger("id"));
		return !result.isPublished() && isMine;
	}

	@Override
	public void bind(Request<Toolkit> request, Toolkit entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title");
		
	}

	@Override
	public void unbind(Request<Toolkit> request, Toolkit entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		Collection<Artefact> artefacts;
		artefacts = this.repository.findArtefactsPublished();
		
		request.unbind(entity, model, "code", "title");
		model.setAttribute("quantity", 1);
		model.setAttribute("artefacts", artefacts);
	}

	@Override
	public Toolkit findOne(Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(id);
		return result;
	}

	@Override
	public void validate(Request<Toolkit> request, Toolkit entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("quantity")) {
			try {
				Integer number;
				int artefactId;
				Artefact artefact;
				artefactId = request.getModel().getInteger("artefactId");
				artefact = this.repository.findArtefactById(artefactId);
				number = request.getModel().getInteger("quantity");
				errors.state(request, artefact.getType() == ArtefactType.COMPONENT || (artefact.getType() == ArtefactType.TOOL &&  number <=1 ), "quantity", "inventor.toolkit.form.label.quantity.tool.error");
			} catch (ConversionFailedException e) {
				errors.state(request, false, "quantity", "inventor.toolkit.form.label.quantity.string.error");
			}
		
		}
	}

	@Override
	public void update(Request<Toolkit> request, Toolkit entity) {
		assert request != null;
		assert entity != null;

		int artefactId;
		Artefact artefact;
		Quantity quantity;
		Integer number;
		
		artefactId = request.getModel().getInteger("artefactId");
		artefact = this.repository.findArtefactById(artefactId);
		quantity = this.repository.findQuantityByToolkitAndArtefact(entity.getId(), artefactId);
		
		if(quantity == null) {
			quantity = new Quantity();
			quantity.setArtefact(artefact);
			quantity.setToolkit(entity);
		}
		
		number = request.getModel().getInteger("quantity");
		if(number == 0) {
			this.repository.delete(quantity);
		}else {
		
		quantity.setNumber(number);
		this.repository.save(quantity);
		}
	}
}


