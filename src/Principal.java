import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractListModel;


public class Principal {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
    
	
	private Carcasa carcasa;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	
	public void setCarcasa(Carcasa cr){
		carcasa=cr;
	}
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 501);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JPanel panel_principal = new JPanel();
		panel_principal.setBounds(0, 0, 734, 463);
		frame.getContentPane().add(panel_principal);
		panel_principal.setLayout(null);
		
		final JPanel panel_central = new JPanel();
		panel_central.setBounds(10, 62, 714, 257);
		panel_principal.add(panel_central);
		
		panel_central.setLayout(null);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(62, 28, 21, 20);
		panel_central.add(lblId);
		
		textField = new JTextField();
		textField.setBounds(98, 28, 102, 20);
		panel_central.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("FactorForma");
		lblNewLabel.setBounds(10, 59, 73, 20);
		panel_central.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(98, 59, 102, 20);
		panel_central.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(98, 90, 102, 20);
		panel_central.add(textField_2);
		
		JLabel label = new JLabel("id");
		label.setBounds(62, 90, 21, 20);
		panel_central.add(label);
		
		JLabel label_1 = new JLabel("FactorForma");
		label_1.setBounds(10, 121, 73, 20);
		panel_central.add(label_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(98, 121, 102, 20);
		panel_central.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(346, 28, 102, 20);
		panel_central.add(textField_4);
		
		JLabel label_2 = new JLabel("id");
		label_2.setBounds(298, 28, 21, 20);
		panel_central.add(label_2);
		
		JLabel label_3 = new JLabel("FactorForma");
		label_3.setBounds(263, 59, 73, 20);
		panel_central.add(label_3);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(346, 59, 102, 20);
		panel_central.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(346, 90, 102, 20);
		panel_central.add(textField_6);
		
		JLabel label_5 = new JLabel("FactorForma");
		label_5.setBounds(263, 121, 73, 20);
		panel_central.add(label_5);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(346, 121, 102, 20);
		panel_central.add(textField_7);
		
		JLabel label_4 = new JLabel("FactorForma");
		label_4.setBounds(263, 90, 73, 20);
		panel_central.add(label_4);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(98, 152, 102, 20);
		panel_central.add(textField_8);
		
		JLabel label_6 = new JLabel("FactorForma");
		label_6.setBounds(10, 152, 73, 20);
		panel_central.add(label_6);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(346, 152, 102, 20);
		panel_central.add(textField_9);
		
		JLabel lblFactorformaoiuy = new JLabel("FactorForma1" +
				"23456789oiuy");
		lblFactorformaoiuy.setBounds(196, 152, 140, 31);
		panel_central.add(lblFactorformaoiuy);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(98, 183, 102, 20);
		panel_central.add(textField_10);
		
		JLabel label_8 = new JLabel("FactorForma");
		label_8.setBounds(10, 183, 73, 20);
		panel_central.add(label_8);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(346, 183, 102, 20);
		panel_central.add(textField_11);
		
		JLabel label_9 = new JLabel("FactorForma");
		label_9.setBounds(258, 183, 73, 20);
		panel_central.add(label_9);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(502, 0, 212, 255);
		panel_central.add(scrollPane);
		
		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list_1);
	
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setBounds(30, 417, 89, 23);
		panel_principal.add(btnAlta);
		
		JButton btnmodificar = new JButton("Modificar");
		btnmodificar.setBounds(140, 417, 89, 23);
		panel_principal.add(btnmodificar);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.setBounds(246, 417, 89, 23);
		panel_principal.add(btnBaja);
		
		JButton btnbuscar = new JButton("buscar");
		btnbuscar.setBounds(30, 362, 89, 23);
		panel_principal.add(btnbuscar);
		
		
	
		
		JButton btnCompatible = new JButton("Buscar compatible");
		btnCompatible.setBounds(602, 429, 122, 23);
		panel_principal.add(btnCompatible);
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"inicio", "carcasa", "placa base", "vladimir"}));
		comboBox.setBounds(279, 11, 114, 20);
		panel_principal.add(comboBox);
		
		
		
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    
		    	
		    	JComboBox jb = (JComboBox)e.getSource();
		    	String jc= (String)jb.getSelectedItem();
		  
		    	
		    	switch (jc) {
		    	
		    	case "carcasa":
		    		carcasa.selectCarcasas();
		    		panel_principal.remove(panel_central);
		    		
		    		panel_principal.add(carcasa.getPanel());
		    
		    		frame.setVisible(false);
		       		frame.setVisible(true);
		   
		    		break;
		    	case "inicio":
		    		carcasa.selectCarcasas();
		    	
		    		panel_principal.add(carcasa.getPanel());
		    
		    		frame.setVisible(false);
		       		frame.setVisible(true);
		    	
		    		break;
		    	}
		    	
		    	
		    		
		
		 
		    
		    	
		  
		      
		    }
		});
	}
	}

