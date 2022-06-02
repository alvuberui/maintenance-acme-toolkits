package acme.features.patron.chimpum;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.features.spam.SpamDetector;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;


@Service
public class PatronChimpumUpdateService  implements AbstractUpdateService<Patron,Chimpum>{

	
	@Autowired
	protected PatronChimpumRepository repository;
	
	
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
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {	
		assert request != null;
		assert entity != null;
		assert errors != null;

	
		
	
		
		
		
		request.bind(entity, errors,"code", "budget", "initPeriod",
				"finalPeriod", "description", "link");
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
	
	
		
		
		request.unbind(entity, model,"code", "budget", "initPeriod",
				"finalPeriod", "description", "link");
	
	
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		
		int id;
		Chimpum chimpum;
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findChimpumById(id);
		
		
		return chimpum;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		final List<String> currenciesList = new ArrayList<>();
        final String currencies = this.repository.findAllCurrencies();
        final String[] currenciesArray = currencies.split(" ");

        for(int i=0; i < currenciesArray.length; i++) {
            currenciesList.add(currenciesArray[i].trim());
        } 
        if(!errors.hasErrors("budget")) {
            errors.state(request, entity.getBudget().getAmount() > 0 , "budget", "patron.chimpum.form.label.budget.positive.error");
            final String money = entity.getBudget().getCurrency();
            errors.state(request, currenciesList.contains(money) , "budget", "patron.chimpum.form.error.currency");
        }
		
		if (!errors.hasErrors("description")) {
			errors.state(request, SpamDetector.error(entity.getDescription(),  this.repository.findSystemConfiguration()), "description", "any.form.error.spam");
		}
		

		if(!errors.hasErrors("code")) {
            Chimpum chimpum;
            chimpum = this.repository.findOneByCode(entity.getCode());
            errors.state(request, chimpum == null || chimpum.getId() == entity.getId(), "code", "inventor.chimpum.error.duplicated");
        }
		
		
	
		
		if(!errors.hasErrors("finalPeriod")) {

            if(entity.getInitPeriod()!=null) {


            final long p = ChronoUnit.DAYS.between(LocalDate.of(entity.getInitPeriod().getYear(), entity.getInitPeriod().getMonth()+1, entity.getInitPeriod().getDate()), 
            LocalDate.of(entity.getFinalPeriod().getYear(), entity.getFinalPeriod().getMonth()+1, entity.getFinalPeriod().getDate()));

            errors.state(request, p == 7, "finalPeriod", "inventor.chimpum.form.label.period.week.error");
            }

        }


        if(!errors.hasErrors("initPeriod")) {



            final Period periodCreationInit = Period.between(LocalDate.of(entity.getCreationMoment().getYear(), entity.getCreationMoment().getMonth()+1, entity.getCreationMoment().getDate()), 
                LocalDate.of(entity.getInitPeriod().getYear(), entity.getInitPeriod().getMonth()+1, entity.getInitPeriod().getDate()));


            if(periodCreationInit.isNegative()==true && entity.getCreationMoment().getYear()!= entity.getInitPeriod().getYear()) {
            errors.state(request, periodCreationInit.isNegative()!=true, "initPeriod", "inventor.chimpum.form.label.period.beforeyear.error");

            }else if(entity.getCreationMoment().getYear()== entity.getInitPeriod().getYear()) {
                errors.state(request, periodCreationInit.getMonths()>=1, "initPeriod", "inventor.chimpum.form.label.period.month.error");

            }



        }
	
	}

	@Override
	public void update(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		
		this.repository.save(entity);
		
		
	}

}
