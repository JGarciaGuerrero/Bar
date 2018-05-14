package Modelo;

public class Productos {
	int cod_Producto;
	String nombre;
	double precio;
	int cod_Cat;
	
	public Productos(int cod_Producto, String nombre, double precio, int cod_Cat) {
		super();
		this.cod_Producto = cod_Producto;
		this.nombre = nombre;
		this.precio = precio;
		this.cod_Cat = cod_Cat;
	}
	
	public Productos(String nombre, double precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}

	public int getCod_Producto() {
		return cod_Producto;
	}

	public void setCod_Producto(int cod_Producto) {
		this.cod_Producto = cod_Producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCod_Cat() {
		return cod_Cat;
	}

	public void setCod_Cat(int cod_Cat) {
		this.cod_Cat = cod_Cat;
	}
	
	

}
