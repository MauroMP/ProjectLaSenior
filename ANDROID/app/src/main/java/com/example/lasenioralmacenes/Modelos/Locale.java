package com.example.lasenioralmacenes.Modelos;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the LOCALES database table.
 * 
 */

public class Locale implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long locId;


	private String locDescripcion;


	private String locDireccion;


	private String locTipo;



	private List<Almacenamiento> almacenamientos;



	private Ciudade ciudade;

	public Locale() {
	}

	public Long getLocId() {
		return this.locId;
	}

	public void setLocId(Long locId) {
		this.locId = locId;
	}

	public String getLocDescripcion() {
		return this.locDescripcion;
	}

	public void setLocDescripcion(String locDescripcion) {
		this.locDescripcion = locDescripcion;
	}

	public String getLocDireccion() {
		return this.locDireccion;
	}

	public void setLocDireccion(String locDireccion) {
		this.locDireccion = locDireccion;
	}

	public String getLocTipo() {
		return this.locTipo;
	}

	public void setLocTipo(String locTipo) {
		this.locTipo = locTipo;
	}

	public List<Almacenamiento> getAlmacenamientos() {
		return this.almacenamientos;
	}

	public void setAlmacenamientos(List<Almacenamiento> almacenamientos) {
		this.almacenamientos = almacenamientos;
	}

	public Almacenamiento addAlmacenamiento(Almacenamiento almacenamiento) {
		getAlmacenamientos().add(almacenamiento);
		almacenamiento.setLocale(this);

		return almacenamiento;
	}

	public Almacenamiento removeAlmacenamiento(Almacenamiento almacenamiento) {
		getAlmacenamientos().remove(almacenamiento);
		almacenamiento.setLocale(null);

		return almacenamiento;
	}

	public Ciudade getCiudade() {
		return this.ciudade;
	}

	public void setCiudade(Ciudade ciudade) {
		this.ciudade = ciudade;
	}

}