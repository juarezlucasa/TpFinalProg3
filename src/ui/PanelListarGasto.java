package ui;

import entidades.Gasto;
import entidades.GastoService;
import exceptions.ServicioException;

public class PanelListarGasto extends PanelTemplateGasto {
	
	public PanelListarGasto(String titulo) {
		super(titulo);
		super.cambiarEtiquetaBoton("Modificar Gasto");
		super.habilitarJText();
		super.deshabilitarBotonOk();
	}

	public void botonOk(Gasto gasto) {
		GastoService s = new GastoService();
		try {
			s.modificarGasto(gasto);
			mostrarExito("Se modificó el gasto al sistema");
		} catch (ServicioException e) {
			mostrarError("No se pudo modificar el gasto al sistema", e.getMessage());
		}

	}

}
