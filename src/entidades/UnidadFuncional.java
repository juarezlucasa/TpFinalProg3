package entidades;

import exceptions.DAOException;
import interfaces.EdificioDao;
import utiles.TableManager;

public class UnidadFuncional {
	
	private int idEdificio;
	private String depto;
	private String propietario;
	
	public UnidadFuncional(int idEdificio, String depto, String propietario) {
		this.idEdificio=idEdificio;
		this.depto=depto;
		this.propietario=propietario;
	}
	
	
/* ########################### INICIO DE GETTER Y SETTER ###############################*/
	

	public UnidadFuncional() {
	}

	public int getIdEdificio() {
		return idEdificio;
	}

	public void setIdEdificio(int idEdificio) {
		this.idEdificio = idEdificio;
	}
	
	public String getDepto() {
		return depto;
	}
	public void setDepto(String direccion) {
		this.depto = depto;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	
/* ########################### FIN DE GETTER Y SETTER ###############################*/	
	
    public String toString() {
        return "Edificio{" +
                "Id Edificio='" + idEdificio + '\'' +
                ", Deptartamento='" + depto + '\'' +
                ", Propeitario='" + propietario + '\'' +
                '}';
    }
	
/* ########################### FIN DE GETTER Y SETTER ###############################*/
	
	public static void grabarUf() throws DAOException {
		String sqlCreate = "CREATE TABLE unidadfuncional ( id_edificio INTEGER , departamento VARCHAR(256) , propietario VARCHAR(256), PRIMARY KEY(id_edificio, departamento))";
		String sqlDrop = "DROP TABLE unidadfuncional";
		TableManager tm = new TableManager();
		tm.dropTable(sqlDrop);
		tm.createTable(sqlCreate);
		
	}
	
	
}
