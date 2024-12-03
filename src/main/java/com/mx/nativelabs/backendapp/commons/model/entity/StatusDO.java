package com.mx.nativelabs.backendapp.commons.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "CAT_TIPO_NEGOCIO")
@Entity
public class StatusDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_negocio")
	private Integer idTypeBusiness;
	
	@Column(name = "nombre")
	private String name;
	
	@Column(name = "descripcion")
	private String description;

	public Integer getIdTypeBusiness() {
		return idTypeBusiness;
	}

	public void setIdTypeBusiness(Integer type) {
		this.idTypeBusiness = type;
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