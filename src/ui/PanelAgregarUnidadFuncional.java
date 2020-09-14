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
import entidades.UnidadFuncional;
import entidades.UnidadFuncionalService;
import exceptions.DAOException;
import exceptions.ServicioException;

public class PanelAgregarUnidadFuncional extends PanelTemplateUf {

	public PanelAgregarUnidadFuncional(String titulo) {
		super(titulo);
		cambiarEtiquetaBoton("Agregar UF");
	}

	public void botonOK(UnidadFuncional uf) {
		UnidadFuncionalService s = new UnidadFuncionalService();
		try {
			s.crearUf(uf);
			JOptionPane.showMessageDialog(null, "Se agregó el departamento al sistema", "Confirmación",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ServicioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}