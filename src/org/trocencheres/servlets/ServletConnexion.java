package org.trocencheres.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.trocencheres.beans.Utilisateur;
import org.trocencheres.bll.BLLException;
import org.trocencheres.bll.ProjetEnchereManager;

/**
 * author JY
 * Servlet implementation class ServletConnexion
 * Servlet permettant de faire la v�rification de l'identifiant et du mot de passe saisis
 * La servlet VerifConnexion envoie ici quand les deux champs identifiant +mdp  sont bien remplis
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private ProjetEnchereManager pem;

	
	public ServletConnexion() {
		super();
		this.pem = ProjetEnchereManager.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("dans doPost de servlet connexion");
		String identifiant = (String)request.getParameter("identifiant").trim();
		String mdp = (String)request.getParameter("motdepasse").trim();
		Integer count = (Integer) request.getSession().getAttribute("compteur");

	
			try {
		
			
			Utilisateur utilisateurRecupere = pem.getUserByLogin(identifiant, mdp); // cette m�thode construit un utilisateur vide puis set les attributs avec le r�sultat de la requete sql
			System.out.println("utilisateur : "+ utilisateurRecupere.toString());
			if (utilisateurRecupere.getNoUtilisateur() != 0) { // les no util sont en identity 1,1 dans la base donc impossible d'�tre �gal � zero, tandis que le constructeur par d�faut initialise � zero le no_util qui est de type int
				System.out.println("utilisateur trouve ac succès");
				request.getSession().setAttribute("utilisateurConnecte", utilisateurRecupere);

			} else { // si no_util=0 ca veut dire aucune ligne trouv�e dans le resultat de la requete
				System.out.println("utilisateur non trouve");
				request.getSession().setAttribute("utilisateurConnecte", null);

			}
			if (count == null) { // variable permettant de savoir cb de tentatives de connexions  il y a eu 
				count = 1;
			} else {
				count += 1;
			}
			request.getSession().setAttribute("compteur", count);
			request.getSession().setAttribute("identifiant", utilisateurRecupere.getPseudo());
			System.out.println("va dans servlet Connexion");
			//on renvoie � la servlet connexion qui va dispatcher selon que l'utilisateur r�cup�r� est nul ou pas
			this.getServletContext().getRequestDispatcher("/VerifConnexion").forward(request, response);

		} catch (BLLException e) {
			//redirection � une page d'erreur si pb avec la m�thode utilisant : pem.getUserByLogin(identifiant, mdp) ou la conexion sql
			request.setAttribute("erreur", e);
			this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);

		}

	
	}
}
