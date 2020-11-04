package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import entidades.Edificio;
import entidades.EdificioService;
import entidades.UnidadFuncional;
import entidades.UnidadFuncionalService;
import exceptions.CamposVaciosException;
import exceptions.ServicioException;
import exceptions.UfNoEncontradaException;

public abstract class PanelTemplateUf extends PanelTemplate {

	private JTextField textoIdEdificio = new JTextField(30);
	private JTextField textoDepartamento = new JTextField(30);
	private JTextField textoPropietario = new JTextField(30);
	private JLabel tituloLab1, tituloLab2, tituloLab3;

	public PanelTemplateUf(String titulo) {
		super(titulo);
		initUI(titulo);
	}

	public void botonOkStart() throws CamposVaciosException {

		if (textoIdEdificio.getText().isEmpty() || textoDepartamento.getText().isEmpty()
				|| textoPropietario.getText().isEmpty()) {
			throw new CamposVaciosException("Campos vacios");
		}

		try {
			int idEdificio = Integer.parseInt(textoIdEdificio.getText());
			String departamento = textoDepartamento.getText();
			String propietario = textoPropietario.getText();
			UnidadFuncional uf = new UnidadFuncional(idEdificio, departamento, propietario);
			botonOK(uf);
		} catch (NumberFormatException e) {
			mostrarError("Error en la carga", "El ID del edificio debe ser numérico");
		}

	}

	public abstract void botonOK(UnidadFuncional uf);

	public String[] obtenerTitulosTabla() {
		String[] titulos = { "ID Edificio", "Departamento", "Propietario" };
		return titulos;
	}

	public String[][] obtenerDatosTabla() {
		UnidadFuncionalService s = new UnidadFuncionalService();
		ArrayList<UnidadFuncional> listadoUf = s.listarUf();
		String matrizInfo[][] = new String[listadoUf.size()][3];
		for (int i = 0; i < listadoUf.size(); i++) {
			matrizInfo[i][0] = listadoUf.get(i).getIdEdificio() + "";
			matrizInfo[i][1] = listadoUf.get(i).getDepto() + "";
			matrizInfo[i][2] = listadoUf.get(i).getPropietario() + "";
		}

		return matrizInfo;
	}

	public void moverListaAJtext(JTable tabla) {
		int selectedRowIndex = tabla.getSelectedRow();
		textoIdEdificio.setText(tabla.getValueAt(selectedRowIndex, 0).toString());
		textoDepartamento.setText(tabla.getValueAt(selectedRowIndex, 1).toString());
		textoPropietario.setText(tabla.getValueAt(selectedRowIndex, 2).toString());

	}

	private void initUI(String titulo) {
		this.setLayout(null);
		this.setVisible(true);
		/* Cuadros de Texto */

		this.add(textoIdEdificio);
		textoIdEdificio.setBounds(200, 10, 220, 30);

		this.add(textoDepartamento);
		textoDepartamento.setBounds(200, 50, 220, 30);

		this.add(textoPropietario);
		textoPropietario.setBounds(200, 90, 220, 30);

		/* Etiquetas */
		tituloLab1 = new JLabel("Edificio");
		tituloLab1.setBounds(10, 10, 200, 30);
		this.add(tituloLab1);

		tituloLab2 = new JLabel("Departamento");
		tituloLab2.setBounds(10, 50, 200, 30);
		this.add(tituloLab2);

		tituloLab3 = new JLabel("Propietario");
		tituloLab3.setBounds(10, 90, 200, 30);
		this.add(tituloLab3);
	}

	public void deshabilitarTextPanel() {
		textoIdEdificio.setEnabled(false);
		textoDepartamento.setEnabled(false);
		textoPropietario.setEnabled(false);
		deshabilitarBotonOk();
	}

	public void habilitarJText() {
		textoIdEdificio.setEnabled(false);
		textoDepartamento.setEnabled(true);
		textoPropietario.setEnabled(true);
	}

}
