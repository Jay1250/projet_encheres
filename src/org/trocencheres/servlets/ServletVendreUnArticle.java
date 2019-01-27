package org.trocencheres.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletVendreUnArticle
 */
@WebServlet("/ServletVendreUnArticle")
public class ServletVendreUnArticle extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * servlet accessible via le lien VendreUnArticle dans la navbar 
     * cette servlet permet de vérifier la connexion avant de ridiriger vers la page VendreUnArticle.jsp
     * @see HttpServlet#HttpServlet()
     */
    public ServletVendreUnArticle() {
        super();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// si la session a expiré:
				if (request.getSession().getAttribute("utilisateurConnecte") == null) {

						this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
				
					
				} else {
					// si la session utilisateur est toujours active
					System.out.println("va ds vendreUnArticle.jsp");
					this.getServletContext().getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp").forward(request, response);
				}

			}

			

}
