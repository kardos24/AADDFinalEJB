package modelo.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import modelo.dao.JDBC.JDBCCatalogoDAO;
import modelo.dao.JDBC.JDBCCategoriaDAO;
import modelo.dao.JDBC.JDBCUsuarioDAO;

public class JDBCDAOFactoria extends DAOFactoria {

	private DataSource ds;

	public JDBCDAOFactoria() throws DAOException, ClassNotFoundException,
			java.sql.SQLException {
//		ds = ConnectionPool.getInstance("jdbc:mysql://localhost:3306/aadd","root", "");
		
		try {
			InitialContext contexto = new InitialContext();
			ds = (DataSource) contexto.lookup("java:comp/env/jdbc/AADD");
		} catch (NamingException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return (UsuarioDAO) new JDBCUsuarioDAO(ds);
	}

	@Override
	public CategoriaDAO getCategoriaDAO() {
		return (CategoriaDAO) new JDBCCategoriaDAO(ds);
	}

	@Override
	public CatalogoDAO getCatalogoDAO() {
		return (CatalogoDAO) new JDBCCatalogoDAO(ds);
	}

}
