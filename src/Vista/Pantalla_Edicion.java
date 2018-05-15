package Vista;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;

import Controlador.Controlador_Edicion;
import Modelo.ConexionBBDD;
import Modelo.Productos;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pantalla_Edicion extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField_Pro;
	private JTextField textField_Pre;
	private Productos producto;
	private Pantalla_1_2 pantalla_12;
	private ConexionBBDD conexion;

	public Pantalla_Edicion(Productos p, Pantalla_1_2 pantalla_12, ConexionBBDD conexion) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				pantalla_12.setVisible(true);
			}
			@Override
			public void windowClosing(WindowEvent e) {
				pantalla_12.setVisible(true);
			}
		});
		
		addWindowListener(new WindowAdapter() {				
			   public void windowClosing(WindowEvent e) {
			     System.exit(0);
			   }
			 });
		this.pantalla_12 = pantalla_12;
		this.conexion = conexion;
		this.producto = p;
		setPantallaEdicion();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	public void setPantallaEdicion() {
		frame = new JFrame();
		frame.setBounds(100, 100, 361, 491);
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblCategoria.setBounds(30, 50, 119, 66);
		frame.getContentPane().add(lblCategoria);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblProducto.setBounds(30, 145, 119, 59);
		frame.getContentPane().add(lblProducto);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblPrecio.setBounds(30, 229, 119, 59);
		frame.getContentPane().add(lblPrecio);
		
		
		
		
		textField_Pro = new JTextField();
		textField_Pro.setFont(new Font("Verdana", Font.PLAIN, 24));
		textField_Pro.setBounds(159, 148, 176, 42);
		frame.getContentPane().add(textField_Pro);
		textField_Pro.setColumns(10);
		textField_Pro.setText(producto.getNombre());
		
		
		
		
		textField_Pre = new JTextField();
		textField_Pre.setFont(new Font("Verdana", Font.PLAIN, 24));
		textField_Pre.setBounds(159, 246, 176, 42);
		frame.getContentPane().add(textField_Pre);
		textField_Pre.setColumns(10);
		textField_Pre.setText(String.valueOf(producto.getPrecio()));
		
		
		
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Actualizar producto
				String newnombre = textField_Pro.getText();
				Double newprecio =Double.parseDouble(textField_Pre.getText());
				
				if(newnombre != null && newprecio != 0) {
					producto.setPrecio(newprecio);
					producto.setNombre(newnombre);
					Controlador_Edicion control_edi = new Controlador_Edicion(conexion, producto);
					control_edi.Guardar();	
					frame.dispose();
				}
			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 24));
		btnNewButton.setBounds(87, 355, 176, 59);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblNewLabel.setBounds(159, 68, 176, 42);
		lblNewLabel.setText(String.valueOf(producto.getCod_Cat()));
		frame.getContentPane().add(lblNewLabel);
		
		
		
		frame.setVisible(true);
	}
}
