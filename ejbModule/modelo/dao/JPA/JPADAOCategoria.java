package modelo.dao.JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import modelo.Catalogo;
import modelo.Categoria;
import modelo.dao.CategoriaDAO;
import modelo.dao.DAOException;

public class JPADAOCategoria implements CategoriaDAO {

	private EntityManager em;

	public JPADAOCategoria(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}

	@Override
	public Categoria create(String nombre) throws DAOException {
		try {
			em.getTransaction().begin();
			Categoria categoJPA = new Categoria(nombre);
			em.persist(categoJPA);
			em.getTransaction().commit();
			return categoJPA;
		} catch (Exception ex) {
			throw new DAOException(ex.getMessage());
		}
	}

}
