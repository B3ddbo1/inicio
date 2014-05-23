import java.awt.Component;
import java.sql.*;
import java.util.LinkedList;
/*
 * Maneja el intercambio de datos dentro de la base de datos y la aplicacion
 */
public class OracleAccess {
	// Atributos de la clase

	
	private String surl = "jdbc:oracle:thin:@localhost:1521:XE";
	private Connection conexion;
	private Statement stmt;
	private ResultSet rset;
	private boolean autentificacion_correcta = false; // se pone true si se produce un login exitoso  y false si hay error
	private String temp;  // almacena los String de las sentencias selects
	private int intentos; // intentos totales antes de que se cierre la aplicacion (maximo 3)
//	private String usr = "issinatour";
	private String usr = "vlad";
//	private String pwd = "issinatour90";
	private String pwd = "vlad";


	// Constructor que crea la conexión
	public OracleAccess() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(surl, "vlad",
					"vlad");
			System.out.println(" - Conexión con ORACLE establecida -");

		} catch (Exception e) {
			System.out.println(" – Error de Conexión con ORACLE-");
			e.printStackTrace();
		}
		intentos=0;
	}
//  recoge las sentencias sql, las ejecuta y devuelve el resultado en  temp
	public String Consulta(String query, int columna) throws SQLException {
		
		stmt = conexion.createStatement();
		rset = stmt.executeQuery(query);
		rset.next();
		return rset.getString(columna);
	}
	
	////////////////////////////////////////////////
	
	//consulta una fila 
	
	public LinkedList <String> consulta_caracteristicas(String query) throws SQLException {
		LinkedList<String> caracteristicas = new LinkedList <String>();
		stmt = conexion.createStatement();
		rset = stmt.executeQuery(query);
		ResultSetMetaData rsmd = rset.getMetaData();
		rset.next();
		int test= rsmd.getColumnCount();
		

		for(int i = 1; i<=test; i++){
			
			caracteristicas.add(rset.getString(i));
		}
			
		return  caracteristicas;
	
	}
	
	
	
	
	/////////////////////////////////////////
	
	
	
	
	//consulta todas las filas de una columna
public LinkedList <String> Consulta_todo(String query, int columna) throws SQLException { 
		LinkedList<String> consulta = new LinkedList <String>();
		stmt = conexion.createStatement();
		rset = stmt.executeQuery(query);
		while (rset.next())
			consulta.add(rset.getString(columna));
			;
			
		return  consulta;
	
	}
	
// recoge las sentencias sql INSERT, y las realiza
	public void Insertar(String query, int columna) throws SQLException {
		stmt = conexion.createStatement();
		stmt.executeUpdate(query);
		stmt.close();
	}
	
	// Trigger que autoincrementa las id de los usuarios antes de que se inserte el dato y le asigna el id
	/*
	 *CREATE SEQUENCE usuarioIncremento
  MINVALUE 1
  INCREMENT BY 1
  START WITH 1
  nomaxvalue
;

Create or replace Trigger Triggerup
  before insert on issinatour.USUARIOS
  for each row
  begin
    Select issinatour.usuarioIncremento.nextval 
    into:new.ID_USUARIO 
    from dual;
  end; 
	 *
	 */
	
	
  // registra un nuevo usuario en la BBDD, si ya existe no crea un usuario nuevo y devuelve string con el resultado de la operacion
	public String Registro(String user, String pass) throws SQLException {
		String comparar = Consulta(String.format(
				"SELECT NOMBRE FROM issinatour.USUARIOS WHERE NOMBRE= '%s' ",
				user), 1);
		if (!user.equals(comparar)) {
			Insertar(
					"INSERT INTO issinatour.USUARIOS (NOMBRE,PASSWORD) VALUES ("
							+ "'" + user + "'" + ",'" + pass + "'" + ")", 1);
		} else {
			return "Este nombre ya existe";
		}
		return "Registro completo";
	}
	
// verifica la contraseña de la base de datos y la contraseña , si son iguales autentificacion_correcta=true, sino autentificacion_correcta=false y suma 1 inteno
	public void verificar_pass(String pass, String pass2) throws SQLException {
		if (pass2.equals(pass)) {
			autentificacion_correcta = true;
		} else {
			autentificacion_correcta = false;
			intentos++;
		}
	}
	
//recibe usuario y password y verifica que tenga privilegios; si no se introduce ningun usuario devuelve false
	public boolean verificar_login(String usuario, String password) {
		String passwordreal = "p";
		String registros = "";
		// si el usuario esta vacio no te puedes loguear
		try {
			if (usuario.equals("")) {
				verificar_pass(password, passwordreal);
				return activo();
			}
			registros = (Consulta(
					"SELECT NOMBRE FROM issinatour.USUARIOS WHERE NOMBRE= "
							+ "'" + usuario + "'", 1));
			if (usuario.equals(registros)) {
				passwordreal = Consulta(
						"SELECT PASSWORD FROM issinatour.USUARIOS WHERE NOMBRE="
								+ "'" + usuario + "'", 1);
			} else {
				Consulta(
						"SELECT PASSWORD FROM issinatour.USUARIOS WHERE NOMBRE="
								+ "'" + usuario + "'", 1);
			}
			verificar_pass(password, passwordreal);
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return activo();
	}
	
	
	public int getintentos(){
		return intentos;
	}

	public boolean activo() {
		return autentificacion_correcta;
	}
}