package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import entidades.Expensa;

public class MiFrame extends JFrame implements ActionListener {

	private JMenuItem m11, m12, m13, m21, m22, m23, m31,m32,m33,m41,m42;
	private JMenuBar mb;
	public MiFrame(String titulo) {
		super(titulo);
		initUI();
	}

	private void initUI() {
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mb = new JMenuBar();
		JMenu m1 = new JMenu("Edificio");
		JMenu m2 = new JMenu("Unidad funcional");
		JMenu m3 = new JMenu("Gastos");
		JMenu m4 = new JMenu("Expensas");
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		mb.add(m4);
		
		m11 = new JMenuItem("Agregar edificio");
		m11.addActionListener(this);
		m12 = new JMenuItem("Borrar edificio");
		m12.addActionListener(this);
		m13 = new JMenuItem("Modificar edificio");
		m13.addActionListener(this);
		
		m21 = new JMenuItem("Agregar unidad");
		m21.addActionListener(this);
		m22 = new JMenuItem("Borrar unidad");
		m22.addActionListener(this);
		m23 = new JMenuItem("Modificar unidad");
		m23.addActionListener(this);
		
		m31 = new JMenuItem("Cargar Gastos");
		m31.addActionListener(this);
		m32 = new JMenuItem("Eliminar Gastos");
		m32.addActionListener(this);
		m33 = new JMenuItem("Modificar Gastos");
		m33.addActionListener(this);
		
		m41 = new JMenuItem("Liquidar/Abonar Expensas");
		m41.addActionListener(this);
		
		
		m1.add(m11);
		m1.add(m12);
		m1.add(m13);
		m2.add(m21);
		m2.add(m22);
		m2.add(m23);
		m3.add(m31);
		m3.add(m32);
		m3.add(m33);
		m4.add(m41);
		add(BorderLayout.NORTH, mb);
		this.setVisible(true);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
	
	public void limpiarFrame() {
		getContentPane().removeAll(); //or .remove(previousPanel);
		add(BorderLayout.NORTH, mb);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == m11) {
			limpiarFrame();
			getContentPane().add(new PanelAgregarEdificio("Pepe"));
		}
		if (e.getSource() == m12) {
			limpiarFrame();
			getContentPane().add(new PanelEliminarEdificio("Pepe1"));
		}
		if (e.getSource() == m13) {
			limpiarFrame();
			getContentPane().add(new PanelListarEdificio("Pepe2"));
		}
		
		if (e.getSource() == m21) {
			limpiarFrame();
			getContentPane().add(new PanelAgregarUnidadFuncional("Pepe2"));
		}
		
		if (e.getSource() == m22) {
			limpiarFrame();
			getContentPane().add(new PanelEliminarUnidadFuncional("Pepe2"));
		}
		if (e.getSource() == m23) {
			limpiarFrame();
			getContentPane().add(new PanelListarUnidadFuncional("Pepe2"));
		}
		if (e.getSource() == m31) {
			limpiarFrame();
			getContentPane().add(new PanelAgregarGasto("Pepe2"));
		}
		if (e.getSource() == m32) {
			limpiarFrame();
			getContentPane().add(new PanelEliminarGasto("Pepe2"));
		}
		if (e.getSource() == m33) {
			limpiarFrame();
			getContentPane().add(new PanelListarGasto("Pepe2"));
		}
		if (e.getSource() == m41) {
			limpiarFrame();
			getContentPane().add(new PanelLiquidarExpensas("Pepe"));
		}
		setVisible(true);
	}
}
