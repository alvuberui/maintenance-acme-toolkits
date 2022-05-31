package acme.features.inventor.chimpum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chimpum.Chimpum;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorChimpumController extends AbstractController<Inventor, Chimpum> {
	
	@Autowired
	protected InventorChimpumListService listService;
	
	@Autowired
	protected InventorChimpumShowService	showService;
	
	@Autowired

	protected InventorChimpumUpdateService updateService;

	protected InventorChimpumDelete deleteService;

	
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-mine-chimpums", "list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);

	}
}
