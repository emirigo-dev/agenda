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
import dto.PreferenciaContactoDTO;
import dto.ProvinciaDTO;


public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private static VentanaPersona INSTANCE;
	private static VentanaPersona INSTANCE_EDITAR;
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
	private JComboBox preferenciaContactoComboBox;
	private JComboBox pais;
	private JComboBox provincia;
	private JComboBox localidad;
	private HashMap<String, TipoContactoDTO> tipoContactoByName;
	private HashMap<String, PreferenciaContactoDTO> preferenciaContactoByName;
	List<LocalidadDTO> localidades;
	UbicacionDTO ubicacion;

	
	
	public static VentanaPersona getInstance(HashMap<String, TipoContactoDTO> tipoContactoByName, HashMap<String, PreferenciaContactoDTO> preferenciaContactoByName, UbicacionDTO ubicacion)
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPersona(tipoContactoByName, preferenciaContactoByName, ubicacion); 	
			return new VentanaPersona(tipoContactoByName, preferenciaContactoByName, ubicacion);
		}
		else
			return new VentanaPersona(tipoContactoByName, preferenciaContactoByName, ubicacion);
	}
	
	public static VentanaPersona getInstance(HashMap<String, TipoContactoDTO> tipoContactoByName, HashMap<String, PreferenciaContactoDTO> preferenciaContactoByName, PersonaDTO persona,ArrayList<String> datosUbicacion, UbicacionDTO ubicacion)
	{
		INSTANCE_EDITAR = new VentanaPersona(tipoContactoByName, preferenciaContactoByName, persona, datosUbicacion, ubicacion); 	
		return new VentanaPersona(tipoContactoByName, preferenciaContactoByName, persona, datosUbicacion, ubicacion);
	}

	private VentanaPersona(HashMap<String, TipoContactoDTO> tipoContactoByName, HashMap<String, PreferenciaContactoDTO> preferenciaContactoByName, UbicacionDTO ubicacion) 
	{
		super();
		
		this.tipoContactoByName = tipoContactoByName;
		this.preferenciaContactoByName = preferenciaContactoByName;
		this.ubicacion = ubicacion;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 526, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 526, 600);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(29, 11, 113, 19);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(29, 52, 113, 19);
		panel.add(lblTelfono);
		
		JLabel lblTipoContacto = new JLabel("Tipo de contacto");
		lblTipoContacto.setBounds(29,92,113,19);
		panel.add(lblTipoContacto);
		
		JLabel lblPreferenciaContacto = new JLabel("Preferecia de contacto");
		lblPreferenciaContacto.setBounds(29,131,150,19);
		panel.add(lblPreferenciaContacto);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(29, 171, 114, 19);
		panel.add(lblFechaNacimiento);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(29, 205, 100, 19);
		panel.add(lblEmail);
		
		JLabel lblDatosDeDomicilio = new JLabel("Datos de Domicilio");
		lblDatosDeDomicilio.setBounds(29, 250, 114, 19);
		panel.add(lblDatosDeDomicilio);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(29, 280, 100, 19);
		panel.add(lblCalle);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(29, 314, 100, 19);
		panel.add(lblAltura);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(260, 314, 25, 19);
		panel.add(lblPiso);
		
		JLabel lblDepto = new JLabel("Depto");
		lblDepto.setBounds(343, 314, 36, 19);
		panel.add(lblDepto);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(29, 348, 100, 19);
		panel.add(lblPais);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(29, 396, 100, 19);
		panel.add(lblProvincia);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(29, 444, 100, 19);
		panel.add(lblLocalidad);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(192, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(192, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		String[] tipoContacto = this.tipoContactoByName.keySet().toArray(new String[this.tipoContactoByName.keySet().size()]);
		this.tipoContactoComboBox  = new JComboBox(tipoContacto);
		tipoContactoComboBox.setBounds(192, 92, 100, 23);
		panel.add(tipoContactoComboBox);
		
		String[] preferenciaContacto = this.preferenciaContactoByName.keySet().toArray(new String[this.preferenciaContactoByName.keySet().size()]);
		this.preferenciaContactoComboBox  = new JComboBox(preferenciaContacto);
		preferenciaContactoComboBox.setBounds(192, 131, 100, 23);
		panel.add(preferenciaContactoComboBox);
		
		fechNacimiento = new JDateChooser("yyyy/MM/dd","####/##/##", '_');
		fechNacimiento.setBounds(192, 171, 110, 23);
		panel.add(fechNacimiento);
		
		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(192, 205, 258, 23);
		panel.add(Email);
		
		calle = new JTextField();
		calle.setColumns(10);
		calle.setBounds(192, 280, 258, 23);
		panel.add(calle);
		
		altura = new JTextField();
		altura.setColumns(10);
		altura.setBounds(192, 314, 66, 23);
		panel.add(altura);
		
		piso = new JTextField();
		piso.setColumns(10);
		piso.setBounds(290, 314, 51, 23);
		panel.add(piso);
		
		depto = new JTextField();
		depto.setColumns(10);
		depto.setBounds(380, 314, 51, 23);
		panel.add(depto);
		
		pais = new JComboBox();
		pais.setBounds(192, 348, 258, 23);
		panel.add(pais);
		
		provincia = new JComboBox();
		provincia.setBounds(192, 396, 258, 23);
		panel.add(provincia);
		
		localidad = new JComboBox();
		localidad.setBounds(192, 444, 258, 23);
		panel.add(localidad);
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(322, 485, 89, 23);
		panel.add(btnAgregarPersona);
		
		cargarPaises();
		cargarProvincias();
		cargarLocalidades();
		this.pais.addActionListener(e -> cargarProvincias());
		this.provincia.addActionListener(x -> cargarLocalidades());
		
		this.setVisible(false);
	}
	
	private VentanaPersona(HashMap<String, TipoContactoDTO> tipoContactoByName, HashMap<String, PreferenciaContactoDTO> preferenciaContactoByName, PersonaDTO persona, ArrayList<String> datosUbicacion, UbicacionDTO ubicacion){
		super();
		
		this.tipoContactoByName = tipoContactoByName;
		this.preferenciaContactoByName = preferenciaContactoByName;
		this.ubicacion = ubicacion;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 526, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 526, 600);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(29, 11, 113, 19);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(29, 52, 113, 19);
		panel.add(lblTelfono);
		
		JLabel lblTipoContacto = new JLabel("Tipo de contacto");
		lblTipoContacto.setBounds(29,92,113,19);
		panel.add(lblTipoContacto);
		
		JLabel lblPreferenciaContacto = new JLabel("Preferecia de contacto");
		lblPreferenciaContacto.setBounds(29,131,150,19);
		panel.add(lblPreferenciaContacto);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(29, 171, 114, 19);
		panel.add(lblFechaNacimiento);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(29, 205, 100, 19);
		panel.add(lblEmail);
		
		JLabel lblDatosDeDomicilio = new JLabel("Datos de Domicilio");
		lblDatosDeDomicilio.setBounds(29, 250, 114, 19);
		panel.add(lblDatosDeDomicilio);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(29, 280, 100, 19);
		panel.add(lblCalle);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(29, 314, 100, 19);
		panel.add(lblAltura);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(260, 314, 25, 19);
		panel.add(lblPiso);
		
		JLabel lblDepto = new JLabel("Depto");
		lblDepto.setBounds(343, 314, 36, 19);
		panel.add(lblDepto);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(29, 348, 100, 19);
		panel.add(lblPais);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(29, 396, 100, 19);
		panel.add(lblProvincia);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(29, 444, 100, 19);
		panel.add(lblLocalidad);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(192, 8, 164, 20);
		txtNombre.setText(persona.getNombre());
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(192, 49, 164, 20);
		txtTelefono.setText(persona.getTelefono());
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		String[] tipoContacto = this.tipoContactoByName.keySet().toArray(new String[this.tipoContactoByName.keySet().size()]);
		this.tipoContactoComboBox  = new JComboBox(tipoContacto);
		tipoContactoComboBox.setBounds(192, 92, 100, 23);
		tipoContactoComboBox.setSelectedItem(persona.getTipoContacto());
		panel.add(tipoContactoComboBox);
		
		String[] preferenciaContacto = this.preferenciaContactoByName.keySet().toArray(new String[this.preferenciaContactoByName.keySet().size()]);
		this.preferenciaContactoComboBox  = new JComboBox(preferenciaContacto);
		preferenciaContactoComboBox.setBounds(192, 131, 100, 23);
		preferenciaContactoComboBox.setSelectedItem(persona.getPreferenciaContacto());
		panel.add(preferenciaContactoComboBox);
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		fechNacimiento = new JDateChooser("yyyy/MM/dd","####/##/##", '_');
		fechNacimiento.setDateFormatString(persona.getCumpleanios());
		if (persona.getCumpleanios() != null) {
			LocalDate date = LocalDate.parse(persona.getCumpleanios());
			fechNacimiento.setDate(Date.from(date.atStartOfDay(defaultZoneId).toInstant()));
		}
		fechNacimiento.setBounds(192, 171, 110, 23);
		panel.add(fechNacimiento);
		
		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(192, 205, 258, 23);
		Email.setText(persona.getEmail());
		panel.add(Email);
		
		calle = new JTextField();
		calle.setColumns(10);
		calle.setBounds(192, 280, 258, 23);
		calle.setText(persona.getCalle());
		panel.add(calle);
		
		altura = new JTextField();
		altura.setColumns(10);
		altura.setBounds(192, 314, 66, 23);
		altura.setText(persona.getAltura());
		panel.add(altura);
		
		piso = new JTextField();
		piso.setColumns(10);
		piso.setBounds(290, 314, 51, 23);
		piso.setText(persona.getPiso());
		panel.add(piso);
		
		depto = new JTextField();
		depto.setColumns(10);
		depto.setBounds(380, 314, 51, 23);
		depto.setText(persona.getDpto());
		panel.add(depto);
		
		pais = new JComboBox();
		pais.setBounds(192, 348, 258, 23);
		panel.add(pais);
		
		provincia = new JComboBox();
		provincia.setBounds(192, 396, 258, 23);
		panel.add(provincia);
		
		localidad = new JComboBox();
		localidad.setBounds(192, 444, 258, 23);
		panel.add(localidad);
		
		btnEditarPersona = new JButton("Editar");
		btnEditarPersona.setBounds(322, 485, 89, 23);
		panel.add(btnEditarPersona);
		
		cargarPaises();
		cargarProvincias();
		cargarLocalidades();
		this.pais.addActionListener(e -> cargarProvincias());
		this.provincia.addActionListener(x -> cargarLocalidades());
		pais.setSelectedItem(datosUbicacion.get(0));
		provincia.setSelectedItem(datosUbicacion.get(1));
		localidad.setSelectedItem(datosUbicacion.get(2));

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
	
	public String getPrefereceContactName() {
		return preferenciaContactoComboBox.getSelectedItem().toString();
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
		this.preferenciaContactoComboBox.setSelectedItem("Email");
		this.pais.setSelectedItem("Argentina");
		this.provincia.setSelectedItem("Buenos Aires");
		this.localidad.setSelectedItem("CABA");

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
	
	
	public void llenaUbicacion(UbicacionDTO ubicacion) {
		this.ubicacion = ubicacion;
	}

	public UbicacionDTO getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(UbicacionDTO ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
}

