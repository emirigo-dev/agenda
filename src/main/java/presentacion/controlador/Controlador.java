package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personasEnTabla;
		private HashMap<String, TipoContactoDTO> tipoDeContactoByName;
		private HashMap<String, LocalidadDTO> localidadByName;
		private HashMap<String, ProvinciaDTO> provinciaById;
		private HashMap<String, PaisDTO> paisById;
		private VentanaPersona ventanaPersona; 
		private Agenda agenda;
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
			this.agenda = agenda;
			this.ventanaPersona = VentanaPersona.getInstance(this.agenda.obtenerTipoContacto());		
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
		}
		
		private void ventanaAgregarPersona(ActionEvent a) {
			this.ventanaPersona.mostrarVentana();
		}

		private void guardarPersona(ActionEvent p) {
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			String tipoContacto = this.ventanaPersona.getContactTypeName();
			String localidadPersona = this.ventanaPersona.getLocalidadName();
			String callePersona = this.ventanaPersona.getCalle().getText();
			String alturaCalle = this.ventanaPersona.getAltura().getText();
			String piso = this.ventanaPersona.getPiso().getText();
			String email = this.ventanaPersona.getEmail().getText();
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(this.ventanaPersona.getFechNacimiento().getDate());
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel);
			nuevaPersona.setTipoContactoId(tipoDeContactoByName.get(tipoContacto).getIdTipoContacto());
			nuevaPersona.setLocalidad(localidadByName.get(localidadPersona).getLocalidad());
			nuevaPersona.setIdLocalidad(localidadByName.get(localidadPersona).getIdLocalidad());
			nuevaPersona.setCalle(callePersona);
			nuevaPersona.setAltura(alturaCalle);
			nuevaPersona.setPiso(piso);
			nuevaPersona.setEmail(email);
			nuevaPersona.setCumpleanios(date);
			this.agenda.agregarPersona(nuevaPersona);
			this.refrescarTabla();
			this.ventanaPersona.cerrar();
			
		}

		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();	
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
		
		public void inicializar()
		{
			this.refrescarTabla();
			this.vista.show();
		}
		
		private void refrescarTabla()
		{
			this.personasEnTabla = agenda.obtenerPersonas();
			this.tipoDeContactoByName = agenda.obtenerTipoContacto();
			this.localidadByName = agenda.obtenerLocalidades();
			this.provinciaById = agenda.obtenerProvincias();
			this.paisById = agenda.obtenerPaises();
			this.ventanaPersona.llenarTipoContacto(this.tipoDeContactoByName);
		
			
			this.vista.llenarTabla(this.personasEnTabla);
		}
		
//		public static HashMap<Integer, TipoContactoDTO> hola(){
//			return;
//		}
//		this.tipoDeContactoById = agenda.obtenerTipoContacto();
//		this.localidadById = agenda.obtenerLocalidades();
//		this.provinciaById = agenda.obtenerProvincias();
//		this.paisById = agenda.obtenerPaises();

		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
