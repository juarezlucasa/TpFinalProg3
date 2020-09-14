package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import entidades.Edificio;
import entidades.EdificioService;
import exceptions.DAOException;
import exceptions.ServicioException;

public class PanelListarEdificio extends PanelTemplateEdificio{

	public PanelListarEdificio(String titulo) {
		super(titulo);
		super.deshabilitarTextPanel();
		super.deshabilitarBotonOk();
		super.cambiarEtiquetaBoton("Modificar Edificio");
	}
	
	public void botonOK(Edificio edificio) {
		EdificioService s = new EdificioService();
		try {
			s.actualizarEdificio(edificio);
			JOptionPane.showMessageDialog(null, "Edificio modificado", "Confirmación",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ServicioException e) {
			JOptionPane.showMessageDialog(null, "No se pudo actualizar el edificio al sistema - "+ e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
