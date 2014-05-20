import java.sql.*;
/*
 * Maneja el intercambio de datos dentro de la base de datos y la aplicacion
 */
public class OracleAccess {
	// Atributos de la clase

	private static String surl = "jdbc:oracle:thin:@localhost:1521:XE";
	private static Connection conexion;
	static Statement stmt;
	static ResultSet rset;
	static boolean autentificacion_correcta = false; // se pone true si se produce un login exitoso  y false si hay error
	static String temp;  // almacena los String de las sentencias selects
	static int intentos; // intentos totales antes de que se cierre la aplicacion (maximo 3)

	// Constructor que crea la conexión
	public OracleAccess() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(surl, "issinatour",
					"issinatour90");
			System.out.println(" - Conexión con ORACLE establecida -");

		} catch (Exception e) {
			System.out.println(" – Error de Conexión con ORACLE-");
			e.printStackTrace();
		}
		intentos=0;
	}
//  recoge las sentencias sql, las ejecuta y devuelve el resultado en  temp
	public static String Consulta(String query, int columna)
			throws SQLException {

		stmt = conexion.createStatement();
		rset = stmt.executeQuery(query);
		while (rset.next())

			temp = rset.getString(columna);

		// rset.close();
		// stmt.close();
		return temp;

	}
// recoge las sentencias sql INSERT, y las realiza
	public static void Insertar(String query, int columna) throws SQLException {
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
	public static String Registro(String user, String pass) throws SQLException {

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

				passwordreal = OracleAccess.Consulta(
						"SELECT PASSWORD FROM issinatour.USUARIOS WHERE NOMBRE="
								+ "'" + usuario + "'", 1);

			} else {

				OracleAccess.Consulta(
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