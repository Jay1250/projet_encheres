package org.trocencheres.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerifConnexion author : JY
 */
@WebServlet("/VerifConnexion")
public class VerifConnexion extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet() servlet appel�e pour v�rifier que la session
	 *      de l'utilisateur n'a pas expir�. Elle est appel�e lors des actions
	 *      suivantes : cas 1: sur la page d'accueil (de connexion), le bouton
	 *      connexion renvoie � cette servlet via une m�thode post avec les
	 *      setParmeter des champs identifiant et mot de passe avant de transmettre
	 *      : - si les deux champs ne sont pas remplis : renvoie � la meme page de
	 *      connexion - si champs remplis, renvoie � la servletConnexion qui v�rifie
	 *      que les pseudos et mdp correspondent � un utilisateur dans la base puis
	 *      renvoie � la servlet session pour ouvrir une session avec set Attribut
	 *      Utilisateur trouve (� ce stade il peut �tre null) et d�marrer un
	 *      compteur qui comptera le nb de tentatives effectu�es par l'utilisateur
	 *      cas 2: sur la barre de navigation, le lien "connexion" ou le lien
	 *      "trocencheres.org" renvoie � cette servlet via m�thode get avant de
	 *      transmettre � la page de connexion si la session a expir� ou � la page
	 *      listeEncheres si session active
	 */
	public VerifConnexion() {
		super();

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
		// variable compteur qui permet de d�tecter cb de fois l'utilisateur a envoye un
		// formulaire avec des champs saisis (pemret de connaitre son nb de tentatives
		// infructueuses si count >)
		// cette variable est nulle lors du premier envoi du formulaire du client sur la
		// page connexion
		Integer count = (Integer) request.getSession().getAttribute("compteur");
		String identifiant = (String) request.getParameter("identifiant");
		System.out.println("identifiant = "+identifiant);
		String mdp = (String) request.getParameter("motdepasse");
		System.out.println("mdp= "+mdp);

// si aucun utilisateur "valide" a �t� mont� en session (cas 1 : premiere connexion cas 2 : au moins une tentative infructuseuse (soit mdp incorrect soit champs non remplis) cas 3 : session expir�e)
		if (request.getSession().getAttribute("utilisateurConnecte") == null) {

			if (identifiant == null && mdp == null) {// si acc�s via le lien url connexion dans bar nav et aucun
														// utilisateur en session
				this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
			}

			// si 1ere tentative, ou suivantes si les pr�c�dentes n'ont pas abouti faute de
			// remplir tous les champs
			else if (count == null) {
				identifiant.trim();
				mdp.trim();
				// si les deux champs ne sont pas remplis: renvoie � la m�me page connexion avec
				// un message d'alerte
				if (identifiant.equals("")|| mdp.equals("")) {
					// alerte personnalis�e selon le champ qui n'a pas �t� renseign�
					if (identifiant.equals(""))
						request.setAttribute("identifiantNonRenseigne", true);
					if (mdp.equals(""))
						request.setAttribute("mdpNonRenseigne", true);

					System.out.println("reste dans connexion.jsp avec msg d'alerte selon le champ non rempli");
					this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);

				} else { // si tous les champs sont bien renseign�s
					System.out.println("va ds servlet connexion pour verif identifiant et mdp");
					this.getServletContext().getRequestDispatcher("/ServletConnexion").forward(request, response);

				}
			} else { // si au moins la 1ere tentative a �t� infructueuse : redirection vers une page
						// d'erreur

				System.out.println("va ds erreurconnexion.jsp");
				this.getServletContext().getRequestDispatcher("/WEB-INF/erreurConnexion.jsp").forward(request,
						response);
			}
		} else {
			// si un utilisateur valide a �t� trouve et/ou que une session utilisateur est
			// d�j� active
			System.out.println("va ds listeEncheres.jsp");
			this.getServletContext().getRequestDispatcher("/WEB-INF/listeEncheres.jsp").forward(request, response);
		}

	}

}
