package com.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Exception.ServiciosException;
import Servicio.ProductoBeanRemote;
import dominio.Producto;


@RequestScoped
@Path("prod")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ProductosRest {

	
	@EJB
	private ProductoBeanRemote prodbean;
	
	@GET
	@Path("prods")
	@Produces({ MediaType.APPLICATION_JSON})
	public List<Producto> getProductos () throws ServiciosException {
		List<Producto> listprod = new ArrayList<>();
	listprod = prodbean.obtenerProductos();
	return listprod;
	}
	
	 @GET
	    @Path("saludar")
	 @Produces("text/plain")
	    public String saludar(){
	 return "Hola a quien diga hola con producto";
	    }

}
