package modelo.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import modelo.dao.JPA.JPADAOCatalogo;
import modelo.dao.JPA.JPADAOCategoria;
import modelo.dao.JPA.JPADAOUsuario;

public class JPADAOFactoria extends DAOFactoria {
//	@PersistenceContext ( unitName ="PracticaAADDFinal")
	private EntityManagerFactory emf;

	public JPADAOFactoria() {
		emf = Persistence.createEntityManagerFactory("PracticaAADDFinal"); // Sin el @PersistenceContext
	}
	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new JPADAOUsuario(emf);
	}

	@Override
	public CategoriaDAO getCategoriaDAO() {
		return new JPADAOCategoria(emf);
	}

	@Override
	public CatalogoDAO getCatalogoDAO() {
		return new JPADAOCatalogo(emf);
	}

}
