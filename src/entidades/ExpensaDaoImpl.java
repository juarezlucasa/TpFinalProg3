package entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.DAOException;
import exceptions.ServicioException;
import interfaces.ExpensaDao;
import utiles.DBManager;

public class ExpensaDaoImpl implements ExpensaDao {

	public void limpiarExpensa() throws DAOException {
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			String sql = "DELETE FROM EXPENSAS";
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

	public void crearExpensa(Expensa e) throws DAOException {
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO expensas (ID_EDIFICIO, DEPARTAMENTO,ANIOMES, MONTO, MONTO_ABONADO ) VALUES ('"
					+ e.getIdEdificio() + "', '" + e.getDepto() + "', '" + e.getAniomes() + "'," + e.getMonto()
					+ "," + e.getMontoPagado() + ")";
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

	@Override
	public ArrayList<Expensa> listarExpensas(String periodo) {
		ArrayList<Expensa> lista = new ArrayList<>();
		String sql = "SELECT A.ID_EDIFICIO, A.DEPARTAMENTO, B.PROPIETARIO, A.ANIOMES, A.MONTO, A.MONTO_ABONADO FROM EXPENSAS A INNER JOIN UNIDADFUNCIONAL B ON A.ID_EDIFICIO=B.ID_EDIFICIO AND A.DEPARTAMENTO=B.DEPARTAMENTO where ANIOMES = '"
				+ periodo + "'";
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
				String aniomes = rs.getString("aniomes");
				int monto = rs.getInt("monto");
				int montoPagado = rs.getInt("monto_abonado");
				Expensa unaExpensa = new Expensa(idEdificio, departamento, propietario, aniomes, monto, montoPagado);
				lista.add(unaExpensa);

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

	@Override
	public int calcularGastosTotales() {
		int gastoTotal = 0;
		String sql = "select sum(monto)MONTO from gastos";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				gastoTotal = rs.getInt(1);
				System.out.println(gastoTotal);
			}
		} catch (SQLException e) {
			try {
				c.rollback();
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
		return gastoTotal;
	}

	@Override
	public int calcularCantUf() {
		int cant = 0;
		String sql = "select count(*)CANT from unidadfuncional";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				cant = rs.getInt(1);
				System.out.println(cant);
			}

		} catch (SQLException e) {
			try {
				c.rollback();
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
		return cant;
	}

	@Override
	public void pagarExpensa(UnidadFuncional e) throws DAOException {
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			String sql = "";
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

	@Override
	public void pagarExpensa(int idEdificio, String departamento, String periodo, int monto) throws DAOException {
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			String sql = "UPDATE EXPENSAS SET MONTO_ABONADO = " + monto + " WHERE ID_EDIFICIO= " + idEdificio
					+ " AND departamento = '" + departamento + "' and aniomes= '" + periodo + "'";
			int rowsUpdated = s.executeUpdate(sql);
			if (rowsUpdated == 0) {
				throw new DAOException("Error Primero debe calcular expensas para abonar");
			}
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

}
