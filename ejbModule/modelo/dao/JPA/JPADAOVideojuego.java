package modelo.dao.JPA;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import modelo.Categoria;
import modelo.Usuario;
import modelo.VideojuegoItem;
import modelo.dao.DAOException;
import modelo.dao.VideojuegoDAO;

public class JPADAOVideojuego implements VideojuegoDAO {

	private EntityManager em;

	public JPADAOVideojuego(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}

	@Override
	public VideojuegoItem create(String nombre, String generoPrincipal, String generoSecundario, String generoOtros,
			String descripcion, String nota, Date fechaLanzamiento, String urlFoto, String urlFicha,
			List<Categoria> nombreCategorias) throws DAOException {
		try {
			em.getTransaction().begin();
			VideojuegoItem newJuego = new VideojuegoItem(nombre, generoPrincipal, generoSecundario, generoOtros,
					descripcion, nota, fechaLanzamiento, urlFoto, urlFicha);
			newJuego.setPlataformas(nombreCategorias);
			em.persist(newJuego);
			em.getTransaction().commit();
			return newJuego;
		} catch (Exception ex) {
			throw new DAOException(ex.getMessage());
		}
	}

	@Override
	public VideojuegoItem findByVideojuego(String idJuego) throws DAOException {
		try {
			Integer id = Integer.decode(idJuego);
			VideojuegoItem jueJPA = em.find(VideojuegoItem.class, id);
			return jueJPA;
		} catch (Exception ex) {
			throw new DAOException(ex.getMessage());
		}
	}

	@Override
	public List<VideojuegoItem> findAll() throws DAOException {
		Query query = em.createQuery("SELECT v FROM VideojuegoItem");
		List<VideojuegoItem> resultList = new LinkedList<>();
		try {
			resultList = (List<VideojuegoItem>) query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
		return resultList;
	}

	@Override
	public void delete(String idJuego) throws DAOException {
		try {
			Integer id = Integer.decode(idJuego);
			em.getTransaction().begin();
			VideojuegoItem juego = em.find(VideojuegoItem.class, id);
			em.remove(juego);
			em.getTransaction().commit();
		} catch (Exception ex) {
			throw new DAOException(ex.getMessage());
		}
	}

}
