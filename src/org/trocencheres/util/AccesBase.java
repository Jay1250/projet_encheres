package org.trocencheres.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.trocencheres.dal.DALException;

public class AccesBase {
	public static Connection getConnection() throws DALException{
		
		String uri = Parametre.lire("dbUrl");
		String user = Parametre.lire("dbUser");
		String password = Parametre.lire("dbPassword");
		Connection connexion = null;
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			connexion =  DriverManager.getConnection(uri, user, password);
		} catch (ClassNotFoundException e) {
			throw new DALException("Probleme chargement driver "+e.getMessage());
		} catch (SQLException e1){
		
			throw new DALException("Probleme connexion "+e1.getMessage());
		}
		
		return connexion;		
	}
}
