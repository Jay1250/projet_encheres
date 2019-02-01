package org.trocencheres.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.trocencheres.beans.Utilisateur;

import org.trocencheres.bll.BLLException;
import org.trocencheres.bll.ProjetEnchereManager;

/**
 * author JY Servlet implementation class ServletCreerCompte
 */
@WebServlet(name = "ServletCreerCompte", urlPatterns = "/CreerCompte")
public class ServletCreerCompte extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;
	private ProjetEnchereManager pem;

	/**
	 * @throws BLLException
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCreerCompte() throws BLLException {
		super();

		this.pem = ProjetEnchereManager.getInstance();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/creerCompte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");



		String pseudo = request.getParameter("pseudo").trim();
		String nom = request.getParameter("nom").trim();
		String prenom = request.getParameter("prenom").trim();
		String email =  request.getParameter("email").trim().toLowerCase();
		String telephone =  request.getParameter("telephone").trim();
		String rue =  request.getParameter("rue").trim();
		String codePostal = request.getParameter("codepostal").trim();
		String ville =  request.getParameter("ville").trim();
		String motDePasse =  request.getParameter("motdepasse").trim();
		String confirmationMotDePasse =  request.getParameter("confirmation").trim();

		ArrayList<Integer> ventes = new ArrayList<>();
		boolean administrateur = false;
		int credit = 250;
		int noUtilisateur = 0;

		Utilisateur newUtilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue,
				codePostal, ville, credit, administrateur, ventes, motDePasse);
		


		if (pseudo.equals("") || nom.equals("") || prenom.equals("") || email.equals("") || rue.equals("")
				|| codePostal.equals("") || ville.equals("") || motDePasse.equals("")
				|| confirmationMotDePasse.equals("")) {
			request.setAttribute("champsNonRemplis", true);
		} else {
			request.setAttribute("champsNonRemplis", false);
		}
		if (pem.pseudoExists(pseudo))
			request.setAttribute("pseudoExists", true);
		else
			request.setAttribute("pseudoExists", false);

		if (pem.emailExists(email))
			request.setAttribute("emailExists", true);
		else
			request.setAttribute("emailExists", false);

		if (!telephone.equals("") && pem.telephoneExists(telephone))
			request.setAttribute("telephoneExists", true);
		else
			request.setAttribute("telephoneExists", false);

		if (!motDePasse.equals(confirmationMotDePasse))
			request.setAttribute("confirmationKo", true);
		else
			request.setAttribute("confirmationKo", false);

		Boolean pseudoExists = (Boolean) request.getAttribute("pseudoExists");
		Boolean emailExists = (Boolean) request.getAttribute("emailExists");
		Boolean telephoneExists = (Boolean) request.getAttribute("telephoneExists");
		Boolean confirmationKo = (Boolean) request.getAttribute("confirmationKo");
		Boolean champsNonRemplis = (Boolean) request.getAttribute("champsNonRemplis");


		if (!pseudoExists && !emailExists && !telephoneExists && !confirmationKo && !champsNonRemplis) {
			try {
			
				pem.addUser(newUtilisateur);

				request.getSession().setAttribute("utilisateurConnecte", newUtilisateur);
				response.sendRedirect("<%=request.getContextPath()%>/ListEncheres");

			} catch (BLLException e) {
				request.setAttribute("erreur", e);
				this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
			}

		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/creerCompte.jsp").forward(request, response);
		}

	}

}