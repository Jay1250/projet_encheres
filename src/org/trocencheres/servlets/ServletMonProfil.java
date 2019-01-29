package org.trocencheres.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletMonProfil
 */
@WebServlet(name = "ServletMonProfil", urlPatterns = "/MonProfil")
public class ServletMonProfil extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /** accessible via le lien url dans la navbar "Mon Profil"
     * cette servlet permet de vérifier si la session utilisateur est toujours active avanyt de rediriger vers la page monProfil.jsp
     * @see HttpServlet#HttpServlet()
     */
    public ServletMonProfil() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // si la session a expiré:
        if (request.getSession().getAttribute("utilisateurConnecte") == null) {

            this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);

        } else {
            String modifierProfil = (String) request.getParameter("modifierProfil");

            if (modifierProfil != null && modifierProfil.equals("true")) {
                this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
            } else {
                // si la session utilisateur est toujours active
                System.out.println("va ds monProfil.jsp");
                this.getServletContext().getRequestDispatcher("/WEB-INF/monProfil.jsp").forward(request, response);
            }
        }

    }

}
