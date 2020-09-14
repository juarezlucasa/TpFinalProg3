package interfaces;

import entidades.Edificio;
import exceptions.DAOException;
import exceptions.ServicioException;

import java.sql.SQLException;
import java.util.List;

public interface EdificioDao {

    public void crearEdificio(Edificio u) throws DAOException;
    public void borrarEdificio(int idEdificio) throws DAOException;
    public void modificarEdificio(Edificio u) throws DAOException;
    public List<Edificio> listarEdificios();
    public Edificio consultarEdificio(int idEdificio) throws DAOException;
	
}
