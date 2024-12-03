package com.mx.nativelabs.backendapp.commons.model.response;

import java.util.List;

import com.mx.nativelabs.backendapp.commons.model.cat.BaseCatDTO;

public class ResponseListDTO extends BaseResponseDTO  {

	private static final long serialVersionUID = 1L;
	
	public ResponseListDTO() {}
	
	public ResponseListDTO(int code, List<? extends BaseDTO> data, String message) {
		this.data = data;
		this.setCode(code);
		this.setMessage(message);
	}
	
	private List<? extends BaseDTO> data;
	
	private MetadataDTO metadata;

	public List<? extends BaseDTO> getData() {
		return data;
	}

	public void setData(List<? extends BaseDTO> data) {
		this.data = data;
	}

	public MetadataDTO getMetadata() {
		return metadata;
	}

	public void setMetadata(MetadataDTO metadata) {
		this.metadata = metadata;
	}

}
