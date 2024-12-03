package com.mx.nativelabs.backendapp.commons.model.dto;

import java.util.List;

import com.mx.nativelabs.backendapp.commons.Utils;
import com.mx.nativelabs.backendapp.commons.model.cat.BaseCatDTO;
import com.mx.nativelabs.backendapp.commons.model.response.BaseDTO;

public class MenuDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private List<FileDTO> files;
	
	private int status;

	private BranchOfficeDTO branchOffice;

	private BaseCatDTO typeMenu;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BranchOfficeDTO getIdBranchOffice() {
		return branchOffice;
	}

	public void setIdBranchOffice(BranchOfficeDTO branchOficce) {
		this.branchOffice = branchOficce;
	}

	public BaseCatDTO getTypeMenu() {
		return typeMenu;
	}

	public void setTypeMenu(BaseCatDTO typeMenu) {
		this.typeMenu = typeMenu;
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
}
