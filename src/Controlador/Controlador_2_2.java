package Controlador;

import java.util.ArrayList;

import Modelo.ConexionBBDD;
import Modelo.Contiene;
import Modelo.Productos;
import Vista.Pantalla_1_2;
import Vista.Pantalla_2_2;

public class Controlador_2_2 {
	
	private ConexionBBDD conexion;
	private int mesa;
	
	public Controlador_2_2 (ConexionBBDD conexion,int mesa) {
		this.conexion = conexion;
		this.mesa = mesa;
	}
	
	public ArrayList<Contiene> ActualizarTablaContiene(int mesa){
		return conexion.ConsultaTablaCont(mesa);
	}
	
	public ArrayList<Productos> ActualizarTablaProductos(String NombreCat){
		return conexion.ConsultaTablaProductos(NombreCat);
	}
	
	public int Producto(String nombreprod) {
		return conexion.Producto(nombreprod);
	}
	
	public void A�adirComandas(int nummesa, ArrayList<String> tabla, ArrayList<String> tabla2, ArrayList<String> tabla3) {
		conexion.NuevaComanda(nummesa, tabla, tabla2, tabla3);
	}
	
	public void DeleteComanda(int num) {
		
	}

}
