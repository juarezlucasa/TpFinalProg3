package ui;

import entidades.UnidadFuncional;
import entidades.UnidadFuncionalService;
import exceptions.ServicioException;

public class PanelListarUnidadFuncional extends PanelTemplateUf  {

	public PanelListarUnidadFuncional(String titulo) {
		super(titulo);
		super.habilitarJText();
		super.deshabilitarBotonOk();
		cambiarEtiquetaBoton("Modificar UF");
	}
	
	public void botonOK(UnidadFuncional uf) {
		UnidadFuncionalService s = new UnidadFuncionalService();
		try {
			s.actualizarUf(uf);
			mostrarExito("Se modificó la UF con éxito");
			super.deshabilitarTextPanel();
		} catch (ServicioException e1) {
			mostrarError("Error al modificar la UF ",e1.getMessage());
		}
	}

}
