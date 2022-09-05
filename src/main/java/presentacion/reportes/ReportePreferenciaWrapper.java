package presentacion.reportes;

public class ReportePreferenciaWrapper {
	
	private String preferenciaContacto;
	private int quantity;
	
	public ReportePreferenciaWrapper(String preferenciaContacto, int quantity) {
		this.preferenciaContacto = preferenciaContacto;
		this.quantity = quantity;
	}

	public String getPreferenciaContacto() {
		return preferenciaContacto;
	}

	public void setPreferenciaContacto(String preferenciaContacto) {
		this.preferenciaContacto = preferenciaContacto;
	}

	public int getquantity() {
		return quantity;
	}

	public void setquantity(int quantity) {
		this.quantity = quantity;
	}
}
