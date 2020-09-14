package test;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import entidades.Edificio;
import entidades.EdificioDaoImpl;
import entidades.EdificioService;
import entidades.UnidadFuncional;
import exceptions.DAOException;
import exceptions.ServicioException;
import interfaces.EdificioDao;
import ui.MiFrame;
import ui.PanelAgregarEdificio;
import ui.PanelEliminarEdificio;
import ui.PanelListarEdificio;
import utiles.DBManager;
import utiles.TableManager;

public class Test {

	//private static final List<Edificio> EdificioService = null;

	public static void main(String [] args) {
		
		//UnidadFuncional.grabarUf();
        JFrame frame = new MiFrame("Consorcios 2.0");
        //frame.getContentPane().add(new PanelAgregarEdificio("Pepe"));
        frame.setVisible(true);
		
	} 
	
}
