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
 * Servlet implementation class ServletCreerCompte
 */
@WebServlet("/ServletCreerCompte")
public class ServletCreerCompte extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;
	private ProjetEnchereManager pem;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCreerCompte() {
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
	
		

		Integer count = (Integer) request.getSession().getAttribute("compteur");

		// lors du premier appel a cette servlet par la jsp connexion via son lien "se connecter", aller à la page jsp Créer un compte:

		if (count == null) {
			count = 1;
			System.out.println("count= "+count);
			request.getSession().setAttribute("compteur", count);
			this.getServletContext().getRequestDispatcher("/WEB-INF/creerCompte.jsp").forward(request, response);
			//lors du 2e appel appel on considere que le formulaire a été rempli : 		
		} else {
			String pseudo = request.getParameter("pseudo").trim();
			String nom = request.getParameter("nom").trim();
			String prenom = request.getParameter("prenom").trim();
			String email = request.getParameter("email").trim().toLowerCase();
			String telephone = request.getParameter("telephone").trim();
			String rue = request.getParameter("rue").trim();
			String codePostal = request.getParameter("codepostal").trim();
			String ville = request.getParameter("ville").trim();
			String motDePasse = request.getParameter("motdepasse");
			String confirmationMotDePasse = request.getParameter("confirmation");

			ArrayList<Integer> ventes = new ArrayList<>();
			boolean administrateur = false;
			int credit = 0;
			int noUtilisateur = 0;

			Utilisateur newUtilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue,
					codePostal, ville, credit, administrateur, ventes, motDePasse);
			
			count = count.intValue() + 1;
			System.out.println(count);
			System.out.println("nom non renseigne " +nom);
			System.out.println("prenom avec espaces en trim " +prenom);
			request.getSession().setAttribute("compteur", count);
			
			if (pseudo == "" || nom == "" || prenom == "" || email == "" || rue == "" || codePostal == ""
					|| ville == "" || motDePasse == "" || confirmationMotDePasse == "")
				request.setAttribute("champsNonRemplis", true);
			else 
				request.setAttribute("champsNonRemplis", false);

				if (pem.pseudoExists(pseudo))
					request.setAttribute("pseudoExists", true);
				else
					request.setAttribute("pseudoExists", false);

				if (pem.emailExists(email))
					request.setAttribute("emailExists", true);
				else
					request.setAttribute("emailExists", false);

				if (pem.telephoneExists(telephone))
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

				System.out.println("pseudoExiste= "+request.getAttribute("pseudoExists"));
				System.out.println("email existe= "+request.getAttribute("emailExists"));
				System.out.println("telephone exist= "+request.getAttribute("telephoneExists"));
				System.out.println("confirmation KO= "+request.getAttribute("confirmationKo"));
				System.out.println("champs non remplis= "+request.getAttribute("champsNonRemplis"));

				if (!pseudoExists && !emailExists && !telephoneExists && !confirmationKo && !champsNonRemplis) {
					try {
						System.out.println(newUtilisateur.toString());
						pem.addUser(newUtilisateur);

						request.getSession().setAttribute("utilisateurConnecte", newUtilisateur);
						this.getServletContext().getRequestDispatcher("/WEB-INF/listeEncheres.jsp").forward(request,
								response);

					} catch (BLLException e) {
						request.setAttribute("erreur", e);
						this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
					}

				} else {
					this.getServletContext().getRequestDispatcher("/WEB-INF/creerCompte.jsp").forward(request,
							response);
				}

			}

		
	}
}