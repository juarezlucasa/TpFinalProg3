package entidades;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import exceptions.ServicioException;

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

	public String toString() {
		return "Expensa{" + "Id Edificio='" + super.getIdEdificio() + '\'' + ", departamento='" + super.getDepto()
				+ '\'' + ", propietario='" + super.getPropietario() + '\'' + ", aniomes='" + aniomes + '\''
				+ ", monto='" + monto + '\'' + '}';
	}

}
