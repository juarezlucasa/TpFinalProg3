package entidades;

import java.util.List;

import entidades.EdificioDaoImpl;
import exceptions.DAOException;
import exceptions.ServicioException;
import interfaces.EdificioDao;

public class EdificioService {

	private EdificioDao dao;
	
	public EdificioService() {
		dao = new EdificioDaoImpl();
	}
	
	public void crearEdificio(Edificio u) throws ServicioException {
			try {
				dao.crearEdificio(u);
			} catch (DAOException e) {
				throw new ServicioException(e);
			}

	}

	public void eliminarEdificio(int idEdificio) throws ServicioException  {
		try {
			dao.borrarEdificio(idEdificio);
		} catch (DAOException e) {
			throw new ServicioException(e);
		}
	}

	public void actualizarEdificio(Edificio u) throws ServicioException { 
		try {
			dao.modificarEdificio(u);
		} catch (DAOException e) {
			throw new ServicioException(e);
		}
	}

	public List<Edificio> listarEdificios() {
		return dao.listarEdificios();
	}

	public Edificio consultarEdificio(int idEdificio) throws ServicioException {
		try {
			return dao.consultarEdificio(idEdificio);
		} catch (DAOException e) {
			throw new ServicioException(e);
		}
		
	}

}
