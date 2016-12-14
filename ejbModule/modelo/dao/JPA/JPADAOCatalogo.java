package modelo.dao.JPA;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import modelo.Catalogo;
import modelo.Usuario;
import modelo.dao.CatalogoDAO;
import modelo.dao.DAOException;

public class JPADAOCatalogo implements CatalogoDAO {

	private EntityManager em;

	public JPADAOCatalogo(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}

	@Override
	public Catalogo create(String nombre, Date fecha, String web, String url,
			Usuario usuario) throws DAOException {
		try {
			em.getTransaction().begin();
			Catalogo cataJPA = new Catalogo(nombre, fecha, web, url, usuario);
			em.persist(cataJPA);
			em.getTransaction().commit();
			return cataJPA;
		} catch (Exception ex) {
			throw new DAOException(ex.getMessage());
		}
	}

	@Override
	public Catalogo findByNombre(String nombre) throws DAOException {
		Catalogo catJPA = em.find(Catalogo.class, nombre);
		return catJPA;
	}

}
