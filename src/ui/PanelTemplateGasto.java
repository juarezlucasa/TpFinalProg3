package ui;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import entidades.Gasto;
import entidades.GastoService;
import exceptions.CamposVaciosException;
import exceptions.ServicioException;

public abstract class PanelTemplateGasto extends PanelTemplate {

	private static JTextField TextoidMonto = new JTextField(30);
	private static JTextField textoMontoGasto = new JTextField(30);
	private static JTextField textoDescripcion = new JTextField(100);
	private static JLabel tituloId;
	private static JLabel tituloMonto;
	private static JLabel tituloDescripcion;

	public PanelTemplateGasto(String titulo) {
		super(titulo);
		initUI(titulo);
	}
	
	public abstract void botonOk(Gasto gasto);

	public void initUI(String titulo) {

		this.setLayout(null);
		this.setVisible(true);

		/* Etiquetas y Textos */
		tituloId = new JLabel("ID del Gasto");
		tituloId.setBounds(10, 10, 200, 30);
		this.add(tituloId);
		this.add(TextoidMonto);
		TextoidMonto.setBounds(150, 10, 200, 30);

		tituloMonto = new JLabel("Monto del gasto");
		tituloMonto.setBounds(10, 50, 200, 30);
		this.add(tituloMonto);
		this.add(textoMontoGasto);
		textoMontoGasto.setBounds(150, 50, 200, 30);

		tituloDescripcion = new JLabel("Descripcion del gasto");
		tituloDescripcion.setBounds(10, 90, 200, 30);
		this.add(tituloDescripcion);
		this.add(textoDescripcion);
		textoDescripcion.setBounds(150, 90, 200, 30);
	}

	// IMPLEMENTO EL METODO botonOkStart (Abstracto en PanelTemplate)
	// En esta instancia creo el objeto Gasto con los JTextFields
	// E invoco al botonOk, que en esta clase es abstracto.
	// Lo implementan los paneles AgregarGasto / EliminarGasto / ListarGasto
	// Con su comportamiento correspondiente.
	public void botonOkStart() throws CamposVaciosException {

		if (TextoidMonto.getText().isEmpty() || textoMontoGasto.getText().isEmpty()
				|| textoDescripcion.getText().isEmpty()) {
			throw new CamposVaciosException("Campos vacios");
		}

		GastoService s = new GastoService();
		try {
			int id = Integer.parseInt(TextoidMonto.getText());
			int monto = Integer.parseInt(textoMontoGasto.getText());
			String descripcion = textoDescripcion.getText();
			Gasto gasto = new Gasto(id, monto, descripcion);
			botonOk(gasto);
			limpiarJText();
		}
		catch (NumberFormatException e) {
			mostrarError("Error en la carga", "Tanto el Id del gasto como el monto tienen que ser de tipo numérico");
		}
	}


	public String[] obtenerTitulosTabla() {
		String[] titulos = { "ID", "Fecha", "Descripcion", "Monto" };
		return titulos;
	}

	public String[][] obtenerDatosTabla() {
		GastoService s = new GastoService();
		ArrayList<Gasto> listadoGasto = s.listarGasto();
		String matrizInfo[][] = new String[listadoGasto.size()][4];
		for (int i = 0; i < listadoGasto.size(); i++) {
			matrizInfo[i][0] = listadoGasto.get(i).getId() + "";
			matrizInfo[i][1] = listadoGasto.get(i).getFecha() + "";
			matrizInfo[i][2] = listadoGasto.get(i).getDescripcion() + "";
			matrizInfo[i][3] = listadoGasto.get(i).getMonto() + "";
		}

		return matrizInfo;
	}

	public void moverListaAJtext(JTable tabla) {
		int selectedRowIndex = tabla.getSelectedRow();
		TextoidMonto.setText(tabla.getValueAt(selectedRowIndex, 0).toString());
		textoMontoGasto.setText(tabla.getValueAt(selectedRowIndex, 3).toString());
		textoDescripcion.setText(tabla.getValueAt(selectedRowIndex, 2).toString());

	}

	public void deshabilitarTextPanel() {
		TextoidMonto.setEnabled(false);
		textoMontoGasto.setEnabled(false);
		textoDescripcion.setEnabled(false);
	}
	
	public void habilitarJText() {
		TextoidMonto.setEnabled(true);
		textoMontoGasto.setEnabled(true);
		textoDescripcion.setEnabled(true);
	}
		
	public void limpiarJText() {
		TextoidMonto.setText(null);
		textoMontoGasto.setText(null);
		textoDescripcion.setText(null);
	}
}
