package Vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.UIManager;

import Controlador.Controlador_Inicial;
import Modelo.ConexionBBDD;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pantalla_Inicial {

	private JFrame frame;
	private ConexionBBDD conexion;
	private Controlador_Inicial controlador_ini;
	private JButton btnAdmon;
	JButton btnBar;


	public Pantalla_Inicial() {
		setPantallaInicial();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void setPantallaInicial() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(176, 196, 222));
		frame.setBounds(100, 100, 353, 258);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnAdmon = new JButton("ADMON");
		btnAdmon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { 
				controlador_ini.AbrirAdmon();
			}
		});
		
		
		btnAdmon.setFont(new Font("Verdana", Font.PLAIN, 24));
		btnAdmon.setBounds(52, 26, 201, 73);
		frame.getContentPane().add(btnAdmon);

		
		btnBar = new JButton("BAR");
		btnBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador_ini.AbrirBar();
			}
		});
		
		
		btnBar.setFont(new Font("Verdana", Font.PLAIN, 24));
		btnBar.setBounds(52, 110, 201, 73);
		frame.getContentPane().add(btnBar);
		
		
		
		frame.setVisible(true);
	}
	

	
	public void setControlador(Controlador_Inicial controlador_ini) {
		this.controlador_ini = controlador_ini;
	}
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

}
