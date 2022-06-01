package acme.features.inventor.chimpum;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.entities.artefact.ArtefactType;
import acme.entities.chimpum.Chimpum;
import acme.features.spam.SpamDetector;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, Chimpum>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

        boolean result;

        int artefactId;
        Inventor inventor;


        artefactId = request.getModel().getInteger("id");
        inventor = this.repository.findInventorByArtefactId(artefactId);

        result = request.getPrincipal().getActiveRoleId()==inventor.getId() && this.repository.findArtefactById(artefactId).getType() == ArtefactType.COMPONENT;
        

        return result;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "budget", "initPeriod", "finalPeriod", "description", "link");
		
		
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"code", "budget", "initPeriod", "finalPeriod", "description", "link");
		model.setAttribute("artefactId", request.getModel().getInteger("id"));
	}

	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {
		assert request != null;
		
		Chimpum result;
		Artefact artefact;
		Date creationMoment;
		Inventor inventor;
		creationMoment = new Date(System.currentTimeMillis() -1);
		
		artefact = this.repository.findArtefactById(request.getModel().getInteger("id"));
		inventor = this.repository.findOneInventorById(request.getPrincipal().getActiveRoleId());
		result = new Chimpum();
		result.setInventor(inventor);
		result.setArtefact(artefact);
		result.setCreationMoment(creationMoment);
		
		return result;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final List<String> currenciesList = new ArrayList<String>();
        final String currencies = this.repository.findAllCurrencies();
        final String[] currenciesArray = currencies.split(" ");

        for(int i=0; i < currenciesArray.length; i++) {
            currenciesList.add(currenciesArray[i].trim());
        } 
        
        if(!errors.hasErrors("code")) {
            Chimpum chimpum;
            chimpum = this.repository.findOneByCode(entity.getCode());
            errors.state(request, chimpum == null, "code", "inventor.chimpum.error.duplicated");
        }
        
        if(!errors.hasErrors("budget")) {
            errors.state(request, entity.getBudget().getAmount() >= 0 , "budget", "inventor.chimpum.form.label.budget.positive.error");
            final String money = entity.getBudget().getCurrency();
            errors.state(request, currenciesList.contains(money) , "budget", "inventor.chimpum.form.error.currency");
        }
		
		if (!errors.hasErrors("description")) {
			errors.state(request, SpamDetector.error(entity.getDescription(),  this.repository.findSystemConfiguration()), "description", "any.form.error.spam");
		}
		
	
		if(!errors.hasErrors("initPeriod") && !errors.hasErrors("finalPeriod")) {			
			final Period p2 = Period.between(LocalDate.of(entity.getCreationMoment().getYear(), entity.getCreationMoment().getMonth()+1, entity.getCreationMoment().getDate()), 
					LocalDate.of(entity.getInitPeriod().getYear(), entity.getInitPeriod().getMonth()+1, entity.getInitPeriod().getDate()));
		
			
			final long p = ChronoUnit.DAYS.between(LocalDate.of(entity.getInitPeriod().getYear(), entity.getInitPeriod().getMonth()+1, entity.getInitPeriod().getDate()), 
					LocalDate.of(entity.getFinalPeriod().getYear(), entity.getFinalPeriod().getMonth()+1, entity.getFinalPeriod().getDate()));
	
			errors.state(request, p2.getMonths() >= 1, "initPeriod", "inventor.chimpum.form.label.period.month.error");
			errors.state(request, p == 7, "finalPeriod", "inventor.chimpum.form.label.period.week.error");
		}
		
	}

	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		Date creationMoment;
		
		creationMoment = new Date(System.currentTimeMillis() -1);
		entity.setCreationMoment(creationMoment);
		this.repository.save(entity);
		
	}

}
