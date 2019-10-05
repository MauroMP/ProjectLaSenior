package dominio;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RENGLONESPEDIDOS database table.
 * 
 */
@Entity
@Table(name="RENGLONESPEDIDOS")
@NamedQuery(name="Renglonespedido.findAll", query="SELECT r FROM Renglonespedido r")
public class Renglonespedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RENPED_NRO")
	private long renpedNro;

	@Column(name="RENPED_CANT")
	private BigDecimal renpedCant;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="PED_ID")
	private Pedido pedido;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="PROD_ID")
	private Producto producto;

	public Renglonespedido() {
	}

	public long getRenpedNro() {
		return this.renpedNro;
	}

	public void setRenpedNro(long renpedNro) {
		this.renpedNro = renpedNro;
	}

	public BigDecimal getRenpedCant() {
		return this.renpedCant;
	}

	public void setRenpedCant(BigDecimal renpedCant) {
		this.renpedCant = renpedCant;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}