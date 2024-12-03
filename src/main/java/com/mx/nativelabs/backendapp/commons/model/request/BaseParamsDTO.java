package com.mx.nativelabs.backendapp.commons.model.request;

import java.io.Serializable;


public class BaseParamsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int page;
	
	private int size;

	public BaseParamsDTO() {
		this.setPage(0);
		this.setSize(10);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
