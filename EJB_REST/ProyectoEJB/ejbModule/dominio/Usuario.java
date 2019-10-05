package dominio;

import java.io.Serializable;

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
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name="USUARIOS")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_usu", sequenceName = "seq_Usu_Codigo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usu")
	@Column(name="USU_CODIGO")
	private Long usuCodigo;

	@Column(name="USU_NOMBRE")
	private String usuNombre;

	@Column(name="USU_CORREO")
	private String usuCorreo;

	@Column(name="USU_APELLIDO")
	private String usuApellido;

	@Column(name="USU_NOMACCESO")
	private String usuNomacceso;

	@Column(name="USU_PASSWORD")
	private String usuPassword;
/*
	//bi-directional many-to-one association to Producto
	@OneToMany(fetch=FetchType.LAZY,mappedBy="usuario")
	private List<Producto> productos;

	//bi-directional many-to-one association to Recepcione
	@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
	private List<Recepcione> recepciones;
*/
	//bi-directional many-to-one association to Perfile
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PERF_CODIGO")
	private Perfile perfile;

	public Usuario() {
	}

	public long getUsuCodigo() {
		return this.usuCodigo;
	}

	public void setUsuCodigo(long usuCodigo) {
		this.usuCodigo = usuCodigo;
	}

	public String getUsuNombre() {
		return this.usuNombre;
	}

	public void setUsuCombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	public String getUsuCorreo() {
		return this.usuCorreo;
	}

	public void setUsuCorreo(String usuCorreo) {
		this.usuCorreo = usuCorreo;
	}

	public String getUsuApellido() {
		return this.usuApellido;
	}

	public void setUsuCpellido(String usuApellido) {
		this.usuApellido = usuApellido;
	}

	public String getUsuNomacceso() {
		return this.usuNomacceso;
	}

	public void setUsuNomacceso(String usuNomacceso) {
		this.usuNomacceso = usuNomacceso;
	}

	public String getUsuPassword() {
		return this.usuPassword;
	}

	public void setUsuPassword(String usuPassword) {
		this.usuPassword = usuPassword;
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
		producto.setUsuario(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setUsuario(null);

		return producto;
	}

	public List<Recepcione> getRecepciones() {
		return this.recepciones;
	}

	public void setRecepciones(List<Recepcione> recepciones) {
		this.recepciones = recepciones;
	}

	public Recepcione addRecepcione(Recepcione recepcione) {
		getRecepciones().add(recepcione);
		recepcione.setUsuario(this);

		return recepcione;
	}

	public Recepcione removeRecepcione(Recepcione recepcione) {
		getRecepciones().remove(recepcione);
		recepcione.setUsuario(null);

		return recepcione;
	}
*/
	public Perfile getPerfile() {
		return this.perfile;
	}

	public void setPerfile(Perfile perfile) {
		this.perfile = perfile;
	}

}