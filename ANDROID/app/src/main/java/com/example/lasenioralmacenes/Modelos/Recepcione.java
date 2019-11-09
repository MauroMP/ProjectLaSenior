package com.example.lasenioralmacenes.Modelos;

import java.io.Serializable;
import java.util.Date;




/**
 * The persistent class for the RECEPCIONES database table.
 * 
 */

public class Recepcione implements Serializable {
	private static final long serialVersionUID = 1L;


	private long recepId;

	private String recComentario;


	private Date recFecha;


	private Pedido pedido;


	private Usuario usuario;

	public Recepcione() {
	}

	public long getRecepId() {
		return this.recepId;
	}

	public void setRecepId(long recepId) {
		this.recepId = recepId;
	}

	public String getRecComentario() {
		return this.recComentario;
	}

	public void setRecComentario(String recComentario) {
		this.recComentario = recComentario;
	}

	public Date getRecFecha() {
		return this.recFecha;
	}

	public void setRecFecha(Date recFecha) {
		this.recFecha = recFecha;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}