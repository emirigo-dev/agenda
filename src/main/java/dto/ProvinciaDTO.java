package dto;

public class ProvinciaDTO {
	
	private String idPais;
	private String idProvincia;
	private String provincia;
	
	public ProvinciaDTO(String idPais, String idProvincia, String provincia) {
		this.idPais = idPais;
		this.idProvincia = idProvincia;
		this.provincia = provincia;
	}

	public String getIdPais() {
		return idPais;
	}

	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}

	public String getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


}
