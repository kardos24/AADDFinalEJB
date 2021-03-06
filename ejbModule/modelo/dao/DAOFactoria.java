package modelo.dao;

import javax.ejb.Stateless;

import modelo.dao.EJB.DAOFactoriaLocal;

@Stateless(name = "Factoria")
public class DAOFactoria implements DAOFactoriaLocal {

	protected DAOFactoria factoria = null;

	// Metodos factoria
	public UsuarioDAO getUsuarioDAO() {
		return factoria.getUsuarioDAO();
	}

	public CategoriaDAO getCategoriaDAO() {
		return factoria.getCategoriaDAO();
	}

	public CatalogoDAO getCatalogoDAO() {
		return factoria.getCatalogoDAO();
	}

	@Override
	public VideojuegoDAO getVideojuegoDAO() {
		return factoria.getVideojuegoDAO();
	}

	// Declaracion como constantes de los tipos de factoria
	public final static int JDBC = 1;
	public final static int JPA = 2;
	public final static int MYSQL = 1;

	public void setDAOFactoria(int tipo) throws DAOException {
		System.out.println("DAOFactoria.setDAOFactoria()");
		switch (tipo) {
			case JDBC: {
				try {
					factoria = new JDBCDAOFactoria();
				} catch (Exception e) {
					throw new DAOException(e.getMessage());
				}
			}
			case JPA: {
				try {
					factoria = new JPADAOFactoria();
				} catch (Exception e) {
					throw new DAOException(e.getMessage());
				}
			}
		}
	}

	
}
