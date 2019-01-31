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
					<input class="form-check-input" type="checkbox" name="choix1"
						value="ventesEnCours"> <label class="form-check-label"
						for="checkventes">Mes ventes en cours</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" name="choix2"
						value="ventesTerminees"> <label class="form-check-label"
						for="checkventes">Mes ventes terminées</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" name="choix3"
						value="encheresEnCours"> <label class="form-check-label"
						for="checkencheres">Mes enchères en cours</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" name="choix4"
						value="acquisitions"> <label class="form-check-label"
						for="checkacquisition">Mes acquisitions</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" name="choix5"
						value="autresEucheres"> <label class="form-check-label"
						for="checkdefault">Autres enchères</label>
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


		<%
			if (request.getParameter("choix1") != null && request.getParameter("choix1").equals("ventesEnCours")) {
		%>
		<h2 class="text-center">Mes ventes en cours</h2>
		<hr />
		<%
			if (request.getSession().getAttribute("ventesEnCours") == null) {
		%>
		<p>Vous n'avez aucune vente</p>
		<%
			} else {
					ArrayList<Vente> ventesEnCours = (ArrayList<Vente>) request.getSession()
							.getAttribute("ventesEnCours");
					for (Vente v : ventesEnCours) {
		%>

		<!--  mes ventes en cours -->

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
						<%=v.getRetrait()%></h6>
					<h5 class="card-title text-left">
						Meilleure offre :
						<%
						if (request.getSession().getAttribute("encheresEnCours") == null) {
					%>
						Aucune offre
						<%
						} else {
										ArrayList<Enchere> encheresEnCours = (ArrayList<Enchere>) request.getSession()
												.getAttribute("encheresEnCours");
										Enchere e = encheresEnCours.get(ventesEnCours.indexOf(v));
										ArrayList<Utilisateur> utilisateursEnCours = (ArrayList<Utilisateur>) request.getSession()
												.getAttribute("utilisateursEnCours");
										Utilisateur u = utilisateursEnCours.get(encheresEnCours.indexOf(e));
					%>

						<%=e.getMontantEnchere()%>
						points par : <a
							href="/ProjetEncheres/Profil?userId=<%=u.getNoUtilisateur()%>"
							class="card-link"> <%=u.getPseudo()%></a>
						<%
							}
						%>
					</h5>
					<h5 class="card-title text-right">
						<a href="/ProjetEncheres/Vente?saleId=<%=v.getNoVente()%>">détails</a>

					</h5>
				</div>
			</div>
		</div>

		<!-- fin mes ventes en cours -->


		<!--  mes ventes terminées -->
		<%
			}
				}
			}
			if (request.getParameter("choix2") != null && request.getParameter("choix2").equals("ventesTerminees")) {
		%>
		<h2 class="text-center">Mes ventes terminées</h2>
		<hr />
		<%
			if (request.getSession().getAttribute("ventesTerminees") == null) {
		%>
		<p>Vous n'avez aucune vente</p>
		<%
			} else {
					ArrayList<Vente> ventesTerminees = (ArrayList<Vente>) request.getSession()
							.getAttribute("ventesTerminees");
					for (Vente v : ventesTerminees) {
		%>

		<!--  mes ventes en cours -->

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
						<%=v.getRetrait()%></h6>
					<h5 class="card-title text-left">
						Meilleure offre :
						<%
						if (request.getSession().getAttribute("encheresTerminees") == null) {
					%>
						Aucune offre
						<%
						} else {
										ArrayList<Enchere> encheresTerminees = (ArrayList<Enchere>) request.getSession()
												.getAttribute("encheresTerminees");
										Enchere e = encheresTerminees.get(ventesTerminees.indexOf(v));
										ArrayList<Utilisateur> utilisateursTermines = (ArrayList<Utilisateur>) request.getSession()
												.getAttribute("utilisateursTermines");
										Utilisateur u = utilisateursTermines.get(encheresTerminees.indexOf(e));
					%>

						<%=e.getMontantEnchere()%>
						points par : <a
							href="/ProjetEncheres/Profil?userId=<%=u.getNoUtilisateur()%>"
							class="card-link"> <%=u.getPseudo()%></a>
						<%
							}
						%>
					</h5>
					<h5 class="card-title text-right">
						<a href="/ProjetEncheres/Vente?saleId=<%=v.getNoVente()%>">détails</a>

					</h5>
				</div>
			</div>
		</div>

		<!-- fin mes ventes en cours -->


		<!--  mes ventes terminées -->
		<%
			}
				}
			}
			if (request.getParameter("choix3") != null && request.getParameter("choix3").equals("encheresEnCours")) {
		%>
		<h2 class="text-center">Mes enchères en cours</h2>
		<hr />
		<%
			if (request.getSession().getAttribute("ventesEnCours") == null) {
		%>
		<p>Vous n'avez aucune aucune enchère en cours</p>
		<%
			} else {
					ArrayList<Vente> ventesEnCours2 = (ArrayList<Vente>) request.getSession()
							.getAttribute("ventesEnCours2");
					ArrayList<Utilisateur> utilisateursEnCours2 = (ArrayList<Utilisateur>) request.getSession()
							.getAttribute("utilisateursEnCours2");
					
					for (Vente v : ventesEnCours2) {
						Utilisateur u = utilisateursEnCours2.get(ventesEnCours2.indexOf(v));
		%>


		<!--  mes enchères en cours -->

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
						<%=v.getRetrait()%></h6>
					<p>
						Vendeur :
					
						<a href="/ProjetEncheres/Profil?userId=<%=u.getNoUtilisateur()%>"
							class="card-link"><%=u.getPseudo()%></a>
					</p>
					<h5 class="card-title text-left">Classement : 1</h5>
					<h5 class="card-title text-right">
						<a href="/ProjetEncheres/Vente?saleId=<%=v.getNoVente()%>">détails</a>
					</h5>
				</div>
			</div>
		</div>

		<!-- fin mes enchères en cours -->

		<%
			}
				}
			}
			if (request.getParameter("choix4") != null && request.getParameter("choix4").equals("acquisitions")) {
		%>
		<h2 class="text-center">Mes acquisitions</h2>
		<hr />
		<%
			if (request.getSession().getAttribute("ventesTerminees2") == null) {
		%>
		<p>Vous n'avez emporté aucune enchère</p>
		<%
			} else {
					ArrayList<Vente> ventesTerminees2 = (ArrayList<Vente>) request.getSession()
							.getAttribute("ventesTerminees2");
					ArrayList<Utilisateur> utilisateursTermines2 = (ArrayList<Utilisateur>) request.getSession()
							.getAttribute("utilisateursTermines2");
					
					for (Vente v : ventesTerminees2) {
						Utilisateur u = utilisateursTermines2.get(ventesTerminees2.indexOf(v));
		%>

		<!--  mes acquisitions -->

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
						<%=v.getRetrait()%></h6>
					<p>
						Vendeur :
						<a href="/ProjetEncheres/Profil?userId=<%=u.getNoUtilisateur()%>"
							class="card-link"><%=u.getPseudo()%></a>
					</p>
					<h5 class="card-title text-left">
						Prix d'achat final:
						<%=v.getPrixVente()%></h5>
					<h5 class="card-title text-right">
						<a href="/ProjetEncheres/Vente?saleId=<%=v.getNoVente()%>">détails</a>

					</h5>
				</div>
			</div>
		</div>
		
		<!-- fin mes acquisitions -->
		<%
			}
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