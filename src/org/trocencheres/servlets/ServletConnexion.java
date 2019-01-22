package org.trocencheres.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.trocencheres.beans.Utilisateur;
import org.trocencheres.dal.DALException;
import org.trocencheres.dal.UtilisateurDAO;






/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletConnexion() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("motdepasse");

		try {
			Utilisateur utilisateurTrouve = UtilisateurDAO.rechercher(identifiant, mdp);

			request.setAttribute("utilisateurConnecte", utilisateurTrouve);
			request.setAttribute("identifiant", identifiant);
			this.getServletContext().getRequestDispatcher("/ServletSession").forward(request, response);

		} catch (

		DALException e) {
			request.setAttribute("erreur", e);
			this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
