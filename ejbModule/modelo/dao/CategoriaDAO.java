package modelo.dao;

import modelo.Categoria;

public interface CategoriaDAO {
	
	Categoria create(String nombre) throws DAOException;
}
