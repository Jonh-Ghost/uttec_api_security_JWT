package com.mx.nativelabs.backendapp.commons.model.cat;

import java.io.Serializable;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.model.response.BaseDTO;

public class BaseCatDTO extends BaseDTO  implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private String description;
	
	public BaseCatDTO(){}

	public BaseCatDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public BaseCatDTO(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return Utils.objectToString(this);
	}
}
