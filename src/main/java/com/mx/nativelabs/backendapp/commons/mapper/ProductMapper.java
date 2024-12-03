package com.mx.nativelabs.backendapp.commons.mapper;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.model.dto.ProductDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.ProductDO;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    private ProductMapper() {

    }

    public static ProductDTO toGet(ProductDO productDO) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productDO.getIdProduct());
        productDTO.setName(productDO.getName());
        productDTO.setSpeciality(productDO.getSpeciality());
        productDTO.setDescription(productDO.getDescription());
        productDTO.setPrice(productDO.getPrice());
        productDTO.setStatus(productDO.getStatus());
        productDTO.setType(productDO.getType());
        productDTO.setUrlImage(productDO.getUserCreatedAt());
        return productDTO;
    }

    public static List<ProductDTO> toGet(List<ProductDO> productDOS) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductDO productDO : productDOS) {
            productDTOS.add(toGet(productDO));
        }
        return productDTOS;
    }

    public static ProductDO toCreate(ProductDTO productDTO) {
        ProductDO productDO = new ProductDO();
        productDO.setIdProduct(productDTO.getId());
        productDO.setName(productDTO.getName());
        productDO.setSpeciality(productDTO.getSpeciality());
        productDO.setDescription(productDTO.getDescription());
        productDO.setPrice(productDTO.getPrice());
        productDO.setStatus(productDTO.getStatus());
        productDO.setType(productDTO.getType());
        productDO.setModificationDate(Utils.getDate());
        productDO.setUserCreatedAt("email_01@gmail.com");
        productDO.setUserModifiedAt("email_01@gmail.com");
        productDO.setCreationDate(Utils.getDate());
        productDO.setRegistrationDate(Utils.getDate());
        return productDO;
    }

    public static void toUpdate(ProductDTO productDTO, ProductDO productDO) {
        productDO.setName(productDTO.getName());
        productDO.setSpeciality(productDTO.getSpeciality());
        productDO.setDescription(productDTO.getDescription());
        productDO.setPrice(productDTO.getPrice());
        productDO.setStatus(productDTO.getStatus());
        productDO.setType(productDTO.getType());
        productDO.setUserModifiedAt("email_01@gmail.com");
        productDO.setModificationDate(Utils.getDate());
    }

}
