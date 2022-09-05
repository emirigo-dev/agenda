package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;
import dto.LocalidadDTO;

public class LocalidadDAOSQL implements LocalidadDAO{
	private static final String readall = "SELECT * FROM LOCALIDAD";
	
	public HashMap<String, LocalidadDTO> readAll() 
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		HashMap<String, LocalidadDTO> localidadByName = new HashMap<String, LocalidadDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				LocalidadDTO localidad = getLocalidadDTO(resultSet);
				localidadByName.put(localidad.getLocalidad(), localidad);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return localidadByName;
	}
		
	private LocalidadDTO getLocalidadDTO(ResultSet resultSet) throws SQLException
	{
		String idProvincia = resultSet.getString("idProvincia");
		String idLocalidad = resultSet.getString("idLocalidad");
		String localidadName = resultSet.getString("localidad");
		LocalidadDTO localidad = new LocalidadDTO(idProvincia, idLocalidad, localidadName);
		
		return localidad; 
	}

}
