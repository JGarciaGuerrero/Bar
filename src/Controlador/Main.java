package Controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import Modelo.ConexionBBDD;
import Vista.Pantalla_Inicial;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties propiedades = new Properties();
		InputStream entrada = null;
		String usr = "";
		String pwd = "";
		String schema = "";
		String url = "";
		
		try {
			File miFichero = new File("src//configuracion.txt");
			if(miFichero.exists()) {
				entrada = new FileInputStream(miFichero);
				propiedades.load(entrada);
				usr = String.valueOf(propiedades.getProperty("User"));
				pwd = String.valueOf(propiedades.getProperty("Password"));
				schema = String.valueOf(propiedades.getProperty("Schema"));
			}else {
				System.err.println("Fichero no encontrado");
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(entrada != null);{
				try {
					entrada.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		ConexionBBDD conexion = new ConexionBBDD(usr, pwd, schema);
		Pantalla_Inicial pantalla_ini = new Pantalla_Inicial();
		Controlador_Inicial controlador_ini = new Controlador_Inicial(conexion, pantalla_ini);
		
		pantalla_ini.setControlador(controlador_ini);
		

	}

}
