package dto;

public class PaisDTO {

	private String idPais;
	private String pais;
	
	public PaisDTO(String idPais, String pais) {
		this.idPais = idPais;
		this.pais = pais;
	}

	public String getIdPais() {
		return idPais;
	}

	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	@Override
	public String toString() {
		return "Pais [idPais=" + idPais   + ", pais=" + pais + "]";
	}
	
}
