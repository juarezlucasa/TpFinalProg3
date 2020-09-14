package entidades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.DAOException;
import exceptions.ServicioException;
import interfaces.ExpensaDao;

public class ExpensaService {
	
	private ExpensaDao dao;
	
	public ExpensaService() {
		dao = new ExpensaDaoImpl();
	}
	
	public void crearExpensa(Expensa u) throws ServicioException  {
		try {
			dao.crearExpensa(u);
		} catch (DAOException e) {
			throw new ServicioException(e);
		}
	}
	
	public void limpiarExpensa() throws ServicioException  {
		try {
			dao.limpiarExpensa();
		} catch (DAOException e) {
			throw new ServicioException(e);
		}
	}
	
	public void pagarExpensa(int idEdificio, String departamento, String periodo, int monto) throws ServicioException  {
		try {
			dao.pagarExpensa(idEdificio, departamento, periodo, monto);
		} catch (DAOException e) {
			throw new ServicioException(e);
		}
	}

	public ArrayList<Expensa> listarExpensas(String periodo) {
		return dao.listarExpensas(periodo);
	}

	public int calcularGastosTotales() {
		return dao.calcularGastosTotales();
	}
	
	public int calcularCantUf() {
		return dao.calcularCantUf();
	}

}
