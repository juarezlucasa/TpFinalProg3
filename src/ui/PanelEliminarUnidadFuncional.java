package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entidades.Edificio;
import entidades.EdificioService;
import entidades.UnidadFuncional;
import entidades.UnidadFuncionalService;
import exceptions.DAOException;
import exceptions.ServicioException;

public class PanelEliminarUnidadFuncional extends PanelTemplateUf {

	public PanelEliminarUnidadFuncional(String titulo) {
		super(titulo);
		super.deshabilitarTextPanel();
		super.deshabilitarBotonOk();
		cambiarEtiquetaBoton("Eliminar UF");
	}
	
	public void botonOK(UnidadFuncional uf) {
		UnidadFuncionalService s = new UnidadFuncionalService();
		try {
			s.eliminarUf(uf.getIdEdificio(), uf.getDepto());
			JOptionPane.showMessageDialog(null, "Se eliminó el edificio con éxito", "Confirmación",
					JOptionPane.INFORMATION_MESSAGE);
			super.deshabilitarTextPanel();
		} catch (ServicioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
		
}
