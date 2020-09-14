package entidades;

public class Gasto {
	
	private int monto;
	private String descripcion;
	
	public Gasto(int monto, String descripcion) {
		this.setMonto(monto);
		this.setDescripcion(descripcion);
	}
	
/* ########################### INICIO DE GETTER Y SETTER ###############################*/

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

}
