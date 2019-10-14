package com.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 * The persistent class for the PRODUCTOS database table.
 * 
 */

@Named("producto")
@SessionScoped
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private long prodId;

	
	private String prodEstiba;

	
	private Date prodFelab;

	
	private Date prodFven;

	
	private String prodLote;

	
	private String prodNombre;

	
	private Double prodPeso;

	
	private Double prodPrecio;

	
	private String prodSegmetac;

	
	private Long prodStkmin;

	
	private Double prodStktotal;

	
	private Double prodVol;

	private List<Movimiento> movimientos;
	
	private Familia familia;

	private Usuario usuario;
	
	private Almacenamiento almacenamiento;
	 
	private List<Renglonespedido> renglonespedidos;
	 
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

	
	public List<Movimiento> getMovimientos() { return this.movimientos; }
	 
	public void setMovimientos(List<Movimiento> movimientos) { this.movimientos =
	 movimientos; }
	 
	public Movimiento addMovimiento(Movimiento movimiento) {
	getMovimientos().add(movimiento); movimiento.setProducto(this);
	 
	return movimiento; }
	
	public Movimiento removeMovimiento(Movimiento movimiento) {
	getMovimientos().remove(movimiento); movimiento.setProducto(null);
	 
	return movimiento; }
	
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
	 
	 
    public List<Renglonespedido> getRenglonespedidos() { return
	this.renglonespedidos; }
	 
	 public void setRenglonespedidos(List<Renglonespedido> renglonespedidos) {
	this.renglonespedidos = renglonespedidos; }
	 
	 public Renglonespedido addRenglonespedido(Renglonespedido renglonespedido) {
	 getRenglonespedidos().add(renglonespedido);
	 renglonespedido.setProducto(this);
	
	  return renglonespedido; }
	 
	 public Renglonespedido removeRenglonespedido(Renglonespedido renglonespedido)
	 { getRenglonespedidos().remove(renglonespedido);
	 renglonespedido.setProducto(null);
	 
	  return renglonespedido; }

	@Override
	public String toString() {
		return "Producto [prodId=" + prodId + ", prodEstiba=" + prodEstiba + ", prodFelab=" + prodFelab + ", prodFven="
				+ prodFven + ", prodLote=" + prodLote + ", prodNombre=" + prodNombre + ", prodPeso=" + prodPeso
				+ ", prodPrecio=" + prodPrecio + ", prodSegmetac=" + prodSegmetac + ", prodStkmin=" + prodStkmin
				+ ", prodStktotal=" + prodStktotal + ", prodVol=" + prodVol + ", movimientos=" + movimientos
				+ ", familia=" + familia + ", usuario=" + usuario + ", almacenamiento=" + almacenamiento
				+ ", renglonespedidos=" + renglonespedidos + "]";
	}
	 
	 
	 
}