package com.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the ALMACENAMIENTOS database table.
 * 
 */
@Entity
@Table(name="ALMACENAMIENTOS")
@NamedQuery(name="Almacenamiento.obtenerTodos", query="SELECT a FROM Almacenamiento a ORDER BY a.almaNombre")
@NamedQuery(name="Almacenamiento.obtenerAlmacen", query="SELECT a FROM Almacenamiento a WHERE a.almaNombre LIKE :almaNombre")
public class Almacenamiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_alma", sequenceName="seq_Alma_Id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_alma")
	@Column(name="ALMA_ID")
	private Long almaId;
	
	@Column(name="ALMA_NOMBRE", unique=true)
	private String almaNombre;

	@Column(name="ALMA_CANESTIBA")
	private Long almaCanestiba;

	@Column(name="ALMA_CAPPESO")
	private Double almaCappeso;

	@Column(name="ALMA_COSTOOP")
	private Double almaCostoop;

	@Column(name="ALMA_DESCRIPCION")
	private String almaDescripcion;

	@Column(name="ALMA_VOLUMEN")
	private Double almaVolumen;

	//bi-directional many-to-one association to Locale
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="LOC_ID")
	private Locale locale;

	//bi-directional many-to-one association to Movimiento
	//@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	//private List<Movimiento> movimientos;
	

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
/*
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
*/
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

	
	
	

}