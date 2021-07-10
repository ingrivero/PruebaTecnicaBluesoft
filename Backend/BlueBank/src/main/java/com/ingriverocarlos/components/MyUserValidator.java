package com.ingriverocarlos.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

import com.ingriverocarlos.model.User;
import com.ingriverocarlos.service.UserServiceImpl;
/**
 * @author carlos rivero
 * 
 * Clase de apoyo que verifica si los campos definidos son unicos en la BD
 * */
@Component
public class MyUserValidator {
	@Autowired
    private UserServiceImpl service;
	
	private List<ObjectError> errors;
	
	public boolean validate(User user, List<String> fields) {
		
		errors = new ArrayList<ObjectError>(fields.size());
		
		int countErrors = 0;
		
		for(int i=0; i<fields.size(); i++) {
			String fieldName = fields.get(i);
			try {
				boolean passed = service.isUnique(user, fieldName);
				System.out.println(fieldName+":"+passed);
				if(!passed) {
					countErrors++;
					ObjectError error = new ObjectError("correo", "Ya existe un usuario registrado con el campo "+fieldName+" indicado.");
					errors.add(error);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return (countErrors == 0);
	}

	public List<ObjectError> getErrors() {
		return errors;
	}

}
