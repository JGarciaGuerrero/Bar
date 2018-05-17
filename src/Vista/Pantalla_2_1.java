package Vista;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.Controlador_2_1;
import Modelo.Contiene;
import Modelo.TMContiene;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Pantalla_2_1 {

	private JFrame frame;
	private JTable table;
	private ArrayList<Contiene> ListaCont;
	private Controlador_2_1 controlador_21;
	public JComboBox comboBox;
	private TMContiene TablaDatos;
	private Pantalla_Inicial pantalla_ini;
	private JTextField textField;
	private JLabel lblPrecio;
	private JLabel label;
	private ArrayList<Integer> num_mesa_combo;
	private JLabel lblPagado;


	public Pantalla_2_1(ArrayList<Contiene> lista, Pantalla_Inicial pantalla_ini, ArrayList<Integer> mesa) {
		num_mesa_combo = new ArrayList<Integer>();
		num_mesa_combo = mesa;
		ListaCont = new ArrayList<Contiene>();
		ListaCont = lista;
		this.pantalla_ini = pantalla_ini;
		setPantalla_21();
	}

	public void setPantalla_21() {
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
		frame.setBounds(100, 100, 727, 494);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 34, 208, 45);
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 24));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Numero Mesa"}));
		for(int i = 0; i < num_mesa_combo.size(); i++) {
			comboBox.addItem(num_mesa_combo.get(i));
		}
		
		comboBox.addActionListener(new Escuchador());
		frame.getContentPane().add(comboBox);
		
		JButton btnAadir = new JButton("");
		btnAadir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int aux = comboBox.getSelectedIndex();
				if(!String.valueOf(comboBox.getSelectedItem()).equals("Numero Mesa")) {
					controlador_21.Abrir22(aux);
				}else {
					JOptionPane.showMessageDialog(null, "Elige mesa");
				}

			}
		});
		btnAadir.setBounds(69, 111, 73, 73);
		btnAadir.setToolTipText("A\u00F1adir Comanda");
		btnAadir.setIcon(new ImageIcon("src//Media//Anadir.png"));
		frame.getContentPane().add(btnAadir);
		
		
		JButton btnGuardar = new JButton("");
		btnGuardar.addActionListener(new Escuchador_Ticket() {
			
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGuardar.setBounds(69, 279, 73, 73);
		btnGuardar.setToolTipText("Pagar y guardar");
		btnGuardar.setIcon(new ImageIcon("src//Media//Guardar.png"));
		frame.getContentPane().add(btnGuardar);
		
		JButton btnFinalizar = new JButton("");
		
		btnFinalizar.addActionListener(new Escuchador_Fin() {
			
		});
		
		btnFinalizar.setBounds(69, 195, 73, 73);
		btnFinalizar.setToolTipText("Hacer ticket");
		btnFinalizar.setIcon(new ImageIcon("src//Media//Ticket.png"));
		frame.getContentPane().add(btnFinalizar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(229, 34, 456, 315);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Verdana", Font.PLAIN, 22));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Cantidad", "Total", "Producto", "Comanda"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblTotal.setBounds(229, 360, 105, 30);
		frame.getContentPane().add(lblTotal);
		
		lblPagado = new JLabel("Pagado");
		lblPagado.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				lblPagado.setText("");
			}
			public void focusLost(FocusEvent e) {
				if(lblPagado.getText().equals("")) {
					lblPagado.setText("€");
				}
			}
		});
		lblPagado.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblPagado.setBounds(391, 360, 105, 30);
		frame.getContentPane().add(lblPagado);
		lblPagado.setText("€");
		
		JLabel lblDevuelto = new JLabel("Devuelto");
		lblDevuelto.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblDevuelto.setBounds(558, 360, 127, 30);
		frame.getContentPane().add(lblDevuelto);
		
		lblPrecio = new JLabel("\u20AC");
		lblPrecio.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblPrecio.setBounds(229, 401, 73, 24);
		lblPrecio.setVisible(false);
		frame.getContentPane().add(lblPrecio);
		
		textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.PLAIN, 24));
		textField.setText("\u20AC");
		textField.setBounds(391, 401, 94, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		label = new JLabel("\u20AC");
		label.setFont(new Font("Verdana", Font.PLAIN, 24));
		label.setBounds(558, 401, 94, 25);
		frame.getContentPane().add(label);
		frame.setVisible(true);
		label.setVisible(false);
	}

	public void setContolador(Controlador_2_1 controlador) {
		this.controlador_21 = controlador;
	}
	
	
	private class Escuchador implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==comboBox) {
				TablaDatos = new TMContiene(controlador_21.ActualizarTablaContiene(comboBox.getSelectedIndex()));
				table.setModel(TablaDatos);
			}
			
		}
		
	}
	
	private class Escuchador_Fin implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(!String.valueOf(comboBox.getSelectedItem()).equals("Numero Mesa")) {
				int mesa = Integer.parseInt(String.valueOf(comboBox.getSelectedItem().toString()));
				controlador_21.DeleteMesa(mesa);
				TablaDatos = new TMContiene(controlador_21.ActualizarTablaContiene(comboBox.getSelectedIndex()));
				table.setModel(TablaDatos);
				
				lblPrecio.setVisible(true);
				lblPrecio.setText(String.valueOf(controlador_21.HacerTicket(mesa)));
				textField.setVisible(true);
				label.setVisible(true);	
			}else {
				JOptionPane.showMessageDialog(null, "Elige mesa");
			}

		}
		
	}
	
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
		TablaDatos = new TMContiene(controlador_21.ActualizarTablaContiene(comboBox.getSelectedIndex()));
		table.setModel(TablaDatos);
		
	}
	
	private class Escuchador_Ticket implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(!String.valueOf(comboBox.getSelectedItem()).equals("Numero Mesa")) {
				int num_mesa = Integer.parseInt(comboBox.getSelectedItem().toString());
				double precio = Double.parseDouble(lblPrecio.getText());
				double pagado = 0;
				double total = -1;
				if(textField.getText().equals("€") || textField.getText().equals(null)) {
					pagado = Double.parseDouble(textField.getText());
					total = pagado - precio;
				}else {
					JOptionPane.showMessageDialog(null, "Introduce una cifra");
				}
				if(total >= 0) {
					label.setText(String.valueOf(total));
					controlador_21.GuardarTicket(total, num_mesa);
				}else {
					JOptionPane.showMessageDialog(null, "Te intentan timar");
				}

			}else {
				JOptionPane.showMessageDialog(null, "Elige mesa");
			}

			
		}
		
	}
}
