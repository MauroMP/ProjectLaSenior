package dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the FAMILIAS database table.
 * 
 */
@Entity
@Table(name="FAMILIAS")
@NamedQuery(name="Familia.obtenerTodas", query="SELECT f FROM Familia f")
public class Familia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_fami", sequenceName="seq_Fami_Codi", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_fami")
	@Column(name="FAMI_CODI")
	private Long famiCodi;

	@Column(name="FAMI_DESCRIP")
	private String famiDescrip;

	@Column(name="FAMI_INCOMPAT")
	private String famiIncompat;

	@Column(name="FAMI_NOMBRE")
	private String famiNombre;
/*
	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="familia", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Producto> productos = new ArrayList<>();
*/
	public Familia() {
	}

	public Long getFamiCodi() {
		return this.famiCodi;
	}

	public void setFamiCodi(Long famiCodi) {
		this.famiCodi = famiCodi;
	}

	public String getFamiDescrip() {
		return this.famiDescrip;
	}

	public void setFamiDescrip(String famiDescrip) {
		this.famiDescrip = famiDescrip;
	}

	public String getFamiIncompat() {
		return this.famiIncompat;
	}

	public void setFamiIncompat(String famiIncompat) {
		this.famiIncompat = famiIncompat;
	}

	public String getFamiNombre() {
		return this.famiNombre;
	}

	public void setFamiNombre(String famiNombre) {
		this.famiNombre = famiNombre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
/*
	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setFamilia(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setFamilia(null);

		return producto;
	}
*/
}