package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entidades.Edificio;
import entidades.EdificioService;
import exceptions.DAOException;
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
			JOptionPane.showMessageDialog(null, "Se agregó el edificio al sistema", "Confirmación",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ServicioException e) {
			JOptionPane.showMessageDialog(null, "No se pudo agregar el edificio al sistema - "+ e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	}