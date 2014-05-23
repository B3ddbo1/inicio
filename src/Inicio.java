import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.*;
import java.awt.Font;
import java.awt.Color;

// clase que maneja la pantalla de inicio de login
public class Inicio extends JFrame{

	private String usuario; // buffer donde se almacena usuario
	private String password; // buffer donde se almacena contraseña
	private JTextField textuser; // texto donde el usuario introduce el nombre de usuario
	private JTextField textpass; // texto donde el usuario introduce la contraseña
	private JLabel lblPassword; // label donde aparece "password"
	private JLabel lblestado; // label que indica los errores y exitos
	private JButton btnreset; // boton reset que borra el texto escrito y lo deja vacio
	private JButton btnregistrarse; // boton para ir a la ventana de registros
	private Registro registro;
	private OracleAccess modelo;

	public Inicio() {
		setBounds(100, 10, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		lblestado = new JLabel("");
		lblestado.setForeground(Color.RED);
		lblestado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblestado.setBounds(158, 228, 104, 23);
		getContentPane().add(lblestado);
		btnregistrarse = new JButton("Registrarse");
		btnregistrarse.setBounds(24, 228, 113, 23);
		getContentPane().add(btnregistrarse);

		JLabel lblUser = new JLabel("User:");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUser.setBounds(158, 60, 82, 26);
		getContentPane().add(lblUser);

		textuser = new JTextField();
		textuser.setBounds(259, 60, 134, 20);
		getContentPane().add(textuser);
		textuser.setColumns(10);

		lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(158, 113, 76, 17);
		getContentPane().add(lblPassword);

		textpass = new JTextField();
		textpass.setBounds(259, 113, 134, 20);
		getContentPane().add(textpass);
		textpass.setColumns(10);

		JButton btninicio = new JButton("inicio");
		btninicio.setBounds(173, 166, 89, 23);
		getContentPane().add(btninicio);

		btnreset = new JButton("Reset");
		btnreset.setBounds(304, 166, 89, 23);
		getContentPane().add(btnreset);

		// imagen de fondo colocada en un label que ocupa todo el interior de la pantalla
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 434, 262);
		getContentPane().add(lblFondo);
		lblFondo.setIcon(new ImageIcon("src/images/background.jpg"));

		// listener del boton reset: pone en blanco los textfields
		btnreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textpass.setText("");
				textuser.setText("");
			}
		});

		// listener del boton inicio, recoge los datos y si tiene privilegios
		// imprime el mensaje "conectado" y sino error.
		btninicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getdatos();
				if (modelo.verificar_login(usuario, password)) {
					lblestado.setText("Conectado!!");
				} else {
					lblestado.setText("Error!!!");
				}
				// si hay mas de dos intentos sale del programa
				if (modelo.getintentos() > 2) {
					System.exit(0);
				}
			}

		});

		// cambia a la ventana registro
		btnregistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vistaRegistro();
			}
		});
	}
	
	public void vistainicio() {
		registro.setVisible(false);
		setVisible(true);
	}
	
	public void vistaRegistro(){
		registro.setVisible(true);
		setVisible(false);
	}

	// recoge los valores puestos en los textfields en las variables de buffer
	public void getdatos() {
		usuario = textuser.getText();
		password = textpass.getText();
	}
	
	public void setModelo (OracleAccess ora){
		this.modelo = ora;
	}
	
	public void setRegistro (Registro reg){
		this.registro = reg;
	}
}