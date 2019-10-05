package dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the RECEPCIONES database table.
 * 
 */
@Entity
@Table(name="RECEPCIONES")
@NamedQuery(name="Recepcione.findAll", query="SELECT r FROM Recepcione r")
public class Recepcione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RECEP_ID")
	private long recepId;

	@Column(name="REC_COMENTARIO")
	private String recComentario;

	@Column(name="REC_FECHA")
	private Date recFecha;

	//bi-directional many-to-one association to Pedido
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PED_ID")
	private Pedido pedido;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USU_CODIGO")
	private Usuario usuario;

	public Recepcione() {
	}

	public long getRecepId() {
		return this.recepId;
	}

	public void setRecepId(long recepId) {
		this.recepId = recepId;
	}

	public String getRecComentario() {
		return this.recComentario;
	}

	public void setRecComentario(String recComentario) {
		this.recComentario = recComentario;
	}

	public Date getRecFecha() {
		return this.recFecha;
	}

	public void setRecFecha(Date recFecha) {
		this.recFecha = recFecha;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}