package com.ingriverocarlos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingriverocarlos.model.Transaccion;
import com.ingriverocarlos.repository.TransaccionRepository;
import com.ingriverocarlos.service.interfaces.TransaccionService;


@Service
public class TransaccionServiceImpl implements TransaccionService {

	@Autowired
	private TransaccionRepository transaccionRepository;

	
	@Override
	public Iterable<Transaccion> getTransacciones(Integer userId, Integer cuentaId) {
		return transaccionRepository.findAllByUserIdAndCuentaId(userId, cuentaId);
	}
	
	@Override
	public void register(Transaccion cuenta) {
		transaccionRepository.save(cuenta);
	}
	

	@Override
	public void delete(Integer transaccionId) {
		transaccionRepository.deleteById(transaccionId);
	}

}
