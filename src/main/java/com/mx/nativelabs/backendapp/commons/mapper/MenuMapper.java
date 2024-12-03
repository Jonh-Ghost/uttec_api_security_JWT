package com.mx.nativelabs.backendapp.commons.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mx.nativelabs.backendapp.commons.model.dto.MenuDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.MenuDO;

public class MenuMapper {

    public MenuMapper() {}

    public static MenuDTO toGet(MenuDO menuDO) {
        MenuDTO menuDTO = new MenuDTO();
        
        menuDTO.setId(menuDO.getIdMenu());
        menuDTO.setStatus(menuDO.getStatus());
        menuDTO.setTypeMenu(CatMapper.toMap(menuDO.getTypeMenu()));
        
        return menuDTO;
    }

    public static List<MenuDTO> toGet(List<MenuDO> menuDOS) {
        List<MenuDTO> menuDTOS = new ArrayList<>();
        for (MenuDO menuDO : menuDOS) {
            menuDTOS.add(toGet(menuDO));
        }
        return menuDTOS;
    }

    public static MenuDO toCreate(MenuDTO menuDTO) {
    	
        MenuDO menuDO = new MenuDO();
        menuDO.setIdMenu(menuDTO.getId());
        menuDO.setStatus(menuDTO.getStatus());
        
        return menuDO;
        
    }

    public static void toUpdate(MenuDTO menuDTO, MenuDO menuDO) {
    	
        menuDO.setStatus(menuDTO.getStatus());
       
    }

}
