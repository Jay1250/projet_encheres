package org.trocencheres.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.trocencheres.beans.Utilisateur;
import org.trocencheres.beans.Vente;
import org.trocencheres.dal.DALException;
import org.trocencheres.dal.UtilisateurDAO;
import org.trocencheres.dal.UtilisateurDAOFactory;

/**
 * Servlet implementation class ServletCreerCompte
 */
@WebServlet("/ServletCreerCompte")
public class ServletCreerCompte extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDAO daoUtilisateur;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCreerCompte() {
		super();
		this.daoUtilisateur = UtilisateurDAOFactory.getUtilisateurDao();
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
		// int credit;
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("motdepasse");
		String confirmationMotDePasse = request.getParameter("confirmation");

		ArrayList<Integer> ventes = new ArrayList<>();
		boolean administrateur = false;
		int credit = 0;
		int noUtilisateur = 0;
		
		Utilisateur utilisateurCree=new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue,
				codePostal, ville, credit, administrateur, ventes, motDePasse);

		if (!motDePasse.equals(confirmationMotDePasse)) {

			this.getServletContext().getRequestDispatcher("/creerCompte.jsp").forward(request, response);
		} else {
			try {
				//select by email pseudo et tel pour éviter double enregistrement pour une mm personne
				daoUtilisateur.insert(utilisateurCree);
				if(utilisateurCree.getNoUtilisateur()!=0) {
					request.getSession().setAttribute("identifiant",utilisateurCree.getPseudo());
					this.getServletContext().getRequestDispatcher("/WEB-INF/monProfil.jsp").forward(request, response);
				}
			} catch (DALException e) {
				request.setAttribute("erreur", e);
				this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);

			}
		}

	}

}
