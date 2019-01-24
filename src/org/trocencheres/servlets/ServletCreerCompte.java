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

import org.trocencheres.bll.BLLException;
import org.trocencheres.bll.ProjetEnchereManager;

/**
 * Servlet implementation class ServletCreerCompte
 */
@WebServlet("/ServletCreerCompte")
public class ServletCreerCompte extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private ProjetEnchereManager pem;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCreerCompte() {
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

		Utilisateur newUtilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue,
				codePostal, ville, credit, administrateur, ventes, motDePasse);

		// select by email pseudo et tel pour éviter double enregistrement pour une mm
		// personne

		if (pseudo == null)
			this.getServletContext().getRequestDispatcher("/WEB-INF/creerCompte.jsp").forward(request, response);
		else {
			if (pem.pseudoExists(pseudo))
				request.setAttribute("pseudoExists", true);
			else
				request.setAttribute("pseudoExists", false);

			if (pem.emailExists(email))
				request.setAttribute("emailExists", true);
			else
				request.setAttribute("emailExists", false);

			if (pem.telephoneExists(telephone))
				request.setAttribute("telephoneExists", true);
			else
				request.setAttribute("telephoneExists", false);

			if (!motDePasse.equals(confirmationMotDePasse))
				request.setAttribute("confirmationKo", true);
			else
				request.setAttribute("confirmationKo", false);

			Boolean pseudoExists = (Boolean) request.getAttribute("pseudoExists");
			Boolean emailExists = (Boolean) request.getAttribute("emailExists");
			Boolean telephoneExists = (Boolean) request.getAttribute("telephoneExists");
			Boolean confirmationKo = (Boolean) request.getAttribute("confirmationKo");

			System.out.println(request.getAttribute("pseudoExists"));
			System.out.println(request.getAttribute("emailExists"));
			System.out.println(request.getAttribute("telephoneExists"));
			System.out.println(request.getAttribute("confirmationKo"));
			if (!pseudoExists && !emailExists && !telephoneExists && !confirmationKo) {
				try {
					System.out.println(newUtilisateur.toString());
					pem.addUser(newUtilisateur);
					
					
					request.getSession().setAttribute("utilisateurConnecte", newUtilisateur);
					this.getServletContext().getRequestDispatcher("/WEB-INF/monProfil.jsp").forward(request, response);

				} catch (BLLException e) {
					request.setAttribute("erreur", e);
					this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
				}

			} else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/creerCompte.jsp").forward(request, response);
			}

		}
	}

}
