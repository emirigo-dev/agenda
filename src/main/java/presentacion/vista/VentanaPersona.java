package presentacion.vista;
 

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import dto.TipoContactoDTO;
import dto.UbicacionDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;


public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField Email;
	private JTextField calle;
	private JTextField altura;
	private JTextField piso;
	private JTextField depto;
	private JDateChooser fechNacimiento;
	private JButton btnAgregarPersona;
	private JButton btnEditarPersona;
	private JComboBox tipoContactoComboBox;
	private static VentanaPersona INSTANCE;
	private static VentanaPersona INSTANCE_EDITAR;
	private JComboBox pais;
	private JComboBox provincia;
	private JComboBox localidad;
	private HashMap<String, TipoContactoDTO> tipoContactoByName;
	List<LocalidadDTO> localidades;
	UbicacionDTO ubicacion = UbicacionDTO.constructor();


	
	public static VentanaPersona getInstance(HashMap<String, TipoContactoDTO> tipoContactoByName)
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPersona(tipoContactoByName); 	
			return new VentanaPersona(tipoContactoByName);
		}
		else
			return new VentanaPersona(tipoContactoByName);
	}
	
	public static VentanaPersona getInstance(HashMap<String, TipoContactoDTO> tipoContactoByName, PersonaDTO persona)
	{
		INSTANCE_EDITAR = new VentanaPersona(tipoContactoByName, persona); 	
		return new VentanaPersona(tipoContactoByName, persona);
	}

	private VentanaPersona(HashMap<String, TipoContactoDTO> tipoContactoByName) 
	{
		super();
		
		this.tipoContactoByName = tipoContactoByName;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 438, 475);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(29, 11, 113, 19);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(29, 52, 113, 19);
		panel.add(lblTelfono);
		
		String[] tipoContacto = this.tipoContactoByName.keySet().toArray(new String[this.tipoContactoByName.keySet().size()]);
		JLabel lblTipoContacto = new JLabel("Tipo de contacto");
		lblTipoContacto.setBounds(29,92,113,19);
		panel.add(lblTipoContacto);
		
		this.tipoContactoComboBox  = new JComboBox(tipoContacto);
		tipoContactoComboBox.setBounds(153, 92, 100, 23);
		panel.add(tipoContactoComboBox);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(29, 131, 114, 19);
		panel.add(lblFechaNacimiento);
		
		JLabel lblDatosDeDomicilio = new JLabel("Datos de Domicilio");
		lblDatosDeDomicilio.setBounds(29, 224, 114, 19);
		panel.add(lblDatosDeDomicilio);
		
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(29, 165, 100, 19);
		panel.add(lblEmail);
		
		
		txtNombre = new JTextField();
		txtNombre.setBounds(153, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(153, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(153, 167, 258, 23);
		panel.add(Email);
		
		
		calle = new JTextField();
		calle.setColumns(10);
		calle.setBounds(153, 252, 258, 23);
		panel.add(calle);
		
		altura = new JTextField();
		altura.setColumns(10);
		altura.setBounds(153, 286, 66, 23);
		panel.add(altura);
		
		piso = new JTextField();
		piso.setColumns(10);
		piso.setBounds(262, 286, 51, 23);
		panel.add(piso);
		
		depto = new JTextField();
		depto.setColumns(10);
		depto.setBounds(360, 286, 51, 23);
		panel.add(depto);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(229, 288, 25, 19);
		panel.add(lblPiso);
		
		JLabel lblDepto = new JLabel("Depto");
		lblDepto.setBounds(325, 288, 36, 19);
		panel.add(lblDepto);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(29, 254, 100, 19);
		panel.add(lblCalle);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(29, 288, 100, 19);
		panel.add(lblAltura);
		
		
		fechNacimiento = new JDateChooser("yyyy/MM/dd","####/##/##", '_');
		fechNacimiento.setBounds(153, 129, 110, 23);
		panel.add(fechNacimiento);
		
	
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(322, 430, 89, 23);
		panel.add(btnAgregarPersona);
		
		pais = new JComboBox();
		pais.setBounds(153, 320, 258, 23);
		panel.add(pais);
		
		provincia = new JComboBox();
		provincia.setBounds(153, 354, 258, 23);
		panel.add(provincia);
		
		localidad = new JComboBox();
		localidad.setBounds(153, 388, 258, 23);
		panel.add(localidad);
		
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(29, 322, 100, 19);
		panel.add(lblPais);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(29, 356, 100, 19);
		panel.add(lblProvincia);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(29, 390, 100, 19);
		panel.add(lblLocalidad);
		
		
		cargarPaises();
		cargarProvincias();
		cargarLocalidades();
		this.pais.addActionListener(e -> cargarProvincias());
		this.provincia.addActionListener(x -> cargarLocalidades());
		
		this.setVisible(false);
	}
	
	private VentanaPersona(HashMap<String, TipoContactoDTO> tipoContactoByName, PersonaDTO persona){
		super();
		
		this.tipoContactoByName = tipoContactoByName;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 438, 475);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(29, 11, 113, 19);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(29, 52, 113, 19);
		panel.add(lblTelfono);
		
		String[] tipoContacto = this.tipoContactoByName.keySet().toArray(new String[this.tipoContactoByName.keySet().size()]);
		JLabel lblTipoContacto = new JLabel("Tipo de contacto");
		lblTipoContacto.setBounds(29,92,113,19);
		panel.add(lblTipoContacto);
		
		this.tipoContactoComboBox  = new JComboBox(tipoContacto);
		tipoContactoComboBox.setBounds(153, 92, 100, 23);
		tipoContactoComboBox.setSelectedItem(persona.getTipoContacto());
		panel.add(tipoContactoComboBox);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(29, 131, 114, 19);
		panel.add(lblFechaNacimiento);
		
		JLabel lblDatosDeDomicilio = new JLabel("Datos de Domicilio");
		lblDatosDeDomicilio.setBounds(29, 224, 114, 19);
		panel.add(lblDatosDeDomicilio);
		
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(29, 165, 100, 19);
		panel.add(lblEmail);
		
		
		txtNombre = new JTextField();
		txtNombre.setBounds(153, 8, 164, 20);
		txtNombre.setText(persona.getNombre());
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(153, 49, 164, 20);
		txtTelefono.setText(persona.getTelefono());
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(153, 167, 258, 23);
		Email.setText(persona.getEmail());
		panel.add(Email);
		
		
		calle = new JTextField();
		calle.setColumns(10);
		calle.setBounds(153, 252, 258, 23);
		calle.setText(persona.getCalle());
		panel.add(calle);
		
		altura = new JTextField();
		altura.setColumns(10);
		altura.setBounds(153, 286, 66, 23);
		altura.setText(persona.getAltura());
		panel.add(altura);
		
		piso = new JTextField();
		piso.setColumns(10);
		piso.setBounds(262, 286, 51, 23);
		piso.setText(persona.getPiso());
		panel.add(piso);
		
		depto = new JTextField();
		depto.setColumns(10);
		depto.setBounds(360, 286, 51, 23);
		depto.setText(persona.getDpto());
		panel.add(depto);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(229, 288, 25, 19);
		panel.add(lblPiso);
		
		JLabel lblDepto = new JLabel("Depto");
		lblDepto.setBounds(325, 288, 36, 19);
		panel.add(lblDepto);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(29, 254, 100, 19);
		panel.add(lblCalle);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(29, 288, 100, 19);
		panel.add(lblAltura);
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		fechNacimiento = new JDateChooser("yyyy/MM/dd","####/##/##", '_');
		fechNacimiento.setDateFormatString(persona.getCumpleanios());
		if (persona.getCumpleanios() != null) {
			LocalDate date = LocalDate.parse(persona.getCumpleanios());
			fechNacimiento.setDate(Date.from(date.atStartOfDay(defaultZoneId).toInstant()));
		}
		fechNacimiento.setBounds(153, 129, 110, 23);
		panel.add(fechNacimiento);
	
		btnEditarPersona = new JButton("Editar");
		btnEditarPersona.setBounds(322, 430, 89, 23);
		panel.add(btnEditarPersona);
		
		pais = new JComboBox();
		pais.setBounds(153, 320, 258, 23);
		panel.add(pais);
		
		provincia = new JComboBox();
		provincia.setBounds(153, 354, 258, 23);
		panel.add(provincia);
		
		localidad = new JComboBox();
		localidad.setBounds(153, 388, 258, 23);
		panel.add(localidad);
		
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(29, 322, 100, 19);
		panel.add(lblPais);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(29, 356, 100, 19);
		panel.add(lblProvincia);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(29, 390, 100, 19);
		panel.add(lblLocalidad);
		
		
		cargarPaises();
		cargarProvincias();
		cargarLocalidades();
		this.pais.addActionListener(e -> cargarProvincias());
		this.provincia.addActionListener(x -> cargarLocalidades());
		
		this.setVisible(false);
	}
	
	public JTextField getPiso() {
		return piso;
	}

	public void setPiso(JTextField piso) {
		this.piso = piso;
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
	
	public JButton getBtnEditarPersona() 
	{
		return btnEditarPersona;
	}

	public void cerrar() {
		
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.Email.setText(null);
		this.calle.setText(null);
		this.altura.setText(null);
		this.piso.setText(null);
		this.depto.setText(null);
		this.fechNacimiento.setDate(null);
		this.tipoContactoComboBox.setSelectedItem("Amigo");
		this.dispose();
	}
	
	public void llenarTipoContacto(HashMap<String, TipoContactoDTO> tipoContactoByName) {
		this.tipoContactoByName = tipoContactoByName;
	}
	
	
	private void cargarPaises() {
		this.pais.removeAllItems();
		for(PaisDTO pais: ubicacion.getPaises()) {
			String item = pais.getPais();
			this.pais.addItem(item);
		}
	}
	
	private void cargarProvincias() {
		this.provincia.removeAllItems();
		if(this.pais.getSelectedItem() != null) {
			String foreingKey = ubicacion.getPaisIdByName(this.pais.getSelectedItem().toString());
			List<ProvinciaDTO> todasProvincias = ubicacion.getProvincias(foreingKey);
			if(!todasProvincias.isEmpty()) {
				for(ProvinciaDTO provincia : todasProvincias) {
					this.provincia.addItem(provincia.getProvincia());
				}
			}
		}
	}
	
	private void cargarLocalidades() {
		this.localidad.removeAllItems();
		if(this.provincia.getSelectedItem() != null) {
			String foreingKey = ubicacion.getProvinciaIdByName(this.provincia.getSelectedItem().toString());
			List<LocalidadDTO> todasLocalidades = ubicacion.getLocalidades(foreingKey);
			if(!todasLocalidades.isEmpty()) {
				for(LocalidadDTO localidad : todasLocalidades) {
					this.localidad.addItem(localidad.getLocalidad());	
				}
			}
		}
	}

	public String getLocalidadName() {
		return this.localidad.getSelectedItem().toString();
	}
	
	
	public JTextField getEmail() {
		return Email;
	}

	public void setEmail(JTextField email) {
		Email = email;
	}

	public JTextField getCalle() {
		return calle;
	}
	

	public JDateChooser getFechNacimiento() {
		return fechNacimiento;
	}

	public void setFechNacimiento(JDateChooser fechNacimiento) {
		this.fechNacimiento = fechNacimiento;
	}

	public void setCalle(JTextField calle) {
		this.calle = calle;
	}

	public JTextField getAltura() {
		return altura;
	}

	public void setAltura(JTextField altura) {
		this.altura = altura;
	}

	public JTextField getDepto() {
		return depto;
	}

	public void setDepto(JTextField depto) {
		this.depto = depto;
	}

	public JComboBox getLocalidad() {
		return localidad;
	}

	public void setLocalidad(JComboBox localidad) {
		this.localidad = localidad;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}
}

