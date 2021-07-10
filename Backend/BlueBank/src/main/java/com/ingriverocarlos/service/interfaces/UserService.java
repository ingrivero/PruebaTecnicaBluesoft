package com.ingriverocarlos.service.interfaces;


import java.util.Optional;

import com.ingriverocarlos.model.User;

public interface UserService {
	
	Iterable<User>  list();
	User register(User user);
	void update(User user);
	void delete(Integer userId);
	Optional<User> getUser(Integer userId);
	User findByCorreo(String correo);
	User findByCedula(String cedula);
	boolean isEmailAlreadyInUse(String correo);
}

