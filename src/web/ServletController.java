package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;

import dao.IProduitDao;
import dao.LoginDao;
import dao.ProduitDaoImpl;
import metier.entities.Produit;
import metier.entities.User;


@WebServlet({"/","/home",})
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IProduitDao produitDao;

	public void init(ServletConfig config) throws ServletException {
		produitDao = new ProduitDaoImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path=request.getServletPath();
		
		if(path.equals("/login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			/*
			 * On verifie si cet utilisateur est dans la base de données et le dirige vers
			 * la page d'accueil avec son nom Pour cela on appelle la fonction static de la
			 * classe LoginDao qui retourne un object de la classe User
			 */
			User user = LoginDao.checkLogin(email, password);
			System.out.println(user.toString());
			if(!user.equals(null)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", user.getUsername());
				response.sendRedirect("chercher?motCle=");
			}
			else {
				response.sendRedirect("login.jsp");
			}
			
		}
		// Si c'est log out : on ferme la session et
		// on redirige l'utilisateur vers la page de connection
		else if(path.contentEquals("/logout")) {
			HttpSession session = request.getSession();
			session.removeAttribute("username");
			session.invalidate();
			response.sendRedirect("login.jsp");
		}
		else if(path.equals("/home") || path.equals("/")) {
			request.getRequestDispatcher("produits.jsp").forward(request, response);
		}
		/*
		 * On recupere le mot saisi dans la zone de recherche et fait appel
		 * à la méthode produitsParMotCle de l'interface IProduitDao qui fait un SELECT .... LIKE
		 * En suite on attache la liste des produits récupeerés au modèle pour pouvoir l'utiliser
		 * dans la View produits.jsp
		 */
		else if(path.equals("/chercher")) {
			String motCle = request.getParameter("motCle");
			ProduitModel model = new ProduitModel();
			model.setMotCle(motCle);
			List<Produit> listProduit = produitDao.produitsParMotCle("%"+motCle+"%");
			model.setListProduit(listProduit);
			request.setAttribute("model", model);
			request.getRequestDispatcher("produits.jsp").forward(request, response);
		}
		else if(path.equals("/creer")) {
			request.setAttribute("produit", new Produit()); // initier les valeurs par 0 ou null
			request.getRequestDispatcher("ajouterProduit.jsp").forward(request, response);
		}
		else if(path.equals("/enregister")) {
			String designation = request.getParameter("designation");
			double prix = Double.parseDouble(request.getParameter("prix"));
			int quantite = Integer.parseInt(request.getParameter("quantite"));
		
			Produit produit = produitDao.saveProduit(new Produit(designation,prix,quantite));
			request.setAttribute("produit", produit);
			request.getRequestDispatcher("produit-info.jsp").forward(request, response);
		}
		else if(path.equals("/supprimer")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Produit produit = produitDao.getProduit(id);
			request.setAttribute("produit", produit);
			request.getRequestDispatcher("supprimerProduit.jsp").forward(request, response);	
		}
		
		else if(path.equals("/confirmer-suppression")) {
			Long id = Long.parseLong(request.getParameter("id"));
			produitDao.deleteProduit(id);
			response.sendRedirect("chercher?motCle=");
		}
		else if(path.contentEquals("/modifier")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Produit produit = produitDao.getProduit(id);
			request.setAttribute("produit", produit);
			request.getRequestDispatcher("modifierProduit.jsp").forward(request, response);	

		}
		else if(path.equals("/valider-modification")) {
			
			Long id = Long.parseLong(request.getParameter("id"));
			String designation = request.getParameter("designation");
			double prix = Double.parseDouble(request.getParameter("prix"));
			int quantite = Integer.parseInt(request.getParameter("quantite"));
		
			Produit produit = new Produit(designation,prix,quantite);
			// We set the same same id;
			produit.setId(id);
			// We update product :
			produitDao.updateProduit(produit);
			// And we redirect user to home page
			response.sendRedirect("chercher?motCle=");
		}
		else {
			response.sendError(Response.SC_NOT_FOUND);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
