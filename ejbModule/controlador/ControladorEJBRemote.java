package controlador;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import modelo.Catalogo;
import modelo.Categoria;
import modelo.Usuario;
import modelo.VideojuegoItem;

@Remote
public interface ControladorEJBRemote {
	boolean login(String usuario, String clave);
	Usuario registrar(String nif, String nombre, String usuario,String clave, String email);
	void addCatalogo(Usuario usuario, Catalogo catalogo);
	List<Catalogo> recuperarCatalogosPorUsuario(String usuario) ;
	List<Usuario> recuperarUsuarios();
	List<Catalogo> recuperarCatalogosUsuarioActivo();
	void registrarJuego(String nombre, String generoPrincipal, String generoSecundario, String generoOtros,
			String descripcion, String nota, Date fechaLanzamiento, String urlFoto, String urlFicha, List<String> nombreCategorias);
	List<Categoria> getCategorias();
	Categoria getCategoria(String nombre);
	Categoria registrarCategoria(String nombreCategoria);
	void registrarCatalogo(String nombreCatalogo, String[] selectedCategorys);
	List<VideojuegoItem> getVideojuegos();
	void logout();
	void establecerCategoriasCatalogo(String[] selectedCategorys);
	void setCatalogoActivo(String nombreCat);
	void eliminarJuego(String idJuego);
	VideojuegoItem getJuego(String idJuego);
	void setJuegoActivo(String idJuego);
	Catalogo getCatalogo(String nombreCatalogo);
	Usuario getUsuarioActivo();
	void setUsuarioActivo(String usuario);
	void eliminarCatalogo(String nombreCat);
}
