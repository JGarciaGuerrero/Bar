package Controlador;

import java.util.ArrayList;

import Modelo.ConexionBBDD;
import Modelo.Contiene;
import Vista.Pantalla_2_1;
import Vista.Pantalla_2_2;

public class Controlador_2_1 {
	private ConexionBBDD conexion;
	private Pantalla_2_1 pantalla_21;
	
	public Controlador_2_1(ConexionBBDD conexion, Pantalla_2_1 p) {
		this.conexion = conexion;
		this.pantalla_21 = p;
	}
	
	public ArrayList<Contiene> ActualizarTablaContiene(int mesa){
		return conexion.ConsultaTablaCont(mesa);
	}
	
	public void Abrir22(int mesa) {
		Pantalla_2_2 pantalla_22 = new Pantalla_2_2(conexion.ConsultaTablaCat(), pantalla_21, conexion, mesa);

	}
	
	public void DeleteMesa(int mesa) {
		conexion.DeleteMesa(mesa);
	}
	
	public int HacerTicket(int mesa) {
		int total = conexion.HacerTicket(mesa);
		return total;
	}
	
	public void GuardarTicket(double total, int num_mesa) {
		conexion.GuardarTicket(total, num_mesa);
	}
	

}