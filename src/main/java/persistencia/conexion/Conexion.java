package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class Conexion 
{
	public static Conexion instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(Conexion.class);
	private String user = "root";
	private String password = "root";
	
	private Conexion()
	{
		this.connection = inicializarConexion("agenda", this.user, this.password);
	}
	
	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return this.connection;
	}
	
	public void cerrarConexion()
	{
		try 
		{
			this.connection.close();
			log.info("Conexion cerrada");
		}
		catch (SQLException e) 
		{
			log.error("Error al cerrar la conexión!", e);
		}
		instancia = null;
	}
	
	private static Connection inicializarConexion(String bdd, String user, String pass) {
		Connection cnx = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); 
			cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + bdd, user, pass);
			cnx.setAutoCommit(false);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return cnx;
	}
	
	public static boolean successConnection(String user, String password) {
		return inicializarConexion("agenda", user, password) == null ? false : true;
	}
}
