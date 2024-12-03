package com.mx.nativelabs.backendapp.commons.model.response;

public class ResponseObjectDTO extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;

	private BaseDTO data;
	
	public ResponseObjectDTO(){}
	
	public ResponseObjectDTO(int code, BaseDTO data, String message){
		this.setCode(code);
		this.setMessage(message);
		this.data = data;
	}

	public BaseDTO getData() {
		return data;
	}

	public void setData(BaseDTO data) {
		this.data = data;
	}
	
}
