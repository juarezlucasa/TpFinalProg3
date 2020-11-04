package ui;

import entidades.Gasto;
import entidades.GastoService;
import exceptions.ServicioException;

public class PanelEliminarGasto extends PanelTemplateGasto {

	public PanelEliminarGasto(String titulo) {
		super(titulo);
		super.cambiarEtiquetaBoton("Eliminar Gasto");
		deshabilitarTextPanel();
		super.deshabilitarBotonOk();
	}

	@Override
	public void botonOk(Gasto gasto) {
		GastoService s = new GastoService();
		try {
			s.eliminarGasto(gasto);
			mostrarExito("Se eliminó el gasto al sistema");
		} catch (ServicioException e) {
			mostrarError("No se pudo eliminar el gasto al sistema", e.getMessage());
		}
		
	}

}
