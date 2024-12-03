package com.mx.nativelabs.backendapp.commons.model.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "PRODUCTO")
@Entity
public class ProductDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private Integer idProduct;

	@Column(name = "nombre")
	private String name;

	@Column(name = "especialidad")
	private String speciality;

	@Column(name = "descripcion")
	private String description;

	@Column(name = "precio")
	private Float price;

	@Column(name = "estatus")
	private int status;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_negocio")
	private UserDO business;

	@Column(name = "id_tipo_menu_producto")
	private Integer type;

	@Column(name = "fecha_modificacion")
	private Date modificationDate;

	@Column(name = "usuario_creacion")
	private String userCreatedAt;

	@Column(name = "usuario_modificacion")
	private String userModifiedAt;

	@Column(name = "fecha_creacion")
	private Date creationDate;

	@Column(name = "fecha_registro")
	private Date registrationDate;
	
	public ProductDO() {
		
	}
	
	public ProductDO(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UserDO getIdBusiness() {
		return business;
	}

	public void setIdBusiness(UserDO business) {
		this.business = business;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getUserCreatedAt() {
		return userCreatedAt;
	}

	public void setUserCreatedAt(String userCreatedAt) {
		this.userCreatedAt = userCreatedAt;
	}

	public String getUserModifiedAt() {
		return userModifiedAt;
	}

	public void setUserModifiedAt(String userModifiedAt) {
		this.userModifiedAt = userModifiedAt;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
}
