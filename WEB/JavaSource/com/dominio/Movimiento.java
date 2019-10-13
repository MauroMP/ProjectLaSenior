package com.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named("movimiento")
@SessionScoped
public class Movimiento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	


	private Long movId;

	
	private Double movCantidad;

	
	private String movDescripcion;

	
	private Date movFecha;

	
	private String movTipo;
	
	
	private Double movCosto;

	
	private Almacenamiento almacenamiento;

	private Producto producto;

	public Movimiento() {
	}

	public Long getMovId() {
		return this.movId;
	}

	public void setMovId(Long movId) {
		this.movId = movId;
	}

	public Double getMovCantidad() {
		return this.movCantidad;
	}

	public void setMovCantidad(Double movCantidad) {
		this.movCantidad = movCantidad;
	}

	public String getMovDescripcion() {
		return this.movDescripcion;
	}

	public void setMovDescripcion(String movDescripcion) {
		this.movDescripcion = movDescripcion;
	}

	public Date getMovFecha() {
		return this.movFecha;
	}

	public void setMovFecha(Date movFecha) {
		this.movFecha = movFecha;
	}


	public String getMovTipo() {
		return this.movTipo;
	}

	public void setMovTipo(String movTipo) {
		this.movTipo = movTipo;
	}
	
	

	public Double getMovCosto() {
		return movCosto;
	}

	public void setMovCosto(Double movCosto) {
		this.movCosto = movCosto;
	}

		public Almacenamiento getAlmacenamiento() {
		return this.almacenamiento;
	}

	public void setAlmacenamiento(Almacenamiento almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void altaMovimiento() {
	Movimiento movimiento = new Movimiento();
	movimiento.setMovCantidad(movCantidad);
		
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimiento other = (Movimiento) obj;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}
	
	
	
	

}