package modelo;

import java.util.HashMap;
import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import dto.UbicacionDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;
import persistencia.dao.interfaz.UbicacionDAO;


public class Agenda 
{
	private PersonaDAO persona;	
	private TipoContactoDAO tipoContacto;
	private LocalidadDAO localidad;
	private UbicacionDAO ubicacion;
	
	public Agenda(DAOAbstractFactory metodo_persistencia)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
		this.tipoContacto = metodo_persistencia.createTipoContactoDAO();
		this.localidad = metodo_persistencia.createLocalidadDAO();
		this.ubicacion = metodo_persistencia.createUbicacionDAO();
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
		return this.tipoContacto.readAll();	
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

