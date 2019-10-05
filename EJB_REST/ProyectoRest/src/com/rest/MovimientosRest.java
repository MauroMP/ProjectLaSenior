package com.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Exception.ServiciosException;
import Servicio.MovimientoBeanRemote;
import dominio.Movimiento;

@RequestScoped
@Path("mov")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MovimientosRest {
	
	@EJB
	private MovimientoBeanRemote movbean;
	
	 @GET
	    @Path("saludar")
	 @Produces("text/plain")
	    public String saludar(){
	 return "Hola a quien diga hola con movimiento";
	    }
	 
	
	@GET
	@Path("mov/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Movimiento getMovimiento (@PathParam("id") Long id) {
		Movimiento mov = new Movimiento();
	
		try {
			mov = movbean.obtenerMovimiento(id);
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return mov;
	}
	
	 @GET
	 @Path("movs")
	 @Produces({ MediaType.APPLICATION_JSON})
		public List<Movimiento> getMovimientos () {
			List<Movimiento> listmov = new ArrayList<>();
		
			try {
				listmov = movbean.obtenerTodos();
			} catch (ServiciosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return listmov;
		}
     
	 @GET
	 @Path("movsp/{producto}")
	 @Produces({ MediaType.APPLICATION_JSON})
	 public List<Movimiento> getMovimientosP (@PathParam ("producto") String producto) {
			List<Movimiento> listmov = new ArrayList<>();
		
			try {
				listmov = movbean.obtenerMovimientosP(producto);
			} catch (ServiciosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return listmov;
		}
	 
	 @GET
	 @Path("movsa/{almacen}")
	 @Produces({ MediaType.APPLICATION_JSON})
	 public List<Movimiento> getMovimientosA (@PathParam ("almacen") String almacen) {
			List<Movimiento> listmov = new ArrayList<>();
		
			try {
				listmov = movbean.obtenerMovimientosA(almacen);
			} catch (ServiciosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return listmov;
		}
	 @GET
	 @Path("movsd/{descripcion}")
	 @Produces({ MediaType.APPLICATION_JSON})
	 public List<Movimiento> getMovimientosD (@PathParam ("descripcion") String desc) {
			List<Movimiento> listmov = new ArrayList<>();
		
			try {
				listmov = movbean.obtenerMovimientosD(desc);
			} catch (ServiciosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return listmov;
		}
	  
	 @GET
	 @Path("movsp/{fecha}")
	 @Produces({ MediaType.APPLICATION_JSON})
	 public List<Movimiento> getMovimientosF (@PathParam ("fecha") Date fecha) {
			List<Movimiento> listmov = new ArrayList<>();
		
			try {
				listmov = movbean.obtenerMovimientosF(fecha);
			} catch (ServiciosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return listmov;
		}
	 
	 @POST
	 @Path("movadd")
	 @Produces({ MediaType.APPLICATION_JSON})
	 @Consumes({ MediaType.APPLICATION_JSON})
	 public void addMovimiento (Movimiento mov) {
		
		 
		 try {
			movbean.crearMovimiento(mov);
			
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 }
	
}
