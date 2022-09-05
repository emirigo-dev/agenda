package persistencia.dao.interfaz;
import java.util.HashMap;
import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;

public interface TipoContactoDAO 
{
	
	public HashMap<String, TipoContactoDTO> readAll(Conexion conexion);
}
