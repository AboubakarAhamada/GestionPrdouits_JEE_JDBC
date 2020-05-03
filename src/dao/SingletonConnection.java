/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Aboubakar
 *
 */

/*
 * Dans cette classe nous utilisons le design pattern Sengleton pour
 * ne créer qu'une seule connection pour la base de données (éviter qu'à chaque
 * requette on crée une connection.
 * Et dans ce cas, il ne faut pas fermer la connnection, il faut la laisser ouverte.
 */
public class SingletonConnection {
	
	private static Connection connection;
	private static String url="jdbc:mysql://localhost:3306/gestion_produits_jee?serverTime=UTC";
	private static String user="root";
	private static String password="aboubakar";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}

}
