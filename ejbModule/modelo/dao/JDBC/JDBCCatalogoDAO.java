package modelo.dao.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import controlador.ControladorEJB;
import modelo.Catalogo;
import modelo.Usuario;
import modelo.dao.CatalogoDAO;
import modelo.dao.DAOException;

public class JDBCCatalogoDAO implements CatalogoDAO {

	private DataSource ds;

	public JDBCCatalogoDAO(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Catalogo create(String nombre, Date fecha, String web, String url,
			Usuario usuario) {
		return null;
	}

	@Override
	public Catalogo findByNombre(String nombre) throws DAOException {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con
					.prepareStatement("SELECT nombre,fecha,web,url,cod_categoria,usuario FROM Catalogo WHERE nombre = ? ");
			stmt.setString(1, nombre);
			ResultSet rs = stmt.executeQuery();

			Catalogo catalogo = null;
			if (rs.next()) {
//				catalogo = new Catalogo(rs.getString("nombre"),
//						rs.getDate("fecha"), rs.getString("web"),
//						rs.getString("url"), Controlador.getInstance().rs.getString("usuario"));
			}

			stmt.close();
			con.close();

			return catalogo;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void delete(String idCatalogo) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
