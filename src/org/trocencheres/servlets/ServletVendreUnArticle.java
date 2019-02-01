package org.trocencheres.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.trocencheres.beans.Categorie;
import org.trocencheres.beans.Retrait;
import org.trocencheres.beans.Utilisateur;
import org.trocencheres.beans.Vente;
import org.trocencheres.bll.BLLException;
import org.trocencheres.bll.ProjetEnchereManager;

/**
 * author JI
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
		if(isStillConnected(request, response)) {
			request.setCharacterEncoding("UTF-8");
			// get categories
            try {
                Map<Integer, Categorie> categories= null;
                categories = pem.getCategories();
                if (categories != null)
                    request.setAttribute("categorie", categories);
                Utilisateur user =(Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
				// get default user retrait address
				pem.getUserById(user.getNoUtilisateur());
				request.setAttribute("rue", user.getRue());
				request.setAttribute("codePostal", user.getCodePostal());
				request.setAttribute("ville", user.getVille());	
			} catch (BLLException e1) {
				e1.printStackTrace();
			}
			 this.getServletContext().getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp").forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(isStillConnected(request, response)) {
			request.setCharacterEncoding("UTF-8");
			//recup fields
			String article = request.getParameter("article");
			String description = request.getParameter("description");
			Date finEnchere = null;
			if(request.getParameter("finencheredate") != null && request.getParameter("finencheredate") != "" && request.getParameter("finencheretime") != null && request.getParameter("finencheretime") != "")
				finEnchere = creationFinEnchere(request);	
			int prixInitial = Integer.parseInt(request.getParameter("prixinitial"));
			String rue = request.getParameter("rue");	
			String codePostal = request.getParameter("codepostal");
			String ville = request.getParameter("ville");
			Utilisateur user =(Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
			//  ***** verif form
			boolean isFormOk = true;
			if(article == null || article.trim().equals("")) {
				request.setAttribute("articleNonRenseigne", true);
				isFormOk = false;
			}
			if(description == null || description.trim().equals("")) {
				request.setAttribute("descriptionNonRenseigne", true);
				isFormOk = false;
			}
			if(finEnchere == null) {
				request.setAttribute("finEnchereNonRenseigne", true);
				isFormOk = false;
			}
			if(prixInitial == 0) {
				request.setAttribute("articleNonRenseigne", true);
				isFormOk = false;
			}
			if(rue == null || rue.trim().equals("")) {
				request.setAttribute("rueNonRenseigne", true);
				isFormOk = false;
			}
			if(codePostal == null || codePostal.trim().equals("")) {
				request.setAttribute("codePostalNonRenseigne", true);
				isFormOk = false;
			}
			if(ville == null || ville.trim().equals("")) {
				request.setAttribute("villeNonRenseigne", true);
				isFormOk = false;
			}
			// *****
			if(isFormOk) {
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
					user.getNoUtilisateur(),//utilisateurATrouver
					Integer.parseInt(request.getParameter("categorie"))//noCategorie
					);
				try {
					pem.addSale(vente, retrait);
					response.sendRedirect("<%=request.getContextPath()%>/ListEncheres");
				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("erreur", true);
					this.getServletContext().getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp").forward(request, response);
				}
			}
			else {
				// get categories
                Map<Integer, Categorie> categories = null;
                try {
                    categories = pem.getCategories();
                } catch (BLLException e) {
                    e.printStackTrace();
                }
                if (categories != null) request.setAttribute("categorie", categories);
				// preserve fields if they exist
				request.setAttribute("article", article);
				request.setAttribute("description", description);
				request.setAttribute("finencheredate",  request.getParameter("finencheredate")); 
				request.setAttribute("finencheretime",  request.getParameter("finencheretime")); 
				request.setAttribute("prixinitial", prixInitial);
				request.setAttribute("rue", rue);
				request.setAttribute("codePostal", codePostal);
				request.setAttribute("ville", ville);
				this.getServletContext().getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp").forward(request, response);
			}
		}
	}
	
	private Date creationFinEnchere(HttpServletRequest request) {
		Date finEnchere = null;
		String dateFinEnchere = request.getParameter("finencheredate");
		String timeFinEnchere = request.getParameter("finencheretime");
		timeFinEnchere = timeFinEnchere.replaceAll(":","-");
		String parseDateTime = dateFinEnchere +" " +  timeFinEnchere;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm");
		try {
			finEnchere = sdf.parse(parseDateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return finEnchere;
	}
	
	private boolean isStillConnected(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean stillConnected = false;
		// si la session a expiré:
		if (request.getSession().getAttribute("utilisateurConnecte") == null) {
            request.getSession().invalidate();
			response.sendRedirect("<%=request.getContextPath()%>/Connexion");
		// si la session utilisateur est toujours active
        }else
			 stillConnected = true;
		return stillConnected;
	}
}
	

