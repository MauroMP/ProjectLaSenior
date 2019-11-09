package com.example.lasenioralmacenes.Modelos;

import java.io.Serializable;


import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PEDIDOS database table.
 * 
 */

public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;


	private long pedId;


	private String pedEstado;


	private Date pedFechEstimEnt;


	private Date pedFecha;


	private List<Recepcione> recepciones;

	private List<Renglonespedido> renglonespedidos;

	public Pedido() {
	}

	public long getPedId() {
		return this.pedId;
	}

	public void setPedId(long pedId) {
		this.pedId = pedId;
	}

	public String getPedEstado() {
		return this.pedEstado;
	}

	public void setPedEstado(String pedEstado) {
		this.pedEstado = pedEstado;
	}

	public Date getPedFechEstimEnt() {
		return this.pedFechEstimEnt;
	}

	public void setPedFechEstimEnt(Date pedFechEstimEnt) {
		this.pedFechEstimEnt = pedFechEstimEnt;
	}

	public Date getPedFecha() {
		return this.pedFecha;
	}

	public void setPedFecha(Date pedFecha) {
		this.pedFecha = pedFecha;
	}

	public List<Recepcione> getRecepciones() {
		return this.recepciones;
	}

	public void setRecepciones(List<Recepcione> recepciones) {
		this.recepciones = recepciones;
	}

	public Recepcione addRecepcione(Recepcione recepcione) {
		getRecepciones().add(recepcione);
		recepcione.setPedido(this);

		return recepcione;
	}

	public Recepcione removeRecepcione(Recepcione recepcione) {
		getRecepciones().remove(recepcione);
		recepcione.setPedido(null);

		return recepcione;
	}

	public List<Renglonespedido> getRenglonespedidos() {
		return this.renglonespedidos;
	}

	public void setRenglonespedidos(List<Renglonespedido> renglonespedidos) {
		this.renglonespedidos = renglonespedidos;
	}

	public Renglonespedido addRenglonespedido(Renglonespedido renglonespedido) {
		getRenglonespedidos().add(renglonespedido);
		renglonespedido.setPedido(this);

		return renglonespedido;
	}

	public Renglonespedido removeRenglonespedido(Renglonespedido renglonespedido) {
		getRenglonespedidos().remove(renglonespedido);
		renglonespedido.setPedido(null);

		return renglonespedido;
	}

}