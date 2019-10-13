package com.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the CIUDADES database table.
 * 
 */
@Entity
@Table(name="CIUDADES")
@NamedQuery(name="Ciudade.findAll", query="SELECT c FROM Ciudade c")
public class Ciudade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CIU_ID")
	private Long ciuId;

	@Column(name="CIU_NOMBRE")
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