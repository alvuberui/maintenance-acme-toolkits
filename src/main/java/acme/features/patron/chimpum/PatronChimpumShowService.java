package acme.features.patron.chimpum;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;


@Service
public class PatronChimpumShowService implements AbstractShowService<Patron,Chimpum>{

	@Autowired
	protected PatronChimpumRepository repository;
	
	@Autowired
    protected ExchangeService exchangeService ;

	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		boolean result;
		final int chimpumId;
		final Chimpum chimpum;
		Principal user;
		final int patronId;
		
		
		chimpumId = request.getModel().getInteger("id");
		user = request.getPrincipal();
		chimpum = this.repository.findChimpumById(chimpumId);
		patronId = chimpum.getPatron().getId();
		
		result = (patronId == user.getActiveRoleId());
		
		return result;
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;
		
		int id;
		
		Chimpum chimpum;
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findChimpumById(id);
		
		return chimpum;
	}


	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model, "code", "budget", "initPeriod",
				"finalPeriod", "description", "link", "creationMoment");
		model.setAttribute("chimpumId", entity.getId());
		
		model.setAttribute("moneyExchange", this.exchangeService.exchangeMoneySystemConfiguration(entity.getBudget()));
        if(entity.getArtefact() != null) {
            model.setAttribute("toolName", entity.getArtefact().getName());
            model.setAttribute("toolCode", entity.getArtefact().getCode());
            model.setAttribute("toolDescription", entity.getArtefact().getDescription());
            model.setAttribute("toolTechonology", entity.getArtefact().getTechnology());
            model.setAttribute("toolRetailPrice", entity.getArtefact().getRetailPrice());
            model.setAttribute("moneyExchangeRetail", this.exchangeService.exchangeMoneySystemConfiguration(entity.getArtefact().getRetailPrice()));
            model.setAttribute("toolInventor", entity.getArtefact().getInventor().getUserAccount().getUsername());
        }
	}

}
