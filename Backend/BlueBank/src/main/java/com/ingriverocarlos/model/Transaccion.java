package com.ingriverocarlos.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author carlos rivero
 * 
 * Clase Modelo para el usuario
 * */

@Entity
@Table(name = "transacciones")
public class Transaccion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "userId")
	private Integer userId;
	
	@Column(name = "cuentaId")
	private Integer cuentaId;
	
	@Column(name = "tipo")
	private String tipo;

	@Column(name = "monto")
	private Double monto;
	
	@Column(name = "fecha")
	private String fecha;
	
	
	public void setCurrentFecha() {
		Date date = new Date();
		String strDate = "yyyy-mm-dd HH:mm:ss"; 
        SimpleDateFormat objStrDate = new SimpleDateFormat(strDate); 
		this.fecha = objStrDate.format(date);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCuentaId() {
		return cuentaId;
	}
	public void setCuentaId(Integer cuentaId) {
		this.cuentaId = cuentaId;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	
	@Override
	public String toString() {
		return "User [id=" + id + ", tipo=" + tipo + ", monto=" + monto +", fecha=" + fecha +"]";
	}
	
	
}
