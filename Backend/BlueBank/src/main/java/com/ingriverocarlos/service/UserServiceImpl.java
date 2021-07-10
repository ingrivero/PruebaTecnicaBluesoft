package com.ingriverocarlos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingriverocarlos.model.User;
import com.ingriverocarlos.repository.UserRepository;
import com.ingriverocarlos.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Iterable<User> list() {
		return userRepository.findAll();
	}
	
	@Override
	public Optional<User> getUser(Integer userId) {
		return userRepository.findById(userId);
	}
	
	@Override
	public User findByCorreo(String correo) {
		return userRepository.findByCorreo(correo);
	}
	
	@Override
	public User findByCedula(String cedula) {
		return userRepository.findByCedula(cedula);
	}
	
	@Override
	public boolean isEmailAlreadyInUse(String correo) {
		User user = userRepository.findByCorreo(correo);
		return user != null ? true : false;
	}
	
	public boolean isUnique(User value, String fieldName) throws Exception {

		switch(fieldName) {
			case "correo":
				String correo = value.getCorreo();
				return userRepository.findByCorreo(correo) != null ? false : true;
			
			case "cedula":
				String cedula = value.getCedula();
				return userRepository.findByCedula(cedula) != null ? false : true;
				
			default:
				 throw new Exception("Campo "+fieldName+" no soportado");
		}
	}

	@Override
	public User register(User user) {
		return userRepository.save(user);
	}

	@Override
	public void update(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(Integer userId) {
		userRepository.deleteById(userId);
	}

}
