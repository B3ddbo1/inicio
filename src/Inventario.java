public class Inventario {

	public static void main(String[] args) {
		try {
			OracleAccess miModelo = new OracleAccess();
			Inicio miInicio = new Inicio();
			Registro miRegistro = new Registro(miModelo, miInicio);
			Principal miPrincipal = new Principal();
			Carcasa carcasa = new Carcasa();	
			miPrincipal.setCarcasa(carcasa);
			carcasa.setcodigo(miModelo);
			
			miInicio.setModelo(miModelo);
			miInicio.setRegistro(miRegistro);
			miInicio.setVisible(false);
			miPrincipal.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
