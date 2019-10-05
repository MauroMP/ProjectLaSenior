package Servicio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import Exception.ServiciosException;
import dominio.Familia;
import dominio.Movimiento;
import dominio.Producto;
import dominio.Usuario;

/**
 * Session Bean implementation class ProductoBean
 */
@Stateless
public class ProductoBean implements ProductoBeanRemote {

	/**
	 * Default constructor.
	 */
	public ProductoBean() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	private EntityManager em;

	public void crearProducto(Producto producto) throws ServiciosException {
		try {
			em.persist(producto);
			em.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiciosException("No se puedo crear el producto");
		}
	}

	public List<Familia> obtenerFamilias() {
		List<Familia> familias = new ArrayList<>();
		Familia familia;
		try {

			TypedQuery<Familia> query = em.createQuery("SELECT f FROM Familia f", Familia.class);
			List<Familia> result = query.getResultList();

			for (Familia f : result) {
				familia = new Familia();
				familia.setFamiCodi(f.getFamiCodi());
				familia.setFamiDescrip(f.getFamiDescrip());
				familia.setFamiNombre(f.getFamiNombre());
				familias.add(familia);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return familias;
	}

	public List<Producto> obtenerProductos() {

		TypedQuery<Producto> query = em.createNamedQuery("Producto.obtenerTodos", Producto.class);
		return query.getResultList();
	}

	public Familia obtenerFamiliasporNombre(String nomfamilia) {

		TypedQuery<Familia> query = em.createQuery("SELECT f FROM Familia f WHERE f.famiNombre = :nomb", Familia.class)
				.setParameter("nomb", nomfamilia);

		return query.getSingleResult();

	}

	public Usuario obtenerUsuario(Integer usuarioid) {

		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuCodigo = :id", Usuario.class)
				.setParameter("id", usuarioid);

		return query.getSingleResult();

	}

	public void eliminarProducto(String nomProducto) {
		Producto producto = null;
		TypedQuery<Producto> query = em
				.createQuery("SELECT p FROM Producto p WHERE p.prodNombre = :nomb", Producto.class)
				.setParameter("nomb", nomProducto);
		producto = query.getSingleResult();
		try {
			em.remove(producto);
			em.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("No se pudo eliminar ese Producto");
		}
	}

	public Producto cargarDatos(String nomProducto) {

		TypedQuery<Producto> query = em
				.createQuery("SELECT p FROM Producto p WHERE p.prodNombre = :nomb", Producto.class)
				.setParameter("nomb", nomProducto);
		return query.getSingleResult();
	}

	public void actualizarProducto(Producto producto) throws ServiciosException {
		try {
			em.merge(producto);
			em.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("No se pudo actualizar Producto");
		}
	}

	public boolean verificonombre(String nom) throws ServiciosException {
		boolean repite = false;
		TypedQuery<Producto> query = em
				.createQuery("SELECT p FROM Producto p WHERE p.prodNombre = :nomb", Producto.class)
				.setParameter("nomb", nom);
		List<Producto> listProd = query.getResultList();
		if (listProd.size() >= 1) {
			repite = true;
		}
		return repite;
	}

	public boolean buscarProdXMov(String nomProducto) throws ServiciosException {
		boolean existe = true;
		Producto producto = null;

		// Primero obtengo el prod id del producto
		TypedQuery<Producto> query = em
				.createQuery("SELECT p FROM Producto p WHERE p.prodNombre = :nomb", Producto.class)
				.setParameter("nomb", nomProducto);

		producto = query.getSingleResult();
		Long id = producto.getProdId();

		// Obtener en una lista todos los Movimientos
		TypedQuery<Movimiento> querys = em.createNamedQuery("Movimiento.obtenerTodos", Movimiento.class);
		List<Movimiento> result = querys.getResultList();

		// Recorro la lista y comparo el Id del Producto a eliminar con los id de la
		// Lista de Movimiento
		for (int i = 0; i < result.size(); i++) {
			// compare the two user input
			Long idmov = result.get(i).getProducto().getProdId();
			int compara = Long.compare(id, idmov);
			if (compara == 0) {
				existe = false;
			}
		}
		return existe;
	}
}
