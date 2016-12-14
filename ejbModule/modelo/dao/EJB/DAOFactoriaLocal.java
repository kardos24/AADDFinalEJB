package modelo.dao.EJB;

import javax.ejb.Local;

import modelo.dao.CatalogoDAO;
import modelo.dao.CategoriaDAO;
import modelo.dao.DAOException;
import modelo.dao.UsuarioDAO;

@Local
public interface DAOFactoriaLocal {
	UsuarioDAO getUsuarioDAO();
	CategoriaDAO getCategoriaDAO();
	CatalogoDAO getCatalogoDAO();
	
	void setDAOFactoria(int tipo) throws DAOException;

}
