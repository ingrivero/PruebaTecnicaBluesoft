package com.ingriverocarlos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingriverocarlos.model.Transaccion;


/**
 * @author carlos rivero
 * 
 * Clase para gestionar la conexion a BD
 **/

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer>{

	/*
	 * Metodos auxiliares para realizar la validacion correspondiente
	 * a cada campo.
	 * */
	Iterable<Transaccion>  findAllByUserId(Integer userId);
	
	//Iterable<Transaccion>  findAllByUserIdAndCuentaId(Integer userId, Integer cuentaId);
	
	Iterable<Transaccion>  findAllByCuentaId(Integer cuentaId);
	
}
