package com.mx.nativelabs.backendapp.commons.model.response;

public class MetadataDTO {
	
	private PaginationDTO pagination;
	
	private SortDTO sort;

	public MetadataDTO(){}

	public MetadataDTO(PaginationDTO pagination, SortDTO sort) {
		this.pagination = pagination;
		this.sort = sort;
	}

	public PaginationDTO getPagination() {
		return pagination;
	}

	public void setPagination(PaginationDTO pagination) {
		this.pagination = pagination;
	}

	public SortDTO getSort() {
		return sort;
	}

	public void setSort(SortDTO sort) {
		this.sort = sort;
	}

}
