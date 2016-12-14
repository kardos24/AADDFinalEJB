package modelo.dao;

import java.util.List;

import modelo.Usuario;

public interface UsuarioDAO {
	Usuario create(String nif, String nombre, String usuario, String clave,	String email) throws DAOException;
	
	void update(Usuario usuario) throws DAOException;
	
	Usuario findByUsuario(String usuario) throws DAOException;
	
	List<Usuario> findAll() throws DAOException;
}
