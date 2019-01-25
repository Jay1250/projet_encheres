package org.trocencheres.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerifConnexion
 */
@WebServlet("/VerifConnexion")
public class VerifConnexion extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifConnexion() {
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
		Integer count = (Integer) request.getSession().getAttribute("compteur");
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("motdepasse");

		if (request.getSession().getAttribute("utilisateurConnecte") == null) {
			if (count ==null) {
				if (identifiant == null && mdp == null) {
					this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
				} else {
					this.getServletContext().getRequestDispatcher("/ServletConnexion").forward(request, response);

				}
			} else {

				this.getServletContext().getRequestDispatcher("/WEB-INF/erreurConnexion.jsp").forward(request,
						response);
			}
		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/listeEncheres.jsp").forward(request, response);
		}

	}

}
