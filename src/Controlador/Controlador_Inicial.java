package Controlador;

import Modelo.ConexionBBDD;
import Vista.Pantalla_1_2;
import Vista.Pantalla_2_1;
import Vista.Pantalla_Inicial;

public class Controlador_Inicial {
	private ConexionBBDD conexion;
	private Pantalla_Inicial pantalla_ini;
	
	public Controlador_Inicial(ConexionBBDD conexion, Pantalla_Inicial pantalla_ini) {
		super();
		this.conexion = conexion;
		this.pantalla_ini = pantalla_ini;
	}

	public void AbrirAdmon() {
		Pantalla_1_2 pantalla_12 = new Pantalla_1_2(conexion.ConsultaTablaCat(), pantalla_ini);
		Controlador_1_2 controlador_12 = new Controlador_1_2(conexion, pantalla_12);
		
		pantalla_12.setControlador(controlador_12);
		pantalla_ini.setVisible(false);
		
	}
	
	public void AbrirBar() {
		Pantalla_2_1 pantalla_21 = new Pantalla_2_1(conexion.ConsultaTablaCont(0), pantalla_ini, conexion.RellenaMesa());
		Controlador_2_1 controlador_21 = new Controlador_2_1(conexion, pantalla_21);
		
		pantalla_21.setContolador(controlador_21);
		pantalla_ini.setVisible(false);
	}
	

}
