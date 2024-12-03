package com.mx.nativelabs.backendapp.commons.model.response;

public class SortDTO {

	private String type;
	
	private String field;

	public SortDTO(){}
	
	public SortDTO(String type, String field) {
		this.type = type;
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
}
