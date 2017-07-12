package modelo.dao.JPA;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import modelo.Catalogo;
import modelo.Categoria;
import modelo.Usuario;
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

	@Override
	public List<Categoria> findAll() throws DAOException {
		Query query = em.createQuery("SELECT c FROM Categoria");
		List<Categoria> resultList = new LinkedList<>();
		try{
			resultList = (List<Categoria>) query.getResultList();
		} catch(Exception e ){
			throw new DAOException(e.getMessage());
		}
		return resultList;
	}

	@Override
	public Categoria findByCategoria(String categoria) throws DAOException {
		Categoria categoriaJPA = em.find(Categoria.class, categoria);
		return categoriaJPA;
	}

}
