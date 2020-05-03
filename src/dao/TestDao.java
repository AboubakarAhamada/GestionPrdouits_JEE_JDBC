package dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import metier.entities.Produit;
import metier.entities.User;

/**
 * @author Aboubakar
 *
 */
public class TestDao {

	IProduitDao produitDao;
	
	@Before
	public void setUp() throws Exception {
		produitDao = new ProduitDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveProduit() {
		Produit p1 = new Produit("Console",3000,26);
		Produit p2 = produitDao.saveProduit(p1);
		System.out.print(p2.toString());
		assertTrue(p1.getDesignation()==p2.getDesignation());
	}

	@Test
	public void testProduitsParMotCle() {
		String motCle = "%ip%";
		List<Produit> listProduit = produitDao.produitsParMotCle(motCle);
		
		for(Produit produit:listProduit) {
			System.out.println(produit.toString());
		}
			
	}

	@Test
	public void testGetProduit() {
		Produit p = produitDao.getProduit(1L);
		System.out.println(p.toString());
		assertTrue(p.getDesignation().equals("Montre"));
	}

	@Test
	public void testUpdateProduit() {
		Produit p = new Produit("iPhone 7",4000,17);
		p.setId(22L);
		// Update product with id=1
		produitDao.updateProduit(p);
		Produit p2 = produitDao.getProduit(1L);
		System.out.println(p2.toString());
		assertTrue(p2.getDesignation().equals(p.getDesignation()));
	}

	@Test
	public void testDeleteProduit() {
		long id =27;
		produitDao.deleteProduit(id);
		Produit p = produitDao.getProduit(id);
		System.out.println(p.toString());
		
	}
	
	@Test 
	public void testCheckLogin() {
		User user = LoginDao.checkLogin("admin@gmail.com", "admin");
		System.out.println(user.toString());
	}

}
