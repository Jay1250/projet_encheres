package org.trocencheres.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.trocencheres.beans.Utilisateur;
import org.trocencheres.bll.BLLException;
import org.trocencheres.bll.ProjetEnchereManager;

/**
 * author JY
 * Servlet implementation class ServletConnexion
 * Servlet permettant de faire la vérification de l'identifiant et du mot de passe saisis
 */
@WebServlet(name = "ServletConnexion", urlPatterns = "/Connexion")
public class ServletConnexion extends HttpServlet implements Servlet {
    private static final long serialVersionUID = 1L;
    private ProjetEnchereManager pem;


    public ServletConnexion() throws BLLException {
        super();
        this.pem = ProjetEnchereManager.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("utilisateurConnecte") != null) {
            response.sendRedirect("/ProjetEncheres/ListEncheres");
        } else {
            session.invalidate();
            this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String identifiant = request.getParameter("identifiant");
        String mdp = request.getParameter("motdepasse");

        if (identifiant != null && !identifiant.trim().equals(""))
            identifiant = identifiant.trim();
        else request.setAttribute("identifiantNonRenseigne", true);

        if (mdp != null && !mdp.trim().equals(""))
            mdp = mdp.trim();
        else request.setAttribute("mdpNonRenseigne", true);

        if(request.getAttribute("mdpNonRenseigne") != null || request.getAttribute("identifiantNonRenseigne") != null)
            this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
        else {
            try {
                Utilisateur utilisateurRecupere = pem.getUserByLogin(identifiant, mdp); // cette méthode construit un utilisateur vide puis set les attributs avec le résultat de la requete sql
                if (utilisateurRecupere.getNoUtilisateur() != 0) { // les no util sont en identity 1,1 dans la base donc impossible d'être égal à zero, tandis que le constructeur par défaut initialise à zero le no_util qui est de type int
                    request.getSession().setAttribute("utilisateurConnecte", utilisateurRecupere);
                    this.getServletContext().getRequestDispatcher("/ListEncheres").forward(request, response);
                }
                else { // si no_util=0 ca veut dire aucune ligne trouvée dans le resultat de la requete
                    request.getSession().setAttribute("utilisateurConnecte", null);
                    request.getSession().invalidate();
                    request.setAttribute("identifiantOuMdpIncorrects", true);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
                }

            } catch (BLLException e) {
                //redirection à une page d'erreur si pb avec la méthode utilisant : pem.getUserByLogin(identifiant, mdp) ou la conexion sql
                e.printStackTrace();
                request.setAttribute("erreur", e);
                this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
            }
        }
    }
}
