package acme.features.patron.chimpum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chimpum.Chimpum;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;


@Controller
public class PatronChimpumController  extends AbstractController<Patron, Chimpum>{
	
	@Autowired 
	protected PatronChimpumListService listAll;
	
	@Autowired 
	protected PatronChimpumShowService show;
	
	@Autowired 
	protected PatronChimpumCreateService create;
	@Autowired 
	protected PatronChimpumUpdateArtefactService updateArtefact;
	@Autowired 
	protected PatronChimpumUpdateService update;
	
	@Autowired 
	protected PatronChimpumDeleteService delete;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.show);
		super.addCommand("create", this.create);
		super.addCommand("list", this.listAll);
		super.addCommand("update-artefact","update" ,this.updateArtefact);
		super.addCommand("update" ,this.update);
		super.addCommand("delete" ,this.delete);
	}
}
