package modelo.dao;

import java.util.List;

import modelo.Categoria;

public interface CategoriaDAO {
	
	Categoria create(String nombre) throws DAOException;
	
	Categoria findByCategoria(String categoria) throws DAOException;

	List<Categoria> findAll() throws DAOException;
}
