package persistencia.dao.interfaz;

import java.util.ArrayList;
import java.util.HashMap;
import dto.PreferenciaContactoDTO;

public interface PreferenciaContactoDAO {

	public HashMap<String, PreferenciaContactoDTO> readAll();
	public ArrayList<PreferenciaContactoDTO> readAllJasper();
}
