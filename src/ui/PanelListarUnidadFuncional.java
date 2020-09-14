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
import entidades.UnidadFuncional;
import entidades.UnidadFuncionalService;
import exceptions.DAOException;
import exceptions.ServicioException;

public class PanelListarUnidadFuncional extends PanelTemplateUf  {

	public PanelListarUnidadFuncional(String titulo) {
		super(titulo);
		super.deshabilitarTextPanel();
		super.deshabilitarBotonOk();
		cambiarEtiquetaBoton("Modificar UF");
	}
	
	public void botonOK(UnidadFuncional uf) {
		UnidadFuncionalService s = new UnidadFuncionalService();
		try {
			s.actualizarUf(uf);
			JOptionPane.showMessageDialog(null, "Se Modificó la unidad funcional con éxito", "Confirmación",
					JOptionPane.INFORMATION_MESSAGE);
			super.deshabilitarTextPanel();
		} catch (ServicioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
