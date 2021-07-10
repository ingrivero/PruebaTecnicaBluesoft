package com.ingriverocarlos.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ingriverocarlos.components.MyResponseBody;
import com.ingriverocarlos.components.MyUserValidator;
import com.ingriverocarlos.model.User;
import com.ingriverocarlos.service.UserServiceImpl;

/**
 * @author carlos rivero
 * 
 * Clase controladora que gestiona las peticiones REST
 **/

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private MyUserValidator validator;
	
	@GetMapping(path="/all")
	public @ResponseBody MyResponseBody<Iterable<User>> getAllUsers() {
		
	  MyResponseBody<Iterable<User>> response = new MyResponseBody<Iterable<User>>();
	  response.setStatus(true);
	  response.setMessage("Consulta Exitosa");
	  response.setData(userService.list());
	  
	  return response;
	}
	
	@GetMapping(path="/get/{userId}")
	public @ResponseBody MyResponseBody<User> getUser(@PathVariable("userId") Integer userId) {
		
		Optional<User> user = userService.getUser(userId);
		MyResponseBody<User> response = new MyResponseBody<User>();
		if(user.isEmpty()){
			response.setStatus(false);
			response.setMessage("No existe usuario con id indicado");
		}
		else {
			response.setStatus(true);
			response.setMessage("Consulta Exitosa");
			response.setData(user.get());
		}
	  
		return response;
	}
	
	@PostMapping(path="/register")
	public @ResponseBody MyResponseBody<?> register(@RequestBody @Valid User user, BindingResult bindingResult) {
		/*
		 * Para cada usuario nuevo a registrar definimos los campos que deben ser unicos
		 * para luego pasarlos al validador y verificarlos. 
		 **/
		ArrayList<String> fields = new ArrayList<String>(2);
		fields.add("correo");
		fields.add("cedula");
		
		/*
		 * Primero validamos si el formato de cada campo es correcto para su almacenamiento
		 **/
		if(bindingResult.hasErrors()) {
			MyResponseBody<List<ObjectError>> response = new MyResponseBody<List<ObjectError>>();
			response.setStatus(false);
			response.setMessage("Campos con valores incorrectos.");
			response.setData(bindingResult.getAllErrors());
			return response;
		}
		/*
		 * De no haber errores de formato en los campos, procedemos a validar los campos que deben
		 * ser unicos en BD.
		 **/
		else if(!validator.validate(user, fields)) {
			MyResponseBody<List<ObjectError>> response = new MyResponseBody<List<ObjectError>>();
			response.setStatus(false);
			response.setMessage("Campos con valores incorrectos.");
			response.setData(validator.getErrors());
			return response;
		}
		/*
		 * Si todo es correcto en este punto procedemos a mostrar un mensaje exitoso.
		 **/
		else{
			User newUser = userService.register(user);
			MyResponseBody<User>response = new MyResponseBody<User>();
			response.setStatus(true);
			response.setMessage("Opereación realizada con exito.");
			response.setData(newUser);
			return response;
		}
	}
	
	@PutMapping(path="/update")
	public @ResponseBody MyResponseBody<String> update(@RequestBody @Valid User user) {
		userService.update(user);
		MyResponseBody<String> response = new MyResponseBody<String>();
		response.setStatus(true);
		response.setMessage("Consulta Exitosa.");
		return response;
	}
	
	@DeleteMapping(path="/delete/{userId}")
	public @ResponseBody MyResponseBody<String> delete(@PathVariable("userId") Integer userId) {
		userService.delete(userId);
		MyResponseBody<String> response = new MyResponseBody<String>();
		response.setStatus(true);
		response.setMessage("Consulta Exitosa.");
		return response;
	}
}
