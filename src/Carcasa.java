import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;

//clase carcasa
public class Carcasa {
	
	private OracleAccess codigo;

	JTextField arrayTextFields[];
	
	JPanel panel_Carcasa;
	JList list_1;
	String id;//temp seleccionado


	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	
	public void setcodigo(OracleAccess ora){
		codigo=ora;
	}
	
	public void selectCarcasas() {
		String consulta= "SELECT * FROM vlad.CARCASA ";
		
		try {
		
		final LinkedList <String> hola =	codigo.Consulta_todo(consulta, 1);
		final String [] prueba = new String [hola.size()] ;
		hola.toArray(prueba);
		list_1.setModel(new AbstractListModel() {
			String[] values =  prueba	;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	////////////////////////////
	
	
	MouseListener mouseListener = new MouseAdapter() {
	      public void mouseClicked(MouseEvent mouseEvent) {
	    	  list_1 = (JList) mouseEvent.getSource();
	        if (mouseEvent.getClickCount() == 1) {
	          int index = list_1.locationToIndex(mouseEvent.getPoint());
	          if (index >= 0) {
	            Object o = list_1.getModel().getElementAt(index);
//	            System.out.println("Double-clicked on: " + o.toString());
//	            System.out.println(list_1.getSelectedValue());
	            muestraCarac((String)list_1.getSelectedValue());
	            
	          }
	        }
	      }};
	
	      
	   public void muestraCarac(String id){   
				String consulta= "SELECT * FROM vlad.CARCASA WHERE ID_CARCASA="+id;
				
				try {
					 System.out.println(id);

				 LinkedList <String> hola =	codigo.consulta_caracteristicas(consulta);
	

				 
				 for(int i=0;i<arrayTextFields.length;i++){
					 arrayTextFields[i].setText(hola.get(i));

					 
				 }
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			}
	
	
	
	///////////////////////
	
	
	
	public JPanel getPanel(){
		
		return panel_Carcasa;
	}
	public Carcasa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		arrayTextFields=new JTextField[9];

		panel_Carcasa = new JPanel();
		panel_Carcasa.setBounds(10, 62, 714, 257);
	
		
		panel_Carcasa.setLayout(null);
		
		JLabel label_1 = new JLabel("ID_CARCASA");
		label_1.setBounds(10, 28, 73, 20);
		panel_Carcasa.add(label_1);
		
		arrayTextFields[0] = new JTextField();
		arrayTextFields[0].setBounds(98, 28, 102, 20);
		panel_Carcasa.add(arrayTextFields[0]);
		arrayTextFields[0].setColumns(10);
		
		
		
		JLabel lblFactorforma = new JLabel("FACTORFORMA");
		lblFactorforma.setBounds(253, 28, 83, 20);
		panel_Carcasa.add(lblFactorforma);
		
		arrayTextFields[1] = new JTextField();
		arrayTextFields[1].setColumns(10);
		arrayTextFields[1].setBounds(346, 28, 102, 20);
		panel_Carcasa.add(arrayTextFields[1]);
		
		JLabel lblBahiasy = new JLabel("BAHIAS3Y14");
		lblBahiasy.setBounds(10, 59, 73, 20);
		panel_Carcasa.add(lblBahiasy);
		
		arrayTextFields[2] = new JTextField();
		arrayTextFields[2].setBounds(98, 59, 102, 20);
		panel_Carcasa.add(arrayTextFields[2]);
		arrayTextFields[2].setColumns(10);
		
		JLabel lblBahiasy_1 = new JLabel("BAHIAS5Y14");
		lblBahiasy_1.setBounds(253, 59, 83, 20);
		panel_Carcasa.add(lblBahiasy_1);
		
		arrayTextFields[3] = new JTextField();
		arrayTextFields[3].setColumns(10);
		arrayTextFields[3].setBounds(346, 59, 102, 20);
		panel_Carcasa.add(arrayTextFields[3]);
		
		JLabel lblClase = new JLabel("CLASE");
		lblClase.setBounds(10, 90, 73, 20);
		panel_Carcasa.add(lblClase);
		
		arrayTextFields[4] = new JTextField();
		arrayTextFields[4].setColumns(10);
		arrayTextFields[4].setBounds(98, 90, 102, 20);
		panel_Carcasa.add(arrayTextFields[4]);
		
		JLabel lblArmario = new JLabel("ARMARIO");
		lblArmario.setBounds(253, 90, 83, 20);
		panel_Carcasa.add(lblArmario);
		
		arrayTextFields[5] = new JTextField();
		arrayTextFields[5].setColumns(10);
		arrayTextFields[5].setBounds(346, 90, 102, 20);
		panel_Carcasa.add(arrayTextFields[5]);
		
		JLabel lblAlto = new JLabel("ALTO");
		lblAlto.setBounds(10, 121, 73, 20);
		panel_Carcasa.add(lblAlto);
		
		arrayTextFields[6] = new JTextField();
		arrayTextFields[6].setColumns(10);
		arrayTextFields[6].setBounds(98, 121, 102, 20);
		panel_Carcasa.add(arrayTextFields[6]);
		
		JLabel lblAncho = new JLabel("ANCHO");
		lblAncho.setBounds(253, 121, 83, 20);
		panel_Carcasa.add(lblAncho);
		
		arrayTextFields[7] = new JTextField();
		arrayTextFields[7].setColumns(10);
		arrayTextFields[7].setBounds(346, 121, 102, 20);
		panel_Carcasa.add(arrayTextFields[7]);
		
		JLabel lblLargo = new JLabel("LARGO");
		lblLargo.setBounds(10, 152, 73, 20);
		panel_Carcasa.add(lblLargo);
		
		arrayTextFields[8] = new JTextField();
		arrayTextFields[8].setColumns(10);
		arrayTextFields[8].setBounds(98, 152, 102, 20);
		panel_Carcasa.add(arrayTextFields[8]);
		

		

		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(518, 0, 186, 257);
		panel_Carcasa.add(scrollPane);
		
		list_1 = new JList();
		scrollPane.setViewportView(list_1);
	
	      list_1.addMouseListener(mouseListener); //ad listener

	
	}
}
