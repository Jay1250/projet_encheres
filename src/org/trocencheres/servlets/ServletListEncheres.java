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
		Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");

		if (utilisateurConnecte != null) {
			if ((request.getParameter("choix1") != null)) {

				ArrayList<Vente> ventesEnCours = null;
				ArrayList<Enchere> encheresEnCours = null;
				ArrayList<Utilisateur> utilisateursEnCours = null;

				try {

					ventesEnCours = pem.selectAllCurrentSalesByUser(utilisateurConnecte.getNoUtilisateur());
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

					if (!ventesEnCours.isEmpty())
						request.getSession().setAttribute("ventesEnCours", ventesEnCours);

					if (!encheresEnCours.isEmpty()) {
						request.getSession().setAttribute("encheresEnCours", encheresEnCours);
						request.getSession().setAttribute("utilisateursEnCours", utilisateursEnCours);
					}

				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("erreur", e);
					this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
				}

			}
			if (request.getParameter("choix2") != null) {

				ArrayList<Vente> ventesTerminees = null;
				ArrayList<Enchere> encheresTerminees = null;
				ArrayList<Utilisateur> utilisateursTermines = null;

				try {
					ventesTerminees = pem.selectAllEndedSalesByUser(utilisateurConnecte.getNoUtilisateur());
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

					if (!ventesTerminees.isEmpty())
						request.getSession().setAttribute("ventesTerminees", ventesTerminees);

					if (!encheresTerminees.isEmpty()) {
						request.getSession().setAttribute("encheresTerminees", encheresTerminees);

						request.getSession().setAttribute("utilisateursTermines", utilisateursTermines);
					}

				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("erreur", e);
					this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);

				}
			}
			if (request.getParameter("choix3") != null) {
				ArrayList<Enchere> encheresEnCours = null;
				ArrayList<Vente> ventesEnCours = null;
				ArrayList<Utilisateur> utilisateursEnCours = null;

				try {

					encheresEnCours = pem.selectAllCurrentAuctionsByUser(utilisateurConnecte.getNoUtilisateur());
					ventesEnCours = new ArrayList<Vente>();
					utilisateursEnCours = new ArrayList<Utilisateur>();

					if (!encheresEnCours.isEmpty()) {

						for (Enchere e : encheresEnCours) {
							Vente vente = pem.getSaleById(e.getNoVente());
							if (vente != null && vente.getNoUtilisateur() != 0) {
								ventesEnCours.add(vente);

								for (Vente v : ventesEnCours) {
									Utilisateur utilisateur = pem.getUserById(v.getNoUtilisateur());
									utilisateursEnCours.add(utilisateur);
								}

							}
							request.getSession().setAttribute("encheresEnCoursAcheteur", encheresEnCours);
							request.getSession().setAttribute("ventesEnCours", ventesEnCours);
							request.getSession().setAttribute("utilisateursEnCours", utilisateursEnCours);
						}

					}

				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("erreur", e);
					this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
				}

			}
			if (request.getParameter("choix4") != null) {

				ArrayList<Enchere> encheresTerminees = null;
				ArrayList<Vente> ventesTerminees = null;
				ArrayList<Utilisateur> utilisateursTermines = null;

				try {
					encheresTerminees = pem.selectAllEndedAuctionsByUser(utilisateurConnecte.getNoUtilisateur());
					ventesTerminees = new ArrayList<Vente>();
					utilisateursTermines = new ArrayList<Utilisateur>();

					if (!encheresTerminees.isEmpty()) {
						for (Enchere e : encheresTerminees) {
							Vente vente = pem.getSaleById(e.getNoVente());
							if (vente != null && vente.getNoUtilisateur() != 0)
								ventesTerminees.add(vente);

						}
						for (Vente v : ventesTerminees) {
							Utilisateur utilisateur = pem.getUserById(v.getNoUtilisateur());
							utilisateursTermines.add(utilisateur);
						}
						request.getSession().setAttribute("encheresTerminees", encheresTerminees);
						request.getSession().setAttribute("ventesTerminees", ventesTerminees);

						request.getSession().setAttribute("utilisateursTermines", utilisateursTermines);
					}

				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("erreur", e);
					this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
				}
			}
			
			if (request.getParameter("choix5") != null) {

			}
			request.getRequestDispatcher("/WEB-INF/listeEncheres.jsp").forward(request, response);

		}
		if (utilisateurConnecte == null) {

			session.invalidate();
			response.sendRedirect("/ProjetEncheres/Connexion");
		} else {

			request.getRequestDispatcher("/WEB-INF/listeEncheres.jsp").forward(request, response);
		}
	}
}