package ui;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
	private JButton botonBuscar = new JButton("Buscar");

	public PanelTemplateUf(String titulo) {
		super(titulo);
		initUI(titulo);
	}

	public void botonOkStart() throws CamposVaciosException {

		if (textoIdEdificio.getText().isEmpty() || textoDepartamento.getText().isEmpty()
				|| textoPropietario.getText().isEmpty()) {
			throw new CamposVaciosException("Campos vacios");
		}

		int idEdificio = Integer.parseInt(textoIdEdificio.getText());
		String departamento = textoDepartamento.getText();
		String propietario = textoPropietario.getText();
		UnidadFuncional uf = new UnidadFuncional(idEdificio, departamento, propietario);
		botonOK(uf);

	}

	public abstract void botonOK(UnidadFuncional uf);

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

		botonBuscar = new JButton("Buscar");
		botonBuscar.setBounds(50, 200, 100, 30);
		this.add(botonBuscar);
		botonBuscar.setVisible(false);
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					chequearTextoVacio();
				} catch (CamposVaciosException e1) {
					JOptionPane.showMessageDialog(null, "Error en la busqueda. " + e1.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				
				int idABuscar = Integer.parseInt(textoIdEdificio.getText());
				String deptoABuscar = textoDepartamento.getText();
				UnidadFuncional uf = new UnidadFuncional();
				uf = buscarUf(idABuscar,deptoABuscar);
				try {
					verificarUfBuscado(uf);
				} catch (UfNoEncontradaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				textoPropietario.setText(uf.getPropietario());
				habilitarTextPanel();
				habilitarBotonOk();

			}
			
			private void verificarUfBuscado(UnidadFuncional uf)  throws UfNoEncontradaException {
				if (uf.getIdEdificio() == 0) {
					throw new UfNoEncontradaException("Unidad Funcional no encontrada");
				}
			}

			private UnidadFuncional buscarUf(int idEdficio, String depto) {
				UnidadFuncional uf = new UnidadFuncional();
				UnidadFuncionalService s = new UnidadFuncionalService();
				try {
					uf = s.consultarUf(idEdficio, depto);
				} catch (ServicioException e) {
					e.printStackTrace();
				}
				return uf;
			}

			private void chequearTextoVacio() throws CamposVaciosException {
				if (textoIdEdificio.getText().isEmpty() || textoDepartamento.getText().isEmpty()) {
					throw new CamposVaciosException("Campos vacios");
				}

			}

			private void habilitarTextPanel() {
				textoPropietario.setEnabled(true);
				deshabilitarBotonOk();
			}
		});

	}

	public void deshabilitarTextPanel() {
		textoPropietario.setEnabled(false);
		botonBuscar.setVisible(true);
		deshabilitarBotonOk();
	}

}
