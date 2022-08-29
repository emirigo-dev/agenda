package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.PreferenciaContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PreferenciaContactoDAO;

public class PreferenciaContactoDAOSQL implements PreferenciaContactoDAO{
	private static final String readall = "SELECT * FROM PREFERENCIA_CONTACTO";
	private static final String readJasper = "SELECT p.Nombre, pc.idPreferenciaContacto, pc.preferenciaContacto, count(p.idPreferenciaContacto) * 100.0 / sum(count(p.idPreferenciaContacto)) over() AS preferenciaContactoContador FROM PREFERENCIA_CONTACTO pc LEFT JOIN PERSONAS p ON pc.idPreferenciaContacto = p.idPreferenciaContacto GROUP BY pc.preferenciaContacto";
	

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
	
	public ArrayList<PreferenciaContactoDTO> readAllJasper() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PreferenciaContactoDTO> reports = new ArrayList<PreferenciaContactoDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(readJasper);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				reports.add(getContadorPrefenciaContacto(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return reports;
	}
	
	private PreferenciaContactoDTO getContadorPrefenciaContacto(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idPreferenciaContacto");
		String preferenciaContacto = resultSet.getString("preferenciaContacto");
		PreferenciaContactoDTO preferenciaContactoDTO = new PreferenciaContactoDTO(id, preferenciaContacto);
		System.out.println(resultSet.getInt("preferenciaContactoContador"));
		preferenciaContactoDTO.setPreferenciaContactoContador(resultSet.getDouble("preferenciaContactoContador"));
		preferenciaContactoDTO.setNombrePersona(resultSet.getString("p.Nombre"));
				
		return preferenciaContactoDTO; 
	}

}
