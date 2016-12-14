package modelo.dao;

import javax.ejb.Local;

@Local
public interface DAOFactoriaLocal {
	UsuarioDAO getUsuarioDAO();
	CategoriaDAO getCategoriaDAO();
	CatalogoDAO getCatalogoDAO();
	
	void setDAOFactoria(int tipo) throws DAOException;

}
