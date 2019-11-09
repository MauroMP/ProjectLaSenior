package com.example.lasenioralmacenes.Modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;




/**
 * The persistent class for the FAMILIAS database table.
 * 
 */

public class Familia implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long famiCodi;

	
	private String famiDescrip;

	
	private String famiIncompat;

	
	private String famiNombre;

	
	
	private List<Producto> productos = new ArrayList<Producto>();

	public Familia() {
	}

	public Long getFamiCodi() {
		return this.famiCodi;
	}

	public void setFamiCodi(Long famiCodi) {
		this.famiCodi = famiCodi;
	}

	public String getFamiDescrip() {
		return this.famiDescrip;
	}

	public void setFamiDescrip(String famiDescrip) {
		this.famiDescrip = famiDescrip;
	}

	public String getFamiIncompat() {
		return this.famiIncompat;
	}

	public void setFamiIncompat(String famiIncompat) {
		this.famiIncompat = famiIncompat;
	}

	public String getFamiNombre() {
		return this.famiNombre;
	}

	public void setFamiNombre(String famiNombre) {
		this.famiNombre = famiNombre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setFamilia(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setFamilia(null);

		return producto;
	}

	@Override
	public String toString() {
		return "Familia [famiCodi=" + famiCodi + ", famiDescrip=" + famiDescrip + ", famiIncompat=" + famiIncompat
				+ ", famiNombre=" + famiNombre + ", productos=" + productos + "]";
	}
	
	

}