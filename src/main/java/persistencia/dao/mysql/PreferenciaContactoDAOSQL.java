package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import dto.PreferenciaContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PreferenciaContactoDAO;

public class PreferenciaContactoDAOSQL implements PreferenciaContactoDAO{
	private static final String readall = "SELECT * FROM PREFERENCIA_CONTACTO";
	

	public HashMap<String, PreferenciaContactoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		HashMap<String, PreferenciaContactoDTO> preferenciasContactoByName = new HashMap<String, PreferenciaContactoDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				PreferenciaContactoDTO preferenciaContacto = getPreferenciaContactoDTO(resultSet);
				preferenciasContactoByName.put(preferenciaContacto.getPreferenciaContacto(), preferenciaContacto);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return preferenciasContactoByName;
	}
	
	private PreferenciaContactoDTO getPreferenciaContactoDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idPreferenciaContacto");
		String preferenciaContacto = resultSet.getString("preferenciaContacto");
		PreferenciaContactoDTO preferenciaContactoDTO = new PreferenciaContactoDTO(id, preferenciaContacto);
				
		return preferenciaContactoDTO; 
	}

}
