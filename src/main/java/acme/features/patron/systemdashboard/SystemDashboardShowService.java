package acme.features.patron.systemdashboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.SystemDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class SystemDashboardShowService implements AbstractShowService<Patron, SystemDashboard>{
	@Autowired
	protected  SystemDashboardRepository repository;

	@Override
	public boolean authorise(final Request<SystemDashboard> request) {
		assert request != null;
		return true;
	}


	@Override
	public SystemDashboard findOne(final Request<SystemDashboard> request) {
		assert request != null;
		
		final SystemDashboard result = new SystemDashboard();
		final Map<String,Double> avgBudgetByCurrency = new HashMap<String, Double>();
		final Map<String,Double> deviationBudgetByCurrency = new HashMap<String, Double>();
		final Map<String,Double> maxBudgetByCurrency = new HashMap<String, Double>();		 
		final Map<String,Double> minBudgetByCurrency = new HashMap<String, Double>();
		
		for(final String linea : this.repository.averageBudgetByCurrency()) {
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			avgBudgetByCurrency.put(divisa, key);
		}
		
		for(final String linea : this.repository.deviationBudgetByCurrency()) {
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			deviationBudgetByCurrency.put(divisa, key);
		}
		
		for(final String linea : this.repository.maxBudgetByCurrency()) {
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			maxBudgetByCurrency.put(divisa, key);
		}
		
		for(final String linea : this.repository.minBudgetByCurrency()) {
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			minBudgetByCurrency.put(divisa, key);
		}
		
		if(this.repository.allTools() == 0) {
			result.setRatioToolWithChimpum(0.0);
		}else {			
			result.setRatioToolWithChimpum(Double.valueOf(this.repository.allToolsWithChimpum())/this.repository.allTools());
		}
		
		result.setAvgBudgetByCurrency(avgBudgetByCurrency);
		result.setDeviationBudgetByCurrency(deviationBudgetByCurrency);
		result.setMaxBudgetByCurrency(maxBudgetByCurrency);
		result.setMinBudgetByCurrency(minBudgetByCurrency);
		
		
		return result;
	}

	@Override
	public void unbind(final Request<SystemDashboard> request, final SystemDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ratioToolWithChimpum", "avgBudgetByCurrency", "deviationBudgetByCurrency",
			"maxBudgetByCurrency", "minBudgetByCurrency");
	}}


