package Modelo;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TMContiene implements TableModel{
	
	ArrayList<Contiene> Contiene;
	
	
	public TMContiene(ArrayList<Modelo.Contiene> contiene) {
		this.Contiene = contiene;
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
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		String sAux = "";
		switch (columnIndex) {
			case 0:
				sAux = "Cantidad";
				break;
			case 1:
				sAux = "Total";
				break;
			case 2:
				sAux = "Producto";
				break;
			case 3:
				sAux = "Comanda";
		}
		return sAux;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.Contiene.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Contiene c = Contiene.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return c.getCantidad();
		case 1:
			return c.getPrecio();
		case 2:
			return c.getCod_Producto();
		case 3:
			return c.getCod_Comanda();
		default:
			return null;
		}
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
		Contiene c = Contiene.get(rowIndex);
		
	}

}
