package interfaces;

import java.util.ArrayList;
import java.util.List;

import entidades.Edificio;
import entidades.Gasto;
import exceptions.DAOException;

public interface GastoDao {
    public void crearGasto(Gasto u) throws DAOException;
    public void borrarGasto(Gasto u) throws DAOException;
    public void modificarGasto(Gasto u) throws DAOException;
    public ArrayList<Gasto> listarGasto();
}
