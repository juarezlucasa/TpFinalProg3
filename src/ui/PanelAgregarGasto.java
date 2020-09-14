package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entidades.Edificio;
import entidades.EdificioService;
import entidades.Gasto;
import entidades.GastoService;
import exceptions.CamposVaciosException;
import exceptions.DAOException;
import exceptions.ServicioException;

public class PanelAgregarGasto extends PanelTemplate {

	private static JTextField textoMontoGasto = new JTextField(30);
	private static JTextField textoDescripcion = new JTextField(100);
	private static JLabel tituloMonto;
	private static JLabel tituloDescripcion;

	public PanelAgregarGasto(String titulo) {
		super(titulo);
		initUI(titulo);
		cambiarEtiquetaBoton("Cargar Gasto");
	}

	public void initUI(String titulo) {
		
		this.setLayout(null);
		this.setVisible(true);

		/* Etiquetas y Textos */
		tituloMonto = new JLabel("Monto del gasto");
		tituloMonto.setBounds(10, 10, 200, 30);
		this.add(tituloMonto);
		this.add(textoMontoGasto);
		textoMontoGasto.setBounds(150, 10, 200, 30);

		tituloDescripcion = new JLabel("Descripcion del gasto");
		tituloDescripcion.setBounds(10, 50, 200, 30);
		this.add(tituloDescripcion);
		this.add(textoDescripcion);
		textoDescripcion.setBounds(150, 50, 200, 30);
	}
	
	public void botonOkStart() throws CamposVaciosException {
		
		if (textoMontoGasto.getText().isEmpty() || textoDescripcion.getText().isEmpty()) {
			  throw new CamposVaciosException("Campos vacios");	
			}
		
		GastoService s = new GastoService();
		int monto = Integer.parseInt(textoMontoGasto.getText());
		String descripcion = textoDescripcion.getText();
		Gasto gasto = new Gasto(monto, descripcion);
		try {
			s.crearGasto(gasto);
			JOptionPane.showMessageDialog(null, "Se agregó el Gasto al sistema", "Confirmación",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ServicioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
