package com.example.lasenioralmacenes.Modelos;

import java.io.Serializable;



/**
 * The persistent class for the CIUDADES database table.
 * 
 */

public class Ciudade implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long ciuId;


	private String ciuNombre;

	public Ciudade() {
	}

	public Long getCiuId() {
		return this.ciuId;
	}

	public void setCiuId(Long ciuId) {
		this.ciuId = ciuId;
	}

	public String getCiuNombre() {
		return this.ciuNombre;
	}

	public void setCiuNombre(String ciuNombre) {
		this.ciuNombre = ciuNombre;
	}


}