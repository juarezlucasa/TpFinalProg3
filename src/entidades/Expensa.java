package entidades;

import java.awt.List;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import exceptions.DAOException;
import exceptions.ServicioException;
import ui.MiFrame;
import ui.PanelLiquidarExpensas;

public class Expensa extends UnidadFuncional {

	private String aniomes;
	private int monto;
	private int montoPagado;

	public Expensa(int idEdificio, String depto, String propietario, String aniomes, int monto, int montoPagado) {
		super(idEdificio, depto, propietario);
		this.setAniomes(aniomes);
		this.monto = monto;
		this.setMontoPagado(montoPagado);
	}

	/*
	 * ########################### INICIO DE GETTER Y SETTER
	 * ###############################
	 */

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public String getAniomes() {
		return aniomes;
	}

	public void setAniomes(String aniomes) {
		this.aniomes = aniomes;
	}
	
	public int getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(int montoPagado) {
		this.montoPagado = montoPagado;
	}

	/*
	 * ########################### FIN DE GETTER Y SETTER
	 * ###############################
	 */

	public static void calcularExpensas() {

		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int mes = localDate.getMonthValue();
		int anio = localDate.getYear();
		String aniomes = mes+"/"+anio;
		System.out.println(aniomes);
		ExpensaService s = new ExpensaService();
		ArrayList<Expensa> chequeoExpensas = new ArrayList();
		chequeoExpensas = s.listarExpensas(aniomes);
		if (chequeoExpensas.isEmpty()) {
			UnidadFuncionalService u = new UnidadFuncionalService();
			int gastos = s.calcularGastosTotales(); // CALCULO LA CANTIDAD DE GASTOS TOTALES EN LA BASE.
			//System.out.println(gastos);
			int cantEdificios = s.calcularCantUf(); // CALCULO LA CANTIDAD DE UNIDADES FUNCINOALES DEL EDIFICIO.
			//System.out.println(cantEdificios);
			int montoExpensas = gastos / cantEdificios;
			int montoPagado=0;
			ArrayList<UnidadFuncional> listadoUf = new ArrayList();
			listadoUf = u.listarUf();
			
			for (int i = 0; i < cantEdificios; i = i + 1) {
				UnidadFuncional uf = new UnidadFuncional();
				uf = listadoUf.get(i); // Genero el objeto UnidadFuncional para armar el objeto Expensas, para luego
										// guardarlo en la tabla Expensas en la BD
				Expensa ex = new Expensa(uf.getIdEdificio(), uf.getDepto(), uf.getPropietario(), aniomes, montoExpensas, montoPagado );
				// Trato de insertar el edificio en la base de datos.
				try {
					s.crearExpensa(ex);
					//JOptionPane.showMessageDialog(null, "Se agregó la expensa al sistema", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
				} catch (ServicioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		}

	}
	
	public String toString() {
        return "Expensa{" +
                "Id Edificio='" + super.getIdEdificio() + '\'' +
                ", departamento='" + super.getDepto() + '\'' +
                ", propietario='" + super.getPropietario() + '\'' +
                ", aniomes='" + aniomes + '\'' +
                ", monto='" + monto + '\'' +
                '}';
    }

}
