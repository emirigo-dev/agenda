package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.PaisDTO;
import dto.PersonaDTO;
import dto.UbicacionDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.UbicacionDAO;

public class UbicacionDAOSQL implements UbicacionDAO {
	
	private static String readLocalidad = "SELECT * FROM LOCALIDAD";
	private static String readProv = "SELECT * FROM PROVINCIA";
	private static String readPais = "SELECT * FROM PAIS";
	

	@Override
	public UbicacionDTO readAll() {
		UbicacionDTO ubicacion = new UbicacionDTO();
		PreparedStatement statementPais;
		PreparedStatement statementProvincia;
		PreparedStatement statementLocalidad;
		ResultSet resultSetPais;
		ResultSet resultSetProvincia;
		ResultSet resultSetLocalidad;
		Conexion conexion = Conexion.getConexion();
		try  {
			statementPais = conexion.getSQLConexion().prepareStatement(readPais);
			resultSetPais = statementPais.executeQuery();
			while(resultSetPais.next()) {
				getUbicacionPaises(ubicacion, resultSetPais);
				
			}
			statementProvincia = conexion.getSQLConexion().prepareStatement(readProv);
			resultSetProvincia = statementProvincia.executeQuery();
			while(resultSetProvincia.next()) {
				getUbicacionProvincias(ubicacion, resultSetProvincia);
				
			}
			statementLocalidad = conexion.getSQLConexion().prepareStatement(readLocalidad);
			resultSetLocalidad = statementLocalidad.executeQuery();
			while(resultSetLocalidad.next()) {
				getUbicacionLocalidad(ubicacion, resultSetLocalidad);
				
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ubicacion;
	}
	
	
	private UbicacionDTO getUbicacionPaises(UbicacionDTO ubicacion ,ResultSet resultSet) throws SQLException
	{
		ubicacion.setPaises(resultSet.getString("idPais"),  resultSet.getString("pais"));
		return ubicacion; 
	}
	

	
	private UbicacionDTO getUbicacionProvincias(UbicacionDTO ubicacion, ResultSet resultSet) throws SQLException
	{
		ubicacion.setProvinciaByIdPais(resultSet.getString("idPais"), resultSet.getString("idProvincia"), resultSet.getString("provincia"));
		return ubicacion;
	}
	
	private UbicacionDTO getUbicacionLocalidad(UbicacionDTO ubicacion, ResultSet resultSet) throws SQLException
	{
		ubicacion.setLocalidadByIdProvincia(resultSet.getString("idProvincia"), resultSet.getString("idLocalidad"), resultSet.getString("localidad"));
		return ubicacion;
	}


}
