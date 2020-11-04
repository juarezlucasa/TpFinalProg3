package ui;

import entidades.UnidadFuncional;
import entidades.UnidadFuncionalService;
import exceptions.ServicioException;

public class PanelAgregarUnidadFuncional extends PanelTemplateUf {

	public PanelAgregarUnidadFuncional(String titulo) {
		super(titulo);
		cambiarEtiquetaBoton("Agregar UF");
	}

	public void botonOK(UnidadFuncional uf) {
		UnidadFuncionalService s = new UnidadFuncionalService();
		try {
			s.crearUf(uf);
			mostrarExito("Se agregó el departamento al sistema");
		} catch (ServicioException e1) {
			mostrarError("Erro al agregar la UF",e1.getMessage());
		}
	}

}