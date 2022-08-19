package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;
import dto.TipoContactoDTO;

public class TipoContactoDAOSQL implements TipoContactoDAO{
	private static final String readall = "SELECT * FROM TIPO_CONTACTO";

	public HashMap<String, TipoContactoDTO> readAll() 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		HashMap<String, TipoContactoDTO> tiposContactoByName = new HashMap<String, TipoContactoDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				TipoContactoDTO tipoContacto = getTipoContactoDTO(resultSet);
				tiposContactoByName.put(tipoContacto.getTipo(), tipoContacto);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tiposContactoByName;
	}
		
	private TipoContactoDTO getTipoContactoDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idTipoContacto");
		String tipo = resultSet.getString("Tipo");
		TipoContactoDTO tipoContacto = new TipoContactoDTO(id, tipo);
				
		return tipoContacto; 
	}

}
