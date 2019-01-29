package org.trocencheres.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.trocencheres.beans.Retrait;
import org.trocencheres.beans.Utilisateur;
import org.trocencheres.beans.Vente;
import org.trocencheres.bll.BLLException;
import org.trocencheres.bll.ProjetEnchereManager;

/**
 * Servlet implementation class ServletVendreUnArticle
 */
@WebServlet(name="ServletVendreUnArticle", urlPatterns = "/VendreUnArticle")
public class ServletVendreUnArticle extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private ProjetEnchereManager pem;
       
    /**
     * servlet accessible via le lien VendreUnArticle dans la navbar 
     * cette servlet permet de vérifier la connexion avant de ridiriger vers la page VendreUnArticle.jsp
     * @throws BLLException 
     * @see HttpServlet#HttpServlet()
     */
    public ServletVendreUnArticle() throws BLLException {
        super();
        this.pem = ProjetEnchereManager.getInstance();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if(isStillConnected(request, response))
			System.out.println("connecté !");
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(isStillConnected(request, response)) {
			

			System.out.println("va ds vendreUnArticle.jsp");
		
			String article = request.getParameter("article");
			String description = request.getParameter("description");
			Date finEnchere = null;	
			String startDateStr = request.getParameter("finenchere");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				finEnchere = sdf.parse(startDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			int prixInitial = Integer.parseInt(request.getParameter("prixinitial"));
			String rue = request.getParameter("rue");	
			String codePostal = request.getParameter("codepostal");
			String ville = request.getParameter("ville");
			
			Utilisateur u=(Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
			u.getNom();
			
			Retrait retrait = new Retrait(
				0,
				rue,
				codePostal,
				ville	
			);
					
			
			
			Vente vente = new Vente(
					0, 
					article, 
					description,
					finEnchere, 
					prixInitial, 
					0, 
					null,
					0,//utilisateurATrouver
					0//noCategorie
					);
			
		System.out.println(vente);
		}
		}
	}
	
	private boolean isStillConnected(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean stillConnected = false;
		
		// si la session a expiré:
		if (request.getSession().getAttribute("utilisateurConnecte") == null) 
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
			
		// si la session utilisateur est toujours active
		 else {
			 this.getServletContext().getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp").forward(request, response);
			 stillConnected = true;
		 }
		return stillConnected;
	}
}
	

