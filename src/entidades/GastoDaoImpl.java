package entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.DAOException;
import exceptions.ServicioException;
import interfaces.GastoDao;
import utiles.DBManager;

public class GastoDaoImpl implements GastoDao {

	public void crearGasto(Gasto u) throws DAOException {
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO gastos (FECHA, MONTO, DESCRIPCION) VALUES (current_timestamp , " + u.getMonto()
					+ ", '" + u.getDescripcion() + "')";
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e0) {
			try {
				c.rollback();
				throw new DAOException(e0);
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public List<Gasto> listarGasto() throws DAOException {
		List<Gasto> lista = new ArrayList<>();
		String sql = "SELECT * FROM GASTOS";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int monto = rs.getInt("monto");
				String descripcion = rs.getString("descripcion");
				Gasto unGasto = new Gasto(monto, descripcion);
				lista.add(unGasto);

			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
		return lista;
	}
}
