/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.entities.Produit;

/**
 * @author Aboubakar
 *
 */
public class ProduitDaoImpl implements IProduitDao {

	@Override
	public Produit saveProduit(Produit p) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO produit(designation, prix, quantite) VALUES(?,?,?)";
		
		Connection connection = SingletonConnection.getConnection();
		try {
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, p.getDesignation());
			ps.setDouble(2, p.getPrix());
			ps.setInt(3, p.getQuantite());
			
			ps.executeUpdate();
			
			/*
			 * Dans l'affichage nous aurons besoin de l'Id du produit
			 * Pour recuperer l'Id du produit on execute une requete SELECT:
			 */
			PreparedStatement ps2 = connection.prepareStatement("SELECT MAX(id) AS MAX_ID FROM produit");
			ResultSet rs = ps2.executeQuery();
			
			if(rs.next()) {
				// On ajoute cette id à l'objet produit à l'aide du Setter.
				p.setId(rs.getLong("MAX_ID"));
			}
			
			ps.close();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// On retourne un object de type Produit. 
		return p;
	}

	@Override
	public List<Produit> produitsParMotCle(String motCle) {
		// TODO Auto-generated method stub
		List<Produit> listProduit = new ArrayList();
		String sql = "SELECT * FROM produit WHERE designation LIKE ?";
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, motCle);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Produit produit = new Produit();
				produit.setId(rs.getLong("id"));
				produit.setDesignation(rs.getString("designation"));
				produit.setPrix(rs.getDouble("prix"));
				produit.setQuantite(rs.getInt("quantite"));
				listProduit.add(produit);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listProduit;
	}

	@Override
	public Produit getProduit(Long id) {
		String sql = "SELECT * FROM produit WHERE id=?";
		Produit produit = new Produit();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				produit.setId(rs.getLong("id"));
				produit.setDesignation(rs.getString("designation"));
				produit.setPrix(rs.getDouble("prix"));
				produit.setQuantite(rs.getInt("quantite"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produit;
	}

	@Override
	public Produit updateProduit(Produit p) {
		String sql = "UPDATE produit SET designation=?, prix=?,quantite=? WHERE id=?";
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, p.getDesignation());
			ps.setDouble(2, p.getPrix());
			ps.setInt(3, p.getQuantite());
			ps.setLong(4, p.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public void deleteProduit(Long id) {

		String sql = "DELETE FROM produit WHERE id = ?";
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
