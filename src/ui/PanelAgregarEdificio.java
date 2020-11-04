package ui;

import entidades.Edificio;
import entidades.EdificioService;
import exceptions.ServicioException;

public class PanelAgregarEdificio extends PanelTemplateEdificio {

	
	public PanelAgregarEdificio(String titulo) {
		super(titulo);
		super.cambiarEtiquetaBoton("Agregar Edificio");
	}

	public void botonOK(Edificio edificio) {
		EdificioService s = new EdificioService();
		try {
			s.crearEdificio(edificio);
			mostrarExito("Se agrego el edificio al sistema");
		} catch (ServicioException e) {
			mostrarError("No se pudo agregar el edificio al sistema", e.getMessage());
		}
		
	}

	}