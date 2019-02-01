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
			if ((request.getParameter("ventesEnCours") != null)) {

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
						request.getSession().setAttribute("ventesEnCoursVendeur", ventesEnCoursVendeur);

					if (!encheresVentesEnCoursVendeur.isEmpty()) {
						request.getSession().setAttribute("encheresVentesEnCoursVendeur", encheresVentesEnCoursVendeur);
						request.getSession().setAttribute("utilisateursEncheresVentesEnCoursVendeur", utilisateursEncheresVentesEnCoursVendeur);
					}

				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("erreur", e);
					this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
				}

			}
			if (request.getParameter("ventesTerminees") != null) {

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
						request.getSession().setAttribute("ventesTermineesVendeur", ventesTermineesVendeur);

					if (!encheresVentesTermineesVendeur.isEmpty()) {
						request.getSession().setAttribute("encheresVentesTermineesVendeur", encheresVentesTermineesVendeur);

						request.getSession().setAttribute("utilisateursEncehresVentesTermineesVendeur", utilisateursEncehresVentesTermineesVendeur);
					}

				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("erreur", e);
					this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);

				}
			}
			if (request.getParameter("encheresEnCours") != null) {
				ArrayList<Enchere> encheresEnCoursAcheteur = null;
				ArrayList<Vente> ventesEncheresEnCoursAcheteur = null;
				ArrayList<Utilisateur> vendeursEncheresEnCoursAcheteur = null;

				try {

					encheresEnCoursAcheteur = pem.selectAllCurrentAuctionsByUser(utilisateurConnecte.getNoUtilisateur());
					ventesEncheresEnCoursAcheteur = new ArrayList<Vente>();
					vendeursEncheresEnCoursAcheteur = new ArrayList<Utilisateur>();

					if (!encheresEnCoursAcheteur.isEmpty()) {

						for (Enchere e : encheresEnCoursAcheteur) {
							Vente vente = pem.getSaleById(e.getNoVente());
							if (vente != null && vente.getNoUtilisateur() != 0) {
								ventesEncheresEnCoursAcheteur.add(vente);

								for (Vente v : ventesEncheresEnCoursAcheteur) {
									Utilisateur utilisateur = pem.getUserById(v.getNoUtilisateur());
									vendeursEncheresEnCoursAcheteur.add(utilisateur);
								}

							}

							request.getSession().setAttribute("ventesEncheresEnCoursAcheteur", ventesEncheresEnCoursAcheteur);
							request.getSession().setAttribute("vendeursEncheresEnCoursAcheteur", vendeursEncheresEnCoursAcheteur);
						}

					}

				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("erreur", e);
					this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
				}

			}
			if (request.getParameter("acquisitions") != null) {

				ArrayList<Enchere> encheresVentesTermineesAcheteur = null;
				ArrayList<Vente> acquisitionsAcheteur = null;
				ArrayList<Utilisateur> vendeursAcquisitionsAcheteur = null;

				try {
					encheresVentesTermineesAcheteur = pem.selectAllEndedAuctionsByUser(utilisateurConnecte.getNoUtilisateur());
					acquisitionsAcheteur = new ArrayList<Vente>();
					vendeursAcquisitionsAcheteur = new ArrayList<Utilisateur>();

					if (!encheresVentesTermineesAcheteur.isEmpty()) {
						for (Enchere derniereEnchereAcheteur : encheresVentesTermineesAcheteur) {
							Enchere derniereEnchereVente = pem.getLastAuctionBySale(derniereEnchereAcheteur.getNoVente());
							if (derniereEnchereAcheteur.getNoUtilisateur() == derniereEnchereVente.getNoUtilisateur()) {
								Vente vente = pem.getSaleById(derniereEnchereAcheteur.getNoVente());
								if (vente != null && vente.getNoUtilisateur() != 0)
									acquisitionsAcheteur.add(vente);
							}
							
						}
						for (Vente v : acquisitionsAcheteur) {
							Utilisateur utilisateur = pem.getUserById(v.getNoUtilisateur());
							vendeursAcquisitionsAcheteur.add(utilisateur);
						}

						if(!acquisitionsAcheteur.isEmpty()) {
						request.getSession().setAttribute("acquisitionsAcheteur", acquisitionsAcheteur);
						request.getSession().setAttribute("vendeursAcquisitionsAcheteur", vendeursAcquisitionsAcheteur);
					}
					}

				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("erreur", e);
					this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
				}
			}

			if (request.getParameter("autresEncheres") != null) {

			}

			request.getRequestDispatcher("/WEB-INF/listeEncheres.jsp").forward(request, response);

		} else {
			session.invalidate();
			response.sendRedirect("/ProjetEncheres/Connexion");
		}
	}
}