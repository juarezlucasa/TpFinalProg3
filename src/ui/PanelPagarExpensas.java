package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entidades.ExpensaService;
import exceptions.CamposVaciosException;
import exceptions.DAOException;
import exceptions.ServicioException;

public class PanelPagarExpensas extends PanelTemplate {

	private JTextField textoIdEdificio;
	private JTextField textoDepto;
	private JTextField textoPeriodo;
	private JTextField textoMonto;
	
	public PanelPagarExpensas(String titulo) {
		super(titulo);
		initUI(titulo);
		cambiarEtiquetaBoton("Pagar Expensas");
	}
	

	public void initUI(String titulo) {
		
		this.setLayout(null);
		this.setVisible(true);
		
		/*Etiquetas y Textos*/
		JLabel tituloIDEdificio = new JLabel("ID Edificio");
		tituloIDEdificio.setBounds(10, 10, 200, 30);
		this.add(tituloIDEdificio);
		textoIdEdificio = new JTextField();
		this.add(textoIdEdificio);
		textoIdEdificio.setBounds(200,10,200,30);
		
		JLabel tituloDepto = new JLabel("Departamento");
		tituloDepto.setBounds(10, 50, 200, 30);
		this.add(tituloDepto);
		textoDepto = new JTextField();
		this.add(textoDepto);
		textoDepto.setBounds(200,50,200,30);
		
		JLabel tituloPeriodo = new JLabel("Periodo de pago: MM/YYYY");
		tituloPeriodo.setBounds(10, 90, 200, 30);
		this.add(tituloPeriodo);
		textoPeriodo = new JTextField();
		this.add(textoPeriodo);
		textoPeriodo.setBounds(200,90,200,30);
		
		JLabel tituloMonto = new JLabel("Monto a abonar");
		tituloMonto.setBounds(10, 130, 200, 30);
		this.add(tituloMonto);
		textoMonto = new JTextField();
		this.add(textoMonto);
		textoMonto.setBounds(200,130,200,30);

	}

	public void botonOkStart() throws CamposVaciosException {
		
		if (textoIdEdificio.getText().isEmpty() || textoDepto.getText().isEmpty() || textoPeriodo.getText().isEmpty() || textoMonto.getText().isEmpty()) {
			  throw new CamposVaciosException("Campos vacios");	
			}
		
		int idEdificio = Integer.parseInt(textoIdEdificio.getText());
		String depto = textoDepto.getText();
		String periodo = textoPeriodo.getText();
		int montoAbonar= Integer.parseInt(textoMonto.getText());
		ExpensaService s = new ExpensaService();
		try {
			s.pagarExpensa(idEdificio, depto, periodo, montoAbonar);
			JOptionPane.showMessageDialog(null, "Expensas abonadas para el departamento "+depto, "Confirmación",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ServicioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
