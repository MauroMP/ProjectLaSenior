package com.example.lasenioralmacenes.Modelos;

import java.io.Serializable;
import java.util.List;





/**
 * The persistent class for the USUARIOS database table.
 * 
 */

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long usuCodigo;

	
	private String usuNombre;

	
	private String usuCorreo;

	
	private String usuApellido;

	
	private String usuNomacceso;

	
	private String usuPassword;

	
	private List<Producto> productos;


	private List<Recepcione> recepciones;

	private Perfile perfile;

	public Usuario() {
	}

	public long getUsuCodigo() {
		return this.usuCodigo;
	}

	public void setUsuCodigo(long usuCodigo) {
		this.usuCodigo = usuCodigo;
	}

	public String getUsuNombre() {
		return this.usuNombre;
	}

	public void setUsuCombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	public String getUsuCorreo() {
		return this.usuCorreo;
	}

	public void setUsuCorreo(String usuCorreo) {
		this.usuCorreo = usuCorreo;
	}

	public String getUsuApellido() {
		return this.usuApellido;
	}

	public void setUsuCpellido(String usuApellido) {
		this.usuApellido = usuApellido;
	}

	public String getUsuNomacceso() {
		return this.usuNomacceso;
	}

	public void setUsuNomacceso(String usuNomacceso) {
		this.usuNomacceso = usuNomacceso;
	}

	public String getUsuPassword() {
		return this.usuPassword;
	}

	public void setUsuPassword(String usuPassword) {
		this.usuPassword = usuPassword;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setUsuario(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setUsuario(null);

		return producto;
	}

	public List<Recepcione> getRecepciones() {
		return this.recepciones;
	}

	public void setRecepciones(List<Recepcione> recepciones) {
		this.recepciones = recepciones;
	}

	public Recepcione addRecepcione(Recepcione recepcione) {
		getRecepciones().add(recepcione);
		recepcione.setUsuario(this);

		return recepcione;
	}

	public Recepcione removeRecepcione(Recepcione recepcione) {
		getRecepciones().remove(recepcione);
		recepcione.setUsuario(null);

		return recepcione;
	}

	public Perfile getPerfile() {
		return this.perfile;
	}

	public void setPerfile(Perfile perfile) {
		this.perfile = perfile;
	}

	@Override
	public String toString() {
		return "Usuario [usuCodigo=" + usuCodigo + ", usuNombre=" + usuNombre + ", usuCorreo=" + usuCorreo
				+ ", usuApellido=" + usuApellido + ", usuNomacceso=" + usuNomacceso + ", usuPassword=" + usuPassword
				+ ", productos=" + productos + ", recepciones=" + recepciones + ", perfile=" + perfile + "]";
	}
	
	

}