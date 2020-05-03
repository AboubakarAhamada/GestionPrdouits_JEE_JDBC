package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import metier.entities.User;

public class LoginDao {

	public static User checkLogin(String email, String password) {

		User user = new User();
		String sql = "SELECT * FROM user WHERE email=? AND password=?";
		Connection connection = SingletonConnection.getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			/*
			 * Si la requete a reussi on retourne vrai 
			 * if(rs.next()) { 
			 * return true;
			 *  }
			 */

			if(rs.next()) { 
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getNString("password")); }


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}
}
