package com.ingriverocarlos.service.interfaces;


import com.ingriverocarlos.model.Transaccion;

public interface TransaccionService {
	
	void register(Transaccion transaccion);
	void delete(Integer transaccionId);
	Iterable<Transaccion> getTransacciones(Integer cuentaId);
}

