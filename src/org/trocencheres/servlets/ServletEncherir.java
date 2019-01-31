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
import java.sql.Timestamp;
import java.util.Date;

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
        Object sessionUserAttr = session.getAttribute("utilisateurConnecte");
        if (sessionUserAttr != null) {
            try {
                Utilisateur currentUser = (Utilisateur) sessionUserAttr;
                String saleParameter = request.getParameter("saleId");
                String fromChoices = request.getParameter("fromChoices");
                Object requestDelete = request.getParameter("delete");
                String requestBid = request.getParameter("newbid");
                Vente sale = null;
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                Date currentTime = new Date(currentTimestamp.getTime());
                Enchere lastAuction = null;
                boolean saleEnded = false;

                if (saleParameter != null) {
                    int saleId = Integer.parseInt(saleParameter);
                    sale = pem.getSaleById(saleId);
                    request.setAttribute("vente", sale);
                    if (sale != null) {

                        if (fromChoices != null)
                            request.setAttribute("fromChoices", fromChoices);

                        if (sale.getDateFinEncheres() != null) {
                            saleEnded = currentTime.after(sale.getDateFinEncheres());
                            request.setAttribute("venteTerminee", saleEnded);
                        }

                        if (sale.getNoUtilisateur() != 0) {
                            Utilisateur seller = pem.getUserById(sale.getNoUtilisateur());
                            if (seller != null && seller.getNoUtilisateur() != 0)
                                request.setAttribute("vendeur", seller);
                        }

                        if (sale.getNoVente() != 0) {
                            lastAuction = pem.getLastAuctionBySale(sale.getNoVente());
                            if (lastAuction != null && lastAuction.getMontantEnchere() != 0)
                                request.setAttribute("montantDerniereEnchere", lastAuction.getMontantEnchere());
                            if (lastAuction != null && lastAuction.getNoUtilisateur() != 0) {
                                Utilisateur lastBidder = pem.getUserById(lastAuction.getNoUtilisateur());
                                if (lastBidder != null && lastBidder.getNoUtilisateur() != 0)
                                    request.setAttribute("dernierEncherisseur", lastBidder);
                            }
                        }
                    }
                }
                if ((requestBid != null && requestDelete == null && sale != null) || (requestBid == null && requestDelete != null && sale != null)) {
                    if (requestDelete != null && requestDelete.equals("true") && currentUser.getNoUtilisateur() == sale.getNoUtilisateur()) {
                        pem.removeSale(sale.getNoVente());
                        response.sendRedirect("/ProjetEncheres/ListEncheres");
                    }
                    if (requestBid != null && currentUser.getNoUtilisateur() != sale.getNoUtilisateur()){
                        int bid = Integer.parseInt(requestBid);
                        String errorMessage = "";
                        int minimumBid = ((lastAuction != null && lastAuction.getMontantEnchere() != 0)
                                ? lastAuction.getMontantEnchere() + 1
                                : sale.getPrixInitial());

                        if (currentTime.after(sale.getDateFinEncheres()))
                            errorMessage = "Vente terminée, vous ne pouvez enchérir !";
                        else if (bid < minimumBid)
                            errorMessage = "Le montant est trop bas ! Minimum : " + minimumBid;
                        else if (bid > currentUser.getCredit())
                            errorMessage = "Vous n'avez pas assez de crédit !";
                        else {
                            Enchere lastAuctionForCurrentUser = pem.getLastAuctionByIds(sale.getNoVente(), currentUser.getNoUtilisateur());
                            int newCredit = currentUser.getCredit() - (bid - lastAuctionForCurrentUser.getMontantEnchere());
                            currentUser.setCredit(newCredit);
                            pem.updateUser(currentUser);
                            pem.addAuction(new Enchere(sale.getNoVente(), currentUser.getNoUtilisateur(), currentTime, bid));
                        }

                        if (errorMessage.equals(""))
                            response.sendRedirect("/ProjetEncheres/Vente?saleId=" + sale.getNoVente()
                                    + (fromChoices != null ? ("&fromChoices=" + fromChoices) : ""));
                        else {
                            request.setAttribute("errorBidding", errorMessage);
                            request.getRequestDispatcher("/WEB-INF/encherir.jsp").forward(request, response);
                        }
                    }
                } else
                    request.getRequestDispatcher("/WEB-INF/encherir.jsp").forward(request, response);
            } catch (BLLException e) {
                e.printStackTrace();
                request.setAttribute("erreur", e);
                request.getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
            }
        } else {
            session.invalidate();
            response.sendRedirect("/ProjetEncheres/Connexion");
        }
    }
}
