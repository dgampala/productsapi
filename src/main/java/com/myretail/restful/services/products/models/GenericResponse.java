package com.myretail.restful.services.products.models;

public class GenericResponse {

	String message ;
	String reason ; 
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	} 
	
}
