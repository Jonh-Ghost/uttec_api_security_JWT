package com.mx.nativelabs.backendapp.commons.model.dto;

import com.mx.nativelabs.backendapp.commons.model.response.BaseDTO;

public class BranchOfficeDTO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;
	
	private String name; 
	
	private String nameManager;
	
	private BusinessDTO business;
	
	private Integer active;
	
	private String address;
	
	private String phone;
	
	private String email;
	
	private String publicPath;
	
	private boolean statusQr;
	
	private String urlQr;
	
	public Integer countUpdatesStatusQr;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlQr() {
		return urlQr;
	}

	public void setUrlQr(String urlQr) {
		this.urlQr = urlQr;
	}

	public String getNameManager() {
		return nameManager;
	}

	public void setNameManager(String nameManager) {
		this.nameManager = nameManager;
	}
	
	public BusinessDTO getBusiness() {
		return business;
	}

	public void setBusiness(BusinessDTO business) {
		this.business = business;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public boolean getStatusQr() {
		return statusQr;
	}

	public void setStatusQr(boolean statusQr) {
		this.statusQr = statusQr;
	}

	public String getPublicPath() {
		return publicPath;
	}

	public void setPublicPath(String publicPath) {
		this.publicPath = publicPath;
	}

	public Integer getCountUpdatesStatusQr() {
		return countUpdatesStatusQr;
	}

	public void setCountUpdatesStatusQr(Integer countUpdatesStatusQr) {
		this.countUpdatesStatusQr = countUpdatesStatusQr;
	}

}
