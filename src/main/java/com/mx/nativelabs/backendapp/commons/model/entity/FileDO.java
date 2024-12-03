package com.mx.nativelabs.backendapp.commons.model.entity;

import javax.persistence.*;

@Table(name = "ARCHIVO")
@Entity
public class FileDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_archivo")
	private Integer idFile;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producto")
	private ProductDO product;

	// relacion con menu
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_menu")
	private MenuDO menu;

	@Column(name = "entidad_negocio")
	private Integer entidadNegocio;

	@Column(name = "url")
	private String url;

	@Column(name = "nombre")
	private String name;

	public Integer getIdFile() {
		return idFile;
	}

	public void setIdFile(Integer idFile) {
		this.idFile = idFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ProductDO getProduct() {
		return product;
	}

	public void setProduct(ProductDO product) {
		this.product = product;
	}

	public MenuDO getMenu() {
		return menu;
	}

	public void setMenu(MenuDO menu) {
		this.menu = menu;
	}

	public Integer getEntidadNegocio() {
		return entidadNegocio;
	}

	public void setEntidadNegocio(Integer entidadNegocio) {
		this.entidadNegocio = entidadNegocio;
	}
		
}
