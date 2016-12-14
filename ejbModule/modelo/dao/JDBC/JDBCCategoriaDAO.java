package modelo.dao.JDBC;

import javax.sql.DataSource;

import modelo.Categoria;
import modelo.dao.CategoriaDAO;

public class JDBCCategoriaDAO implements CategoriaDAO {

	public JDBCCategoriaDAO(DataSource ds) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Categoria create(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}
