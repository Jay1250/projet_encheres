package org.trocencheres.servlets;

import org.trocencheres.beans.Utilisateur;
import org.trocencheres.bll.BLLException;
import org.trocencheres.bll.ProjetEnchereManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletProfil", urlPatterns = "/Profil")
public class ServletProfil extends HttpServlet {
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
        if (session.getAttribute("utilisateurConnecte") != null) {
            try {
                String userParameter = request.getParameter("userId");
                if (userParameter != null) {
                    int userId = Integer.parseInt(userParameter);
                    Utilisateur autreUtilisateur = pem.getUserById(userId);
                    request.setAttribute("autreUtilisateur", autreUtilisateur);
                }
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
