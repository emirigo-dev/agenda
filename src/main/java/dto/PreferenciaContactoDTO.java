package dto;

public class PreferenciaContactoDTO {
	
	private int idPreferenciaContacto;
	private String preferenciaContacto;
	
	public PreferenciaContactoDTO(int idPreferenciaContacto, String preferenciaContacto)
	{
		this.idPreferenciaContacto = idPreferenciaContacto;
		this.preferenciaContacto = preferenciaContacto;
	}

	public int getIdPreferenciaContacto() {
		return idPreferenciaContacto;
	}

	public void setIdPreferenciaContacto(int idPreferenciaContacto) {
		this.idPreferenciaContacto = idPreferenciaContacto;
	}

	public String getPreferenciaContacto() {
		return preferenciaContacto;
	}

	public void setPreferenciaContacto(String preferenciaContacto) {
		this.preferenciaContacto = preferenciaContacto;
	}

}

