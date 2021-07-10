package com.ingriverocarlos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingriverocarlos.model.Cuenta;
import com.ingriverocarlos.repository.CuentaRepository;
import com.ingriverocarlos.service.interfaces.CuentaService;

@Service
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	private CuentaRepository cuentaRepository;
	
	@Override
	public List<Cuenta> getAllCuentas() {
		return cuentaRepository.findAll();
	}

	@Override
	public Optional<Cuenta> getCuenta(Integer cuentaId) {
		return cuentaRepository.findById(cuentaId);
	}
	
	@Override
	public Cuenta getCuentaByUser(Integer userId) {
		return cuentaRepository.findByUserId(userId);
	}
	
	@Override
	public void register(Cuenta cuenta) {
		cuentaRepository.save(cuenta);
	}
	
	@Override
	public void update(Cuenta cuenta) {
		cuentaRepository.save(cuenta);
	}

	@Override
	public void delete(Integer cuentaId) {
		cuentaRepository.deleteById(cuentaId);
	}

}
