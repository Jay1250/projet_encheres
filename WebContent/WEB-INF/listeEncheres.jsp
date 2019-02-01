<!-- author JY + JI -->
<%@page import="org.trocencheres.beans.Enchere"%>
<%@page import="org.trocencheres.beans.Vente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.trocencheres.beans.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<title>Troc Encheres</title>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="/ProjetEncheres/theme/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/ProjetEncheres/theme/css/style.css">
<script src="/ProjetEncheres/theme/js/jquery-3.3.1.js"></script>
<script src="/ProjetEncheres/theme/bootstrap/js/bootstrap.min.js"></script>
</head>
<%!String choix = "";%>
<%
	Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
	String identifiant = utilisateurConnecte.getPseudo();
%>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/ProjetEncheres">TrocEncheres.org</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/ProjetEncheres/MonProfil">Mon profil</a></li>
				<li class="active"><a href="/ProjetEncheres/ListEncheres">Les
						enchères</a></li>
				<li><a href="/ProjetEncheres/VendreUnArticle">Vendre un
						article</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/ProjetEncheres/Deconnexion"><span
						class="glyphicon glyphicon-user"></span> Déconnexion</a></li>
			</ul>
		</div>
	</nav>
	<div class="container-fluid">

		<%
			if (request.getAttribute("changerProfil") != null && request.getAttribute("changerProfil").equals(true)) {
		%>
		<div
			class="alert alert-success col-md-2 col-md-push-9 alert-dismissable">
			<strong>Votre profil a bien été changé <%=identifiant%> !
			</strong>
			<button type="button" class="close" data-dismiss="alert">&times;</button>
		</div>
		<%
			} else if (request.getSession().getAttribute("premiereConnexion") != null
					&& request.getSession().getAttribute("premiereConnexion").equals(true)) {
		%>
		<div
			class="alert alert-success col-md-2 col-md-push-9 alert-dismissable">
			<strong>Bonjour <%=identifiant%> !
			</strong>
			<button type="button" class="close" data-dismiss="alert">&times;</button>
		</div>
		<%
			request.getSession().setAttribute("premiereConnexion", null);
			}
		%>


	</div>

	<div class="container">
		<form class="row " action="/ProjetEncheres/ListEncheres" method="post">
			<div class="text-center">
				<img style="max-width: 300px;" src="/ProjetEncheres/logoProjet.png">
			</div>
			<h3 class="text-center">Filtres :</h3>
			<div class="form-group col-md-5 col-md-offset-2">
				<div class="form-check">
					<input class="form-check-input" type="checkbox"
						name="ventesEnCours" value="ventesEnCours"> <label
						class="form-check-label" for="checkventes">Mes ventes en
						cours</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox"
						name="ventesTerminees" value="ventesTerminees"> <label
						class="form-check-label" for="checkventes">Mes ventes
						terminées</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox"
						name="encheresEnCours" value="encheresEnCours"> <label
						class="form-check-label" for="checkencheres">Mes enchères
						en cours</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" name="acquisitions"
						value="acquisitions"> <label class="form-check-label"
						for="checkacquisition">Mes acquisitions</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox"
						name="autresEncheres" value="autresEncheres"> <label
						class="form-check-label" for="checkdefault">Autres
						enchères</label>
				</div>
			</div>
			<div class="form-group col-md-4">
				<label for="inputState">Catégories</label> <select
					class="form-control">
					<option selected>Toutes</option>
					<option>...</option>
				</select>
			</div>
			<div class="form-group col-md-4">
				<input type="password" class="form-control"
					placeholder="Le nom de l'article contient">
			</div>

			<div class="form-group text-center col-md-2 col-md-pull-2">
				<button type="submit" class="btn btn-primary marge">Rechercher</button>
			</div>
		</form>

		<!--  mes ventes en cours -->
		<%	choix = "";
			if (request.getParameter("ventesEnCours") != null)
				choix += "1";
			if (request.getParameter("ventesTerminees") != null)
				choix += "2";
			if (request.getParameter("encheresEnCours") != null)
				choix += "3";
			if (request.getParameter("acquisitions") != null)
				choix += "4";
		%>
		<%
			if (request.getParameter("ventesEnCours") != null) {
		%>
		<h2 class="text-center">Mes ventes en cours</h2>
		<hr />
		<div class="row">
			<%
				if (request.getSession().getAttribute("ventesEnCoursVendeur") == null) {
			%>
			<p>Vous n'avez aucune vente</p>
		</div>
		<%
			} else {
					ArrayList<Vente> ventesEnCoursVendeur = (ArrayList<Vente>) request.getSession()
							.getAttribute("ventesEnCoursVendeur");
					for (Vente v : ventesEnCoursVendeur) {
		%>



		<div class="col-md-3">
			<div class="card col-md-12 block-info-vente">
				<div class="card-body">
					<h4 class="card-title">
						<a href="#"> <%=v.getNomArticle()%>

						</a>
					</h4>

					<h5 class="card-text">
						Prix :
						<%=v.getPrixInitial()%></h5>
					<h6 class="card-subtitle mb-2 text-muted">
						Fin de l'enchère:
						<%=v.getDateFinEncheres()%></h6>
					<h6 class="card-subtitle mb-2 text-muted">
						Retrait:
						<%=v.getRetrait().getRue() + " " + v.getRetrait().getCodePostal() + " "
								+ v.getRetrait().getVille()%></h6>
					<h5 class="card-title text-left">
						Meilleure offre :
						<%
						if (request.getSession().getAttribute("encheresVentesEnCoursVendeur") == null) {
					%>
						Aucune offre
						<%
						} else {
										ArrayList<Enchere> encheresVentesEnCoursVendeur = (ArrayList<Enchere>) request.getSession()
												.getAttribute("encheresVentesEnCoursVendeur");
										Enchere e = encheresVentesEnCoursVendeur.get(encheresVentesEnCoursVendeur.indexOf(v));
										ArrayList<Utilisateur> utilisateursEncheresVentesEnCoursVendeur = (ArrayList<Utilisateur>) request
												.getSession().getAttribute("utilisateursEncheresVentesEnCoursVendeur");
										Utilisateur u = utilisateursEncheresVentesEnCoursVendeur
												.get(utilisateursEncheresVentesEnCoursVendeur.indexOf(e));
					%>

						<%=e.getMontantEnchere()%>
						points par : <a
							href="/ProjetEncheres/Profil?userId=<%=u.getNoUtilisateur()%>&choices=<%=choix%>"
							class="card-link"> <%=u.getPseudo()%></a>
						<%
							}
						%>
					</h5>
					<h5 class="card-title text-right">
						<a
							href="/ProjetEncheres/Vente?saleId=<%=v.getNoVente()%>&choices=<%=choix%>">détails</a>

					</h5>
				</div>
			</div>
		</div>


		<!-- fin mes ventes en cours -->


		<!--  mes ventes terminées -->
		<%
			}
		%>
	</div>
	<%
		}

		}
		if (request.getParameter("ventesTerminees") != null) {
	%>
	<h2 class="text-center">Mes ventes terminées</h2>
	<hr />
	<div class="row">
		<%
			if (request.getSession().getAttribute("ventesTermineesVendeur") == null) {
		%>

		<p>Vous n'avez aucune vente</p>
	</div>
	<%
		} else {
				ArrayList<Vente> ventesTermineesVendeur = (ArrayList<Vente>) request.getSession()
						.getAttribute("ventesTermineesVendeur");
				for (Vente v : ventesTermineesVendeur) {
	%>


	<div class="col-md-3">
		<div class="card col-md-12 block-info-vente">
			<div class="card-body">
				<h4 class="card-title">
					<a href="#"> <%=v.getNomArticle()%>

					</a>
				</h4>

				<h5 class="card-text">
					Prix :
					<%=v.getPrixInitial()%></h5>
				<h6 class="card-subtitle mb-2 text-muted">
					Fin de l'enchère:
					<%=v.getDateFinEncheres()%></h6>
				<h6 class="card-subtitle mb-2 text-muted">
					Retrait:
					<%=v.getRetrait().getRue() + " " + v.getRetrait().getCodePostal() + " "
								+ v.getRetrait().getVille()%></h6>
				<h5 class="card-title text-left">
					Meilleure offre :
					<%
					if (request.getSession().getAttribute("encheresVentesTermineesVendeur") == null) {
				%>
					Aucune offre
					<%
					} else {
									ArrayList<Enchere> encheresVentesTermineesVendeur = (ArrayList<Enchere>) request
											.getSession().getAttribute("encheresVentesTermineesVendeur");
									Enchere e = encheresVentesTermineesVendeur.get(ventesTermineesVendeur.indexOf(v));
									ArrayList<Utilisateur> utilisateursEncehresVentesTermineesVendeur = (ArrayList<Utilisateur>) request
											.getSession().getAttribute("utilisateursEncehresVentesTermineesVendeur");
									Utilisateur u = utilisateursEncehresVentesTermineesVendeur
											.get(encheresVentesTermineesVendeur.indexOf(e));
				%>

					<%=e.getMontantEnchere()%>
					points par : <a
						href="/ProjetEncheres/Profil?userId=<%=u.getNoUtilisateur()%>&choices=<%=choix%>"
						class="card-link"> <%=u.getPseudo()%></a>
					<%
						}
					%>
				</h5>
				<h5 class="card-title text-right">
					<a
						href="/ProjetEncheres/Vente?saleId=<%=v.getNoVente()%>&choices=<%=choix%>">détails</a>

				</h5>
			</div>
		</div>
	</div>


	<!-- fin mes ventes terminees -->


	<!--  mes enchères en cours -->
	<%
		}
	%>
	</div>
	<%
		}

		}
		if (request.getParameter("encheresEnCours") != null) {
	%>
	<h2 class="text-center">Mes enchères en cours</h2>
	<hr />
	<div class="row">
		<%
			if (request.getSession().getAttribute("ventesEncheresEnCoursAcheteur") == null) {
		%>
		<p>Vous n'avez aucune aucune enchère en cours</p>
	</div>
	<%
		} else {
				ArrayList<Vente> ventesEncheresEnCoursAcheteur = (ArrayList<Vente>) request.getSession()
						.getAttribute("ventesEncheresEnCoursAcheteur");
				ArrayList<Utilisateur> vendeursEncheresEnCoursAcheteur = (ArrayList<Utilisateur>) request
						.getSession().getAttribute("vendeursEncheresEnCoursAcheteur");

				for (Vente v : ventesEncheresEnCoursAcheteur) {
					Utilisateur u = vendeursEncheresEnCoursAcheteur.get(ventesEncheresEnCoursAcheteur.indexOf(v));
	%>




	<div class="col-md-3">
		<div class="card col-md-12 block-info-vente">
			<div class="card-body">
				<h4 class="card-title">
					<a href="#"><%=v.getDescription()%></a>
				</h4>
				<h5 class="card-text">
					Prix intiial :
					<%=v.getPrixInitial()%>
					points
				</h5>
				<h6 class="card-subtitle mb-2 text-muted">
					Fin de l'enchère:
					<%=v.getDateFinEncheres()%></h6>
				<h6 class="card-subtitle mb-2 text-muted">
					Retrait:
					<%=v.getRetrait().getRue() + " " + v.getRetrait().getCodePostal() + " "
								+ v.getRetrait().getVille()%></h6>
				<p>
					Vendeur : <a
						href="/ProjetEncheres/Profil?userId=<%=u.getNoUtilisateur()%>&choices=<%=choix%>"
						class="card-link"><%=u.getPseudo()%></a>
				</p>
				<h5 class="card-title text-left">Classement : 1</h5>
				<h5 class="card-title text-right">
					<a
						href="/ProjetEncheres/Vente?saleId=<%=v.getNoVente()%>&choices=<%=choix%>">détails</a>
				</h5>
			</div>
		</div>
	</div>

	<!-- fin mes enchères en cours -->


	<!--  mes acquisitions -->
	<%
		}
	%>
	</div>
	<%
		}

		}
		if (request.getParameter("acquisitions") != null) {
	%>
	<h2 class="text-center">Mes acquisitions</h2>
	<hr />
	<div class="row">
		<%
			if (request.getSession().getAttribute("acquisitionsAcheteur") == null) {
		%>
		<p>Vous n'avez remporté aucune enchère</p>
	</div>
	<%
		} else {
				ArrayList<Vente> acquisitionsAcheteur = (ArrayList<Vente>) request.getSession()
						.getAttribute("acquisitionsAcheteur");
				ArrayList<Utilisateur> vendeursAcquisitionsAcheteur = (ArrayList<Utilisateur>) request.getSession()
						.getAttribute("vendeursAcquisitionsAcheteur");

				for (Vente v : acquisitionsAcheteur) {
					Utilisateur u = vendeursAcquisitionsAcheteur.get(acquisitionsAcheteur.indexOf(v));
	%>



	<div class="col-md-3">
		<div class="card col-md-12 block-info-vente">
			<div class="card-body">
				<h4 class="card-title">
					<a href="#"><%=v.getDescription()%></a>
				</h4>
				<h5 class="card-text">
					Prix intiial :
					<%=v.getPrixInitial()%></h5>
				<h6 class="card-subtitle mb-2 text-muted">
					Fin de l'enchère:
					<%=v.getDateFinEncheres()%></h6>
				<h6 class="card-subtitle mb-2 text-muted">
					Retrait:
					<%=v.getRetrait().getRue() + " " + v.getRetrait().getCodePostal() + " "
								+ v.getRetrait().getVille()%></h6>
				<p>
					Vendeur : <a
						href="/ProjetEncheres/Profil?userId=<%=u.getNoUtilisateur()%>&choices=<%=choix%>"
						class="card-link"><%=u.getPseudo()%></a>
				</p>
				<h5 class="card-title text-left">
					Prix d'achat final:
					<%=v.getPrixVente()%></h5>
				<h5 class="card-title text-right">
					<a
						href="/ProjetEncheres/Vente?saleId=<%=v.getNoVente()%>&choices=<%=choix%>">détails</a>

				</h5>
			</div>
		</div>
	</div>

	<!-- fin mes acquisitions -->

	<!--  autres choix -->

	<%
		}
	%>
	</div>
	<%
		}

		}
		if (request.getParameter("choix5") != null && request.getParameter("choix5").equals("autresEncheres")) {
	%>
	<h2 class="text-center">Mes acquisitions</h2>
	<hr />
	<%
		if (request.getSession().getAttribute("ventes") == null) {
	%>
	<p>Vous n'avez aucune vente</p>
	<%
		} else {
				ArrayList<Vente> ventes = (ArrayList<Vente>) request.getSession().getAttribute("ventes");
				for (Vente v : ventes) {
	%>
	<%
		}
			}
		}
	%>
	
	</div>
</body>
</html>