package ui;

import java.awt.ScrollPane;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import entidades.Expensa;
import entidades.ExpensaService;
import entidades.UnidadFuncional;
import interfaces.ExpensaDao;

public class PanelLiquidarExpensas extends JPanel {
	
	
	private JLabel labelTitulo;
	private Calendar calndr;
	JTable tablaExpensas;
	JScrollPane miScroll;
	
	Date date = new Date();
	LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	int mes = localDate.getMonthValue();
	int anio = localDate.getYear();
	String aniomes = mes+"/"+anio;
	
	public PanelLiquidarExpensas() {
		inicializarComponentes();
		crearTabla();
	}
	
	private void crearTabla() {
		String titulos[]= {"Departamento","Propietario","Monto a pagar","Monto Abonado"};
		String datos[][]=obtenerDatosExpensas();
		tablaExpensas=new JTable(datos,titulos);
		tablaExpensas.setBounds(10, 50, 400, 800);
		miScroll.setViewportView(tablaExpensas);
		
	}

	
	private String[][] obtenerDatosExpensas() {
		
		ExpensaService s= new ExpensaService();
		ArrayList<Expensa> listadoEx = s.listarExpensas(aniomes);
		
		String matrizInfo[][] = new String[listadoEx.size()][4];
		
		for (int i=0; i<listadoEx.size();i++) {
			matrizInfo[i][0]=listadoEx.get(i).getDepto()+"";
			matrizInfo[i][1]=listadoEx.get(i).getPropietario()+"";
			matrizInfo[i][2]=listadoEx.get(i).getMonto()+"";
			matrizInfo[i][3]=listadoEx.get(i).getMontoPagado()+"";
		}
		
		return matrizInfo;
	}

	private void inicializarComponentes() {
		this.setLayout(null);
		labelTitulo = new JLabel();
		labelTitulo.setBounds(27,11,400,30);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setText("Liquidacion de expensas");
		labelTitulo.setFont(new java.awt.Font("Arial",1,18));
		this.add(labelTitulo);
		
		
		JLabel labelMes = new JLabel();
		labelMes.setBounds(10,30,100,30);
		labelMes.setText("Mes: " + aniomes);
		this.add(labelMes);
		
		miScroll=new JScrollPane();
		miScroll.setBounds(27,72,379,130);
		this.add(miScroll);
		
	}

}
