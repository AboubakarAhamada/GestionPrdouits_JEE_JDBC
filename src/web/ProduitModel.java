/**
 * 
 */
package web;
import java.util.ArrayList;
import java.util.List;

import metier.entities.Produit;

/**
 * @author Aboubakar
 *
 */

/*
 * Dans cette classe on déclare les élements qui seront manipulé au niveau
 * de la vue. Il s'agit donc d'une liste des produits et un mot clé (motCle)
 */

public class ProduitModel {
	
	private String motCle;
	private List<Produit> listProduit = new ArrayList<Produit>();
	
	
	public String getMotCle() {
		return motCle;
	}
	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}
	public List<Produit> getListProduit() {
		return listProduit;
	}
	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}
	
	
	

}
