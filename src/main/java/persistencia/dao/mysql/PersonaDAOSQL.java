package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO
{
	private static final String insert = "INSERT INTO personas(nombre, telefono, idTipoContacto, idLocalidad, Calle, altura, piso, email, cumpleanios) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String edit = "UPDATE PERSONAS SET Nombre = ?, Telefono = ?, Calle = ?, altura = ?, piso = ?, dpto = ?, email = ?, cumpleanios = ?, idTipoContacto = ?, idLocalidad = ? WHERE idPersona = ?";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM Personas p LEFT JOIN TIPO_CONTACTO t ON p.idTipoContacto = t.idTipoContacto LEFT JOIN LOCALIDAD l ON p.idLocalidad = l.idLocalidad LEFT JOIN PROVINCIA pr ON pr.idProvincia = l.idProvincia LEFT JOIN Pais pa ON pa.idPais = pr.idPais";
	public boolean insert(PersonaDTO persona)
	{

		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setInt(3, persona.getTipoContactoId());
			statement.setString(4, persona.getIdLocalidad());
			statement.setString(5, persona.getCalle());
			statement.setString(6, persona.getAltura());
			statement.setString(7, persona.getPiso());
			statement.setString(8, persona.getEmail());
			statement.setString(9, persona.getCumpleanios());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}
	
	public boolean edit(PersonaDTO persona)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(edit);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getCalle());
			statement.setString(4, persona.getAltura());
			statement.setString(5, persona.getPiso());
			statement.setString(6, persona.getDpto());
			statement.setString(7, persona.getEmail());
			statement.setString(8, persona.getCumpleanios());
			statement.setInt(9, persona.getTipoContactoId());
			statement.setString(10, persona.getIdLocalidad());
			statement.setInt(11, persona.getIdPersona());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}
	
	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPersonaDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	
	private PersonaDTO getPersonaDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idPersona");
		String nombre = resultSet.getString("Nombre");
		String tel = resultSet.getString("Telefono");
		
		PersonaDTO persona = new PersonaDTO(id, nombre, tel);
		persona.setCalle(resultSet.getString("calle"));
		persona.setAltura(resultSet.getString("altura"));
		persona.setPiso(resultSet.getString("piso"));
		persona.setDpto(resultSet.getString("dpto"));
		persona.setEmail(resultSet.getString("email"));
		persona.setIdLocalidad(resultSet.getString("idLocalidad"));
		persona.setTipoContactoId(resultSet.getInt("idTipoContacto"));
		persona.setCumpleanios(resultSet.getString("cumpleanios"));
		persona.setTipoContacto(resultSet.getString("Tipo"));
		persona.setLocalidad(resultSet.getString("localidad"));
		persona.setProvincia(resultSet.getString("provincia"));
		persona.setPais(resultSet.getString("pais"));
		persona.setIdPersona(resultSet.getInt("idPersona"));
		
		return persona; 
	}
}
