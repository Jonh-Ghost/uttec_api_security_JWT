package com.mx.nativelabs.backendapp.commons.model.dto;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.model.response.BaseDTO;

public class FileDTO extends BaseDTO{
	
    private static final long serialVersionUID = 1L;

	private Integer idProduct;
	
	private Integer idMenu;
    
    private String url;
    
	private String name;
	
	private String mimeType;
	
	private String base;

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
		
	public Integer getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	@Override
    public String toString() {
        return Utils.objectToString(this);
    }
	

}
