package Controlador;

import Modelo.ConexionBBDD;
import Modelo.Productos;

public class Controlador_Edicion {
	private ConexionBBDD conexion;
	private Productos p;

	public Controlador_Edicion(ConexionBBDD conexion, Productos p) {
		super();
		this.conexion = conexion;
		this.p = p;
	}
	
	public void Guardar() {
		conexion.ConsultaActualizaProducto(p);
		
	}
	

}
