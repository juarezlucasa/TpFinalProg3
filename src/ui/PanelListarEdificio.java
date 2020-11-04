package ui;


import entidades.Edificio;
import entidades.EdificioService;
import exceptions.ServicioException;

public class PanelListarEdificio extends PanelTemplateEdificio{

	public PanelListarEdificio(String titulo) {
		super(titulo);
		super.deshabilitarBotonOk();
		super.habilitarJText();
		super.cambiarEtiquetaBoton("Modificar Edificio");
	}
	
	public void botonOK(Edificio edificio) {
		EdificioService s = new EdificioService();
		try {
			s.actualizarEdificio(edificio);
			mostrarExito("Edificio modificado");
		} catch (ServicioException e) {
			mostrarError("Error al actualizar el edificio",e.getMessage());
		}
		
	}

}
