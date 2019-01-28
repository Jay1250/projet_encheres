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
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet("/ServletModifierProfil")
public class ServletModifierProfil extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private ProjetEnchereManager pem;

	/**
	 * @throws BLLException 
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletModifierProfil() throws BLLException {
		super();
		this.pem = ProjetEnchereManager.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
		if(utilisateurConnecte==null)
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		else {

		String pseudo = (String) request.getParameter("pseudo").trim();
		String nom = (String) request.getParameter("nom").trim();
		String prenom = (String) request.getParameter("prenom").trim();
		String email = (String) request.getParameter("email").trim().toLowerCase();
		String telephone = (String) request.getParameter("telephone").trim();
		String rue = (String) request.getParameter("rue").trim();
		String codePostal = (String) request.getParameter("codepostal").trim();
		String ville = (String) request.getParameter("ville").trim();
		String motDePasse = (String) request.getParameter("motdepasse").trim();
		String confirmationMotDePasse = (String) request.getParameter("confirmation").trim();

		ArrayList<Integer> ventes = utilisateurConnecte.getVentesIds();
		boolean administrateur = utilisateurConnecte.isAdministrateur();
		int credit = utilisateurConnecte.getCredit();
		int noUtilisateur = utilisateurConnecte.getNoUtilisateur();
		
		Utilisateur newUtilisateur = new Utilisateur();

		if (!motDePasse.equals("")|| !confirmationMotDePasse.equals("")) {
						
			if (!motDePasse.equals(confirmationMotDePasse)) {
				request.setAttribute("confirmationKo", true);
			}else {
				request.setAttribute("confirmationKo", false);
				 newUtilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue,
						codePostal, ville, credit, administrateur, ventes, motDePasse);
			}
		} else {
			
			request.setAttribute("confirmationKo", false);
			 newUtilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue,
					codePostal, ville, credit, administrateur, ventes);
		}


		if (pseudo.equals("") || nom.equals("") || prenom.equals("") || email.equals("") || rue.equals("")
				|| codePostal.equals("") || ville.equals(""))
			request.setAttribute("champsNonRemplis", true);
		else
			request.setAttribute("champsNonRemplis", false);

		if (!pseudo.equals(utilisateurConnecte.getPseudo()) && pem.pseudoExists(pseudo))
			request.setAttribute("pseudoExists", true);
		else
			request.setAttribute("pseudoExists", false);

		if (!email.equals(utilisateurConnecte.getEmail()) && pem.emailExists(email))
			request.setAttribute("emailExists", true);
		else
			request.setAttribute("emailExists", false);

		if (!telephone.equals(utilisateurConnecte.getTelephone()) && !telephone.equals("") && pem.telephoneExists(telephone))
			request.setAttribute("telephoneExists", true);
		else
			request.setAttribute("telephoneExists", false);


		Boolean pseudoExists = (Boolean) request.getAttribute("pseudoExists");
		Boolean emailExists = (Boolean) request.getAttribute("emailExists");
		Boolean telephoneExists = (Boolean) request.getAttribute("telephoneExists");
		Boolean confirmationKo = (Boolean) request.getAttribute("confirmationKo");
		Boolean champsNonRemplis = (Boolean) request.getAttribute("champsNonRemplis");

		System.out.println("pseudoExiste= " + request.getAttribute("pseudoExists"));
		System.out.println("email existe= " + request.getAttribute("emailExists"));
		System.out.println("telephone exist= " + request.getAttribute("telephoneExists"));
		System.out.println("confirmation KO= " + request.getAttribute("confirmationKo"));
		System.out.println("champs non remplis= " + request.getAttribute("champsNonRemplis"));

		if (!pseudoExists && !emailExists && !telephoneExists && !confirmationKo && !champsNonRemplis) {
			try {
			
				pem.updateUser(newUtilisateur);

				request.getSession().setAttribute("utilisateurConnecte", newUtilisateur);
				request.setAttribute("changerProfil", true);
				this.getServletContext().getRequestDispatcher("/WEB-INF/listeEncheres.jsp").forward(request, response);

			} catch (BLLException e) {
				request.setAttribute("erreur", e);
				this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
			}

		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
		}

	}
	}
}
