package org.trocencheres.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.trocencheres.beans.Utilisateur;

/**
 * Servlet implementation class ServletSession
 */
@WebServlet("/ServletSession")
public class ServletSession extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletSession() {
		super();
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

		Utilisateur utilisateurConnecte = (Utilisateur) request.getAttribute("utilisateurConnecte");
	System.out.println(utilisateurConnecte);
		String identifiant = ((String) request.getAttribute("identifiant")).trim();

		Integer count = (Integer) request.getSession().getAttribute("compteur");

		if (count == null) {
			count = 1;
		} else {
			count = count.intValue() + 1;
		}

		request.getSession().setAttribute("compteur", count);
		request.getSession().setAttribute("utilisateurConnecte", utilisateurConnecte);
		request.getSession().setAttribute("identifiant", identifiant);

		this.getServletContext().getRequestDispatcher("/VerifConnexion").forward(request, response);

	}

}
