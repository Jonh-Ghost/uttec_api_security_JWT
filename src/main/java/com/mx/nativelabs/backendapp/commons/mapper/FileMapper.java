package com.mx.nativelabs.backendapp.commons.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mx.nativelabs.backendapp.commons.model.dto.FileDTO;
import com.mx.nativelabs.backendapp.commons.model.entity.FileDO;

public class FileMapper {

	 public FileMapper() {
	    }

	    public static FileDTO toGet(FileDO fileDO) {
	        FileDTO fileDTO = new FileDTO();
	        fileDTO.setUrl(fileDO.getUrl());
	        fileDTO.setName(fileDO.getName());
	        //fileDTO.setBase(fileDO.getBase());
	        return fileDTO;
	    }

	    public static List<FileDTO> toGet(List<FileDO> fileDOs) {
	        List<FileDTO> menuDTOS = new ArrayList<>();
	        for (FileDO fileDO : fileDOs) {
	            menuDTOS.add(toGet(fileDO));
	        }
	        return menuDTOS;
	    }

	    public static FileDO toCreate(FileDTO fileDTO) {
	        FileDO fileDO = new FileDO();
	        fileDO.setUrl(fileDTO.getUrl());
	        fileDO.setName(fileDTO.getName());
	        //fileDO.setBase(fileDTO.getBase());
	        return fileDO;
	    }

	    public static void toUpdate(FileDTO fileDTO, FileDO fileDO) {
	    	 fileDO.setUrl(fileDTO.getUrl());
	    	 fileDO.setName(fileDTO.getName());
		     //fileDO.setBase(fileDTO.getBase());
	    }

	
}
