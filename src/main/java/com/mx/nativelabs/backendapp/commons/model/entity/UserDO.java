package com.mx.nativelabs.backendapp.commons.model.entity;

import java.util.Date;

import javax.persistence.*;

@Table(name = "DOCUMENTO")
@Entity

public class UserDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_negocio")
	private Integer idBusiness;

	@Column(name = "nombre")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_negocio")
	private StatusDO type;

	@ManyToOne
	@JoinColumn(name = "id_propietario")
	private OwnerDO idOwner;

	@Column(name = "fecha_modificacion")
	private Date dateModification;

	@Column(name = "usuario_creacion")
	private String userCreation;

	@Column(name = "usuario_modificacion")
	private String userModification;

	@Column(name = "fecha_creacion")
	private Date dateCreation;

	@Column(name = "fecha_registro")
	private Date dateRegistration;

	public Integer getIdBusiness() {
		return idBusiness;
	}

	public void setIdBusiness(Integer idBusiness) {
		this.idBusiness = idBusiness;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StatusDO getType() {
		return type;
	}

	public void setType(StatusDO type) {
		this.type = type;
	}

	public OwnerDO getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(OwnerDO idOwner) {
		this.idOwner = idOwner;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public String getUserCreation() {
		return userCreation;
	}

	public void setUserCreation(String userCreation) {
		this.userCreation = userCreation;
	}

	public String getUserModification() {
		return userModification;
	}

	public void setUserModification(String userModification) {
		this.userModification = userModification;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateRegistration() {
		return dateRegistration;
	}

	public void setDateRegistration(Date dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

}
