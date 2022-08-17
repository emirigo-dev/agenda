package persistencia.dao.interfaz;
import java.util.List;
import dto.TipoContactoDTO;

public interface TipoContactoDAO 
{
	
	public List<TipoContactoDTO> readAll();
}
