package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import entidades.Edificio;
import exceptions.CamposVaciosException;

public abstract class PanelTemplate extends JPanel {
	
	private JButton botonOk;
	private JButton botonCancel;

	public PanelTemplate(String titulo) {
		init(titulo);
	}

	private void init(String titulo) {
		this.setLayout(null);
		this.setVisible(true);
		botonOk = new JButton("OK");
		botonOk.setBounds(10, 300, 150, 30);
		botonCancel = new JButton("Cancel");
		botonCancel.setBounds(200, 300, 100, 30);
		this.add(botonOk);
		this.add(botonCancel);
		botonOk.setVisible(true);
		botonCancel.setVisible(true);
		botonOk.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				try {
					botonOkStart();
				} catch (CamposVaciosException e1) {
					JOptionPane.showMessageDialog(null, "Error en la carga. "+ e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				}
		});
		
		botonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				}
		});
	}

	public abstract void botonOkStart() throws CamposVaciosException;
	
	public void deshabilitarBotonOk() {
		botonOk.setEnabled(false);
	}
	
	public void habilitarBotonOk() {
		botonOk.setEnabled(true);
	}
	
	public void cambiarEtiquetaBoton(String newTitle) {
		botonOk.setText(newTitle);
	}

}
