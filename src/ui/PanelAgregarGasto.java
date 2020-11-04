package ui;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import entidades.Edificio;
import entidades.EdificioService;
import entidades.Gasto;
import entidades.GastoService;
import exceptions.CamposVaciosException;
import exceptions.ServicioException;

public class PanelAgregarGasto extends PanelTemplateGasto {

	public PanelAgregarGasto(String titulo) {
		super(titulo);
		initUI(titulo);
		cambiarEtiquetaBoton("Cargar Gasto");
		habilitarJText();
	}

	public void botonOk(Gasto gasto) {
		GastoService s = new GastoService();
		try {
			s.crearGasto(gasto);
			mostrarExito("Se agrego el gasto al sistema");
		} catch (ServicioException e) {
			mostrarError("No se pudo agregar el gasto al sistema", e.getMessage());
		}

	}

}
