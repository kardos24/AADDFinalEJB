package controlador;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import modelo.Catalogo;
import modelo.Categoria;
import modelo.Usuario;
import modelo.VideojuegoItem;
import modelo.dao.DAOException;
import modelo.dao.DAOFactoria;
import modelo.dao.UsuarioDAO;
import modelo.dao.VideojuegoDAO;
import modelo.dao.EJB.DAOFactoriaLocal;

@Stateful(mappedName = "ControladorEJB")
public class ControladorEJB implements ControladorEJBRemote {

	private Usuario usuarioActual = null;
	private Catalogo catalogoActual = null;
	private VideojuegoItem juegoActual = null;

	@EJB(beanName = "Factoria")
	private DAOFactoriaLocal factoria;
	
	@Resource
	private SessionContext contexto;

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
				return true;
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Usuario registrar(String nif, String nombre, String usuario, String clave, String email) {
		UsuarioDAO usuarioDAO;
		try {
			usuarioDAO = factoria.getUsuarioDAO();
			Usuario usu = usuarioDAO.create(nif, nombre, usuario, clave, email);
			usuarioActual = usu;
			return usu;
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
	}

	public List<Usuario> recuperarUsuarios() {
		UsuarioDAO usuarioDao = factoria.getUsuarioDAO();
		try {
			return usuarioDao.findAll();
		} catch (DAOException e) {
			return new LinkedList<Usuario>();
		}
	}

	public List<Catalogo> recuperarCatalogosUsuarioActivo() {
		if (usuarioActual != null)
			return recuperarCatalogosPorUsuario(usuarioActual.getUsuario());
		else
			return null;
	}

	@Override
	public void registrarJuego(String nombre, String generoPrincipal, String generoSecundario, String generoOtros,
			String descripcion, String nota, Date fechaLanzamiento, String urlFoto, String urlFicha, List<String> nombreCategorias) {
		System.out.println("Inicio metodo registrarJuego");
		VideojuegoDAO juegoDao = factoria.getVideojuegoDAO();
		try {
			juegoDao.create(nombre, generoPrincipal, generoSecundario, generoOtros,
					descripcion, nota, fechaLanzamiento,urlFoto, urlFicha, nombreCategorias.stream().map(c -> getCategoria(c)).collect(Collectors.toList()));
		} catch (DAOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public List<Categoria> getCategorias() {
		try {
			return factoria.getCategoriaDAO().findAll();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return new LinkedList<>();
	}

	@Override
	public Categoria getCategoria(String nombre) {
		try {
			return factoria.getCategoriaDAO().findByCategoria(nombre);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Categoria registrarCategoria(String nombreCategoria) {
		try {
			return factoria.getCategoriaDAO().create(nombreCategoria);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void registrarCatalogo(String nombreCatalogo, String[] selectedCategorys) {
		try {
			Catalogo cate = factoria.getCatalogoDAO().create(nombreCatalogo, new Date(), "", "", usuarioActual);
//			cate.setCategorias(Arrays.asList(selectedCategorys).stream().map(s->getCategoria(s)).filter(c -> c != null).collect(Collectors.toList()));
//			cate.setItems(getVideojuegos().stream().filter(j -> j.contieneCategoria(cate.getCategorias())).collect(Collectors.toList()));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<VideojuegoItem> getVideojuegos() {
		try {
			return factoria.getVideojuegoDAO().findAll();
		} catch (DAOException e) {
			e.printStackTrace();
			return new LinkedList<>();
		}
	}

	@Override
	public void logout() {
		usuarioActual = null;
	}

	@Override
	public void establecerCategoriasCatalogo(String[] selectedCategorys) {
//		catalogoActual.setCategorias(Arrays.asList(selectedCategorys).stream().map(s->getCategoria(s)).filter(c -> c != null).collect(Collectors.toList()));
//		catalogoActual.setItems(getVideojuegos().stream().filter(j -> j.contieneCategoria(catalogoActual.getCategorias())).collect(Collectors.toList()));
	}

	@Override
	public void setCatalogoActivo(String nombreCat) {
		try {
			catalogoActual = factoria.getCatalogoDAO().findByNombre(nombreCat);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarJuego(String idJuego) {
		try {
			factoria.getVideojuegoDAO().delete(idJuego);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public VideojuegoItem getJuego(String idJuego) {
		try {
			return factoria.getVideojuegoDAO().findByVideojuego(idJuego);
		} catch (DAOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void setJuegoActivo(String idJuego) {
		juegoActual = getJuego(idJuego);		
	}

	@Override
	public Catalogo getCatalogo(String nombreCatalogo) {
		try {
			return factoria.getCatalogoDAO().findByNombre(nombreCatalogo);
		} catch (DAOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Usuario getUsuarioActivo() {
		return usuarioActual;
	}

	@Override
	public void setUsuarioActivo(String usuario) {
		try {
			usuarioActual = factoria.getUsuarioDAO().findByUsuario(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarCatalogo(String nombreCat) {
		try {
			factoria.getCatalogoDAO().delete(nombreCat);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
