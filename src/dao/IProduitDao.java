package dao;

import java.util.List;

import metier.entities.Produit;

/**
 * @author Aboubakar
 *
 */
public interface IProduitDao {

	public Produit saveProduit(Produit p);
	
	public List<Produit> produitsParMotCle(String motCle);
	
	public Produit getProduit(Long id);
	
	public Produit updateProduit(Produit p);
	
	public void deleteProduit(Long id);
}
