package org.trocencheres.servlets;

import org.trocencheres.beans.Utilisateur;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * author JY
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
        HttpSession session = request.getSession();
        Object sessionUser = session.getAttribute("utilisateurConnecte");

        // si la session a n'a pas expiré:
        if (sessionUser != null) {
            Utilisateur currentUser = (Utilisateur) sessionUser;

            String fromSale = request.getParameter("fromSale");
            if (fromSale != null)
                request.setAttribute("fromSale", fromSale);

            String modifierProfil = request.getParameter("modifierProfil");
            if (modifierProfil != null)
                request.setAttribute("modifierProfil", modifierProfil);



            request.getRequestDispatcher("/WEB-INF/monProfil.jsp").forward(request, response);
        } else {
            session.invalidate();
            response.sendRedirect("/ProjetEncheres/Connexion");
        }
    }
}
