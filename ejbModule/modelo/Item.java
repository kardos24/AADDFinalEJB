package modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {
	@Id
	private Integer id;
	@ManyToOne
	private Catalogo catalogo;

	
}
