package ui;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidades.Expensa;
import entidades.ExpensaService;
import entidades.UnidadFuncional;
import entidades.UnidadFuncionalService;
import exceptions.CamposVaciosException;
import exceptions.ServicioException;

public class PanelLiquidarExpensas extends PanelTemplateUf {

	private JLabel labelTitulo;
	private JLabel labelTituloMontoAbonar;
	private JTextField textoAbonarExpensas = new JTextField(30);
	private Calendar calndr;
	static String aniomes = resolverMesAnio();

	public static String resolverMesAnio() {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int mes = localDate.getMonthValue();
		int anio = localDate.getYear();
		String aniomes = mes + "/" + anio;
		return aniomes;
	}

	public PanelLiquidarExpensas(String titulo) {
		super(titulo);
		initUI(titulo);
		super.cambiarEtiquetaBoton("Abonar Expensas");
		super.deshabilitarTextPanel();
		// calcularExpensas();
		crearTabla();
		chequearExpensasLiquidadas();
	}

	private void initUI(String titulo) {
		this.setLayout(null);
		labelTitulo = new JLabel();
		labelTitulo.setBounds(27, 250, 400, 30);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setText("Liquidacion de expensas");
		labelTitulo.setFont(new java.awt.Font("Arial", 1, 18));
		this.add(labelTitulo);

		JLabel labelMes = new JLabel();
		labelMes.setBounds(10, 270, 100, 30);
		labelMes.setText("Mes: " + aniomes);
		this.add(labelMes);

		labelTituloMontoAbonar = new JLabel();
		labelTituloMontoAbonar.setBounds(10, 130, 400, 30);
		labelTituloMontoAbonar.setText("Monto a Abonar");
		this.add(labelTituloMontoAbonar);

		this.add(textoAbonarExpensas);
		textoAbonarExpensas.setBounds(200, 130, 220, 30);

	}

	// Este metodo chequea si para el corriente periodo ya se liquidaron expensas
	// Da la opción de volver a liquidarlas, por ejemplo si se olvidó de cargar
	// algún
	// Gasto y se deben recalcular expensas.
	public void chequearExpensasLiquidadas() {
		ExpensaService s = new ExpensaService();
		ArrayList<Expensa> chequeoExpensas = new ArrayList();
		//System.out.println(aniomes);
		chequeoExpensas = s.listarExpensas(aniomes);
		if (chequeoExpensas.isEmpty()) {
			calcularExpensas();
			crearTabla();
		} else {
			int resultado = confirmarAccion(
					"Las expensas ya fueron liquidadas para el corriente período. ¿Desea volver a liquidarlas?");
			if (resultado == JOptionPane.YES_OPTION) {
				try {
					s.limpiarExpensa();
					calcularExpensas();
					crearTabla();
				} catch (ServicioException e) {
					mostrarError("Error al recalcular expensas", e.getMessage());
				}
			}

		}
	}

	public void calcularExpensas() {
		ExpensaService s = new ExpensaService();
		UnidadFuncionalService u = new UnidadFuncionalService();
		int gastos = s.calcularGastosTotales(); // CALCULO LA CANTIDAD DE GASTOS TOTALES EN LA BASE.
		int cantEdificios = s.calcularCantUf(); // CALCULO LA CANTIDAD DE UNIDADES FUNCINOALES DEL EDIFICIO.
		int montoExpensas = gastos / cantEdificios;
		int montoPagado = 0;
		ArrayList<UnidadFuncional> listadoUf = new ArrayList();
		listadoUf = u.listarUf();

		for (int i = 0; i < cantEdificios; i = i + 1) {
			//System.out.println(i);
			UnidadFuncional uf = new UnidadFuncional();
			uf = listadoUf.get(i); // Genero el objeto UnidadFuncional para armar el objeto Expensas, para luego
									// guardarlo en la tabla Expensas en la BD
			Expensa ex = new Expensa(uf.getIdEdificio(), uf.getDepto(), uf.getPropietario(), aniomes, montoExpensas,
					montoPagado);
			// Trato de guardar la expensa en la base de datos.
			try {
				s.crearExpensa(ex);
			} catch (ServicioException e1) {
				mostrarError("Error al liquidar expensas - ", e1.getMessage());
			}

		}

	}

	public String[] obtenerTitulosTabla() {
		String titulos[] = { "Edificio", "Departamento", "Propietario", "Monto a pagar", "Monto Abonado" };
		return titulos;
	}

	public String[][] obtenerDatosTabla() {
		ExpensaService s = new ExpensaService();
		ArrayList<Expensa> listadoEx = s.listarExpensas(aniomes);

		String matrizExpensas[][] = new String[listadoEx.size()][5];

		for (int i = 0; i < listadoEx.size(); i++) {
			matrizExpensas[i][0] = listadoEx.get(i).getIdEdificio() + "";
			matrizExpensas[i][1] = listadoEx.get(i).getDepto() + "";
			matrizExpensas[i][2] = listadoEx.get(i).getPropietario() + "";
			matrizExpensas[i][3] = listadoEx.get(i).getMonto() + "";
			matrizExpensas[i][4] = listadoEx.get(i).getMontoPagado() + "";
		}

		return matrizExpensas;
	}

	public void botonOK(UnidadFuncional uf) {
		int montoAbonar = Integer.parseInt(textoAbonarExpensas.getText());
		ExpensaService s = new ExpensaService();
		try {
			s.pagarExpensa(uf.getIdEdificio(), uf.getDepto(), aniomes, montoAbonar);
			mostrarExito("Expensas abonadas para el departmaneto " + uf.getDepto());
		} catch (ServicioException e1) {
			mostrarError("Error al abonar expensas al depto " + uf.getDepto(), e1.getMessage());
		}

	}

}
