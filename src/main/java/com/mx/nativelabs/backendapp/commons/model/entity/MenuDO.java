package com.mx.nativelabs.backendapp.commons.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "MENU")
@Entity
public class MenuDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private Integer idMenu;

    @Column(name = "url")
    private String url;

    @Column(name = "estatus")
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sucursal")
    private BranchOfficeDO branchOffice;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_menu_producto")
    private CatTypeMenuProductDO typeMenu;

	@Column(name = "fecha_modificacion")
    private Date modificationDate;

    @Column(name = "usuario_creacion")
    private String creationUser;

    @Column(name = "usuario_modificacion")
    private String modificationUser;

    @Column(name = "fecha_creacion")
    private Date creationDate;

    @Column(name = "fecha_registro")
    private Date registrationDate;
    
    public MenuDO() {}
    
    public MenuDO(Integer idMenu) {
    	this.idMenu = idMenu;
    }
    

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer IdMenu) {
        idMenu = IdMenu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
   
    public BranchOfficeDO getIdBranchOffice() {
		return branchOffice;
	}

	public void setIdBranchOffice(BranchOfficeDO branchOffice) {
		this.branchOffice = branchOffice;
	}

	public CatTypeMenuProductDO getTypeMenu() {
		return typeMenu;
	}

	public void setTypeMenu(CatTypeMenuProductDO typeMenu) {
		this.typeMenu = typeMenu;
	}

	public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public String getModificationUser() {
        return modificationUser;
    }

    public void setModificationUser(String modificationUser) {
        this.modificationUser = modificationUser;
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
