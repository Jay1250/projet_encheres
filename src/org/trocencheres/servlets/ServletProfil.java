package org.trocencheres.servlets;

import org.trocencheres.beans.Utilisateur;
import org.trocencheres.bll.BLLException;
import org.trocencheres.bll.ProjetEnchereManager;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Kévin Le Devéhat
 */
@WebServlet(name = "ServletProfil", urlPatterns = "/Profil")
public class ServletProfil extends HttpServlet implements Servlet {
    private ProjetEnchereManager pem;

    public ServletProfil() throws BLLException {
        super();
        this.pem = ProjetEnchereManager.getInstance();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object sessionUser = session.getAttribute("utilisateurConnecte");
        if (sessionUser != null) {
            Utilisateur currentUser = (Utilisateur)sessionUser;
            try {
                String fromSale = request.getParameter("fromSale");
                if (fromSale != null)
                    request.setAttribute("fromSale", fromSale);

                String fromChoices = request.getParameter("fromChoices");
                if (fromChoices != null)
                    request.setAttribute("fromChoices", fromChoices);

                String userParameter = request.getParameter("userId");
                int userId = 0;
                if (userParameter != null) {
                    userId = Integer.parseInt(userParameter);
                    Utilisateur autreUtilisateur = pem.getUserById(userId);
                    request.setAttribute("autreUtilisateur", autreUtilisateur);
                }

                if (currentUser.getNoUtilisateur() == userId)
                    response.sendRedirect("/ProjetEncheres/MonProfil"
                            + (fromSale != null
                                ? "?fromSale=" + fromSale
                                : fromChoices != null
                                    ? "?fromChoices=" + fromChoices
                                    : ""));
                else
                    request.getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
            } catch (BLLException e) {
                e.printStackTrace();
                request.setAttribute("erreur", e);
                this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
            }
        } else {
            session.invalidate();
            response.sendRedirect("/ProjetEncheres/Connexion");
        }
    }
}
