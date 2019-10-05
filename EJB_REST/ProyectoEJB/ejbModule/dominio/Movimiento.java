package dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the MOVIMIENTOS database table.
 * 
 */
@Entity
@Table(name="MOVIMIENTOS")
@NamedQuery(name="Movimiento.obtenerTodos", query="SELECT m FROM Movimiento m ORDER BY m.movId")
public class Movimiento implements Serializable {
			/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@SequenceGenerator(name="seq_mov", sequenceName="seq_Mov_Id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_mov")
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MOV_ID")
	private Long movId;

	@Column(name="MOV_CANTIDAD")
	private Double movCantidad;

	@Column(name="MOV_DESCRIPCION")
	private String movDescripcion;

	@Column(name="MOV_FECHA")
	private Date movFecha;

	@Column(name="MOV_TIPO")
	private String movTipo;
	
	@Column(name="MOV_COSTO")
	private Double movCosto;

	//bi-directional many-to-one association to Almacenamiento
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ALMA_ID")
	private Almacenamiento almacenamiento;

	//bi-directional many-to-one association to Producto
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PROD_ID")
	private Producto producto;

	public Movimiento() {
	}

	public Long getMovId() {
		return this.movId;
	}

	public void setMovId(Long movId) {
		this.movId = movId;
	}

	public Double getMovCantidad() {
		return this.movCantidad;
	}

	public void setMovCantidad(Double movCantidad) {
		this.movCantidad = movCantidad;
	}

	public String getMovDescripcion() {
		return this.movDescripcion;
	}

	public void setMovDescripcion(String movDescripcion) {
		this.movDescripcion = movDescripcion;
	}

	public Date getMovFecha() {
		return this.movFecha;
	}

	public void setMovFecha(Date movFecha) {
		this.movFecha = movFecha;
	}


	public String getMovTipo() {
		return this.movTipo;
	}

	public void setMovTipo(String movTipo) {
		this.movTipo = movTipo;
	}
	
	

	public Double getMovCosto() {
		return movCosto;
	}

	public void setMovCosto(Double movCosto) {
		this.movCosto = movCosto;
	}

		public Almacenamiento getAlmacenamiento() {
		return this.almacenamiento;
	}

	public void setAlmacenamiento(Almacenamiento almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void altaMovimiento() {
	Movimiento movimiento = new Movimiento();
	movimiento.setMovCantidad(movCantidad);
		
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimiento other = (Movimiento) obj;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}
	
	
	
	
	
	/*public static void main(String[] args) throws ParseException {
		java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse("08/16/2011");
		
		
	}*/
}