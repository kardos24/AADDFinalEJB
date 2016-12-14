package modelo.dao.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import modelo.Catalogo;
import modelo.Usuario;
import modelo.dao.DAOException;
import modelo.dao.UsuarioDAO;

public class JDBCUsuarioDAO implements UsuarioDAO {

	private DataSource ds;

	public JDBCUsuarioDAO(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Usuario create(String nif, String nombre, String usuario,
			String clave, String email) throws DAOException {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement("INSERT into USUARIO"
					+ "(nif,nombre,usuario,clave,email) "
					+ "values (?,?,?,?,?)");
			stmt.setString(1, nif);
			stmt.setString(2, nombre);
			stmt.setString(3, usuario);
			stmt.setString(4, clave);
			stmt.setString(5, email);

			stmt.executeUpdate();
			stmt.close();
			con.close();

			Usuario c = new Usuario(nif, nombre, usuario, clave, email);

			return c;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

	}

	@Override
	public void update(Usuario usuario)throws DAOException {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement("UPDATE USUARIO SET nif = ?,nombre = ?,clave = ?,email = ? WHERE usuario =  ?");
			stmt.setString(1, usuario.getNif());
			stmt.setString(2, usuario.getNombre());
			stmt.setString(3, usuario.getClave());
			stmt.setString(4, usuario.getEmail());
			stmt.setString(5, usuario.getUsuario());
			stmt.executeUpdate();

			stmt = con.prepareStatement("UPDATE Catalogo SET usuario = ? WHERE nombre =  ?");
			for(Catalogo catalogo : usuario.getCatalogos()){
				stmt.setString(1, usuario.getUsuario());
				stmt.setString(2, catalogo.getNombre());
				stmt.executeUpdate();				
			}
			
			stmt.close();
			con.close();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

	}

	@Deprecated
	public Usuario addCatalogo(Usuario usuario, Catalogo catalogo)throws DAOException {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement("UPDATE Catalogo SET usuario= ? WHERE nombre =  ?");
			stmt.setString(1, usuario.getUsuario());
			stmt.setString(2, catalogo.getNombre());

			stmt.executeUpdate();
			stmt.close();
			con.close();

			usuario.getCatalogos().add(catalogo);

			return usuario;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

	}

	@Override
	public Usuario findByUsuario(String usuario) throws DAOException {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con
					.prepareStatement("SELECT nif,nombre,usuario,clave,email FROM Usuario WHERE usuario = ? ");
			stmt.setString(1, usuario);
			ResultSet rs = stmt.executeQuery();

			Usuario usu = null;
			if (rs.next()) {
				usu = new Usuario(rs.getString("nif"), rs.getString("nobmre"),
						rs.getString("usuario"), rs.getString("clave"),
						rs.getString("email"));
			}

			stmt.close();
			con.close();

			return usu;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<Usuario> findAll() throws DAOException {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement("SELECT nif,nombre,usuario,clave,email FROM Usuario");
			ResultSet rs = stmt.executeQuery();

			List<Usuario> ususList = new LinkedList<>();
			if (rs.next()) {
				Usuario usu = new Usuario(rs.getString("nif"), rs.getString("nobmre"),
						rs.getString("usuario"), rs.getString("clave"),
						rs.getString("email"));
				ususList.add(usu);
			}

			stmt.close();
			con.close();

			return ususList;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

}
