package com.mx.nativelabs.backendapp.commons.model.dto;

import java.util.Date;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.model.cat.BaseCatDTO;
import com.mx.nativelabs.backendapp.commons.model.response.BaseDTO;

public class BusinessDTO extends BaseDTO {
 
private static final long serialVersionUID = 1L;
	
	private String name;
	
	private BaseCatDTO typeBusiness;
	
	private OwnerDTO owner;
	
	private Date dateModification;
	
    private String userCreation;
	
	private String userModification;
	
	private Date dateRegistration;
	
	private Date dateCreation;
	
	private String urlQr;

	public String getUrlQr() {
		return urlQr;
	}

	public void setUrlQr(String urlQr) {
		this.urlQr = urlQr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BaseCatDTO getTypeBusiness() {
		return typeBusiness;
	}

	public void setTypeBusiness(BaseCatDTO typeBusiness) {
		this.typeBusiness = typeBusiness;
	}

	public OwnerDTO getIdOwner() {
		return owner;
	}

	public void setIdOwner(OwnerDTO owner) {
		this.owner = owner;
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

	public Date getDateRegistration() {
		return dateRegistration;
	}

	public void setDateRegistration(Date dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public String toString() {
		return Utils.objectToString(this);
	}
	
}
