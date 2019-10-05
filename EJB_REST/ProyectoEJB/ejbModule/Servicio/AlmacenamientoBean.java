package Servicio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import Exception.ServiciosException;

import dominio.Almacenamiento;
//import dominio.Producto;

/**
 * Session Bean implementation class AlmacenamientoBean
 */
@Stateless
public class AlmacenamientoBean implements AlmacenamientoBeanRemote {

	/**
	 * Default constructor.
	 */
	public AlmacenamientoBean() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	private EntityManager em;

	public void crearAlmacenamiento(Almacenamiento almacenamiento) throws ServiciosException {
		try {
			em.persist(almacenamiento);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se puedo crear el producto");
		}
	}

	public void actualizar(Almacenamiento almacenamiento) throws ServiciosException {
		try {
			em.merge(almacenamiento);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se puedo actualizar el almacenamiento");

		}
	}

	public void borrarAlmacenamiento(Long almaId) throws ServiciosException {
		try {
			Almacenamiento almacenamiento = em.find(Almacenamiento.class, almaId);
			em.remove(almacenamiento);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo borrar el almacenamiento");

		}
	}

	public List<Almacenamiento> obtenerTodos() throws ServiciosException {
		try {
			TypedQuery<Almacenamiento> query = em.createNamedQuery("Almacenamiento.obtenerTodos", Almacenamiento.class);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo listar almacenamientos");

		}
	}

	public Almacenamiento obtenerAlmacen(String nombre) {
		Almacenamiento almacen = new Almacenamiento();
		TypedQuery<Almacenamiento> query = em.createQuery("SELECT a FROM Almacenamiento a WHERE a.almaNombre LIKE :almaNombre", Almacenamiento.class).setParameter("almaNombre", nombre);
		almacen = query.getSingleResult();
		return almacen;

	}
	
	public List<Almacenamiento> obtenerAlmacenenOrden() {
		List<Almacenamiento> almacen = new ArrayList<>();
		TypedQuery<Almacenamiento> query = em.createQuery("SELECT a FROM Almacenamiento a ORDER BY a.almaNombre", Almacenamiento.class);
		almacen = query.getResultList();
		return almacen;

	}
	
}
