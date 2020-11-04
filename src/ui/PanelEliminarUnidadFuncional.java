package ui;


import entidades.UnidadFuncional;
import entidades.UnidadFuncionalService;
import exceptions.ServicioException;

public class PanelEliminarUnidadFuncional extends PanelTemplateUf {

	public PanelEliminarUnidadFuncional(String titulo) {
		super(titulo);
		super.deshabilitarTextPanel();
		super.deshabilitarBotonOk();   //SE HABILITA CUANDO SE HACE CLICK EN UN REGISTRO DE LA TABLA.
		cambiarEtiquetaBoton("Eliminar UF");
	}
	
	public void botonOK(UnidadFuncional uf) {
		UnidadFuncionalService s = new UnidadFuncionalService();
		try {
			s.eliminarUf(uf.getIdEdificio(), uf.getDepto());
			mostrarExito("Se eliminó la UF con éxito");
			super.deshabilitarTextPanel();
		} catch (ServicioException e1) {
			mostrarError("Error al eliminar la UF",e1.getMessage());
		}
	}
		
}
