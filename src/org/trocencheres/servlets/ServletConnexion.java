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
import org.trocencheres.dal.DALException;
import org.trocencheres.dal.UtilisateurDAO;
import org.trocencheres.dal.UtilisateurDAOFactory;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private ProjetEnchereManager pem;

	/**
	 * Default constructor.
	 */
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
		String identifiant = request.getParameter("identifiant").trim();
		String mdp = request.getParameter("motdepasse").trim();

		try {
			Utilisateur utilisateurTrouve = pem.getUserByLogin(identifiant, mdp);
			if (utilisateurTrouve.getNoUtilisateur()!=0) {
				System.out.println("utilisateur trouve ac succès");
				request.setAttribute("utilisateurConnecte", utilisateurTrouve);
				request.setAttribute("identifiant", identifiant);
			} else {
				System.out.println("utilisateur non trouve");
				request.setAttribute("utilisateurConnecte", null);
				request.setAttribute("identifiant", identifiant);
			}
			System.out.println("va dans servlet session");
			this.getServletContext().getRequestDispatcher("/ServletSession").forward(request, response);
		} catch (

		BLLException e) {
			request.setAttribute("erreur", e);
			this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);

		}

	}

}
