package acme.features.patron.chimpum;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;

import acme.entities.artefact.Artefact;
import acme.entities.chimpum.Chimpum;
import acme.features.spam.SpamDetector;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronChimpumCreateService implements AbstractCreateService<Patron, Chimpum>{

	@Autowired
	protected PatronChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		request.bind(entity, errors, "code", "budget", "initPeriod",
				"finalPeriod", "description", "link");
		
		
		final int patronId = request.getPrincipal().getActiveRoleId();
		final Patron patron = this.repository.findPatronById(patronId);
		entity.setPatron(patron);
		entity.setCreationMoment(Date.from(Instant.now()));
		

	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Collection<Artefact> artefacts;
		final Collection<Chimpum> chimpums;
		
		
		artefacts = this.repository.findAllTools();
		chimpums = this.repository.findAllChimpum();
		
		for (final Chimpum c : chimpums) {
			final Artefact artefact = c.getArtefact();
			
			artefacts.remove(artefact);
		}
		
		
		model.setAttribute("artefacts", artefacts.stream().filter(x->!Objects.equal(x, entity.getArtefact())).collect(Collectors.toList()));
		if( entity.getArtefact() != null) {
			model.setAttribute("artefactName", entity.getArtefact().getName());
		}
		
		
		request.unbind(entity, model, "code", "budget", "initPeriod",
				"finalPeriod", "description", "link");
		
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
            errors.state(request, chimpum == null, "code", "inventor.chimpum.error.duplicated");
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
		
		Collection<Artefact> artefacts;
		Collection<Chimpum> chimpums;
		
		
		artefacts = this.repository.findAllTools();
		chimpums = this.repository.findAllChimpum();
		
		for (final Chimpum c : chimpums) {
			final Artefact artefact = c.getArtefact();
			artefacts.remove(artefact);
		}
		
		if (!errors.hasErrors("artefact")) {
			errors.state(request, !artefacts.isEmpty(),  "*", "patron.chimpum.form.error.artefact");
		}
		
		final Integer toolId = request.getModel().getInteger("artefactId");
		
		if(toolId != null && !errors.hasErrors()) {
			entity.setArtefact(this.repository.findToolById(toolId));
		}
		
	}

	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {
		Chimpum result;
		
		result = new Chimpum();
		
		
		return result;
	}

	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;

		final String pattern = "MM/dd/yy";
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		entity.setCode(String.format("%s-%s",entity.getCode() ,simpleDateFormat.format(Date.from(Instant.now()))));
		this.repository.save(entity);
		
	}

}
