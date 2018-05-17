package Vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.Controlador_1_2;
import Modelo.Productos;
import Modelo.TMProductos;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Pantalla_1_2 extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtNombreCategoria;
	private JTable table;
	private JScrollPane scrollPane;
	
	private TMProductos TablaDatos;
	private ArrayList<String> ListaCat;
	private Controlador_1_2 controlador_12;
	private JComboBox comboBox;
	private Pantalla_Inicial pantalla_ini;
	private int seleccion;
	private String nomb;


	public Pantalla_1_2(ArrayList<String> lista, Pantalla_Inicial pantalla_ini) {
		super();
		ListaCat = new ArrayList<String>();
		ListaCat = lista;
		this.pantalla_ini = pantalla_ini;
		setPantalla_12();
	}

	public void setPantalla_12() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			
			public void windowClosed(WindowEvent e) {
				pantalla_ini.setVisible(true);
			}

			public void windowClosing(WindowEvent e) {
				pantalla_ini.setVisible(true);
			}
		});
		frame.getContentPane().setBackground(new Color(176, 196, 222));
		frame.setBounds(100, 100, 984, 538);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JButton btnOk = new JButton("");
		
		btnOk.addActionListener(new Escuchador_Ok() {
				
		});
		
		btnOk.setToolTipText("Ok");
		btnOk.setIcon(new ImageIcon("src//Media//Ok.png"));
		btnOk.setFont(new Font("Verdana", Font.PLAIN, 24));
		btnOk.setBounds(24, 374, 73, 73);
		frame.getContentPane().add(btnOk);

		
		
		
		txtNombre = new JTextField();
		txtNombre.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				txtNombre.setText("");
			}
			public void focusLost(FocusEvent e) {
				if(txtNombre.getText().equals("")) {
					txtNombre.setText("Producto");
				}
			}
		});
		txtNombre.setFont(new Font("Verdana", Font.PLAIN, 24));
		txtNombre.setText("Producto");
		txtNombre.setBounds(24, 148, 299, 38);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		
		
		
		
		txtPrecio = new JTextField();
		txtPrecio.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				txtPrecio.setText("");
			}
			public void focusLost(FocusEvent e) {
				if(txtPrecio.getText().equals("")) {
					txtPrecio.setText("Precio");
				}
			}
		});
		txtPrecio.setFont(new Font("Verdana", Font.PLAIN, 24));
		txtPrecio.setText("Precio");
		txtPrecio.setBounds(24, 224, 299, 38);
		frame.getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);
		
		
		
		txtNombreCategoria = new JTextField();
		txtNombreCategoria.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				txtNombreCategoria.setText("");
			}
			public void focusLost(FocusEvent e) {
				if(txtNombreCategoria.getText().equals("")) {
					txtNombreCategoria.setText("Categoria");
				}
			}
		});
		txtNombreCategoria.setFont(new Font("Verdana", Font.PLAIN, 24));
		txtNombreCategoria.setText("Categoria");
		txtNombreCategoria.setBounds(24, 57, 257, 49);
		frame.getContentPane().add(txtNombreCategoria);
		txtNombreCategoria.setColumns(10);
		txtNombreCategoria.setVisible(false); 
		
		
		
		

		JButton btnCancelar = new JButton("");
		btnCancelar.setToolTipText("Eliminar");
		btnCancelar.addActionListener(new Escuchador_Cancelar() {
				
		});
		btnCancelar.setIcon(new ImageIcon("src//Media//Eliminar.png"));
		btnCancelar.setFont(new Font("Verdana", Font.PLAIN, 24));
		btnCancelar.setBounds(126, 374, 73, 73);
		frame.getContentPane().add(btnCancelar);
		
		
		
		
		JButton btnAtrassssssssssss = new JButton("");
		btnAtrassssssssssss.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboBox.setVisible(true);
				txtNombreCategoria.setVisible(false);
			}
		});
		btnAtrassssssssssss.setToolTipText("Retroceder");
		btnAtrassssssssssss.setIcon(new ImageIcon("src//Media//Atras.png"));
		btnAtrassssssssssss.setFont(new Font("Verdana", Font.PLAIN, 24));
		btnAtrassssssssssss.setBounds(226, 374, 73, 73);
		frame.getContentPane().add(btnAtrassssssssssss);
		
		
		
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 24));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Categoria"}));
		comboBox.setBounds(24, 62, 299, 38);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("Añadir...");
		for(int i = 0; i < ListaCat.size(); i ++) {
			comboBox.addItem(ListaCat.get(i));
		}
		comboBox.addActionListener(new Escuchador());
		
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new CompoundBorder());
		scrollPane.setBounds(454, 11, 484, 458);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(TablaDatos == null) {
					JOptionPane.showMessageDialog(null, "Elige Categoria");
				}else {
					seleccion = table.rowAtPoint(e.getPoint());
					nomb = String.valueOf(table.getValueAt(seleccion, 1));
					Productos p = new Productos(0, null, 0, 0);
					p = TablaDatos.getRow(seleccion);  					
					controlador_12.AbrirPantallaEdicion(p);				
				}
				
				
				
			}
		});
		table.setFont(new Font("Verdana", Font.PLAIN, 20));
		table.setForeground(SystemColor.windowText);
		table.setToolTipText("");
		
		
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"COD_CATEGORIA", "NOMBRE_PRODUCTO", "PRECIO"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewMesa = new JButton("");
		btnNewMesa.setToolTipText("Nueva Mesa");
		btnNewMesa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controlador_12.AnadirMesa();
				JOptionPane.showMessageDialog(null, "Mesa añadida");
			}
		});
		btnNewMesa.setIcon(new ImageIcon("src//Media//Anadir.png"));
		btnNewMesa.setBounds(327, 374, 73, 73);
		frame.getContentPane().add(btnNewMesa);
		

		frame.setVisible(true);
	}
	
	private class Escuchador implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==comboBox) {
				if(!comboBox.getSelectedItem().toString().equals("Añadir...")) {
					
					TablaDatos = new TMProductos(controlador_12.ActualizarTablaProductos((String)comboBox.getSelectedItem()));
					table.setModel(TablaDatos);	
				}else if(comboBox.getSelectedItem().toString().equals("Añadir...")) {
					comboBox.setVisible(false);
					txtNombreCategoria.setVisible(true);
				}
			}
			
		}
		
	}
	
	private class Escuchador_Ok implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String newCat = String.valueOf(txtNombreCategoria.getText());
			String cat = String.valueOf(comboBox.getSelectedItem());
			String newNomb = txtNombre.getText();
			double newPre = 0;
			
			char[] aux = new char[1];
			int aux_coma = 0;
			
			for(int i = 0; i < String.valueOf(txtPrecio.getText()).length(); i++) {
				aux[0] = String.valueOf(txtPrecio.getText()).charAt(i);
				
				if(aux[0] == 46 || aux[0] == 44) {
					aux_coma ++;
				}
			}
			
			for(int i = 0; i < String.valueOf(txtPrecio.getText()).length(); i++) {
				aux[0] = String.valueOf(txtPrecio.getText()).charAt(i);
				
				if(aux[0] < 48 || aux[0] > 57 || aux_coma > 1 && aux[0] != 46 && aux[0] != 44) {
					if(aux[0] != 46 && aux[0] != 44) {
						JOptionPane.showMessageDialog(null, "Introduce un número");
						break;
					}

				}else {
					
					newPre = Double.parseDouble(String.valueOf(txtPrecio.getText()).replace(',', '.'));
					
				}
			}

			
			if(!cat.equals("Añadir...") && !newNomb.equals(null) && newPre != 0) {
				controlador_12.AnadirTablaProductos(cat, newNomb, newPre);
				TablaDatos = new TMProductos(controlador_12.ActualizarTablaProductos((String)comboBox.getSelectedItem()));
				table.setModel(TablaDatos);
				
			}else if(cat.equals("Añadir...")) {
				
				controlador_12.AnadirNuevaCat(newCat, newNomb, newPre);
				TablaDatos = new TMProductos(controlador_12.ActualizarTablaProductos((String)comboBox.getSelectedItem()));
				table.setModel(TablaDatos);
				txtNombreCategoria.setVisible(false);
				comboBox.setVisible(true);
				comboBox.addItem(newCat);
				
			}
			
		}
		
	}
	
	private class Escuchador_Cancelar implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(table.getSelectedRowCount() >= 1) {
				controlador_12.DeleteTablaProductos(nomb);
				TablaDatos = new TMProductos(controlador_12.ActualizarTablaProductos((String)comboBox.getSelectedItem()));
				table.setModel(TablaDatos);	
			}else{
				controlador_12.DeleteCat(comboBox.getSelectedItem().toString());
				TablaDatos = new TMProductos(controlador_12.ActualizarTablaProductos((String)comboBox.getSelectedItem()));
				table.setModel(TablaDatos);	
			}

			
		}
		
	}
	
	public void setControlador(Controlador_1_2 controlador) {
		this.controlador_12 = controlador;
	}
	
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
}

