package persistencia.dao.interfaz;

import java.util.HashMap;
import dto.PaisDTO;

public interface PaisDAO {

	public HashMap<String, PaisDTO> readAll();
}
