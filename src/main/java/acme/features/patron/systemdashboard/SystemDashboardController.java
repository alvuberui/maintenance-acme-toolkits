package acme.features.patron.systemdashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.forms.SystemDashboard;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class SystemDashboardController extends AbstractController<Patron, SystemDashboard>{
	@Autowired
	protected SystemDashboardShowService adShowService;


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.adShowService);
	}

}


