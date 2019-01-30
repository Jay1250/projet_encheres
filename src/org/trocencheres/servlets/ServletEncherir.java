package org.trocencheres.servlets;

import org.trocencheres.beans.Enchere;
import org.trocencheres.beans.Utilisateur;
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

/**
 * @author Kévin Le Devéhat
 */
@WebServlet(name = "ServletEncherir", urlPatterns = "/Vente")
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
                String saleParameter = request.getParameter("saleId");
                if (saleParameter != null) {
                    int saleId = Integer.parseInt(saleParameter);
                    Vente sale = pem.getSaleById(saleId);
                    request.setAttribute("vente", sale);
                    if (sale != null && sale.getNoUtilisateur() != 0) {
                        Utilisateur seller = pem.getUserById(sale.getNoUtilisateur());
                        if (seller != null && seller.getNoUtilisateur() != 0)
                            request.setAttribute("vendeur", seller);
                    }
                    if (sale != null && sale.getNoVente() != 0) {
                        Enchere lastAuction = pem.getLastAuctionBySale(sale.getNoVente());
                        if (lastAuction != null && lastAuction.getMontantEnchere() != 0)
                            request.setAttribute("montantDerniereEnchere", lastAuction.getMontantEnchere());
                        if (lastAuction != null && lastAuction.getNoUtilisateur() != 0) {
                            Utilisateur lastBidder = pem.getUserById(lastAuction.getNoUtilisateur());
                            if (lastBidder != null && lastBidder.getNoUtilisateur() != 0)
                                request.setAttribute("dernierEncherisseur", lastBidder);
                        }
                    }
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
