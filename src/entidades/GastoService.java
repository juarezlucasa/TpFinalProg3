package entidades;

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

	public List<Gasto> listarGasto() throws ServicioException {
				try {
					return dao.listarGasto();
				} catch (DAOException e) {
					throw new ServicioException(e);
				}
	}

}
