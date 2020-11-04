package entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.DAOException;
import interfaces.GastoDao;
import utiles.DBManager;

public class GastoDaoImpl implements GastoDao {

	public void crearGasto(Gasto u) throws DAOException {
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO gastos (ID,FECHA, MONTO, DESCRIPCION) VALUES ("+u.getId() +", current_timestamp , " + u.getMonto()
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

	@Override
	public void borrarGasto(Gasto u) throws DAOException {
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			String sql = "DELETE FROM gastos WHERE ID = " + u.getId() + "";
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
				throw new DAOException(e1);
			}
		}

	}

	@Override
	public void modificarGasto(Gasto u) throws DAOException {
		String sql = "UPDATE gastos set monto = '" + u.getMonto() + "', descripcion = '" + u.getDescripcion()
				+ "' WHERE ID = '" + u.getId() + "'";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e0) {
			try {
				c.rollback();
				e0.printStackTrace();
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
		}

	}

	public ArrayList<Gasto> listarGasto() {
		ArrayList<Gasto> lista = new ArrayList<>();
		String sql = "SELECT * FROM GASTOS";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()){
				int id = rs.getInt("id");
				String fecha = rs.getString("fecha");
				int monto = rs.getInt("monto");
				String descripcion = rs.getString("descripcion");
				Gasto unGasto = new Gasto(id,fecha, monto, descripcion);
				lista.add(unGasto);

			}
		} catch (SQLException e) {
			//No hago nada
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				//No hago nada
			}
		}
		return lista;
	}

}
