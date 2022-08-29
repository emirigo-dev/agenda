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
	private static final String insert = "INSERT INTO personas(nombre, telefono, idTipoContacto, idLocalidad, Calle, altura, piso, dpto, email, cumpleanios, idPreferenciaContacto) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String edit = "UPDATE PERSONAS SET Nombre = ?, Telefono = ?, Calle = ?, altura = ?, piso = ?, dpto = ?, email = ?, cumpleanios = ?, idTipoContacto = ?, idLocalidad = ?, idPreferenciaContacto = ? WHERE idPersona = ?";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM Personas p LEFT JOIN TIPO_CONTACTO t ON p.idTipoContacto = t.idTipoContacto LEFT JOIN PREFERENCIA_CONTACTO pc ON p.idPreferenciaContacto = pc.idPreferenciaContacto LEFT JOIN LOCALIDAD l ON p.idLocalidad = l.idLocalidad LEFT JOIN PROVINCIA pr ON pr.idProvincia = l.idProvincia LEFT JOIN Pais pa ON pa.idPais = pr.idPais";
	private static final String readAllJasperContactoPrefencia = "SELECT idPersona, Nombre, Telefono, total, rf.preferenciaContacto FROM Personas P INNER JOIN (select L_2.idPreferenciaContacto, L_2.preferenciaContacto, count(distinct P_2.idPersona) * 100.0 / sum(count( L_2.idPreferenciaContacto)) over() as Total from personas as P_2 left join PREFERENCIA_CONTACTO as L_2 on P_2.idPreferenciaContacto = L_2.idPreferenciaContacto group by L_2.idPreferenciaContacto) as rf ON P.idPreferenciaContacto = rf.idPreferenciaContacto ORDER BY rf.preferenciaContacto, nombre";
	private static final String readAllJasperUbicacion = "SELECT idPersona, Telefono, nombre, pa.pais, pr.provincia, l.localidad, totalLocalidad, totalProvincia, totalPais FROM Personas P LEFT JOIN TIPO_CONTACTO T ON p.idTipoContacto = t.idTipoContacto LEFT JOIN LOCALIDAD L ON p.idLocalidad = l.idLocalidad LEFT JOIN PROVINCIA pr ON pr.idProvincia = l.idProvincia LEFT JOIN Pais pa ON pa.idPais = pr.idPais INNER JOIN (select L_2.idLocalidad, count(distinct P_2.idPersona) * 100.0 / sum(count(P_2.idPersona)) over() as TotalLocalidad from personas as P_2 left join localidad as L_2 on P_2.idLocalidad = L_2.idLocalidad group by L_2.idLocalidad) as sq ON L.idLocalidad = sq.idLocalidad INNER JOIN (SELECT Prov_2.idProvincia, count(distinct Loc_2.idPersona) * 100.0 / sum(count(Loc_2.idPersona)) over() as TotalProvincia FROM personas as Loc_2 LEFT JOIN localidad as s ON Loc_2.idLocalidad = s.idLocalidad LEFT JOIN provincia as Prov_2 ON s.idProvincia = Prov_2.idProvincia GROUP BY Prov_2.idProvincia) as sqProv ON pr.idProvincia = sqProv.idProvincia INNER JOIN (SELECT paisQ.idPais, count(distinct personaPais.idPersona) * 100.0 / sum(count(personaPais.idPersona)) over() as TotalPais FROM personas as personaPais LEFT JOIN localidad as locPais ON personaPais.idLocalidad = locPais.idLocalidad LEFT JOIN provincia as ProvPais ON locPais.idProvincia = ProvPais.idProvincia LEFT JOIN Pais as paisQ ON ProvPais.idPais = paisQ.idPais GROUP BY  paisQ.idPais) as sqPais ON pa.idPais = sqPais.idPais ORDER BY pa.pais, pr.provincia, L.localidad";

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
			statement.setString(8, persona.getDpto());
			statement.setString(9, persona.getEmail());
			statement.setString(10, persona.getCumpleanios());
			statement.setInt(11, persona.getPreferenciaContactoId());
			
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
			statement.setInt(11, persona.getPreferenciaContactoId());
			statement.setInt(12, persona.getIdPersona());
			
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
	
	public ArrayList<PersonaDTO> readAllJasperContactoPrefencia()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAllJasperContactoPrefencia);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPreferenciaContactoCount(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	
	private PersonaDTO getPreferenciaContactoCount(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idPersona");
		String nombre = resultSet.getString("nombre");
		String tel = resultSet.getString("telefono");
		PersonaDTO persona = new PersonaDTO(id, nombre, tel);
		persona.setTotal(resultSet.getDouble("total"));
		persona.setPreferenciaContacto(resultSet.getString("preferenciaContacto"));
		return persona; 

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
        persona.setPreferenciaContactoId(resultSet.getInt("idPreferenciaContacto"));
        persona.setCumpleanios(resultSet.getString("cumpleanios"));
        persona.setTipoContacto(resultSet.getString("Tipo"));
        persona.setPreferenciaContacto(resultSet.getString("preferenciaContacto"));
        persona.setLocalidad(resultSet.getString("localidad"));
        persona.setProvincia(resultSet.getString("provincia"));
        persona.setPais(resultSet.getString("pais"));
        persona.setIdPersona(resultSet.getInt("idPersona"));
		
		return persona; 
	}
	
	
	public ArrayList<PersonaDTO> readAllJasperUbicacion()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAllJasperUbicacion);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPreferenciaUbicacion(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	
	private PersonaDTO getPreferenciaUbicacion(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idPersona");
		String nombre = resultSet.getString("nombre");
		String tel = resultSet.getString("telefono");
		PersonaDTO persona = new PersonaDTO(id, nombre, tel);
		persona.setLocalidad(resultSet.getString("localidad"));
		persona.setProvincia(resultSet.getString("provincia"));
		persona.setPais(resultSet.getString("pais"));
		persona.setTotalLocalidad(resultSet.getDouble("totalLocalidad"));
		persona.setTotalProvincia(resultSet.getDouble("totalProvincia"));
		persona.setTotalPais(resultSet.getDouble("totalPais"));
		return persona; 

	}
}
