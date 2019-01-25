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
		

		if (request.getSession().getAttribute("utilisateurConnecte") == null) {
			System.out.println(count);
			if (count ==null) {
				String identifiant = (String)request.getParameter("identifiant").trim();
				System.out.println(identifiant);
				String mdp = (String)request.getParameter("motdepasse").trim();
				System.out.println(mdp);
				if (identifiant == "" && mdp == "") {
					System.out.println("va dans connexion.jsp");
					this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
				} else {
					System.out.println("va ds servlet connexion");
					this.getServletContext().getRequestDispatcher("/ServletConnexion").forward(request, response);

				}
			} else {
				System.out.println("va ds erreurconnexion.jsp");
				this.getServletContext().getRequestDispatcher("/WEB-INF/erreurConnexion.jsp").forward(request,
						response);
			}
		} else {
			System.out.println("va ds listeEncheres.jsp");
			this.getServletContext().getRequestDispatcher("/WEB-INF/listeEncheres.jsp").forward(request, response);
		}

	}

}
