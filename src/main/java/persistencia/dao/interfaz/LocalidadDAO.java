package persistencia.dao.interfaz;

import java.util.HashMap;

import dto.LocalidadDTO;

public interface LocalidadDAO {

	public HashMap<String, LocalidadDTO> readAll();
}
