package com.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Exception.ServiciosException;

import Servicio.AlmacenamientoBeanRemote;
import dominio.Almacenamiento;



@RequestScoped
@Path("alma")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class AlmacenamientoRest {
	
	@EJB
	private AlmacenamientoBeanRemote almabean;
	
	@GET
	@Path("almas")
	@Produces({ MediaType.APPLICATION_JSON})
	public List<Almacenamiento> getAlmacenamientos () throws ServiciosException {
		List<Almacenamiento> listalma = new ArrayList<>();
	listalma = almabean.obtenerTodos();
	return listalma;
	}
	
	 @GET
	    @Path("saludar")
	 @Produces("text/plain")
	    public String saludar(){
	 return "Hola a quien diga hola con producto";
	    }
	 
	 @GET
	 @Path("/{nombre}")
	 @Produces({ MediaType.APPLICATION_JSON})
	 public Almacenamiento getAlmacenamiento (@PathParam ("nombre") String nombre) throws ServiciosException {
			Almacenamiento alma = new Almacenamiento();
		alma = almabean.obtenerAlmacen(nombre);
		return alma;
		}
	 

}
