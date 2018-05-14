package Modelo;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TMProductos implements TableModel{
	
	ArrayList<Productos> producto;
	
	

	public TMProductos(ArrayList<Productos> producto) {
		super();
		this.producto = producto;
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		
		String sAux = "";
		switch (columnIndex) {
			case 0:
				sAux = "Cod_Categoria";
				break;
			case 1:
				sAux = "Nombre";
				break;
			case 2:
				sAux = "Precio";
				break;
		}
		return sAux;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.producto.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Productos p = producto.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return p.getCod_Cat();
		case 1:
			return p.getNombre();
		case 2:
			return p.getPrecio();
		default:
			return null;
		}
	}
	
	public Productos getRow(int rowIndex) {
		return producto.get(rowIndex);
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Productos p = producto.get(rowIndex);
		
		
	}

}
