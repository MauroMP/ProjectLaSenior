package Servicio;

import java.util.List;

import javax.ejb.Remote;

import Exception.ServiciosException;
import dominio.Almacenamiento;

@Remote
public interface AlmacenamientoBeanRemote {
	
	void crearAlmacenamiento(Almacenamiento almacenamiento) throws ServiciosException;
	void actualizar(Almacenamiento almacenamiento)throws ServiciosException;
    void borrarAlmacenamiento(Long almaId) throws ServiciosException;
    List<Almacenamiento> obtenerTodos() throws ServiciosException;
    public Almacenamiento obtenerAlmacen(String nombre);
    public List<Almacenamiento> obtenerAlmacenenOrden(); 
 
}
