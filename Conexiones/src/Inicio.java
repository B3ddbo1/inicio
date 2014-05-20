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
public class Inicio {

	private static String usuario; // buffer donde se almacena usuario
	private static String password; // buffer donde se almacena contraseña
	private JFrame frame;
	private static JTextField textuser; // texto donde el usuario introduce el
										// nombre de usuario
	private static JTextField textpass; // texto donde el usuario introduce la
										// contraseña
	private static JLabel lblPassword; // label donde aparece "password"
	private JLabel lblestado; // label que indica los errores y exitos
	private static JButton btnreset; // boton reset que borra el texto escrito y
										// lo deja vacio
	private static JButton btnregistrarse; // boton para ir a la ventana de
											// registros

	private static Inicio window;
	private static Registro registro;
	private static OracleAccess modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		modelo = new OracleAccess();
		registro = new Registro();
		registro.setOracleAccess(modelo);
		registro.setInicio(window);

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					window = new Inicio();
					registro = new Registro();

					registro.frame.setVisible(false);
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});

	}

	public Inicio() {
		initialize();
	}

	public static void vistainicio() {
		registro.frame.setVisible(false);
		window.frame.setVisible(true);
	}

	// recoge los valores puestos en los textfields en las variables de buffer
	public void getdatos() {

		usuario = textuser.getText();
		password = textpass.getText();

	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblestado = new JLabel("");
		lblestado.setForeground(Color.RED);
		lblestado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblestado.setBounds(158, 228, 104, 23);
		frame.getContentPane().add(lblestado);
		btnregistrarse = new JButton("Registrarse");
		btnregistrarse.setBounds(24, 228, 113, 23);
		frame.getContentPane().add(btnregistrarse);

		JLabel lblUser = new JLabel("User:");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUser.setBounds(158, 60, 82, 26);
		frame.getContentPane().add(lblUser);

		textuser = new JTextField();
		textuser.setBounds(259, 60, 134, 20);
		frame.getContentPane().add(textuser);
		textuser.setColumns(10);

		lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(158, 113, 76, 17);
		frame.getContentPane().add(lblPassword);

		textpass = new JTextField();
		textpass.setBounds(259, 113, 134, 20);
		frame.getContentPane().add(textpass);
		textpass.setColumns(10);

		JButton btninicio = new JButton("inicio");
		btninicio.setBounds(173, 166, 89, 23);
		frame.getContentPane().add(btninicio);

		btnreset = new JButton("Reset");
		btnreset.setBounds(304, 166, 89, 23);
		frame.getContentPane().add(btnreset);

		// imagen de fondo colocada en un label que ocupa todo el interior de la
		// pantalla
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(lblFondo);
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

				registro.frame.setVisible(true);
				window.frame.setVisible(false);
			}

		});
	}
}