package modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	@ManyToOne
	private Catalogo catalogo;

	
}
