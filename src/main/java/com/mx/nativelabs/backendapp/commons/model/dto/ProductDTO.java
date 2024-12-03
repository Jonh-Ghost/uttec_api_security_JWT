package com.mx.nativelabs.backendapp.commons.model.dto;

import java.util.List;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.model.response.BaseDTO;


public class ProductDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    private String name;

    private String speciality;

    private String description;

    private Float price;

    private String urlImage;
    
    private Integer type;
    
    private Integer status;

    private BusinessDTO business;
    
    private List<FileDTO> files;
    

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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BusinessDTO getIdBusiness() {
		return business;
	}

	public void setIdBusiness(BusinessDTO business) {
		this.business = business;
	}

	public List<FileDTO> getFiles() {
		return files;
	}

	public void setFiles(List<FileDTO> files) {
		this.files = files;
	}

	@Override
    public String toString() {
        return Utils.objectToString(this);
    }
	
	public static long getSerialversionuid() {
	    return serialVersionUID;
	    
	}
}
