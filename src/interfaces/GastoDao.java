package interfaces;

import java.util.List;

import entidades.Edificio;
import entidades.Gasto;
import exceptions.DAOException;
import exceptions.ServicioException;

public interface GastoDao {
    public void crearGasto(Gasto u) throws DAOException;
    public List<Gasto> listarGasto() throws DAOException;
}
