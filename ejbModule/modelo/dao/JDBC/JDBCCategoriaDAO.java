package modelo.dao.JDBC;

import java.util.List;

import javax.sql.DataSource;

import modelo.Categoria;
import modelo.dao.CategoriaDAO;
import modelo.dao.DAOException;

public class JDBCCategoriaDAO implements CategoriaDAO {

	public JDBCCategoriaDAO(DataSource ds) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Categoria create(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria findByCategoria(String categoria) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
