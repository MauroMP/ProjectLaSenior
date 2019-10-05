package Servicio;

import java.util.List;

import javax.ejb.Remote;

import Exception.ServiciosException;
import dominio.Familia;
import dominio.Producto;
import dominio.Usuario;

@Remote
public interface ProductoBeanRemote {
	public void crearProducto(Producto producto) throws ServiciosException;

	public List<Familia> obtenerFamilias() throws ServiciosException;

	public Familia obtenerFamiliasporNombre(String nomfamilia);

	public Usuario obtenerUsuario(Integer usuarioid);

	public List<Producto> obtenerProductos() throws ServiciosException;

	public void eliminarProducto(String nomProducto) throws ServiciosException;

	public Producto cargarDatos(String nomProducto);

	public void actualizarProducto(Producto producto) throws ServiciosException;

	public boolean verificonombre(String nom) throws ServiciosException;

	public boolean buscarProdXMov(String nomProducto) throws ServiciosException;

}
