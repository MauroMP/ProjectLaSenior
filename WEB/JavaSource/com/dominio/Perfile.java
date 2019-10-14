package com.dominio;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



/**
 * The persistent class for the PERFILES database table.
 * 
 */
@Named("perfil")
@SessionScoped
public class Perfile implements Serializable {
	private static final long serialVersionUID = 1L;


	private long perfCodigo;

	
	private String perfNombre;

	public Perfile() {
	}

	public long getPerfCodigo() {
		return this.perfCodigo;
	}

	public void setPerfCodigo(long perfCodigo) {
		this.perfCodigo = perfCodigo;
	}

	public String getPerfNombre() {
		return this.perfNombre;
	}

	public void setPerfNombre(String perfNombre) {
		this.perfNombre = perfNombre;
	}

	@Override
	public String toString() {
		return "Perfile [perfCodigo=" + perfCodigo + ", perfNombre=" + perfNombre + "]";
	}

	
	
}