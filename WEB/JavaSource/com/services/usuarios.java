package com.services;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named("usu")
@SessionScoped

public class usuarios implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usuNombre;
	private String usuPassword;
	
	


	public String getUsuNombre() {
		return usuNombre;
	}

	public void setUsuNombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	public String getUsuPassword() {
		return usuPassword;
	}

	public void setUsuPassword(String usuPassword) {
		this.usuPassword = usuPassword;
	}




	public String confirmarusuario() {
		usuarios usua = new usuarios();
		usua.setUsuNombre(usuNombre);
		usua.setUsuPassword(usuPassword);
		String nom, pass;
		nom = usua.getUsuNombre();
		pass = usua.getUsuPassword();
		if (nom.equals("usuario") && pass.equals("usuario")){
			return "menuprincipal.xhtml";
		}else{
				System.out.println("Mal ingresado los datos");
				return "login.xhtml";
		}
			
				
		
	}

}


