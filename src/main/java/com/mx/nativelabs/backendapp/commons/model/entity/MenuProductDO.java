package com.mx.nativelabs.backendapp.commons.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "MENU_PRODUCTO")
public class MenuProductDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_menu_producto")
	private Integer idMenuProduct;
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "id_menu")
	private MenuDO idMenu;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producto")
	private ProductDO idProduct;
	
	public Integer getIdMenuProduct() {
		return idMenuProduct;
	}

	public void setIdMenuProduct(Integer idMenuProduct) {
		this.idMenuProduct = idMenuProduct;
	}
	
	public MenuDO getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(MenuDO idMenu) {
		this.idMenu = idMenu;
	}

	public ProductDO getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(ProductDO idProduct) {
		this.idProduct = idProduct;
	}
	
	
	

}
