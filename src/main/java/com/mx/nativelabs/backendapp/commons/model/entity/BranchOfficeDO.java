package com.mx.nativelabs.backendapp.commons.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "SUCURSAL")
@Entity

public class BranchOfficeDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "id_sucursal")
	private Integer idBranchOffice;
	
	@Column(name = "nombre")
	private String name;
	
	@Column(name = "nombre_encargado")
	private String nameManager;
	
	@ManyToOne
	@JoinColumn(name = "id_negocio")
	private UserDO business;
	
	@Column(name = "direccion")
	private String address;
	
	@Column(name = "telefono")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "activo")
	private Integer active;
	
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
	
	public Integer getIdBranchOffice() {
		return idBranchOffice;
	}

	public void setIdBranchOffice(Integer idBranchOffice) {
		this.idBranchOffice = idBranchOffice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameManager() {
		return nameManager;
	}
	
	public UserDO getBusiness() {
		return business;
	}

	public void setBusiness(UserDO business) {
		this.business = business;
	}

	public void setNameManager(String nameManager) {
		this.nameManager = nameManager;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
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
