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

				ArrayList<Vente> ventesEnCoursVendeur = null;
				ArrayList<Enchere> encheresVentesEnCoursVendeur = null;
				ArrayList<Utilisateur> utilisateursEncheresVentesEnCoursVendeur = null;

				try {

					ventesEnCoursVendeur = pem.selectAllCurrentSalesByUser(utilisateurConnecte.getNoUtilisateur());
					encheresVentesEnCoursVendeur = new ArrayList<Enchere>();
					utilisateursEncheresVentesEnCoursVendeur = new ArrayList<Utilisateur>();

					for (Vente v : ventesEnCoursVendeur) {
						Enchere enchere = pem.getLastAuctionBySale(v.getNoVente());
						if (enchere != null && enchere.getNoUtilisateur() != 0)
							encheresVentesEnCoursVendeur.add(enchere);

					}
					for (Enchere e : encheresVentesEnCoursVendeur) {
						utilisateursEncheresVentesEnCoursVendeur.add(pem.getUserById(e.getNoUtilisateur()));
					}

					if (!ventesEnCoursVendeur.isEmpty())
						request.getSession().setAttribute("ventesEnCours", ventesEnCoursVendeur);

					if (!encheresVentesEnCoursVendeur.isEmpty()) {
						request.getSession().setAttribute("encheresEnCours", encheresVentesEnCoursVendeur);
						request.getSession().setAttribute("utilisateursEnCours", utilisateursEncheresVentesEnCoursVendeur);
					}

				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("erreur", e);
					this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
				}

			}
			if (request.getParameter("choix2") != null) {

				ArrayList<Vente> ventesTermineesVendeur = null;
				ArrayList<Enchere> encheresVentesTermineesVendeur = null;
				ArrayList<Utilisateur> utilisateursEncehresVentesTermineesVendeur = null;

				try {
					ventesTermineesVendeur = pem.selectAllEndedSalesByUser(utilisateurConnecte.getNoUtilisateur());
					encheresVentesTermineesVendeur = new ArrayList<Enchere>();
					utilisateursEncehresVentesTermineesVendeur = new ArrayList<Utilisateur>();

					for (Vente v : ventesTermineesVendeur) {
						Enchere enchere = pem.getLastAuctionBySale(v.getNoVente());
						if (enchere != null && enchere.getNoUtilisateur() != 0)
							encheresVentesTermineesVendeur.add(enchere);

					}
					for (Enchere e : encheresVentesTermineesVendeur) {
						utilisateursEncehresVentesTermineesVendeur.add(pem.getUserById(e.getNoUtilisateur()));
					}

					if (!ventesTermineesVendeur.isEmpty())
						request.getSession().setAttribute("ventesTerminees", ventesTermineesVendeur);

					if (!encheresVentesTermineesVendeur.isEmpty()) {
						request.getSession().setAttribute("encheresTerminees", encheresVentesTermineesVendeur);

						request.getSession().setAttribute("utilisateursTermines", utilisateursEncehresVentesTermineesVendeur);
					}

				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("erreur", e);
					this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);

				}
			}
			if (request.getParameter("choix3") != null) {
				ArrayList<Enchere> encheresEnCours2 = null;
				ArrayList<Vente> ventesEnCours2 = null;
				ArrayList<Utilisateur> utilisateursEnCours2 = null;

				try {

					encheresEnCours2 = pem.selectAllCurrentAuctionsByUser(utilisateurConnecte.getNoUtilisateur());
					ventesEnCours2 = new ArrayList<Vente>();
					utilisateursEnCours2 = new ArrayList<Utilisateur>();

					if (!encheresEnCours2.isEmpty()) {

						for (Enchere e : encheresEnCours2) {
							Vente vente = pem.getSaleById(e.getNoVente());
							if (vente != null && vente.getNoUtilisateur() != 0) {
								ventesEnCours2.add(vente);

								for (Vente v : ventesEnCours2) {
									Utilisateur utilisateur = pem.getUserById(v.getNoUtilisateur());
									utilisateursEnCours2.add(utilisateur);
								}

							}

							request.getSession().setAttribute("ventesEnCours2", ventesEnCours2);
							request.getSession().setAttribute("utilisateursEnCours2", utilisateursEnCours2);
						}

					}

				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("erreur", e);
					this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
				}

			}
			if (request.getParameter("choix4") != null) {

				ArrayList<Enchere> encheresTerminees2 = null;
				ArrayList<Vente> ventesTerminees2 = null;
				ArrayList<Utilisateur> utilisateursTermines2 = null;

				try {
					encheresTerminees2 = pem.selectAllEndedAuctionsByUser(utilisateurConnecte.getNoUtilisateur());
					ventesTerminees2 = new ArrayList<Vente>();
					utilisateursTermines2 = new ArrayList<Utilisateur>();

					if (!encheresTerminees2.isEmpty()) {
						for (Enchere e : encheresTerminees2) {
							Enchere e2 = pem.getLastAuctionBySale(e.getNoVente());
							if (e.getNoUtilisateur() == e2.getNoUtilisateur()) {
								Vente vente = pem.getSaleById(e.getNoVente());
								if (vente != null && vente.getNoUtilisateur() != 0)
									ventesTerminees2.add(vente);
							}
							
						}
						for (Vente v : ventesTerminees2) {
							Utilisateur utilisateur = pem.getUserById(v.getNoUtilisateur());
							utilisateursTermines2.add(utilisateur);
						}

						if(!ventesTerminees2.isEmpty()) {
						request.getSession().setAttribute("ventesTerminees2", ventesTerminees2);
						request.getSession().setAttribute("utilisateursTermines2", utilisateursTermines2);
					}
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
		}
	}
}