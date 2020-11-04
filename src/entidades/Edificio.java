package entidades;

import exceptions.DAOException;
import utiles.TableManager;

public class Edificio {
	
	private int idEdificio;
	private String direccion;
	private int pisos;
	private int deptosPorPiso;
	
	public Edificio(int idEdificio, String direccion, int pisos, int deptosPorPiso) {
		this.idEdificio=idEdificio;
		this.direccion=direccion;
		this.pisos=pisos;
		this.deptosPorPiso=deptosPorPiso;
	}
	
	public Edificio(int idEdificio) {
		this.idEdificio=idEdificio;
	}
	
	
/* ########################### INICIO DE GETTER Y SETTER ###############################*/
	
	public Edificio() {
	}

	public int getIdEdificio() {
		return idEdificio;
	}

	public void setIdEdificio(int idEdificio) {
		this.idEdificio = idEdificio;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getPisos() {
		return pisos;
	}
	public void setPisos(int pisos) {
		this.pisos = pisos;
	}
	public int getDeptosPorPiso() {
		return deptosPorPiso;
	}
	public void setDeptosPorPiso(int deptosPorPiso) {
		this.deptosPorPiso = deptosPorPiso;
	}
	
    public String toString() {
        return "Edificio{" +
                "Id Edificio='" + idEdificio + '\'' +
                ", Direccion='" + direccion + '\'' +
                ", Pisos='" + pisos + '\'' +
                ", Deparamentos por pisos='" + deptosPorPiso + '\'' +
                '}';
    }
	
/* ########################### FIN DE GETTER Y SETTER ###############################*/
	
	public static void grabarEdificio() throws DAOException {
		String sqlCreate = "CREATE TABLE edificios ( id_edificio INTEGER IDENTITY, domicilio VARCHAR(256), pisos VARCHAR(256), dep_por_pisos VARCHAR(10))";
		String sqlDrop = "DROP TABLE edificios";
		TableManager tm = new TableManager();
		tm.dropTable(sqlDrop);
		tm.createTable(sqlCreate);
		
	}
	
	
}
