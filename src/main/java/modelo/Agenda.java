package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.PreferenciaContactoDTO;
import dto.TipoContactoDTO;
import dto.UbicacionDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.PreferenciaContactoDAO;
import persistencia.dao.interfaz.TipoContactoDAO;
import persistencia.dao.interfaz.UbicacionDAO;


public class Agenda 
{
	private PersonaDAO persona;	
	private TipoContactoDAO tipoContacto;
	private PreferenciaContactoDAO preferenciaContacto;
	private LocalidadDAO localidad;
	private UbicacionDAO ubicacion;
	private Conexion conexion;
	
	public Agenda(DAOAbstractFactory metodo_persistencia, Conexion conexion)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
		this.tipoContacto = metodo_persistencia.createTipoContactoDAO();
		this.preferenciaContacto = metodo_persistencia.createPreferenciaContactoDAO();
		this.localidad = metodo_persistencia.createLocalidadDAO();
		this.ubicacion = metodo_persistencia.createUbicacionDAO();
		this.conexion = conexion;
	}
	
	public void agregarPersona(PersonaDTO nuevaPersona)
	{
		this.persona.insert(nuevaPersona);
	}
	
	public void editarPersona(PersonaDTO nuevaPersona)
	{
		this.persona.edit(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) 
	{
		this.persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas()
	{
		return this.persona.readAll();		
	}
	
	public HashMap<String, TipoContactoDTO> obtenerTipoContacto()
	{
		return this.tipoContacto.readAll(this.conexion);	
	}
	
	public HashMap<String, PreferenciaContactoDTO> obtenerPreferenciaContacto()
	{
		return this.preferenciaContacto.readAll();	
	}
	

	public HashMap<String, LocalidadDTO> obtenerLocalidades()
	{
		return this.localidad.readAll();	
	}
	
	public UbicacionDTO obtenerUbicaciones()
	{
		return this.ubicacion.readAll();	
	}
}

