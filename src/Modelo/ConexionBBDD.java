package Modelo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.table.DefaultTableModel;

public class ConexionBBDD {	
	private String bd;
	private String url= "jdbc:oracle:thin:@localhost:1521:XE";
	private String usr = "";
	private String pwd = "";
	private Connection conexion;
	

	public ConexionBBDD(String usr, String pwd)  {
		this.usr = usr;
		this.pwd = pwd;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(this.url, this.usr, this.pwd);
			
			if(!conexion.isClosed()) {
				System.out.println("Conexi�n establecida");
			}
			else
				System.out.println("Fallo en Conexi�n");	
			

		}catch (Exception e) {
			System.out.println("ERROR en conexi�n con ORACLE");	
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Productos> ConsultaTablaProductos(String nombreCat) { //Rellenar la tabla de productos con los productos existentes en el admin
		ArrayList<Productos> producto = new ArrayList<Productos>();
		String query = "SELECT * FROM JORGE.producto WHERE COD_CATEGORIA = (SELECT COD_CATEGORIA FROM JORGE.categoria WHERE NOMBRE_CATEGORIA LIKE '"+ nombreCat +"') ORDER BY COD_PRODUCTO";
		//SELECT COD_CATEGORIA, NOMBRE_PRODUCTO, PRECIO FROM PRODUCTO WHERE COD_CATEGORIA = (SELECT COD_CATEGORIA FROM CATEGORIA WHERE NOMBRE_CATEGORIA LIKE 'Cervezas');
		System.out.println(query);
		 
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 int cod_Cat = rset.getInt("COD_CATEGORIA");
				 String nombre = rset.getString("NOMBRE_PRODUCTO");
				 double precio = rset.getDouble("PRECIO");
				 int cod_Pro = rset.getInt("COD_PRODUCTO");
				 Productos p = new Productos(cod_Pro, nombre, precio, cod_Cat);
				 producto.add(p);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		return producto;
		
	}
	
	
	public ArrayList<String> ConsultaTablaCat(){ //Consultar la tabla de categorias, se usa para hacer un filtro
		ArrayList<String> lista = new ArrayList<String>();
		String query = "SELECT * FROM JORGE.categoria";
		 
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				 lista.add(rset.getString("NOMBRE_CATEGORIA"));   
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		return lista;
	}
	
	public ArrayList<Contiene> ConsultaTablaCont(int mesa){
		ArrayList<Contiene> lista = new ArrayList<Contiene>();
		String query = "SELECT C.Cantidad_Producto, C.Total_Producto, C.Cod_Comanda, P.Nombre_Producto FROM JORGE.Contiene C, JORGE.Producto P, JORGE.Comandas Co WHERE Co.Num_Mesa = "+mesa+" AND C.Cod_Comanda = Co.Cod_Comanda AND P.Cod_Producto=C.Cod_Producto ORDER BY C.Cod_Comanda";
		//SELECT C.Cantidad_Producto, C.Total_Producto, C.Cod_Comanda, P.Nombre_Producto FROM JORGE.Contiene C, JORGE.Producto P, JORGE.Comandas Co WHERE Co.Num_Mesa = 1 AND C.Cod_Comanda = Co.Cod_Comanda AND P.Cod_Producto=C.Cod_Producto ORDER BY C.Cod_Comanda
		
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				String nombre_Producto = rset.getString("NOMBRE_PRODUCTO");
				int cod_comanda = rset.getInt("COD_COMANDA");
				int precio = rset.getInt("TOTAL_PRODUCTO");
				int cantidad = rset.getInt("CANTIDAD_PRODUCTO"); 
				Contiene c = new Contiene(nombre_Producto, cod_comanda, cantidad, precio);
				lista.add(c);  
				
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		return lista;
	}
	
	public void ConsultaActualizaProducto(Productos p) {
		String nombre = p.getNombre();
		int cod = p.getCod_Producto();
		double precio = p.getPrecio();
		int resultado = 0;
		
		String query = "UPDATE JORGE.producto SET PRECIO="+precio+", NOMBRE_PRODUCTO='"+nombre+"' WHERE COD_PRODUCTO = "+cod;
		//UPDATE producto SET PRECIO=2, NOMBRE_PRODUCTO='Coca' WHERE COD_PRODUCTO=1
		try {
			Statement stmt = conexion.createStatement();
			resultado = stmt.executeUpdate(query);
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		//return resultado;
		
	}
	
	
	public void AnadirProductos(String nombreCat, String nombrePro, Double pre) {
		
		String query ="INSERT INTO JORGE.producto (Cod_Producto, Nombre_Producto, Precio, Cod_Categoria) VALUES ((SELECT MAX(Cod_Producto) + 1 FROM JORGE.Producto), '"+nombrePro+"', "+pre+", (SELECT Cod_Categoria FROM JORGE.categoria WHERE NOMBRE_CATEGORIA LIKE '"+nombreCat+"'))";
		//INSERT INTO Producto (Cod_Producto, Nombre_Producto, Precio, Cod_Categoria) VALUES ((SELECT MAX(Cod_Producto) + 1 FROM Producto), 'Prueba', 12, (SELECT Cod_Categoria FROM categoria WHERE NOMBRE_CATEGORIA LIKE 'Cervezas'))
		int resultado = 0;
		try {
			Statement stmt = conexion.createStatement();
			resultado = stmt.executeUpdate(query);
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
	}
	
	public void DeleteProductos(String nomb) {
		String query = "DELETE FROM JORGE.producto WHERE NOMBRE_PRODUCTO LIKE '"+nomb+"'";
		System.out.println(query);
		int resultado = 0;
		try {
			Statement stmt = conexion.createStatement();
			resultado = stmt.executeUpdate(query);
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
	}
	
	public void AnadirCat(String nombreCat) {
		String query = "INSERT INTO JORGE.Categoria (Cod_Categoria, Nombre_Categoria) VALUES ((SELECT MAX(Cod_Categoria + 1) FROM JORGE.Categoria ), '"+nombreCat+"')";
		//INSERT INTO Categoria (Cod_Categoria, Nombre_Categoria) VALUES ((SELECT MAX(Cod_Categoria + 1) FROM Categoria ), 'Snacks')
		int resultado = 0;
		try {
			Statement stmt = conexion.createStatement();
			resultado = stmt.executeUpdate(query);
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
	}
	
	public void DeleteMesa(int mesa) {
		ArrayList<Integer> aux = new ArrayList<Integer>();
		String query_aux = "SELECT COD_COMANDA FROM JORGE.COMANDAS WHERE NUM_MESA = "+mesa;
		//SELECT COD_COMANDA FROM COMANDAS WHERE NUM_MESA = 2
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query_aux);
			while(rset.next()) {
				int cod_comanda = rset.getInt("COD_COMANDA");
				aux.add(cod_comanda) ;
				
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		
		for(int i = 0; i < aux.size(); i++) {
			String query = "DELETE FROM JORGE.CONTIENE WHERE COD_COMANDA = "+aux.get(i);
			//DELETE FROM CONTIENE WHERE COD_COMANDA = 3
			int resultado = 0;
			try {
				Statement stmt = conexion.createStatement();
				resultado = stmt.executeUpdate(query);
				stmt.close();
				
			}catch (SQLException s){
				s.printStackTrace();
			}
		}

	}
	
	public int HacerTicket(int mesa) {
		String query = "SELECT SUM(TOTAL_COMANDA) FROM JORGE.COMANDAS WHERE NUM_MESA = "+mesa;
		int total = 0;
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);

			while(rset.next()) {
				total = rset.getInt("SUM(TOTAL_COMANDA)");				
			}
			
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		return total;
		
	}
	
	public void GuardarTicket(double total, int num_mesa){
		String query = "INSERT INTO JORGE.Ticket (Cod_Ticket, Precio) VALUES ((SELECT MAX(Cod_Ticket + 1) FROM JORGE.ticket), "+total+")";
		//INSERT INTO JORGE.Ticket (Cod_Ticket, Precio) VALUES ((SELECT MAX(Cod_Ticket + 1) FROM JORGE.ticket), 10)
		int resultado = 0;
		try {
			Statement stmt = conexion.createStatement();
			resultado = stmt.executeUpdate(query);
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		
		String query_Cont = "INSERT INTO JORGE.Genera (Num_Mesa, Cod_Ticket, Fecha) VALUES ("+num_mesa+", (SELECT MAX(Cod_Ticket + 1) FROM JORGE.Genera), SYSDATE)";
		//INSERT INTO Genera (Num_Mesa, Cod_Ticket, Fecha) VALUES (1, (SELECT MAX(Cod_Ticket + 1) FROM Genera), SYSDATE);
		int resultado_aux = 0;
		try {
			Statement stmt = conexion.createStatement();
			resultado_aux = stmt.executeUpdate(query_Cont);
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}

	}
	
	public void AnadirMesa() {
		String query = "INSERT INTO JORGE.Mesa (Num_Mesa, Lugar, Total_Gastado) VALUES ((SELECT MAX(Num_Mesa + 1) FROM JORGE.Mesa), 'I', 0)";
		//INSERT INTO Mesa (Num_Mesa, Lugar, Total_Gastado) VALUES ((SELECT MAX(Num_Mesa + 1) FROM JORGE.Mesa), 'I', 0);
		int resultado = 0;
		try {
			Statement stmt = conexion.createStatement();
			resultado = stmt.executeUpdate(query);
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
	}
	
	public ArrayList<Integer> RellenaMesa(){
		ArrayList<Integer> aux = new ArrayList<Integer>();
		String query = "SELECT NUM_MESA FROM JORGE.MESA";
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
				int num_mesa = rset.getInt("NUM_MESA");
				aux.add(num_mesa);  
				
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		return aux;
	}
	
	public void DeleteCat(String nombre) {
		String query = "DELETE FROM JORGE.CATEGORIA WHERE COD_CATEGORIA = (SELECT COD_CATEGORIA FROM JORGE.CATEGORIA WHERE NOMBRE_CATEGORIA = '"+nombre+"')";
		//DELETE FROM JORGE.CATEGORIA WHERE COD_CATEGORIA = (SELECT COD_CATEGORIA FROM JORGE.CATEGORIA WHERE NOMBRE_CATEGORIA = 'PRUEBA')
		int resultado = 0;
		try {
			Statement stmt = conexion.createStatement();
			resultado = stmt.executeUpdate(query);
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
	}
	
	public void NuevaComanda(int nummesa, ArrayList<String> tabla, ArrayList<String> tabla2, ArrayList<String> tabla3) {
		String insertcom = "INSERT INTO JORGE.Comandas (COD_COMANDA, TOTAL_COMANDA, NUM_MESA) VALUES ((SELECT MAX(Cod_Comanda) +1 FROM JORGE.Comandas), 0, "+nummesa+")";
		int resultadocont=0;
		int resultadocom=0;
		int resultadoupd=0;
		int total=0;
		
		try {
			Statement stmt = conexion.createStatement();
			resultadocom = stmt.executeUpdate(insertcom);
		}catch(SQLException s){
			s.printStackTrace();
		}
		
		for (int i = 0; i < tabla.size(); i++) {
			try {
				String insercont = "INSERT INTO JORGE.Contiene (CANTIDAD_PRODUCTO, TOTAL_PRODUCTO, COD_PRODUCTO, COD_COMANDA) VALUES "
						+ "("+tabla.get(i)+","+tabla2.get(i)+", (SELECT Cod_Producto FROM JORGE.Producto WHERE Nombre_Producto = '"+tabla3.get(i)+"'), (SELECT MAX(Cod_Comanda) FROM JORGE.Comandas))";
				Statement stmt = conexion.createStatement();
				resultadocont = stmt.executeUpdate(insercont);
				total = total + Integer.parseInt(tabla.get(i));
			}catch(SQLException s){
				s.printStackTrace();
			}
		}
		
		try {
			String updatecom = "UPDATE JORGE.Comandas SET Total_Comanda = "+total+" WHERE Cod_Comanda = (SELECT MAX(Cod_Comanda) FROM JORGE.Comandas)";
			Statement stmt = conexion.createStatement();
			resultadoupd = stmt.executeUpdate(updatecom);
		}catch(SQLException s) {
			s.printStackTrace();
		}
		
	}
	
	public int Producto(String nombreprod) {
		String nombre = "SELECT Precio FROM JORGE.Producto WHERE Nombre_Producto = '"+nombreprod+"'";
		int resultado=0;
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rtst = stmt.executeQuery(nombre);
			while(rtst.next()) {
				resultado = rtst.getInt("Precio");				
			}
			stmt.close();
			rtst.close();
			
		}catch(SQLException s){
			s.printStackTrace();
		}
		
		return resultado;
	}

}