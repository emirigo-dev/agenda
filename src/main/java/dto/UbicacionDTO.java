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
		
	private HashMap<String,List<LocalidadDTO>> localidadByIdProvincia = new HashMap<String, List<LocalidadDTO>>();
	private HashMap<String,List<ProvinciaDTO>> provinciaByIdPais = new HashMap<String,List<ProvinciaDTO>>();
	private HashMap <String, LocalidadDTO> localidadByName = new HashMap <String, LocalidadDTO>();
	private HashMap <String, ProvinciaDTO> provinciaByName = new HashMap <String, ProvinciaDTO>();
	private HashMap <String, PaisDTO> paisByName = new HashMap <String, PaisDTO>();
	private List<PaisDTO> paises = new ArrayList<PaisDTO>();
	
	public UbicacionDTO () {} 

	


	
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
	
	public String getLocalidadIdByName(String LocalidadName) {
		return this.localidadByName.get(LocalidadName).getIdLocalidad();
		}
	
	public String getProvinciaIdByName(String provinciaName) {
		return this.provinciaByName.get(provinciaName).getIdProvincia();

	}
	
	public String getPaisIdByName(String paisName) {
		return this.paisByName.get(paisName).getIdPais();
	}



	public HashMap<String, List<LocalidadDTO>> getLocalidadByIdProvincia() {
		return localidadByIdProvincia;
	}



	public void setLocalidadByIdProvincia(String idProvincia, String idLocalidad, String localidad) {
		if(this.localidadByIdProvincia.containsKey(idProvincia)) {
			localidadByIdProvincia.get(idProvincia).add(new LocalidadDTO(idProvincia,idLocalidad, localidad));
		}else {
			List<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
			localidades.add(new LocalidadDTO(idProvincia, idLocalidad, localidad));
			this.localidadByIdProvincia.put(idProvincia, localidades);
		}
		this.localidadByName.put(localidad, new LocalidadDTO (idProvincia, idLocalidad, localidad));
	}



	public HashMap<String, List<ProvinciaDTO>> getProvinciaByIdPais() {
		return provinciaByIdPais;
	}



	public void setProvinciaByIdPais(String idPais, String idProvincia, String provincia) {
		if(this.provinciaByIdPais.containsKey(idPais)) {
			this.provinciaByIdPais.get(idPais).add(new ProvinciaDTO(idPais, idProvincia, provincia));
		}else {
			List<ProvinciaDTO> provincias = new ArrayList<ProvinciaDTO>();
			provincias.add(new ProvinciaDTO(idPais, idProvincia,provincia));
			this.provinciaByIdPais.put(idPais, provincias);
		}
		this.provinciaByName.put(provincia, new ProvinciaDTO (idPais,idProvincia, provincia));
	
	}



	public HashMap<String, LocalidadDTO> getLocalidadByName() {
		return localidadByName;
	}



	public void setLocalidadByName(HashMap<String, LocalidadDTO> localidadByName) {
		this.localidadByName = localidadByName;
	}



	public HashMap<String, ProvinciaDTO> getProvinciaByName() {
		return provinciaByName;
	}



	public void setProvinciaByName(HashMap<String, ProvinciaDTO> provinciaByName) {
		this.provinciaByName = provinciaByName;
	}



	public HashMap<String, PaisDTO> getPaisByName() {
		return paisByName;
	}



	public void setPaisByName(HashMap<String, PaisDTO> paisByName) {
		this.paisByName = paisByName;
	}



	public void setPaises(String idPais, String nombrePais) {
		this.paisByName.put(nombrePais, new PaisDTO(idPais, nombrePais));
		this.paises.add(new PaisDTO(idPais, nombrePais));
	}
	
	public List<LocalidadDTO> getLocalidades(String idProvincia) {
		return this.localidadByIdProvincia.get(idProvincia);
		}
	
	public List<ProvinciaDTO> getProvincias(String idPais) {
		return this.provinciaByIdPais.get(idPais);

	}
	
	public List<PaisDTO> getPaises() {
		return paises;
	}
	
	
	
	
	
}