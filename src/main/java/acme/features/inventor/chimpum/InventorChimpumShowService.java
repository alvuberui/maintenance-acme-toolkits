package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;


@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor,Chimpum>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Autowired
    protected ExchangeService exchangeService ;

	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		boolean result;
		
		final int chimpumId;
		Principal principal;
		
		chimpumId = request.getModel().getInteger("id");
		principal = request.getPrincipal();
		final Inventor inventor = this.repository.findInventorByChimpum(chimpumId);
		
		result = principal.getActiveRoleId()==inventor.getId();
			
			
		
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
	}

}
