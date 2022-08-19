package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ProvinciaDAO;
import dto.ProvinciaDTO;

public class ProvinciaDAOSQL implements ProvinciaDAO{
	private static final String readall = "SELECT * FROM PROVINCIA";
	
	public HashMap<String, ProvinciaDTO> readAll() 
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		HashMap<String, ProvinciaDTO> provinciaById = new HashMap<String, ProvinciaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				ProvinciaDTO provincia = getProvinciaDTO(resultSet);
				provinciaById.put(provincia.getIdProvincia(), provincia);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return provinciaById;
	}
		
	private ProvinciaDTO getProvinciaDTO(ResultSet resultSet) throws SQLException
	{
		String idPais = resultSet.getString("idPais");
		String idProvincia = resultSet.getString("idProvincia");
		String provinciaName = resultSet.getString("provincia");
		ProvinciaDTO provincia = new ProvinciaDTO(idPais, idProvincia, provinciaName);
		
		return provincia; 
	}
}
