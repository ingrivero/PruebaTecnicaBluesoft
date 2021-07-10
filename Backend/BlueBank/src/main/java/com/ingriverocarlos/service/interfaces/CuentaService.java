package com.ingriverocarlos.service.interfaces;


import java.util.List;
import java.util.Optional;

import com.ingriverocarlos.model.Cuenta;

public interface CuentaService {
	
	List<Cuenta> getAllCuentas();
	void register(Cuenta cuenta);
	void update(Cuenta cuenta);
	void delete(Integer cuentaId);
	Optional<Cuenta> getCuenta(Integer cuentaId);
	Cuenta getCuentaByUser(Integer userId);
}

