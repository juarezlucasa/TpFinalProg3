package entidades;

import exceptions.DAOException;
import utiles.TableManager;

public class Gasto {
	
	private int id;
	private String fecha;
	private int monto;
	private String descripcion;
	
	public Gasto(int id, String fecha, int monto, String descripcion) {
		this.setId(id);
		this.setFecha(fecha);
		this.setMonto(monto);
		this.setDescripcion(descripcion);
	}
	
	public Gasto(int id, int monto, String descripcion) {
		this.setId(id);
		this.setMonto(monto);
		this.setDescripcion(descripcion);
	}
	
/* ########################### INICIO DE GETTER Y SETTER ###############################*/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String toString() {
        return "Gasto{" +
                "Monto ='" + monto + '\'' +
                ", Descripcion='" + descripcion + '\'' +
                '}';
    }
	
	/* ########################### FIN DE GETTER Y SETTER ###############################*/
	public static void crearGastoTabla() throws DAOException {
		String sqlCreate = "CREATE TABLE GASTOS ( ID INTEGER IDENTITY, fecha date, monto INTEGER, descripcion VARCHAR(30))";
		String sqlDrop = "DROP TABLE GASTOS";
		TableManager tm = new TableManager();
		tm.createTable(sqlDrop);
		tm.createTable(sqlCreate);
		
	}
}
