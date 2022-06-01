package acme.entities.chimpum;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;

import acme.entities.artefact.Artefact;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Chimpum extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	@NotBlank
//	@Pattern(regexp = "PATTERN")
	private String code;

	@Valid
	@NotNull
	private Money budget;
	
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date creationMoment;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date initPeriod;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date finalPeriod;
	
	@NotBlank
	@Length(min=1, max= 101)
	private String description;
	
	@URL
	private String link;
	
	@Valid
	@OneToOne(optional=false)
	protected Artefact artefact;
	
	//@NotNull
	@Valid
	@ManyToOne(optional = false) 
	protected Inventor inventor;
	
}
