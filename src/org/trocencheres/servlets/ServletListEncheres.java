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
			ArrayList<Vente> ventes = null;

			ArrayList<Enchere> encheres = null;
			ArrayList<Utilisateur> utilisateurs=null;
			try {
				ventes = pem.selectAllByUser(utilisateurConnecte.getNoUtilisateur());
				encheres=new ArrayList<Enchere>();
				utilisateurs= new ArrayList<Utilisateur>();
				for (Vente v : ventes) {
					
					encheres.add(pem.getLastAuctionBySale(v.getNoVente()));
				}
				for (Enchere e :encheres) {
					utilisateurs.add(pem.getUserById(e.getNoUtilisateur()));
				}

			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("erreur", e);
				this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
			}
			request.getSession().setAttribute("ventes", ventes);
			request.getSession().setAttribute("encheres", encheres);
			request.getSession().setAttribute("utilisateurs", utilisateurs);
			
			
			
			


			request.getRequestDispatcher("/WEB-INF/listeEncheres.jsp").forward(request, response);
		} else {
			session.invalidate();
			response.sendRedirect("/ProjetEncheres/Connexion");
		}
	}
}