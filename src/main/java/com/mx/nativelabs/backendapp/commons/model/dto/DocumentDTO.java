package com.mx.nativelabs.backendapp.commons.model.dto;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.model.response.BaseDTO;

public class DocumentDTO extends BaseDTO{

    private static final long serialVersionUID = 1L;
	
	private MenuDTO idMenu;
	
	private ProductDTO idProduct;

	public MenuDTO getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(MenuDTO idMenu) {
		this.idMenu = idMenu;
	}

	public ProductDTO getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(ProductDTO idProduct) {
		this.idProduct = idProduct;
	}

	@Override
	public String toString() {
        return Utils.objectToString(this);
    }
	
	
}
