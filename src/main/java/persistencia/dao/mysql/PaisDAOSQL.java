package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PaisDAO;
import dto.PaisDTO;

public class PaisDAOSQL implements PaisDAO{
	private static final String readall = "SELECT * FROM PAIS";
	
	public HashMap<String, PaisDTO> readAll() 
	{                     
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		HashMap<String, PaisDTO> paisById = new HashMap<String,PaisDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				PaisDTO pais = getProvinciaDTO(resultSet);
				paisById.put(pais.getIdPais(), pais);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return paisById;
	}
		
	private PaisDTO getProvinciaDTO(ResultSet resultSet) throws SQLException
	{
		String idPais = resultSet.getString("idPais");
		String paisName = resultSet.getString("provincia");
		PaisDTO pais = new PaisDTO(idPais, paisName);
		
		return pais; 
	}
}
