package org.trocencheres.servlets;

import org.trocencheres.beans.Vente;
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

@WebServlet(name = "ServletEncherir", urlPatterns = "/Encherir")
public class ServletEncherir extends HttpServlet implements Servlet {
    private ProjetEnchereManager pem;

    public ServletEncherir() throws BLLException {
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
                String saleParamater = request.getParameter("saleId");
                if (saleParamater != null) {
                    int saleId = Integer.parseInt(saleParamater);
                    Vente vente = pem.getSaleById(saleId);
                    request.setAttribute("vente", vente);
                }
                request.getRequestDispatcher("/WEB-INF/encherir.jsp").forward(request, response);
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
