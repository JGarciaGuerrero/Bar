package Modelo;

public class Contiene {
	
	String nombre_Producto;
	int Cod_Comanda;
	int Cantidad;
	int Precio;
	
	
	public Contiene(String nombre_Producto, int cod_Comanda, int cantidad, int precio) {
		this.nombre_Producto = nombre_Producto;
		this.Cod_Comanda = cod_Comanda;
		this.Cantidad = cantidad;
		this.Precio = precio;
	}


	public String getCod_Producto() {
		return nombre_Producto;
	}


	public void setCod_Producto(String cod_Producto) {
		nombre_Producto = cod_Producto;
	}


	public int getCod_Comanda() {
		return Cod_Comanda;
	}


	public void setCod_Comanda(int cod_Comanda) {
		Cod_Comanda = cod_Comanda;
	}


	public int getCantidad() {
		return Cantidad;
	}


	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}


	public int getPrecio() {
		return Precio;
	}


	public void setPrecio(int precio) {
		Precio = precio;
	}
}
		
	
