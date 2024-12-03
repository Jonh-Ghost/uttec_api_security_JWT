package com.mx.nativelabs.backendapp.commons.model.request;

import java.util.List;

import com.mx.nativelabs.backendapp.commons.Utils;

public class UserFilterDTO extends BaseParamsDTO {

	private static final long serialVersionUID = 1L;

	private List<Integer> statusUser;

	private List<Integer> typeUser;

	private String name;

	private String email;

	public UserFilterDTO(){
		super();
	}

	public List<Integer> getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(List<Integer> statusUser) {
		this.statusUser = statusUser;
	}

	public List<Integer> getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(List<Integer> typeUser) {
		this.typeUser = typeUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return Utils.objectToString(this);
	}

}
