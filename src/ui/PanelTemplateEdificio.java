package ui;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidades.Edificio;
import entidades.EdificioService;
import entidades.Expensa;
import exceptions.CamposVaciosException;
import exceptions.EdificioNoEncontradoException;
import exceptions.ServicioException;

public abstract class PanelTemplateEdificio extends PanelTemplate {

	private JTextField textoIdentificadorEdificio = new JTextField(30);
	private JTextField textoDomicilio = new JTextField(30);
	private JTextField textoPisos = new JTextField(2);
	private JTextField textoDeptosPorPiso = new JTextField(2);
	private JLabel tituloLab1, tituloLab2, tituloLab3, tituloLab4;

	public PanelTemplateEdificio(String titulo) {
		super(titulo);
		initUI(titulo);
	}

	// IMPLEMENTO EL METODO botonOkStart (Abstracto en PanelTemplate)
	// En esta instancia creo el objeto Edificio con los JTextFields
	// E invoco al botonOk, que en esta clase es abstracto.
	// Lo implementan los paneles AgregarEdficio / EliminarEdificio / ListarEdificio
	// Con su comportamiento correspondiente.
	public void botonOkStart() throws CamposVaciosException {

		if (textoIdentificadorEdificio.getText().isEmpty() || textoDomicilio.getText().isEmpty()
				|| textoPisos.getText().isEmpty() || textoDeptosPorPiso.getText().isEmpty()) {
			throw new CamposVaciosException("Campos vacios");
		}

		try {
			int idEdificio = Integer.parseInt(textoIdentificadorEdificio.getText());
			String domicilio = textoDomicilio.getText();
			int pisos = Integer.parseInt(textoPisos.getText());
			int deptoPorPiso = Integer.parseInt(textoDeptosPorPiso.getText());
			Edificio edificio = null;
			edificio = new Edificio(idEdificio, domicilio, pisos, deptoPorPiso);
			botonOK(edificio);
			
		} catch (NumberFormatException e) {
			mostrarError("Error en la carga", "Tanto el Id del edificio como los departamentos tienen que ser numeros");
		}

	}

	public abstract void botonOK(Edificio edificio);

	public String[] obtenerTitulosTabla() {
		String[] titulos = { "ID Depto", "Domicilio", "Pisos", "DeptoPorPisos" };
		return titulos;
	}

	public String[][] obtenerDatosTabla() {
		EdificioService s = new EdificioService();
		ArrayList<Edificio> listadoEdi = s.listarEdificios();
		String matrizInfo[][] = new String[listadoEdi.size()][4];
		for (int i = 0; i < listadoEdi.size(); i++) {
			matrizInfo[i][0] = listadoEdi.get(i).getIdEdificio() + "";
			matrizInfo[i][1] = listadoEdi.get(i).getDireccion() + "";
			matrizInfo[i][2] = listadoEdi.get(i).getPisos() + "";
			matrizInfo[i][3] = listadoEdi.get(i).getDeptosPorPiso() + "";
		}

		return matrizInfo;
	}

	public void moverListaAJtext(JTable tabla) {
		int selectedRowIndex = tabla.getSelectedRow();
		textoIdentificadorEdificio.setText(tabla.getValueAt(selectedRowIndex, 0).toString());
		textoDomicilio.setText(tabla.getValueAt(selectedRowIndex, 1).toString());
		textoPisos.setText(tabla.getValueAt(selectedRowIndex, 2).toString());
		textoDeptosPorPiso.setText(tabla.getValueAt(selectedRowIndex, 3).toString());

	}

	private void initUI(String titulo) {
		this.setLayout(null);
		this.setVisible(true);
		/* Cuadros de Texto */
		this.add(textoIdentificadorEdificio);
		textoIdentificadorEdificio.setBounds(200, 10, 220, 30);

		this.add(textoDomicilio);
		textoDomicilio.setBounds(200, 50, 220, 30);

		this.add(textoPisos);
		textoPisos.setBounds(200, 90, 220, 30);

		this.add(textoDeptosPorPiso);
		textoDeptosPorPiso.setBounds(200, 130, 220, 30);

		/* Etiquetas */

		tituloLab1 = new JLabel("ID Edificio");
		tituloLab1.setBounds(10, 10, 200, 30);
		this.add(tituloLab1);

		tituloLab2 = new JLabel("Domicilio");
		tituloLab2.setBounds(10, 50, 200, 30);
		this.add(tituloLab2);

		tituloLab3 = new JLabel("Cantidad de pisos");
		tituloLab3.setBounds(10, 90, 200, 30);
		this.add(tituloLab3);

		tituloLab4 = new JLabel("Departamentos por pisos");
		tituloLab4.setBounds(10, 130, 200, 30);
		this.add(tituloLab4);
	}

	public void deshabilitarTextPanel() {
		textoIdentificadorEdificio.setEnabled(false);
		textoDomicilio.setEnabled(false);
		textoPisos.setEnabled(false);
		textoDeptosPorPiso.setEnabled(false);
	}

	public void habilitarJText() {
		textoIdentificadorEdificio.setEnabled(false);
		textoDomicilio.setEnabled(true);
		textoPisos.setEnabled(true);
		textoDeptosPorPiso.setEnabled(true);
	}

	public int getIdEdificio() {
		return Integer.parseInt(textoIdentificadorEdificio.getText());
	}

}
