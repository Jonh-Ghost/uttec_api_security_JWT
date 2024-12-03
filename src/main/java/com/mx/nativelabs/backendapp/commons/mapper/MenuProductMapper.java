package com.mx.nativelabs.backendapp.commons.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mx.nativelabs.backendapp.commons.model.dto.MenuDTO;
import com.mx.nativelabs.backendapp.commons.model.dto.DocumentDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.MenuDO;
import com.mx.nativelabs.backendapp.commons.model.entity.MenuProductDO;
import com.mx.nativelabs.backendapp.commons.model.entity.ProductDO;

public class MenuProductMapper {
	
	public MenuProductMapper() {
		
	}

	public static DocumentDTO toGet(MenuProductDO menuProductDO) {
		DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(menuProductDO.getIdMenuProduct());
        return documentDTO;
    }

    public static List<DocumentDTO> toGet(List<MenuProductDO> menuProductDOs) {
        List<DocumentDTO> menuProductDTOS = new ArrayList<>();
        for (MenuProductDO menuProductDO : menuProductDOs) {
        	menuProductDTOS.add(toGet(menuProductDO));
        }
        return menuProductDTOS;
    }

    public static MenuProductDO toCreate(DocumentDTO documentDTO) {
        MenuProductDO menuProductDO = new MenuProductDO();
        menuProductDO.setIdMenu(new MenuDO(documentDTO.getIdMenu().getId()));
        menuProductDO.setIdProduct(new ProductDO(documentDTO.getIdProduct().getId()));
        return menuProductDO;
    }

    public static void toUpdate(MenuDTO menuDTO, MenuDO menuDO) {
               
    }
}
