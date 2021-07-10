package com.ingriverocarlos.rest;

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
import com.ingriverocarlos.model.Cuenta;
import com.ingriverocarlos.service.CuentaServiceImpl;

/**
 * @author carlos rivero
 * 
 * Clase controladora que gestiona las peticiones REST
 **/

@RestController
@CrossOrigin
@RequestMapping("/cuentas")
public class CuentaRestController {

	@Autowired
	private CuentaServiceImpl cuentaService;
	
	@GetMapping(path="/all")
	public @ResponseBody MyResponseBody<List<Cuenta>> getAllCuentas() {
		
		MyResponseBody<List<Cuenta>> response = new MyResponseBody<List<Cuenta>>();
		
		response.setStatus(true);
		response.setMessage("Consulta Exitosa");
		response.setData(cuentaService.getAllCuentas());
		
		return response;
	}
	
	@GetMapping(path="/get/{cuentaId}")
	public @ResponseBody MyResponseBody<?> getCuenta(@PathVariable("cuentaId") Integer cuentaId) {
		
		Optional<Cuenta> cuenta = cuentaService.getCuenta(cuentaId);
		MyResponseBody<Cuenta> response = new MyResponseBody<Cuenta>();
		if(cuenta.isEmpty()){
			response.setStatus(false);
			response.setMessage("No existe la cuenta con el id indicado");
		}
		else {
			response.setStatus(true);
			response.setMessage("Consulta Exitosa");
			response.setData(cuenta.get());
		}
	  
		return response;
	}
	
	@PostMapping(path="/register")
	public @ResponseBody MyResponseBody<?> register(@RequestBody @Valid Cuenta cuenta, BindingResult bindingResult) {
		
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
		else{
			cuentaService.register(cuenta);
			MyResponseBody<String>response = new MyResponseBody<String>();
			response.setStatus(true);
			response.setMessage("Opereación realizada con exito.");
			return response;
		}
	}
	
	@PutMapping(path="/update")
	public @ResponseBody MyResponseBody<String> update(@RequestBody @Valid Cuenta cuenta) {
		cuentaService.update(cuenta);
		MyResponseBody<String> response = new MyResponseBody<String>();
		response.setStatus(true);
		response.setMessage("Consulta Exitosa.");
		return response;
	}
	
	@DeleteMapping(path="/delete/{cuentaId}")
	public @ResponseBody MyResponseBody<String> delete(@PathVariable("cuentaId") Integer cuentaId) {
		cuentaService.delete(cuentaId);
		MyResponseBody<String> response = new MyResponseBody<String>();
		response.setStatus(true);
		response.setMessage("Consulta Exitosa.");
		return response;
	}
}
