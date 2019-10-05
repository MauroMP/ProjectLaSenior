package com.rest;


import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Exception.ServiciosException;
import Servicio.LogeoBeanRemote;
import dominio.Usuario;

@RequestScoped
@Path("logeo")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class LogeoRest {
	
	@EJB
	private LogeoBeanRemote logeobean;
	
	@GET
	@Path("logeo/{nombre}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Usuario getUsuario (@PathParam("nombre") String nombre) throws ServiciosException {
		Usuario usu = new Usuario();
	usu = logeobean.controlUsuario(nombre);
	return usu;
	}
	
	 @GET
	    @Path("saludar")
	 @Produces("text/plain")
	    public String saludar(){
	 return "Hola a quien diga hola al usuario";
	    }

}
