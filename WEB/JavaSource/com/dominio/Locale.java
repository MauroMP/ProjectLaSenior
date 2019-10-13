package com.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the LOCALES database table.
 * 
 */
@Entity
@Table(name="LOCALES")
@NamedQuery(name="Locale.findAll", query="SELECT l FROM Locale l")
public class Locale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOC_ID")
	private Long locId;

	@Column(name="LOC_DESCRIPCION")
	private String locDescripcion;

	@Column(name="LOC_DIRECCION")
	private String locDireccion;

	@Column(name="LOC_TIPO")
	private String locTipo;

	//bi-directional many-to-one association to Almacenamiento
	//@OneToMany(mappedBy="locale", fetch=FetchType.LAZY)
	//private List<Almacenamiento> almacenamientos;

	//bi-directional many-to-one association to Ciudade
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CIU_ID")
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

	/*public List<Almacenamiento> getAlmacenamientos() {
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
	}*/

	public Ciudade getCiudade() {
		return this.ciudade;
	}

	public void setCiudade(Ciudade ciudade) {
		this.ciudade = ciudade;
	}

}