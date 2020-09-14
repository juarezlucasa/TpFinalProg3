package ui;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entidades.Edificio;
import entidades.EdificioService;
import exceptions.CamposVaciosException;
import exceptions.EdificioNoEncontradoException;
import exceptions.ServicioException;

public abstract class PanelTemplateEdificio extends PanelTemplate {

	private JTextField textoIdentificadorEdificio = new JTextField(30);
	private JTextField textoDomicilio = new JTextField(30);
	private JTextField textoPisos = new JTextField(2);
	private JTextField textoDeptosPorPiso = new JTextField(2);
	private JLabel tituloLab1, tituloLab2, tituloLab3, tituloLab4;
	private JButton botonBuscar = new JButton("Buscar");

	public PanelTemplateEdificio(String titulo) {
		super(titulo);
		initUI(titulo);
	}

	public void botonOkStart() throws CamposVaciosException {

		if (textoIdentificadorEdificio.getText().isEmpty() || textoDomicilio.getText().isEmpty()
				|| textoPisos.getText().isEmpty() || textoDeptosPorPiso.getText().isEmpty()) {
			throw new CamposVaciosException("Campos vacios");
		}

		int idEdificio = Integer.parseInt(textoIdentificadorEdificio.getText());
		String domicilio = textoDomicilio.getText();
		int pisos = Integer.parseInt(textoPisos.getText());
		int deptoPorPiso = Integer.parseInt(textoDeptosPorPiso.getText());
		Edificio edificio = null;
		edificio = new Edificio(idEdificio, domicilio, pisos, deptoPorPiso);
		botonOK(edificio);

	}

	public abstract void botonOK(Edificio edificio);

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
				int idEdificio = Integer.parseInt(textoIdentificadorEdificio.getText());
				Edificio edificiobuscado = new Edificio();
				edificiobuscado = buscarEdificio(idEdificio);
				try {
					verificarEdificioBuscado(edificiobuscado);
				} catch (EdificioNoEncontradoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

				habilitarTextPanel();
				habilitarBotonOk();
				textoDomicilio.setText(edificiobuscado.getDireccion());
				textoPisos.setText(Integer.toString(edificiobuscado.getPisos()));
				textoDeptosPorPiso.setText(Integer.toString(edificiobuscado.getDeptosPorPiso()));

			}

			private Edificio buscarEdificio(int idEdificio) {
				EdificioService s = new EdificioService();
				Edificio edificioEncontrado = new Edificio();
				try {
					edificioEncontrado = s.consultarEdificio(idEdificio);
				} catch (ServicioException e) {
					e.printStackTrace();
				}
				return edificioEncontrado;
			}

			private void habilitarTextPanel() {
				textoDomicilio.setEnabled(true);
				textoPisos.setEnabled(true);
				textoDeptosPorPiso.setEnabled(true);
				habilitarBotonOk();
				//botonOk.setVisible(true);
			}

			private void chequearTextoVacio() throws CamposVaciosException {
				if (textoIdentificadorEdificio.getText().isEmpty()) {
					throw new CamposVaciosException("Campos vacios");
				}
			}

			private void verificarEdificioBuscado(Edificio edificiobuscado) throws EdificioNoEncontradoException {
				if (edificiobuscado.getIdEdificio() == 0) {
					throw new EdificioNoEncontradoException("Edificio no encontrado");
					
				}
			}

		});

	}

	public void deshabilitarTextPanel() {
		textoDomicilio.setEnabled(false);
		textoPisos.setEnabled(false);
		textoDeptosPorPiso.setEnabled(false);
		botonBuscar.setVisible(true);
		deshabilitarBotonOk();
		//botonOk.setVisible(false);
	}

	public int getIdEdificio() {
		return Integer.parseInt(textoIdentificadorEdificio.getText());
	}

}
