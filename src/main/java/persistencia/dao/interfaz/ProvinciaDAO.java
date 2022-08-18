package persistencia.dao.interfaz;

import java.util.HashMap;
import dto.ProvinciaDTO;

public interface ProvinciaDAO {

	public HashMap<String, ProvinciaDTO> readAll();
}
