package modelo.dao;

import java.util.Date;
import java.util.List;

import modelo.Catalogo;
import modelo.Usuario;

public interface CatalogoDAO {

	Catalogo create(String nombre, Date fecha, String web, String url, Usuario usuario) throws DAOException;
	
	Catalogo findByNombre(String nombre) throws DAOException;

	void delete(String idCatalogo) throws DAOException;
}
