package com.mx.nativelabs.backendapp.commons.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "CAT_TIPO_MENU_PRODUCTO")
@Entity
public class CatTypeMenuProductDO {
	
	//Campos de la tabla
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_menu_producto")
	private Integer idTypeMenuProduct;
	
	@Column(name = "nombre")
	private String name;
	
	@Column(name = "descripcion")
	private String description;

	//Getters and Setters
	public Integer getIdTypeMenuProduct() {
		return idTypeMenuProduct;
	}

	public void setIdTypeMenuProduct(Integer idTypeMenuProduct) {
		this.idTypeMenuProduct = idTypeMenuProduct;
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
	
}
