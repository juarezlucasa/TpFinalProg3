package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import exceptions.CamposVaciosException;

public abstract class PanelTemplate extends JPanel {

	private JButton botonOk;
	private JButton botonCancel;
	private JTable tabla;
	private JScrollPane miScroll;

	public PanelTemplate(String titulo) {
		init(titulo);
		crearTabla();
	}

	// INICIO DE METODOS ABSTRACTOS DEFINIDOS
	public abstract void botonOkStart() throws CamposVaciosException;

	public abstract String[] obtenerTitulosTabla();

	public abstract String[][] obtenerDatosTabla();

	public abstract void moverListaAJtext(JTable tabla);

	public abstract void habilitarJText();

	// FIN DE METODOS ABSTRACTOS DEFINIDOS

	private void init(String titulo) {
		this.setLayout(null);
		this.setVisible(true);
		// Inicializo boton OK//
		botonOk = new JButton("OK");
		botonOk.setBounds(10, 200, 150, 30);

		// Inicializo boton Cancel//
		botonCancel = new JButton("Cancel");
		botonCancel.setBounds(200, 200, 100, 30);

		// Agrego botones Ok y Cancel al Panel
		this.add(botonOk);
		this.add(botonCancel);
		botonOk.setVisible(true);
		botonCancel.setVisible(true);

		// Agrego el ActionListener al boton OK
		botonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					botonOkStart();
					crearTabla();
				} catch (CamposVaciosException e1) {
					mostrarError("Error en la carga ", e1.getMessage());
				}
			}
		});

		// Agrego el ActionListener al boton Cancel. Siempre que se toque este boton, se
		// cierra el programa.
		botonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Instancio el Scroll de la tabla para agregarlo al panel
		miScroll = new JScrollPane();
		miScroll.setBounds(10, 300, 550, 130);
		this.add(miScroll);
	}

	// METODO PARA CREAR LA TABLA EN EL PANEL
	public void crearTabla() {
		String titulos[] = obtenerTitulosTabla();
		String datos[][] = obtenerDatosTabla();

		tabla = new JTable(datos, titulos);
		tabla.setBounds(300, 300, 1000, 1000);
		miScroll.setViewportView(tabla);
		tabla.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				pasarJtableAJtext(evt);
			}
		});

	}

	// Metodo que se dispara cuando se hace click en una fila de la tabla.
	// Habilita el botonOK del panel donde me encuentre y muevo los datos
	// De la tabla hacia los JTextFields según panel.
	private void pasarJtableAJtext(java.awt.event.MouseEvent evt) {
		habilitarBotonOk();
		moverListaAJtext(tabla);
	}

	public void deshabilitarBotonOk() {
		botonOk.setEnabled(false);
	}

	public void habilitarBotonOk() {
		botonOk.setEnabled(true);
	}

	// Cambio la etiqueta del boton OK según el panel donde esté
	public void cambiarEtiquetaBoton(String newTitle) {
		botonOk.setText(newTitle);
	}

	public void mostrarExito(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Confirmación", JOptionPane.INFORMATION_MESSAGE);
	}

	public void mostrarError(String mensaje, String error) {
		JOptionPane.showMessageDialog(null, mensaje + " - " + error, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	public int confirmarAccion(String pregunta) {
		int resultado = JOptionPane.showConfirmDialog(this, pregunta, "Confirmación", JOptionPane.YES_NO_OPTION);
		if (resultado == JOptionPane.YES_OPTION) {
			return JOptionPane.YES_OPTION;
		}
		else {
			return JOptionPane.NO_OPTION;
		}
	}

}
