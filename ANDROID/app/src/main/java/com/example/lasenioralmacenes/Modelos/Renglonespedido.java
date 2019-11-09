package com.example.lasenioralmacenes.Modelos;

import java.io.Serializable;

import java.math.BigDecimal;


/**
 * The persistent class for the RENGLONESPEDIDOS database table.
 * 
 */

public class Renglonespedido implements Serializable {
	private static final long serialVersionUID = 1L;


	private long renpedNro;


	private BigDecimal renpedCant;


	private Pedido pedido;


	private Producto producto;

	public Renglonespedido() {
	}

	public long getRenpedNro() {
		return this.renpedNro;
	}

	public void setRenpedNro(long renpedNro) {
		this.renpedNro = renpedNro;
	}

	public BigDecimal getRenpedCant() {
		return this.renpedCant;
	}

	public void setRenpedCant(BigDecimal renpedCant) {
		this.renpedCant = renpedCant;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}