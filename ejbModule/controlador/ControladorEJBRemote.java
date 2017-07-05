package controlador;

import java.util.List;

import javax.ejb.Remote;

import modelo.Catalogo;
import modelo.Usuario;

@Remote
public interface ControladorEJBRemote {
	boolean login(String usuario, String clave);
	Usuario registrar(String nif, String nombre, String usuario,String clave, String email);
	void addCatalogo(Usuario usuario, Catalogo catalogo);
	List<Catalogo> recuperarCatalogosPorUsuario(String usuario) ;
	List<Usuario> recuperarUsuarios();
	List<Catalogo> recuperarCatalogosUsuarioActivo();
}
