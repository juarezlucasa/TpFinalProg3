package entidades;

import java.util.ArrayList;
import java.util.List;

import exceptions.DAOException;
import exceptions.ServicioException;
import interfaces.GastoDao;

public class GastoService {

	private GastoDao dao;

	public GastoService() {
		dao = new GastoDaoImpl();
	}

	public void crearGasto(Gasto u) throws ServicioException {
		try {
			dao.crearGasto(u);
		} catch (DAOException e) {
			throw new ServicioException(e);
		}
	}
	
	public void eliminarGasto(Gasto u) throws ServicioException {
		try {
			dao.borrarGasto(u);
		} catch (DAOException e) {
			throw new ServicioException(e);
		}
	}
	
	public void modificarGasto(Gasto u) throws ServicioException {
		try {
			dao.modificarGasto(u);
		} catch (DAOException e) {
			throw new ServicioException(e);
		}
	}

	public ArrayList<Gasto> listarGasto() {
		return dao.listarGasto();
	}

}
