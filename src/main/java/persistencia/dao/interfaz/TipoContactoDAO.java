package persistencia.dao.interfaz;
import java.util.HashMap;
import dto.TipoContactoDTO;

public interface TipoContactoDAO 
{
	
	public HashMap<String, TipoContactoDTO> readAll();
}
