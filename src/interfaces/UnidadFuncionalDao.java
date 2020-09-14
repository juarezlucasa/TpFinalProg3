package interfaces;

import java.util.ArrayList;
import java.util.List;

import entidades.UnidadFuncional;
import exceptions.DAOException;
import exceptions.ServicioException;

public interface UnidadFuncionalDao {
	
    public void crearUf(UnidadFuncional u) throws DAOException;
    public void borrarUf(int idEdificio, String departamento) throws DAOException;
    public void modificarUf(UnidadFuncional u) throws DAOException;
    public ArrayList<UnidadFuncional> listarUf();
    public UnidadFuncional consultarUf(int idEdificio, String departamento) throws DAOException;

}
