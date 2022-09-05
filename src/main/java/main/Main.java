package main;

import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;


public class Main 
{

	public static void main(String[] args) 
	{
		
		Controlador controlador = new Controlador();
		controlador.inicializar();
	}
}
