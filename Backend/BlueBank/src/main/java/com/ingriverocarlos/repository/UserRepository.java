package com.ingriverocarlos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingriverocarlos.model.User;


/**
 * @author carlos rivero
 * 
 * Clase para gestionar la conexion a BD
 **/

public interface UserRepository extends JpaRepository<User, Integer>{

	/*
	 * Metodos auxiliares para realizar la validacion correspondiente
	 * a cada campo.
	 * */
	User findByCorreo(String correo);
	
	User findByCedula(String cedula);
}
