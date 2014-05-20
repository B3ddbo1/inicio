import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;

public class Registro {

	public JFrame frame;
	private static JTextField textuser; // textfield de usuario
	private static JTextField textpass; // textfield de password



	private OracleAccess codigo;
	private Inicio vista;

	public Registro() {
		initialize();
	}


	public void setOracleAccess(OracleAccess o) {
		codigo = o;
	}

	public void setInicio(Inicio v) {
		vista = v;
	}


 
	// envia los datos al modelo
	public static void enviar_datos() {
		try {

			OracleAccess.Registro(textuser.getText(), textpass.getText());

		} catch (Exception e) {
			e.printStackTrace();

		}
	}


	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		final JLabel lblRegistrarse = new JLabel("Registrarse");
		lblRegistrarse.setForeground(Color.WHITE);

		lblRegistrarse.setFont(new Font("Stencil", Font.BOLD, 14));
		lblRegistrarse.setBounds(135, 0, 234, 71);
		frame.getContentPane().add(lblRegistrarse);

		JButton btninicio = new JButton("Inicio");
		btninicio.setBounds(335, 228, 89, 23);
		frame.getContentPane().add(btninicio);

		textpass = new JTextField();
		textpass.setBounds(122, 124, 86, 20);
		frame.getContentPane().add(textpass);
		textpass.setColumns(10);

		textuser = new JTextField();
		textuser.setBounds(122, 66, 86, 20);
		frame.getContentPane().add(textuser);
		textuser.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setBackground(Color.WHITE);
		lblUsuario.setBounds(29, 69, 52, 17);
		frame.getContentPane().add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setBounds(20, 127, 80, 17);
		frame.getContentPane().add(lblContrasea);

		JButton btnregistro = new JButton("Registro!");
		btnregistro.setBounds(119, 179, 89, 23);
		frame.getContentPane().add(btnregistro);

		JLabel lblfondo = new JLabel("");
		lblfondo.setIcon(new ImageIcon("src/images/background.jpg"));
		lblfondo.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(lblfondo);
		
		// listener del boton registro que recoge los valores de los textfield y envia los datos al modelo
		btnregistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					lblRegistrarse.setText(codigo.Registro(textuser.getText(), textpass.getText()));

				} catch (SQLException e) {

					e.printStackTrace();
				}

				enviar_datos();

			}

		});

		//listener que cambia la ventana de registro por la de inicio
		btninicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				vista.vistainicio();

			}

		});

	}
}
