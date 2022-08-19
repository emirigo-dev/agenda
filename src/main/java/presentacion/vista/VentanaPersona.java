package presentacion.vista;
 

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.TipoContactoDTO;

public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnAgregarPersona;
	private JComboBox tipoContactoComboBox;
	private static VentanaPersona INSTANCE;
	private HashMap<String, TipoContactoDTO> tipoContactoByName;

	
	public static VentanaPersona getInstance(HashMap<String, TipoContactoDTO> tipoContactoByName)
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPersona(tipoContactoByName); 	
			return new VentanaPersona(tipoContactoByName);
		}
		else
			return INSTANCE;
	}

	private VentanaPersona(HashMap<String, TipoContactoDTO> tipoContactoByName) 
	{
		super();
		
		this.tipoContactoByName = tipoContactoByName;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 123);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);
		
		String[] tipoContacto = this.tipoContactoByName.keySet().toArray(new String[this.tipoContactoByName.keySet().size()]);
		JLabel lblTipoContacto = new JLabel("Tipo de contacto");
		lblTipoContacto.setBounds(10,92,113,14);
		panel.add(lblTipoContacto);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		this.tipoContactoComboBox  = new JComboBox(tipoContacto);
		tipoContactoComboBox.setBounds(133, 92, 113, 14);
		panel.add(tipoContactoComboBox);
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(208, 92, 89, 23);
		panel.add(btnAgregarPersona);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}
	
	public String getContactTypeName() {
		return tipoContactoComboBox.getSelectedItem().toString();

	}

	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}

	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.dispose();
	}
	
	public void llenarTipoContacto(HashMap<String, TipoContactoDTO> tipoContactoByName) {
		this.tipoContactoByName = tipoContactoByName;
	}
	
}

