package Servicio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import Exception.ServiciosException;
import dominio.Movimiento;

/**
 * Session Bean implementation class MovimientoBean
 */
@Stateless
public class MovimientoBean implements MovimientoBeanRemote {

	/**
	 * Default constructor.
	 */
	public MovimientoBean() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	private EntityManager em;
	private Calendar calendar = Calendar.getInstance();
	private Calendar calendarquery = Calendar.getInstance();

	public void crearMovimiento(Movimiento movimiento) throws ServiciosException {
		try {

			em.persist(movimiento);
			em.flush();

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiciosException("No se puedo crear el movimiento");
		}
	}

	public void borrarMovimiento(Long movId) throws ServiciosException {
		try {
			Movimiento movimiento = em.find(Movimiento.class, movId);
			em.remove(movimiento);
			em.flush();

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiciosException("No se puedo crear el movimiento");
		}
	}

	public void modificarMovimiento(Movimiento movimiento) throws ServiciosException {

		try {
			em.merge(movimiento);
			em.flush();

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiciosException("No se puedo crear el movimiento");
		}
	}

	public List<Movimiento> obtenerTodos() throws ServiciosException {
		try {
			TypedQuery<Movimiento> query = em.createNamedQuery("Movimiento.obtenerTodos", Movimiento.class);
			return query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiciosException("No se puedo crear el movimiento");
		}
	}

	public Movimiento obtenerMovimiento(Long id) throws ServiciosException {
		Movimiento mov = null;
		try {
			TypedQuery<Movimiento> query = em.createQuery("SELECT m FROM Movimiento m WHERE m.movId LIKE :id", Movimiento.class).setParameter("id", id);
			mov = query.getSingleResult();
		} catch (PersistenceException e) {
			// e.printStackTrace();
			throw new ServiciosException("No se puedo obtener el movimiento");
		}
		return mov;
	}

	public List<Movimiento> obtenerMovimientosP(String nombre) throws ServiciosException {
		List<Movimiento> movp = new ArrayList<>();
		List<Movimiento> movresult = new ArrayList<>();
		try {
			TypedQuery<Movimiento> query = em.createNamedQuery("Movimiento.obtenerTodos", Movimiento.class);
			movp = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();

		}
		for (Movimiento i : movp) {
			String nombrep = i.getProducto().getProdNombre();
			if (nombrep.equals(nombre)) {
				movresult.add(i);
			}
		}
		return movresult;
	}

	public List<Movimiento> obtenerMovimientosA(String nombre) throws ServiciosException {
		List<Movimiento> mova = new ArrayList<>();
		List<Movimiento> movresult = new ArrayList<>();
		try {
			TypedQuery<Movimiento> query = em.createNamedQuery("Movimiento.obtenerTodos", Movimiento.class);
			mova = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();

		}
		for (Movimiento i : mova) {
			String nombrea = i.getAlmacenamiento().getAlmaNombre();
			if (nombrea.equals(nombre)) {
				movresult.add(i);
			}
		}
		return movresult;
	}

	public List<Movimiento> obtenerMovimientosF(Date fecha) throws ServiciosException {
		List<Movimiento> movf = new ArrayList<>();
		List<Movimiento> movresult = new ArrayList<>();

		try {
			TypedQuery<Movimiento> query = em.createNamedQuery("Movimiento.obtenerTodos", Movimiento.class);
			movf = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		calendar.setTime(fecha);
		int fechay = calendar.get(Calendar.YEAR);
		int fecham = calendar.get(Calendar.MONTH);
		int fechad = calendar.get(Calendar.DAY_OF_MONTH);

		for (Movimiento i : movf) {
			calendarquery.setTime(i.getMovFecha());
			int fy = calendarquery.get(Calendar.YEAR);
			int fm = calendarquery.get(Calendar.MONTH);
			int fd = calendarquery.get(Calendar.DAY_OF_MONTH);

			if (fechay == fy && fecham == fm && fechad == fd) {

				movresult.add(i);
			}
		}
		return movresult;
	}

	public List<Movimiento> obtenerMovimientosD(String nombre) throws ServiciosException {
		List<Movimiento> movd = new ArrayList<>();
		List<Movimiento> movresult = new ArrayList<>();
		try {
			TypedQuery<Movimiento> query = em.createNamedQuery("Movimiento.obtenerTodos", Movimiento.class);
			movd = query.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();

		}
		for (Movimiento i : movd) {
			String nombred = i.getMovDescripcion();
			if (nombred.contains(nombre)) {
				movresult.add(i);
			}
		}
		return movresult;
	}

}
