package ui;

import javax.swing.JOptionPane;

import entidades.Edificio;
import entidades.EdificioService;
import exceptions.ServicioException;

public class PanelEliminarEdificio extends PanelListarEdificio {

	public PanelEliminarEdificio(String titulo) {
		super(titulo);
		super.deshabilitarTextPanel();
		super.deshabilitarBotonOk();
		super.cambiarEtiquetaBoton("Eliminar Edificio");
	}
	
	public void botonOK(Edificio edificio) {
		EdificioService s = new EdificioService();
		try {
			s.eliminarEdificio(edificio.getIdEdificio());
			JOptionPane.showMessageDialog(null, "Edificio eliminado", "Confirmación",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ServicioException e) {
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el edificio al sistema - "+ e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
