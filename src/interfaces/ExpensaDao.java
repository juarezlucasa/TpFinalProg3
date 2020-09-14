package interfaces;

import java.util.ArrayList;
import java.util.List;

import entidades.Edificio;
import entidades.Expensa;
import entidades.UnidadFuncional;
import exceptions.DAOException;
import exceptions.ServicioException;

public interface ExpensaDao {
	public void crearExpensa(Expensa u) throws DAOException;
	public void limpiarExpensa() throws DAOException;
	public void pagarExpensa(UnidadFuncional e) throws DAOException;
	public ArrayList<Expensa> listarExpensas(String aniomes);
	public int calcularGastosTotales();
	public int calcularCantUf();
	public void pagarExpensa(int idEdificio, String departamento, String periodo, int monto) throws DAOException;

}
