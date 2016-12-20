package modelo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class VideojuegoItem {
	@Id
	@GeneratedValue
	private Integer id;	
	private String nombre;				// Columna 4
	private String generoPrincipal;		// Columna 7 
	private String generoSecundario;	// Columna 9
	private String generoOtros;			// Columna 11
	private String descripci�n;			// Columna 14
	private String nota;				// Columna 6
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaLanzamiento;		// Columna 13
	
	private String urlFoto;				// Columna 1
	private String urlFicha;			// Columna 3
	
	private List<Categoria> plataformas;
	
	

	public VideojuegoItem() {
		super();
		plataformas = new LinkedList<>();
	}

	public VideojuegoItem(String nombre, String generoPrincipal, String generoSecundario, String generoOtros,
			String descripci�n, String nota, Date fechaLanzamiento, String urlFoto, String urlFicha) {
		this();
		this.nombre = nombre;
		this.generoPrincipal = generoPrincipal;
		this.generoSecundario = generoSecundario;
		this.generoOtros = generoOtros;
		this.descripci�n = descripci�n;
		this.nota = nota;
		this.fechaLanzamiento = fechaLanzamiento;
		this.urlFoto = urlFoto;
		this.urlFicha = urlFicha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGeneroPrincipal() {
		return generoPrincipal;
	}

	public void setGeneroPrincipal(String generoPrincipal) {
		this.generoPrincipal = generoPrincipal;
	}

	public String getGeneroSecundario() {
		return generoSecundario;
	}

	public void setGeneroSecundario(String generoSecundario) {
		this.generoSecundario = generoSecundario;
	}

	public String getGeneroOtros() {
		return generoOtros;
	}

	public void setGeneroOtros(String generoOtros) {
		this.generoOtros = generoOtros;
	}

	public String getDescripci�n() {
		return descripci�n;
	}

	public void setDescripci�n(String descripci�n) {
		this.descripci�n = descripci�n;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public String getUrlFicha() {
		return urlFicha;
	}

	public void setUrlFicha(String urlFicha) {
		this.urlFicha = urlFicha;
	}

	public List<Categoria> getPlataformas() {
		return plataformas;
	}

	public void setPlataformas(List<Categoria> plataformas) {
		this.plataformas = plataformas;
	}	
}
