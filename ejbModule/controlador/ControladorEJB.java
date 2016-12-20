package controlador;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;

import modelo.Catalogo;
import modelo.Usuario;
import modelo.dao.DAOException;
import modelo.dao.DAOFactoria;
import modelo.dao.UsuarioDAO;
import modelo.dao.EJB.DAOFactoriaLocal;

@Stateful(mappedName = "ControladorEJB")
public class ControladorEJB implements ControladorEJBRemote{

	private Usuario usuarioActual = null;

	@EJB(beanName = "Contador")
	private Contador contador;

	@EJB(beanName = "Factoria")
	private DAOFactoriaLocal factoria;

	@PostConstruct
	public void configurarFactoria() {
		try {
			factoria.setDAOFactoria(DAOFactoria.JPA);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public boolean login(String usuario, String clave) {
		UsuarioDAO usuarioDAO;
		try {
			usuarioDAO = factoria.getUsuarioDAO();
			Usuario usu = usuarioDAO.findByUsuario(usuario);
			if (usu == null || !Objects.equals(usu.getClave(), clave))
				return false;
			else {
				usuarioActual = usu;
				System.out.println("Login correctos por Aplicacion:" + contador.siguienteValor());
				return true;
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Usuario registrar(String nif, String nombre, String usuario,
			String clave, String email) {
		UsuarioDAO usuarioDAO;
		try {
			usuarioDAO = factoria.getUsuarioDAO();
			return usuarioDAO.create(nif, nombre, usuario, clave, email);
		} catch (DAOException e) {
			return null;
		}

	}

	public void addCatalogo(Usuario usuario, Catalogo catalogo) {

		usuario.getCatalogos().add(catalogo);
		try {
			factoria.getUsuarioDAO().update(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public List<Catalogo> recuperarCatalogosPorUsuario(String usuario) {
		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO();
		Usuario usu;
		try {
			usu = usuarioDAO.findByUsuario(usuario);
			return usu.getCatalogos();
		} catch (DAOException e) {
			return new LinkedList<Catalogo>();
		}

		//
		// CatalogoDAO catalogoDAO = factoria.getCatalogoDAO();
		// List<Catalogo> lista = catalogoDAO.findByUsuario(usuario);
		// return lista;
	}

	public List<Usuario> recuperarUsuarios() {
		UsuarioDAO usuarioDao = factoria.getUsuarioDAO();
		try {
			return usuarioDao.findAll();
		} catch (DAOException e) {
			return new LinkedList<Usuario>();
		}
	}

}
