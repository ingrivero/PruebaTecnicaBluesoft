package com.ingriverocarlos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingriverocarlos.model.Cuenta;


/**
 * @author carlos rivero
 * 
 * Clase para gestionar la conexion a BD
 **/

public interface CuentaRepository extends JpaRepository<Cuenta, Integer>{

	/*
	 * Metodos auxiliares para realizar la validacion correspondiente
	 * a cada campo.
	 * */
	Cuenta findByUserId(Integer userId);
	
}
