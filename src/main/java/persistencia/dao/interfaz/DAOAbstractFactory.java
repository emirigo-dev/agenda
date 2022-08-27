package persistencia.dao.interfaz;


public interface DAOAbstractFactory 
{
	public PersonaDAO createPersonaDAO();
	
	public TipoContactoDAO createTipoContactoDAO();
	
	public PreferenciaContactoDAO createPreferenciaContactoDAO();
	
	public LocalidadDAO createLocalidadDAO();
	
	public UbicacionDAO createUbicacionDAO();
}