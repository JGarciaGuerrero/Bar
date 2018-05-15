package Vista;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.Controlador_2_2;
import Modelo.ConexionBBDD;
import Modelo.Productos;


import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pantalla_2_2 {

	private JFrame frame;
	private JTextField txtCantidad;
	private Pantalla_2_1 pantalla21;
	private ConexionBBDD conexion;
	private int mesa;
	private ArrayList<String> lista;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JTable table_1;
	private int seleccion;

	/**
	 * Create the application.
	 */
	public Pantalla_2_2(ArrayList<String> lista, Pantalla_2_1 pantalla21, ConexionBBDD conexion, int mesa) {
		this.pantalla21 = pantalla21;
		this.conexion = conexion;
		this.mesa = mesa;
		this.lista = new ArrayList<String>();
		this.lista = lista;
		setPantalla22();
	}

	private void setPantalla22() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosed(WindowEvent e) {
				pantalla21.setVisible(true);
			}

			public void windowClosing(WindowEvent e) {
				pantalla21.setVisible(true);
			}
		});
		frame.getContentPane().setBackground(new Color(176, 196, 222));
		frame.setBounds(100, 100, 555, 305);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 24));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Categor\u00EDa" }));
		comboBox.setBounds(21, 27, 147, 41);
		comboBox.addActionListener(new Escuchador());
		for (int i = 0; i < lista.size(); i++) {
			comboBox.addItem(lista.get(i));
		}
		frame.getContentPane().add(comboBox);

		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Verdana", Font.PLAIN, 24));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Producto" }));
		comboBox_1.setBounds(195, 27, 147, 41);
		frame.getContentPane().add(comboBox_1);

		txtCantidad = new JTextField();
		txtCantidad.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				txtCantidad.setText("");
			}
			public void focusLost(FocusEvent e) {
				if(txtCantidad.getText().equals("")) {
					txtCantidad.setText("Cantidad");
				}
			}
		});
		txtCantidad.setFont(new Font("Verdana", Font.PLAIN, 24));
		txtCantidad.setText("Cantidad");
		txtCantidad.setBounds(365, 27, 156, 41);
		frame.getContentPane().add(txtCantidad);
		txtCantidad.setColumns(10);

		JButton btnAadir = new JButton("");
		btnAadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Controlador_2_2 control_22 = new Controlador_2_2(conexion, mesa);
				int total = Integer.parseInt(txtCantidad.getText())*control_22.Producto(comboBox_1.getSelectedItem().toString());
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		        model.addRow(new Object[]{comboBox.getSelectedItem().toString(), comboBox_1.getSelectedItem().toString(), total, txtCantidad.getText()});
			}
		});
		btnAadir.setToolTipText("A\u00F1adir");
		btnAadir.setIcon(new ImageIcon("src//Media//Anadir.png"));
		btnAadir.setBounds(67, 197, 59, 59);
		frame.getContentPane().add(btnAadir);

		JButton btnCancelar = new JButton("");
		
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				pantalla21.setVisible(true);
				
			}
		});
		btnCancelar.setToolTipText("Retroceder");
		btnCancelar.setIcon(new ImageIcon("src//Media//Atras.png"));
		btnCancelar.setBounds(383, 197, 59, 59);
		frame.getContentPane().add(btnCancelar);

		JButton btnAceptar = new JButton("");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Controlador_2_2 control_22 = new Controlador_2_2(conexion, mesa);
				ArrayList <String> precio = new ArrayList<String>();
				ArrayList <String> nombre = new ArrayList<String>();
				ArrayList <String> cantidad = new ArrayList<String>();
				for (int i=0; i<table_1.getRowCount();i++) {
					precio.add(table_1.getValueAt(i,2).toString());
					nombre.add(table_1.getValueAt(i,1).toString());
					cantidad.add(table_1.getValueAt(i,3).toString());
				}
				control_22.AñadirComandas(mesa, cantidad, precio, nombre);
				frame.dispose();
				pantalla21.setVisible(true);
				
			}
		});
		btnAceptar.setToolTipText("Aceptar");
		btnAceptar.setIcon(new ImageIcon("src//Media//Ok.png"));
		btnAceptar.setBounds(304, 197, 59, 59);
		frame.getContentPane().add(btnAceptar);

		JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new Escuchador_Eliminar() {
			
		});
		btnEliminar.setToolTipText("Eliminar");
		btnEliminar.setIcon(new ImageIcon("src//Media//Eliminar.png"));
		btnEliminar.setBounds(148, 197, 59, 59);
		frame.getContentPane().add(btnEliminar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 79, 490, 113);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		
		table_1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				seleccion = table_1.getSelectedRow();				
			}
		});
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Categoría", "Producto", "Precio total", "Cantidad"
			}
		));
		scrollPane.setViewportView(table_1);
		//table_1.setModel(TablaDatos);
		frame.setVisible(true);
		
	

	}


	private class Escuchador implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cat = comboBox.getSelectedItem().toString();
			Controlador_2_2 control_22 = new Controlador_2_2(conexion, mesa);
			ArrayList<Productos> listaprod = new ArrayList<Productos>();
			Productos producto = new Productos(0, null, 0, 0);

			listaprod = control_22.ActualizarTablaProductos(cat);
			comboBox_1.removeAllItems();
			comboBox_1.addItem("Producto");
			for (int i = 0; i < listaprod.size(); i++) {
				producto = listaprod.get(i);
				comboBox_1.addItem(producto.getNombre());
			}

		}

	}
	
	private class Escuchador_Eliminar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.removeRow(seleccion);
			table_1.setModel(model);
		}
		
	}
}