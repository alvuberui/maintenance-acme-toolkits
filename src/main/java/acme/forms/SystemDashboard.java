package acme.forms;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SystemDashboard implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	double ratioToolWithChimpum;
	
	Map<String,Double> avgBudgetByCurrency;
	
	Map<String,Double> deviationBudgetByCurrency;
	
	Map<String,Double> maxBudgetByCurrency;
	
	Map<String,Double> minBudgetByCurrency;

}
