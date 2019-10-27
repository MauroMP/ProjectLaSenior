package com.services;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("menu")
@SessionScoped

public class MenuPrincipal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String nvoProd(){
		return "AltaProducto.xhtml";
	}
	
	public String eliProd(){
		return "BajaProducto.xhtml";
	}

	public String modProd(){
		return "ModificarProducto.xhtml";
	}
	
	public String altaMov() {
		return "altaM.xhtml";
	}
	
	public String bajaMov() {
		return "bajaM.xhtml";
	}
	

}
