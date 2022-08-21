package dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import persistencia.conexion.Conexion;


public class UbicacionDTO {
	
	private static String readLocalidad = "select * from LOCALIDAD";
	private static String readProv = "select * from PROVINCIA";
	private static String readPais = "select * from PAIS";
	
	private static HashMap<String,List<LocalidadDTO>> localidadByIdProvincia = new HashMap<String, List<LocalidadDTO>>();
	private static HashMap<String,List<ProvinciaDTO>> provinciaByIdPais = new HashMap<String,List<ProvinciaDTO>>();
	private static List<PaisDTO> paises = new ArrayList<PaisDTO>();
	private static UbicacionDTO instancia = null;
	
	private UbicacionDTO () {} 
	
	public static UbicacionDTO constructor() {
		if( instancia == null) {
			instancia = new UbicacionDTO();
		}
		readPaises();
		readProvincias();
		readLocalidades();
		return instancia;
	}
	
	
	
	private static void readPaises() {
		paises.clear();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		try  {
			statement = conexion.getSQLConexion().prepareStatement(readPais);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				paises.add(new PaisDTO(resultSet.getString("idPais"), resultSet.getString("pais")));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void readProvincias() {
		provinciaByIdPais.values().clear();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		try  {
			statement = conexion.getSQLConexion().prepareStatement(readProv);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				if(provinciaByIdPais.containsKey(resultSet.getString("idPais"))) {
					provinciaByIdPais.get(resultSet.getString("idPais")).add(new ProvinciaDTO(resultSet.getString("idPais"), resultSet.getString("idProvincia"), resultSet.getString("provincia")));
				}else {
					List<ProvinciaDTO> provincias = new ArrayList<ProvinciaDTO>();
					provincias.add(new ProvinciaDTO(resultSet.getString("idPais"), resultSet.getString("idProvincia"), resultSet.getString("provincia")));
					provinciaByIdPais.put(resultSet.getString("idPais"), provincias);
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private static void readLocalidades() {
		localidadByIdProvincia.values().clear();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		try  {
			statement = conexion.getSQLConexion().prepareStatement(readLocalidad);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				if(localidadByIdProvincia.containsKey(resultSet.getString("idProvincia"))) {
					localidadByIdProvincia.get(resultSet.getString("idProvincia")).add(new LocalidadDTO(resultSet.getString("idProvincia"), resultSet.getString("idLocalidad"), resultSet.getString("localidad")));
				}else {
					List<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
					localidades.add(new LocalidadDTO(resultSet.getString("idProvincia"), resultSet.getString("idLocalidad"), resultSet.getString("localidad")));
					localidadByIdProvincia.put(resultSet.getString("idProvincia"), localidades);
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public List<LocalidadDTO> getLocalidades(String idProvincia) {
		return UbicacionDTO.localidadByIdProvincia.get(idProvincia);
		}
	
	public List<ProvinciaDTO> getProvincias(String idPais) {
		return UbicacionDTO.provinciaByIdPais.get(idPais);

	}
	
	public List<PaisDTO> getPaises() {
		return paises;
	}
	
	public LocalidadDTO getLocalidad(String idLocalidad) {
		LocalidadDTO ret = null;
		Boolean flag = false;
		for(List<LocalidadDTO> localidadesDeUnaProvincia : localidadByIdProvincia.values()) {
			if(!flag) {
				for(LocalidadDTO localidad : localidadesDeUnaProvincia) {
					if(localidad.getIdLocalidad() == idLocalidad) {
						ret = localidad;
						flag = true;
						break;
					}
				}
			}
			break;
		}
		return ret;
	}
	
	public ProvinciaDTO getProvincia(String idProvincia) {
		ProvinciaDTO ret = null;
		Boolean flag = false;
		for(List<ProvinciaDTO> provinciasDeUnPais : provinciaByIdPais.values()) {
			if(!flag) {
				for(ProvinciaDTO provincia : provinciasDeUnPais) {
					if(provincia.getIdProvincia() == idProvincia) {
						ret = provincia;
						flag = true;
						break;
					}
				}
			}
			break;
		}
		return ret;
	}
	
	public PaisDTO getPais(String idPais) {
		PaisDTO ret = null;
		int index = 0;
		while(ret == null) {
			if(paises.get(index).getIdPais() == idPais) {
				ret = paises.get(index);
			}
			index++;
		}
		return ret;
	}
	
	
	
	
	
}