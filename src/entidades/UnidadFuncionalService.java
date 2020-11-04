package entidades;

import java.util.ArrayList;

import exceptions.DAOException;
import exceptions.ServicioException;
import interfaces.UnidadFuncionalDao;

public class UnidadFuncionalService {
	private UnidadFuncionalDao dao;

	public UnidadFuncionalService() {
		dao = new UnidadFuncionalDaoImpl();
	}

	public void crearUf(UnidadFuncional u) throws ServicioException {
		try {
			dao.crearUf(u);
		} catch (DAOException e) {
			throw new ServicioException(e);
		}
	}

	public void eliminarUf(int idEdificio, String departamento) throws ServicioException {
		try {
			dao.borrarUf(idEdificio, departamento);
		} catch (DAOException e) {
			throw new ServicioException(e);
		}
	}

	public void actualizarUf(UnidadFuncional u) throws ServicioException {
		try {
			dao.modificarUf(u);
		} catch (DAOException e) {
			throw new ServicioException(e);
		}
	}

	public ArrayList<UnidadFuncional> listarUf() {
		return dao.listarUf();
	}

	public UnidadFuncional consultarUf(int idEdificio, String departamento) throws ServicioException {
		try {
			return dao.consultarUf(idEdificio, departamento);
		} catch (DAOException e) {
			throw new ServicioException(e);
		}
	}

}
