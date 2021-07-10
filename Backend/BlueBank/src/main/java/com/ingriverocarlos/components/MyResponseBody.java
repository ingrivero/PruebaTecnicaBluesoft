package com.ingriverocarlos.components;

import org.springframework.stereotype.Component;

/**
 * @author carlos rivero
 * 
 * Clase de apoyo para dar respuesta con formato a una peticion REST
 * */
@Component
public class MyResponseBody<T>{
	
	private boolean status;
	private String message;
	private T data;
	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	

}
