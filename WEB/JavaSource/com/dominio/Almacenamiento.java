package com.dominio;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;




/**
 * The persistent class for the ALMACENAMIENTOS database table.
 * 
 */
@Named("almacenamiento")
@SessionScoped
public class Almacenamiento implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long almaId;
	
	
	private String almaNombre;

	
	private Long almaCanestiba;

	
	private Double almaCappeso;

	
	private Double almaCostoop;

	
	private String almaDescripcion;

	
	private Double almaVolumen;

	
	private Locale locale;

	
	private List<Movimiento> movimientos;
	

	public Almacenamiento() {
	}
	
	
	public Long getAlmaId() {
		return this.almaId;
	}
	
	public void setAlmaId(Long almaId) {
		this.almaId = almaId;
	}
	
	public String getAlmaNombre() {
		return almaNombre;
	}

	public void setAlmaNombre(String almanombre) {
		this.almaNombre = almanombre;
	}

	public String getAlmaDescripcion() {
		return this.almaDescripcion;
	}

	public void setAlmaDescripcion(String almaDescripcion) {
		this.almaDescripcion = almaDescripcion;
	}

	public Locale getLocale() {
		return this.locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public List<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Movimiento addMovimiento(Movimiento movimiento) {
		getMovimientos().add(movimiento);
		movimiento.setAlmacenamiento(this);

		return movimiento;
	}

	public Movimiento removeMovimiento(Movimiento movimiento) {
		getMovimientos().remove(movimiento);
		movimiento.setAlmacenamiento(null);

		return movimiento;
	}

	public Long getAlmaCanestiba() {
		return almaCanestiba;
	}

	public void setAlmaCanestiba(Long almaCanestiba) {
		this.almaCanestiba = almaCanestiba;
	}

	public Double getAlmaCappeso() {
		return almaCappeso;
	}

	public void setAlmaCappeso(Double almaCappeso) {
		this.almaCappeso = almaCappeso;
	}

	public Double getAlmaCostoop() {
		return almaCostoop;
	}

	public void setAlmaCostoop(Double almaCostoop) {
		this.almaCostoop = almaCostoop;
	}

	public Double getAlmaVolumen() {
		return almaVolumen;
	}

	public void setAlmaVolumen(Double almaVolumen) {
		this.almaVolumen = almaVolumen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Almacenamiento [almaId=" + almaId + ", almaNombre=" + almaNombre + ", almaCanestiba=" + almaCanestiba
				+ ", almaCappeso=" + almaCappeso + ", almaCostoop=" + almaCostoop + ", almaDescripcion="
				+ almaDescripcion + ", almaVolumen=" + almaVolumen + ", locale=" + locale + ", movimientos="
				+ movimientos + "]";
	}

	
	
	

}