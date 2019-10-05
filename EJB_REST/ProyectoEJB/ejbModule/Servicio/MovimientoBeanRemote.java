package Servicio;

import java.util.Date;
import java.util.List;


import javax.ejb.Remote;

import Exception.ServiciosException;
import dominio.Movimiento;

@Remote
public interface MovimientoBeanRemote {

	public void crearMovimiento(Movimiento movimiento) throws ServiciosException;
	public void borrarMovimiento(Long movId)throws ServiciosException;
	public void modificarMovimiento(Movimiento movimiento)throws ServiciosException;
	public List<Movimiento> obtenerTodos()throws ServiciosException;
	public Movimiento obtenerMovimiento(Long id)throws ServiciosException;
	public List<Movimiento> obtenerMovimientosP(String nombre) throws ServiciosException;
	public List<Movimiento> obtenerMovimientosA(String nombre) throws ServiciosException;
	public List<Movimiento> obtenerMovimientosF(Date fecha) throws ServiciosException; 
	public List<Movimiento> obtenerMovimientosD(String nombre) throws ServiciosException;
}
