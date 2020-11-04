package entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.DAOException;
import interfaces.EdificioDao;
import utiles.DBManager;

public class EdificioDaoImpl implements EdificioDao {

	public void crearEdificio(Edificio e) throws DAOException {
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO edificios (ID_EDIFICIO, DOMICILIO, PISOS, DEP_POR_PISOS ) VALUES ('"
					+ e.getIdEdificio() + "', '" + e.getDireccion() + "', '" + e.getPisos() + "', '"
					+ e.getDeptosPorPiso() + "')";
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e0) {
				throw new DAOException(e0);
			}
		 finally {
			try {
				c.close();
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
		}

	}

	public void borrarEdificio(int idEdificio) throws DAOException {
		String sql = "DELETE FROM edificios WHERE ID_EDIFICIO = '" + idEdificio + "'";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e0) {
			try {
				c.rollback();
				throw new DAOException(e0);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
		}
	}

	public void modificarEdificio(Edificio e) throws DAOException {
		String sql = "UPDATE edificios set id_edificio = '" + e.getIdEdificio() + "', domicilio = '" + e.getDireccion()
				+ "', pisos = '" + e.getPisos() + "', DEP_POR_PISOS = '" + e.getDeptosPorPiso()
				+ "' WHERE id_edificio = '" + e.getIdEdificio() + "'";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e0) {
			try {
				c.rollback();
				throw new DAOException(e0);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public Edificio consultarEdificio(int idEdificio) throws DAOException {
		String sql = "SELECT * FROM edificios WHERE id_edificio = '" + idEdificio + "'";
		Connection c = DBManager.connect();
		Edificio resultado = new Edificio();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				String domicilio = rs.getString("domicilio");
				int pisos = rs.getInt("pisos");
				int depPorPisos = rs.getInt("dep_por_pisos");
				resultado = new Edificio(idEdificio, domicilio, pisos, depPorPisos);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
			
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				// no hago nada
			}
		}
		return resultado;
	}

	public ArrayList<Edificio> listarEdificios() {
		ArrayList<Edificio> lista = new ArrayList<>();
		String sql = "SELECT * FROM edificios";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				// System.out.println("Edificio:");
				// System.out.print("\t" + rs.getInt("id_edificio"));
				// System.out.print("\t" + rs.getString("domicilio"));
				// System.out.print("\t" + rs.getString("pisos"));
				// System.out.print("\t" + rs.getString("dep_por_pisos"));
				// System.out.println();
				int idEdificio = rs.getInt("id_edificio");
				String domicilio = rs.getString("domicilio");
				int pisos = rs.getInt("pisos");
				int depPorPisos = rs.getInt("dep_por_pisos");
				Edificio unEdificio = new Edificio(idEdificio, domicilio, pisos, depPorPisos);
				lista.add(unEdificio);

			}
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// no hago nada
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				// no hago nada
			}
		}
		return lista;
	}

}
