package Controlador;

import java.util.ArrayList;

import Modelo.ConexionBBDD;
import Modelo.Productos;
import Vista.Pantalla_1_2;
import Vista.Pantalla_Edicion;

public class Controlador_1_2 {
	private ConexionBBDD conexion;
	private Pantalla_1_2 pantalla_12;
	
	public Controlador_1_2(ConexionBBDD conexion, Pantalla_1_2 p) {
		super();
		this.conexion = conexion;
		this.pantalla_12 = p;
	}
	
	public ArrayList<Productos> ActualizarTablaProductos(String NombreCat){
		return conexion.ConsultaTablaProductos(NombreCat);
	}
	
	public void AbrirPantallaEdicion(Productos p) {
		Pantalla_Edicion pantalla_e = new Pantalla_Edicion(p, pantalla_12, conexion);
		
	}
	
	public void AnadirTablaProductos(String nombreCat, String nombrePro, Double pre) {
		conexion.AnadirProductos(nombreCat, nombrePro, pre);
		
	}
	
	public void DeleteTablaProductos(String nombre) {
		conexion.DeleteProductos(nombre);
		
	}
	
	public void AnadirNuevaCat(String nombreCat, String nombrePro, Double pre) {
		conexion.AnadirCat(nombreCat);
		conexion.AnadirProductos(nombreCat, nombrePro, pre);
		
	}
	
	public void AnadirMesa() {
		conexion.AnadirMesa();
	}
	
	public void DeleteCat(String nombre) {
		conexion.DeleteCat(nombre);
	}
}


 
