package dto;

public class LocalidadDTO {
	
	private String idProvincia;
	private String idLocalidad;
	private String localidad;
	
	public LocalidadDTO(String idProvincia, String idLocalidad, String localidad) {
		this.idProvincia = idProvincia;
		this.idLocalidad = idLocalidad;
		this.localidad = localidad;
	}

	public String getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(String idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	@Override
	public String toString() {
		return "Localidad [idProvincia=" + idProvincia + ", idLocalidad=" + idLocalidad + ", localidad=" + localidad + "]";
	}

}
