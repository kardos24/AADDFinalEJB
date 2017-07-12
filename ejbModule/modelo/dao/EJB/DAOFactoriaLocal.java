package modelo.dao.EJB;

import javax.ejb.Local;

import modelo.dao.CatalogoDAO;
import modelo.dao.CategoriaDAO;
import modelo.dao.DAOException;
import modelo.dao.UsuarioDAO;
import modelo.dao.VideojuegoDAO;

@Local
public interface DAOFactoriaLocal {
	UsuarioDAO getUsuarioDAO();
	CategoriaDAO getCategoriaDAO();
	CatalogoDAO getCatalogoDAO();
	VideojuegoDAO getVideojuegoDAO();
	
	void setDAOFactoria(int tipo) throws DAOException;

}
