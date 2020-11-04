package interfaces;

import java.util.ArrayList;

import entidades.Expensa;
import entidades.UnidadFuncional;
import exceptions.DAOException;

public interface ExpensaDao {
	public void crearExpensa(Expensa u) throws DAOException;
	public void limpiarExpensa() throws DAOException;
	public ArrayList<Expensa> listarExpensas(String aniomes);
	public int calcularGastosTotales();
	public int calcularCantUf();
	public void pagarExpensa(int idEdificio, String departamento, String periodo, int monto) throws DAOException;

}
