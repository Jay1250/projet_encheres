package org.trocencheres.servlets;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.trocencheres.beans.Enchere;
import org.trocencheres.beans.Utilisateur;
import org.trocencheres.beans.Vente;
import org.trocencheres.bll.BLLException;
import org.trocencheres.bll.ProjetEnchereManager;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletListEncheres", urlPatterns = "/ListEncheres")
public class ServletListEncheres extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private ProjetEnchereManager pem;

	public ServletListEncheres() throws BLLException {
		super();
		this.pem = ProjetEnchereManager.getInstance();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("utilisateurConnecte") != null) {

			Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
			ArrayList<Vente> ventesEnCours = null;

			ArrayList<Enchere> encheresEnCours = null;
			ArrayList<Utilisateur> utilisateursEnCours = null;
			
			ArrayList<Vente> ventesTerminees = null;

			ArrayList<Enchere> encheresTerminees = null;
			ArrayList<Utilisateur> utilisateursTermines = null;
			try {
				
				
				ventesEnCours = pem.selectAllEndedByUser(utilisateurConnecte.getNoUtilisateur());
				encheresEnCours = new ArrayList<Enchere>();
				utilisateursEnCours = new ArrayList<Utilisateur>();
				
				for (Vente v : ventesEnCours) {
					Enchere enchere = pem.getLastAuctionBySale(v.getNoVente());
					if (enchere != null && enchere.getNoUtilisateur() != 0)
						encheresEnCours.add(enchere);

				}
				for (Enchere e : encheresEnCours) {
					utilisateursEnCours.add(pem.getUserById(e.getNoUtilisateur()));
				}
				
				ventesTerminees = pem.selectAllEndedByUser(utilisateurConnecte.getNoUtilisateur());
				encheresTerminees = new ArrayList<Enchere>();
				utilisateursTermines = new ArrayList<Utilisateur>();
				
				ventesTerminees = pem.selectAllCurrentByUser(utilisateurConnecte.getNoUtilisateur());
				encheresTerminees = new ArrayList<Enchere>();
				utilisateursTermines = new ArrayList<Utilisateur>();
				for (Vente v : ventesTerminees) {
					Enchere enchere = pem.getLastAuctionBySale(v.getNoVente());
					if (enchere != null && enchere.getNoUtilisateur() != 0)
						encheresTerminees.add(enchere);

				}
				for (Enchere e : encheresTerminees) {
					utilisateursTermines.add(pem.getUserById(e.getNoUtilisateur()));
				}

			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("erreur", e);
				this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
			}
			
			request.getSession().setAttribute("ventesEnCours", ventesEnCours);
			
			if (!encheresEnCours.isEmpty())
				request.getSession().setAttribute("encheresEnCours", encheresEnCours);

			request.getSession().setAttribute("utilisateursTermines", utilisateursTermines);
			
			request.getSession().setAttribute("ventesTerminees", ventesTerminees);
			
			if (!encheresTerminees.isEmpty())
				request.getSession().setAttribute("encheresTerminees", encheresTerminees);

			request.getSession().setAttribute("utilisateurs", utilisateursEnCours);
			

			request.getRequestDispatcher("/WEB-INF/listeEncheres.jsp").forward(request, response);
		} else {
			session.invalidate();
			response.sendRedirect("/ProjetEncheres/Connexion");
		}
	}
}