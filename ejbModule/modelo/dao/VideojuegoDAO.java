package modelo.dao;

import java.util.Date;
import java.util.List;

import modelo.Categoria;
import modelo.Usuario;
import modelo.VideojuegoItem;

public interface VideojuegoDAO {

	VideojuegoItem create(String nombre, String generoPrincipal, String generoSecundario, String generoOtros,
			String descripcion, String nota, Date fechaLanzamiento, String urlFoto, String urlFicha,
			List<Categoria> nombreCategorias) throws DAOException;

	VideojuegoItem findByVideojuego(String idJuego) throws DAOException;

	List<VideojuegoItem> findAll() throws DAOException;

	void delete(String idJuego) throws DAOException;

}
