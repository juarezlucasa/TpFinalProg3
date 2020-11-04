package entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exceptions.DAOException;
import interfaces.UnidadFuncionalDao;
import utiles.DBManager;

public class UnidadFuncionalDaoImpl implements UnidadFuncionalDao {

	@Override
	public void crearUf(UnidadFuncional u) throws DAOException {
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO unidadfuncional (id_edificio, DEPARTAMENTO, propietario ) VALUES ('"
					+ u.getIdEdificio() + "', '" + u.getDepto() + "', '" + u.getPropietario() + "')";
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

	@Override
	public void borrarUf(int idEdificio, String departamento) throws DAOException {
		String sql = "DELETE FROM unidadfuncional WHERE ID_EDIFICIO = '" + idEdificio + "'" + "AND DEPARTAMENTO = '"
				+ departamento + "'";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			int rowsDeleted = s.executeUpdate(sql);
			if (rowsDeleted == 0) {
				throw new DAOException("la Unidad funcional a eliminar no existe");
				}
			c.commit();
		} catch (SQLException e0) {
			try {
				c.rollback();
				throw new DAOException(e0);
			} catch (SQLException e1) {
				//No hago nada
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
	public void modificarUf(UnidadFuncional u) throws DAOException {
		String sql = "UPDATE unidadfuncional set propietario = '" + u.getPropietario() + "'" + " WHERE id_edificio = '"
				+ u.getIdEdificio() + "' AND departamento= '" + u.getDepto() + "'";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e0) {
			try {
				throw new DAOException(e0);
			} finally {
				try {
					c.close();
				} catch (SQLException e1) {
					// no hago nada
				}

			}
		}
	}
	
	public UnidadFuncional consultarUf(int idEdificio, String departamento) throws DAOException {
		String sql = "SELECT * FROM unidadfuncional WHERE id_edificio = '" + idEdificio + "' AND departamento = '" + departamento + "'";
		Connection c = DBManager.connect();
		UnidadFuncional resultado = new UnidadFuncional();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				String propietario = rs.getString("propietario");
				resultado = new UnidadFuncional(idEdificio, departamento, propietario);
			}

		} catch (SQLException e0) {
			try {
				throw new DAOException(e0);
			} finally {
				try {
					c.close();
				} catch (SQLException e1) {
					// no hago nada
				}

			}
		}
		return resultado;
	}

	@Override
	public ArrayList<UnidadFuncional> listarUf() {
		ArrayList<UnidadFuncional> lista = new ArrayList<>();
		String sql = "SELECT * FROM unidadfuncional";
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
				String departamento = rs.getString("departamento");
				String propietario = rs.getString("propietario");
				UnidadFuncional unaUf = new UnidadFuncional(idEdificio, departamento, propietario);
				lista.add(unaUf);

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
