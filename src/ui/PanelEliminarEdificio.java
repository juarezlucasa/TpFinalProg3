package ui;


import entidades.Edificio;
import entidades.EdificioService;
import exceptions.ServicioException;

public class PanelEliminarEdificio extends PanelTemplateEdificio {

	public PanelEliminarEdificio(String titulo) {
		super(titulo);
		deshabilitarTextPanel();
		super.deshabilitarBotonOk();
		super.cambiarEtiquetaBoton("Eliminar Edificio");
	}
	
	public void botonOK(Edificio edificio) {
		EdificioService s = new EdificioService();
		try {
			s.eliminarEdificio(edificio.getIdEdificio());
			mostrarExito("Edificio eliminado");
		} catch (ServicioException e) {
			mostrarError("No se pudo eliminar el edificio",e.getMessage());
		}
		
	}

}
