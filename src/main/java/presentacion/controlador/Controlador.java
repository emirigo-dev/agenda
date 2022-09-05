package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Agenda;
import persistencia.conexion.Conexion;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.SelectorReporte;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import presentacion.vista.VistaRegistro;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.PreferenciaContactoDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import dto.UbicacionDTO;

public class Controlador implements ActionListener
{
		private Vista vista;
		private VistaRegistro vistaRegistro;
		private List<PersonaDTO> personasEnTabla;
		private HashMap<String, TipoContactoDTO> tipoDeContactoByName;
		private HashMap<String, PreferenciaContactoDTO> preferenciaContactoByName;
		private HashMap<String, LocalidadDTO> localidadByName;
		private HashMap<String, ProvinciaDTO> provinciaById;
		private HashMap<String, PaisDTO> paisById;
		private UbicacionDTO ubicacion;
		private VentanaPersona ventanaPersona;
		private VentanaPersona ventanaPersonaEditar;
		private SelectorReporte selectorReporte;
		private Agenda agenda;
		private Conexion conexion;
		
		public Controlador()
		{
			
		}
		
		public void inicializar()
		{
			this.vistaRegistro = new VistaRegistro();
			this.vistaRegistro.getBtnIniciar().addActionListener(x->validarUsuario(x));
		}
		
		private void validarUsuario(ActionEvent x) {
			String user = this.vistaRegistro.getUser();
			String pass = this.vistaRegistro.getPassword();
			
			if (Conexion.successConnection(user, pass)) {
				this.conexion = Conexion.getConexion();
				conexion.setUser(user);
				conexion.setPassword(pass);
				
				this.vistaRegistro.ocultarVista();
				iniciarAgenda();
			} else {
				this.vistaRegistro.showError();
				this.vistaRegistro.limpiarCampos();
			}
		}
		
		private void iniciarAgenda () {
			
			this.vista = new Vista();
			this.agenda = new Agenda(new DAOSQLFactory(), this.conexion);
			
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnEditar().addActionListener(b->ventanaEditarPersona(b));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(j->ventanaSelectorReporte(j));
			
			this.ventanaPersona = VentanaPersona.getInstance(this.agenda.obtenerTipoContacto(), this.agenda.obtenerPreferenciaContacto(), this.agenda.obtenerUbicaciones());		
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			
			this.selectorReporte = SelectorReporte.getInstance();
			this.selectorReporte.getBtnReportePreferenciaContacto().addActionListener(r->mostrarReportePreferenciaContacto(r));
			this.selectorReporte.getBtnReporteUbicacion().addActionListener(k->mostrarReporteUbicacion(k));
			
			this.refrescarTabla();
			this.vista.show();
		}
		
		private void ventanaAgregarPersona(ActionEvent a) {
			this.ventanaPersona.mostrarVentana();
		}
		
		private void ventanaSelectorReporte (ActionEvent j) {
			this.selectorReporte.mostrarVentana();
		}
		
		private void ventanaEditarPersona(ActionEvent b) {
			if (this.vista.getTablaPersonas().getSelectedRow() >= 0 ) {
				PersonaDTO persona = this.personasEnTabla.get(this.vista.getTablaPersonas().getSelectedRow());
				ArrayList<String> datosUbicacion = new ArrayList<String>();
				datosUbicacion.add(this.personasEnTabla.get(this.vista.getTablaPersonas().getSelectedRow()).getPais());
				datosUbicacion.add(this.personasEnTabla.get(this.vista.getTablaPersonas().getSelectedRow()).getProvincia());
				datosUbicacion.add(this.personasEnTabla.get(this.vista.getTablaPersonas().getSelectedRow()).getLocalidad());
				this.ventanaPersonaEditar = VentanaPersona.getInstance(this.agenda.obtenerTipoContacto(), this.agenda.obtenerPreferenciaContacto(), persona, datosUbicacion, this.agenda.obtenerUbicaciones());		
				this.ventanaPersonaEditar.getBtnEditarPersona().addActionListener(e->editarPersona(e, persona.getIdPersona()));
				
				this.ventanaPersonaEditar.mostrarVentana();
			} else {
				this.vista.showError();
			}
		}

		private void guardarPersona(ActionEvent p) {
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			String tipoContacto = this.ventanaPersona.getContactTypeName();
			String preferenciaContacto = this.ventanaPersona.getPrefereceContactName();
			String localidadPersona = this.ventanaPersona.getLocalidadName();
			String callePersona = this.ventanaPersona.getCalle().getText();
			String alturaCalle = this.ventanaPersona.getAltura().getText();
			String piso = this.ventanaPersona.getPiso().getText();
			String dpto = this.ventanaPersona.getDepto().getText();
			String email = this.ventanaPersona.getEmail().getText();
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel);
			nuevaPersona.setTipoContactoId(tipoDeContactoByName.get(tipoContacto).getIdTipoContacto());
			nuevaPersona.setPreferenciaContactoId(preferenciaContactoByName.get(preferenciaContacto).getIdPreferenciaContacto());
			nuevaPersona.setLocalidad(localidadByName.get(localidadPersona).getLocalidad());
			nuevaPersona.setIdLocalidad(localidadByName.get(localidadPersona).getIdLocalidad());
			nuevaPersona.setCalle(callePersona);
			nuevaPersona.setAltura(alturaCalle);
			nuevaPersona.setPiso(piso);
			nuevaPersona.setDpto(dpto);
			nuevaPersona.setEmail(email);
			if (this.ventanaPersona.getFechNacimiento().getDate() != null) {				
				String date = simpleDateFormat.format(this.ventanaPersona.getFechNacimiento().getDate());
				nuevaPersona.setCumpleanios(date);
			}
			this.agenda.agregarPersona(nuevaPersona);
			this.refrescarTabla();
			this.ventanaPersona.cerrar();
		}
		
		private void editarPersona(ActionEvent p, int idPersona) {
			String nombre = this.ventanaPersonaEditar.getTxtNombre().getText();
			String tel = ventanaPersonaEditar.getTxtTelefono().getText();
			String tipoContacto = this.ventanaPersonaEditar.getContactTypeName();
			String preferenciaContacto = this.ventanaPersonaEditar.getPrefereceContactName();
			String localidadPersona = this.ventanaPersonaEditar.getLocalidadName();
			String callePersona = this.ventanaPersonaEditar.getCalle().getText();
			String alturaCalle = this.ventanaPersonaEditar.getAltura().getText();
			String piso = this.ventanaPersonaEditar.getPiso().getText();
			String dpto = this.ventanaPersonaEditar.getDepto().getText();
			String email = this.ventanaPersonaEditar.getEmail().getText();
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel);
			nuevaPersona.setTipoContactoId(tipoDeContactoByName.get(tipoContacto).getIdTipoContacto());
			nuevaPersona.setPreferenciaContactoId(preferenciaContactoByName.get(preferenciaContacto).getIdPreferenciaContacto());
			System.out.println("PREFERENCIA NAME");
			System.out.println(preferenciaContacto);
			nuevaPersona.setLocalidad(localidadByName.get(localidadPersona).getLocalidad());
			nuevaPersona.setIdLocalidad(localidadByName.get(localidadPersona).getIdLocalidad());
			nuevaPersona.setCalle(callePersona);
			nuevaPersona.setAltura(alturaCalle);
			nuevaPersona.setPiso(piso);
			nuevaPersona.setDpto(dpto);
			nuevaPersona.setEmail(email);
			if (this.ventanaPersonaEditar.getFechNacimiento().getDate() != null) {				
				String date = simpleDateFormat.format(this.ventanaPersonaEditar.getFechNacimiento().getDate());
				nuevaPersona.setCumpleanios(date);
			}
			nuevaPersona.setIdPersona(idPersona);
			this.agenda.editarPersona(nuevaPersona);
			this.refrescarTabla();
			this.ventanaPersonaEditar.cerrar();
		}

		private void mostrarReportePreferenciaContacto(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda("ReporteAgenda.jasper", this.conexion);
			reporte.mostrar();	
			this.selectorReporte.ocultarVentana();
		}
		
		private void mostrarReporteUbicacion(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda("ReporteUbicacion.jasper", this.conexion);
			reporte.mostrar();	
			this.selectorReporte.ocultarVentana();
		}

		public void borrarPersona(ActionEvent s)
		{
			int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila : filasSeleccionadas)
			{
				this.agenda.borrarPersona(this.personasEnTabla.get(fila));
			}
			
			this.refrescarTabla();
		}
		
		private void refrescarTabla()
		{
			this.personasEnTabla = agenda.obtenerPersonas();
			this.tipoDeContactoByName = agenda.obtenerTipoContacto();
			this.preferenciaContactoByName = agenda.obtenerPreferenciaContacto();
			this.ubicacion = agenda.obtenerUbicaciones();
			this.localidadByName = agenda.obtenerLocalidades();
			this.ventanaPersona.llenarTipoContacto(this.tipoDeContactoByName);
			this.ventanaPersona.llenaUbicacion(this.ubicacion);
			
			this.vista.llenarTabla(this.personasEnTabla);
		}

		@Override
		public void actionPerformed(ActionEvent e) { }
		
}