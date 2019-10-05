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
 * The persistent class for the PERFILES database table.
 * 
 */
@Entity
@Table(name="PERFILES")
@NamedQuery(name="Perfile.findAll", query="SELECT p FROM Perfile p")
public class Perfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_perf", sequenceName = "seq_Perf_Codigo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_perf")
	@Column(name="PERF_CODIGO")
	private long perfCodigo;

	@Column(name="PERF_NOMBRE")
	private String perfNombre;

	public Perfile() {
	}

	public long getPerfCodigo() {
		return this.perfCodigo;
	}

	public void setPerfCodigo(long perfCodigo) {
		this.perfCodigo = perfCodigo;
	}

	public String getPerfNombre() {
		return this.perfNombre;
	}

	public void setPerfNombre(String perfNombre) {
		this.perfNombre = perfNombre;
	}

	
}