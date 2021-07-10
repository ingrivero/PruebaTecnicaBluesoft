package com.ingriverocarlos.rest;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ingriverocarlos.components.MyResponseBody;
import com.ingriverocarlos.model.Cuenta;
import com.ingriverocarlos.model.Transaccion;
import com.ingriverocarlos.service.CuentaServiceImpl;
import com.ingriverocarlos.service.TransaccionServiceImpl;

/**
 * @author carlos rivero
 * 
 * Clase controladora que gestiona las peticiones REST
 **/

@RestController
@CrossOrigin
@RequestMapping("/transacciones")
public class TransaccionRestController {

	@Autowired
	private CuentaServiceImpl cuentaService;
	
	@Autowired
	private TransaccionServiceImpl transaccionServiceImpl;
	
	@GetMapping(path="/get/{userId}/{cuentaId}")
	public @ResponseBody MyResponseBody<Iterable<Transaccion>> getTransacciones(@PathVariable("userId") Integer userId, @PathVariable("cuentaId") Integer cuentaId) {
		
		Iterable<Transaccion> transacciones = transaccionServiceImpl.getTransacciones(userId, cuentaId);
		MyResponseBody<Iterable<Transaccion>> response = new MyResponseBody<Iterable<Transaccion>>();
		response.setStatus(true);
		response.setMessage("Consulta Exitosa");
		response.setData(transacciones);
		
		return response;
	}
	
	@PostMapping(path="/register")
	public @ResponseBody MyResponseBody<?> register(@RequestBody @Valid Transaccion transaccion, BindingResult bindingResult) {
		
		Optional<Cuenta> cuentaOpt = cuentaService.getCuenta(transaccion.getCuentaId());
		MyResponseBody<String>response = new MyResponseBody<String>();
	
		if(!cuentaOpt.isEmpty()){
			
			Cuenta cuenta = cuentaOpt.get();
			
			if(transaccion.getTipo().equals("Retiro")){
				Double saldo = cuenta.getSaldo();
				if(saldo >= transaccion.getMonto()){
					transaccion.setCurrentFecha();
					cuenta.setSaldo(saldo - transaccion.getMonto());
					cuentaService.update(cuenta);
					transaccionServiceImpl.register(transaccion);
					response.setStatus(true);
					response.setMessage("Opereación realizada con exito.");
				}else {
					response.setStatus(false);
					response.setMessage("No dispone del saldo suficiente para el retiro, monto maximo de retiro es: "+cuenta.getSaldo());
				}
				
			}else if(transaccion.getTipo().equals("Consignacion")){
				transaccion.setCurrentFecha();
				Double saldo = cuenta.getSaldo();
				cuenta.setSaldo(saldo + transaccion.getMonto());
				cuentaService.update(cuenta);
				transaccionServiceImpl.register(transaccion);
				response.setStatus(true);
				response.setMessage("Opereación realizada con exito.");
				
			}else {
				response.setStatus(false);
				response.setMessage("Tipo de transaccion desconocida: "+transaccion.getTipo());
			}
		}else{
			response.setStatus(false);
			response.setMessage("No exite el numero de cuenta indicado:"+transaccion.getCuentaId());
		}
		
		return response;
	}
	
	
	@DeleteMapping(path="/delete/{transaccionId}")
	public @ResponseBody MyResponseBody<String> delete(@PathVariable("transaccionId") Integer transaccionId) {
		transaccionServiceImpl.delete(transaccionId);
		MyResponseBody<String> response = new MyResponseBody<String>();
		response.setStatus(true);
		response.setMessage("Consulta Exitosa.");
		return response;
	}
}
