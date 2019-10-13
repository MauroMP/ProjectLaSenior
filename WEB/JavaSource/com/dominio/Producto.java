package com.dominio;

import java.io.Serializable;
import java.util.Date;

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
 * The persistent class for the PRODUCTOS database table.
 * 
 */
@Entity
@Table(name = "PRODUCTOS")
@NamedQuery(name = "Producto.obtenerTodos", query = "SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_prod", sequenceName = "seq_Prod_Id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_prod")
	@Column(name = "PROD_ID")
	private long prodId;

	@Column(name = "PROD_ESTIBA")
	private String prodEstiba;

	@Column(name = "PROD_FELAB")
	private Date prodFelab;

	@Column(name = "PROD_FVEN")
	private Date prodFven;

	@Column(name = "PROD_LOTE")
	private String prodLote;

	@Column(name = "PROD_NOMBRE")
	private String prodNombre;

	@Column(name = "PROD_PESO")
	private Double prodPeso;

	@Column(name = "PROD_PRECIO")
	private Double prodPrecio;

	@Column(name = "PROD_SEGMETAC")
	private String prodSegmetac;

	@Column(name = "PROD_STKMIN")
	private Long prodStkmin;

	@Column(name = "PROD_STKTOTAL")
	private Double prodStktotal;

	@Column(name = "PROD_VOL")
	private Double prodVol;

	/*
	 * //bi-directional many-to-one association to Movimiento
	 * 
	 * @OneToMany(mappedBy="producto")// si no comento esto me da error,
	 * fetch=FetchType.EAGER) private List<Movimiento> movimientos;
	 */
	// bi-directional many-to-one association to Familia
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FAMI_CODI")
	private Familia familia;

	// bi-directional many-to-one association to Usuario
	 
	 @ManyToOne(fetch=FetchType.EAGER)	
	 @JoinColumn(name="USU_CODIGO")
	 private Usuario usuario;
	 
	 @ManyToOne(fetch=FetchType.EAGER)
	 @JoinColumn(name="ALMA_ID")
	 private Almacenamiento almacenamiento;
	 
	/*
	 * //bi-directional many-to-one association to Renglonespedido
	 * 
	 * @OneToMany(mappedBy="producto",fetch=FetchType.EAGER) private
	 * List<Renglonespedido> renglonespedidos;
	 */
	public Producto() {
	}

	public long getProdId() {
		return this.prodId;
	}

	public void setProdId(long prodId) {
		this.prodId = prodId;
	}

	public String getProdEstiba() {
		return this.prodEstiba;
	}

	public void setProdEstiba(String prodEstiba) {
		this.prodEstiba = prodEstiba;
	}

	public Date getProdFelab() {
		return this.prodFelab;
	}

	public void setProdFelab(Date prodFelab) {
		this.prodFelab = prodFelab;
	}

	public Date getProdFven() {
		return this.prodFven;
	}

	public void setProdFven(Date prodFven) {
		this.prodFven = prodFven;
	}

	public String getProdLote() {
		return this.prodLote;
	}

	public void setProdLote(String prodLote) {
		this.prodLote = prodLote;
	}

	public String getProdNombre() {
		return this.prodNombre;
	}

	public void setProdNombre(String prodNombre) {
		this.prodNombre = prodNombre;
	}

	public double getProdPeso() {
		return this.prodPeso;
	}

	public void setProdPeso(Double prodPeso) {
		this.prodPeso = prodPeso;
	}

	public Double getProdPrecio() {
		return this.prodPrecio;
	}

	public void setProdPrecio(Double prodPrecio) {
		this.prodPrecio = prodPrecio;
	}

	public String getProdSegmetac() {
		return this.prodSegmetac;
	}

	public void setProdSegmetac(String prodSegmetac) {
		this.prodSegmetac = prodSegmetac;
	}

	public Long getProdStkmin() {
		return this.prodStkmin;
	}

	public void setProdStkmin(Long prodStkmin) {
		this.prodStkmin = prodStkmin;
	}

	public Double getProdStktotal() {
		return this.prodStktotal;
	}

	public void setProdStktotal(Double prodStktotal) {
		this.prodStktotal = prodStktotal;
	}

	public Double getProdVol() {
		return this.prodVol;
	}

	public void setProdVol(Double prodVol) {
		this.prodVol = prodVol;
	}

	/*
	 * public List<Movimiento> getMovimientos() { return this.movimientos; }
	 * 
	 * public void setMovimientos(List<Movimiento> movimientos) { this.movimientos =
	 * movimientos; }
	 * 
	 * public Movimiento addMovimiento(Movimiento movimiento) {
	 * getMovimientos().add(movimiento); movimiento.setProducto(this);
	 * 
	 * return movimiento; }
	 * 
	 * public Movimiento removeMovimiento(Movimiento movimiento) {
	 * getMovimientos().remove(movimiento); movimiento.setProducto(null);
	 * 
	 * return movimiento; }
	 */
	public Familia getFamilia() {
		return this.familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
	
	 public Usuario getUsuario()
	 { return this.usuario; } 
	 
	 public void setUsuario(Usuario usuario) 
	 { this.usuario = usuario; }

	public Almacenamiento getAlmacenamiento() {
		return almacenamiento;
	}

	public void setAlmacenamiento(Almacenamiento almacenamiento) {
		this.almacenamiento = almacenamiento;
	}
	 
	 
	 
	/*
	 * public List<Renglonespedido> getRenglonespedidos() { return
	 * this.renglonespedidos; }
	 * 
	 * public void setRenglonespedidos(List<Renglonespedido> renglonespedidos) {
	 * this.renglonespedidos = renglonespedidos; }
	 * 
	 * public Renglonespedido addRenglonespedido(Renglonespedido renglonespedido) {
	 * getRenglonespedidos().add(renglonespedido);
	 * renglonespedido.setProducto(this);
	 * 
	 * return renglonespedido; }
	 * 
	 * public Renglonespedido removeRenglonespedido(Renglonespedido renglonespedido)
	 * { getRenglonespedidos().remove(renglonespedido);
	 * renglonespedido.setProducto(null);
	 * 
	 * return renglonespedido; }
	 */
}