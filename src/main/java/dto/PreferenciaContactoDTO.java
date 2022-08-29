package dto;

public class PreferenciaContactoDTO {
	
	private int idPreferenciaContacto;
	private String preferenciaContacto;
	private Double preferenciaContactoContador;
	private String Nombre;
	
	public String getNombrePersona() {
		return Nombre;
	}

	public void setNombrePersona(String nombrePersona) {
		this.Nombre = nombrePersona;
	}

	public Double getPreferenciaContactoContador() {
		return preferenciaContactoContador;
	}

	public void setPreferenciaContactoContador(Double preferenciaContactoContador) {
		this.preferenciaContactoContador = preferenciaContactoContador;
	}

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

