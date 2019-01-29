<!-- author JY + JI -->
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
	  <link rel="stylesheet" href="/ProjetEncheres/theme/bootstrap/css/bootstrap.min.css">
	  <link rel="stylesheet" href="/ProjetEncheres/theme/css/style.css">
	  <script src="/ProjetEncheres/theme/js/jquery-3.3.1.js"></script>
	  <script src="/ProjetEncheres/theme/bootstrap/js/bootstrap.min.js"></script>
	</head>
		<% Utilisateur utilisateurConnecte = (Utilisateur)request.getSession().getAttribute("utilisateurConnecte");
			String identifiant = utilisateurConnecte.getPseudo(); %>
	<body>
<nav class="navbar navbar-inverse">
	  		<div class="container-fluid">
			    <div class="navbar-header">
			      <a class="navbar-brand" href="/ProjetEncheres">TrocEncheres.org</a>
			    </div>
			    <ul class="nav navbar-nav">
			      <li><a href="/ProjetEncheres/MonProfil">Mon profil</a></li>
			      <li class="active"><a href="/ProjetEncheres/ListEncheres">Les enchères</a></li>
			      <li><a href="/ProjetEncheres/VendreUnArticle">Vendre un article</a></li>
			    </ul>
			    <ul class="nav navbar-nav navbar-right">
			      <li><a href="/ProjetEncheres/Deconnexion"><span class="glyphicon glyphicon-user"></span> Déconnexion</a></li>
			    </ul>
	  		</div>
		</nav>
		<div class="container-fluid">
			<div class="alert alert-success col-md-2 col-md-push-9 alert-dismissable">
			<% if (request.getAttribute("changerProfil")!=null && request.getAttribute("changerProfil").equals(true)){ %>
			<strong>Votre profil a bien été changé <%=identifiant %>  ! </strong>
			<%}else{ %>
    			<strong>Bonjour <%=identifiant %> !</strong>
    			<%} %>
    			<button type="button" class="close" data-dismiss="alert">&times;</button>
			</div>
		</div>
		
		<div class="container">
			<form class="row ">
				<h3 class="text-center">Filtres : </h3>
				<div class="form-group col-md-5 col-md-offset-2">
					<div class="form-check">
	  					<input class="form-check-input" type="checkbox" value="">
	  					<label class="form-check-label" for="checkventes">Mes ventes en cours</label>
					</div>
					<div class="form-check">
	  					<input class="form-check-input" type="checkbox" value="">
	  					<label class="form-check-label" for="checkventes">Mes ventes terminées</label>
					</div>
					<div class="form-check">
	  					<input class="form-check-input" type="checkbox" value="">
	  					<label class="form-check-label" for="checkencheres">Mes enchères en cours</label>
					</div>
					<div class="form-check">
	  					<input class="form-check-input" type="checkbox" value="">
	  					<label class="form-check-label" for="checkacquisition">Mes acquisitions</label>
					</div>
					<div class="form-check">
	  					<input class="form-check-input" type="checkbox" value="">
	  					<label class="form-check-label" for="checkdefault">Autres enchères</label>
					</div>
				</div>
			    <div class="form-group col-md-4">
      				<label for="inputState">Catégories</label>
      				<select class="form-control">
        				<option selected>Toutes</option>
        				<option>...</option>
      				</select>
    			</div>
    			<div class="form-group col-md-4">
    				<input type="password" class="form-control" placeholder="Le nom de l'article contient">
  				</div>
  				
    			<div class="form-group text-center col-md-2 col-md-pull-2">
			  		<button type="submit" class="btn btn-primary marge">Rechercher</button>
			  	</div>
			</form>
			
			<h2 class="text-center">Mes ventes en cours</h2><hr />
			<%
			if (request.getSession().getAttribute("ventes")==null){
				%>
				<p> Vous n'avez aucune vente </p>
			<%
			}else {
				ArrayList <Vente> ventes=(ArrayList<Vente>)request.getSession().getAttribute("ventes");
				int nbVentes= 1;
				for(int i=0; i<nbVentes;i++ ){
				%>
			
			<!--  mes ventes en cours -->
			
	    	<div class="col-md-3">
	    		<div class="card col-md-12 block-info-vente">
					<div class="card-body">
						<h4 class="card-title"><a href="#">
							<%=ventes.get(i).getNomArticle()%>
						
							</a></h4>
						
						<h5 class="card-text">Prix : <%=ventes.get(i).getPrixInitial()%></h5>
						<h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: <%=ventes.get(i).getDateFinEncheres()%></h6>
						<h6 class="card-subtitle mb-2 text-muted">Retrait: <%=utilisateurConnecte.getVille()%></h6>
						<h5 class="card-title text-left">Meilleure offre : 210 points par <a href="#">jojo45</a></h5>
						<h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>
					</div>
				</div>
	    	</div>
			<div class="col-md-3">
				<div class="card col-md-12 block-info-vente">
					<div class="card-body">
					    <h4 class="card-title"><a href="#">Rocket stove pour riz et pâtes</a></h4>
					    <h5 class="card-text">Prix : 185 points</h5>
					    <h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 09/10/2018</h6>
						<h6 class="card-subtitle mb-2 text-muted">Retrait: 5 rue des Pinsons 44000 Nantes</h6>
					    <h5 class="card-title text-left">Meilleure offre : aucune offre</h5>
					    <h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>
					</div>
				</div>
	    	</div>
	    	<div class="col-md-3">
	    		<div class="card col-md-12 block-info-vente">
					<div class="card-body">
					    <h4 class="card-title"><a href="#">PC Gamer pour travailler</a></h4>
					    <h5 class="card-text">Prix : 210 points</h5>
					    <h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 10/08/2018</h6>
					    <h6 class="card-subtitle mb-2 text-muted">Retrait: 10 allée des Alouettes 44800 Saint Herblain</h6>
						<h5 class="card-title text-left">Meilleure offre : 210 points par <a href="#">jojo45</a></h5>
						<h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>
					 </div>
				</div>
	    	</div>
		    <div class="col-md-3">
    			<div class="card col-md-12 block-info-vente">
				 	<div class="card-body">
				    	<h4 class="card-title"><a href="#">PC Gamer pour travailler</a></h4>
				    	<h5 class="card-text">Prix : 210 points</h5>
				      	<h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 10/08/2018</h6>
				      	<h6 class="card-subtitle mb-2 text-muted">Retrait: 10 allée des Alouettes 44800 Saint Herblain</h6>
				      	<h5 class="card-title text-left">Meilleure offre : aucune offre</h5>
				    	<h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>		
				  	</div>
				</div>
    		</div>
    		<!-- fin mes ventes en cours -->
    		
			<!--  mes ventes terminées -->
			<h2 class="text-center">Mes ventes terminées</h2><hr />
	    	<div class="col-md-3">
	    		<div class="card col-md-12 block-info-vente">
					<div class="card-body">
						<h4 class="card-title"><a href="#">PC Gamer pour travailler</a></h4>
						<h5 class="card-text">Prix : 210 points</h5>
						<h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 10/08/2018</h6>
						<h6 class="card-subtitle mb-2 text-muted">Retrait: 10 allée des Alouettes 44800 Saint Herblain</h6>
						<h5 class="card-title text-left">Acheteurs : 210 points par <a href="#">jojo45</a></h5>
						<h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>
					</div>
				</div>
	    	</div>
			<div class="col-md-3">
				<div class="card col-md-12 block-info-vente">
					<div class="card-body">
					    <h4 class="card-title"><a href="#">Rocket stove pour riz et pâtes</a></h4>
					    <h5 class="card-text">Prix : 185 points</h5>
					    <h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 09/10/2018</h6>
						<h6 class="card-subtitle mb-2 text-muted">Retrait: 5 rue des Pinsons 44000 Nantes</h6>
					    <h5 class="card-title text-left">Acheteurs : aucune offre</h5>
					    <h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>
					</div>
				</div>
	    	</div>
	    	<div class="col-md-3">
	    		<div class="card col-md-12 block-info-vente">
					<div class="card-body">
					    <h4 class="card-title"><a href="#">PC Gamer pour travailler</a></h4>
					    <h5 class="card-text">Prix : 210 points</h5>
					    <h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 10/08/2018</h6>
					    <h6 class="card-subtitle mb-2 text-muted">Retrait: 10 allée des Alouettes 44800 Saint Herblain</h6>
						<h5 class="card-title text-left">Acheteurs : 210 points par <a href="#">jojo45</a></h5>
						<h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>
					 </div>
				</div>
	    	</div>
		    <div class="col-md-3">
    			<div class="card col-md-12 block-info-vente">
				 	<div class="card-body">
				    	<h4 class="card-title"><a href="#">PC Gamer pour travailler</a></h4>
				    	<h5 class="card-text">Prix : 210 points</h5>
				      	<h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 10/08/2018</h6>
				      	<h6 class="card-subtitle mb-2 text-muted">Retrait: 10 allée des Alouettes 44800 Saint Herblain</h6>
				      	<h5 class="card-title text-left">Acheteurs : aucune offre</h5>
				    	<h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>		
				  	</div>
				</div>
    		</div>
    		<%
    		}
			}
    		%>
    		<!-- fin mes ventes terminées -->	
    		
			<!--  mes enchères en cours -->
			<h2 class="text-center test">Mes enchères en cours</h2><hr />
	    	<div class="col-md-3">
	    		<div class="card col-md-12 block-info-vente">
					<div class="card-body">
						<h4 class="card-title"><a href="#">PC Gamer pour travailler</a></h4>
						<h5 class="card-text">Prix : 210 points</h5>
						<h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 10/08/2018</h6>
						<h6 class="card-subtitle mb-2 text-muted">Retrait: 10 allée des Alouettes 44800 Saint Herblain</h6>
						<p>Vendeur : <a href="#" class="card-link">jojo42</a></p>
						<h5 class="card-title text-left">Classement : 1</h5>
						<h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>
					</div>
				</div>
	    	</div>
			<div class="col-md-3">
				<div class="card col-md-12 block-info-vente">
					<div class="card-body">
					    <h4 class="card-title"><a href="#">Rocket stove pour riz et pâtes</a></h4>
					    <h5 class="card-text">Prix : 185 points</h5>
					    <h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 09/10/2018</h6>
						<h6 class="card-subtitle mb-2 text-muted">Retrait: 5 rue des Pinsons 44000 Nantes</h6>
					    <p>Vendeur : <a href="#" class="card-link">NinaJea</a></p>
					    <h5 class="card-title text-left">Classement : 4</h5>
					    <h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>
					</div>
				</div>
	    	</div>
	    	<div class="col-md-3">
	    		<div class="card col-md-12 block-info-vente">
					<div class="card-body">
					    <h4 class="card-title"><a href="#">PC Gamer pour travailler</a></h4>
					    <h5 class="card-text">Prix : 210 points</h5>
					    <h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 10/08/2018</h6>
					    <h6 class="card-subtitle mb-2 text-muted">Retrait: 10 allée des Alouettes 44800 Saint Herblain</h6>
					    <p>Vendeur : <a href="#" class="card-link">jojo42</a></p>
						<h5 class="card-title text-left">Classement : 1</h5>
						<h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>
					 </div>
				</div>
	    	</div>
		    <div class="col-md-3">
    			<div class="card col-md-12 block-info-vente">
				 	<div class="card-body">
				    	<h4 class="card-title"><a href="#">PC Gamer pour travailler</a></h4>
				    	<h5 class="card-text">Prix : 210 points</h5>
				      	<h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 10/08/2018</h6>
				      	<h6 class="card-subtitle mb-2 text-muted">Retrait: 10 allée des Alouettes 44800 Saint Herblain</h6>
				    	<p>Vendeur : <a href="#" class="card-link">jojo42</a></p>
				    	<h5 class="card-title text-left">Classement : 2</h5>
				    	<h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>		
				  	</div>
				</div>
    		</div>
    		<!-- fin mes enchères en cours -->
    		
			<!--  mes acquisitions -->
			<h2 class="text-center">Mes acquisitions</h2><hr />
	    	<div class="col-md-3">
	    		<div class="card col-md-12 block-info-vente">
					<div class="card-body">
						<h4 class="card-title"><a href="#">PC Gamer pour travailler</a></h4>
						<h5 class="card-text">Prix : 210 points</h5>
						<h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 10/08/2018</h6>
						<h6 class="card-subtitle mb-2 text-muted">Retrait: 10 allée des Alouettes 44800 Saint Herblain</h6>
						<p>Vendeur : <a href="#" class="card-link">jojo42</a></p>
						<h5 class="card-title text-left">Prix d'achat final: 110 points</h5>
						<h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>
					</div>
				</div>
	    	</div>
			<div class="col-md-3">
				<div class="card col-md-12 block-info-vente">
					<div class="card-body">
					    <h4 class="card-title"><a href="#">Rocket stove pour riz et pâtes</a></h4>
					    <h5 class="card-text">Prix : 185 points</h5>
					    <h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 09/10/2018</h6>
						<h6 class="card-subtitle mb-2 text-muted">Retrait: 5 rue des Pinsons 44000 Nantes</h6>
					    <p>Vendeur : <a href="#" class="card-link">NinaJea</a></p>
					   	<h5 class="card-title text-left">Prix d'achat final : 300 points </h5>
					    <h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>
					</div>
				</div>
	    	</div>
	    	<div class="col-md-3">
	    		<div class="card col-md-12 block-info-vente">
					<div class="card-body">
					    <h4 class="card-title"><a href="#">PC Gamer pour travailler</a></h4>
					    <h5 class="card-text">Prix : 210 points</h5>
					    <h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 10/08/2018</h6>
					    <h6 class="card-subtitle mb-2 text-muted">Retrait: 10 allée des Alouettes 44800 Saint Herblain</h6>
					    <p>Vendeur : <a href="#" class="card-link">jojo42</a></p>
						<h5 class="card-title text-left">Prix d'achat final : 250 points </h5>
						<h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>
					 </div>
				</div>
	    	</div>
		    <div class="col-md-3">
    			<div class="card col-md-12 block-info-vente">
				 	<div class="card-body">
				    	<h4 class="card-title"><a href="#">PC Gamer pour travailler</a></h4>
				    	<h5 class="card-text">Prix : 210 points</h5>
				      	<h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère: 10/08/2018</h6>
				      	<h6 class="card-subtitle mb-2 text-muted">Retrait: 10 allée des Alouettes 44800 Saint Herblain</h6>
				    	<p>Vendeur : <a href="#" class="card-link">jojo42</a></p>
				    	<h5 class="card-title text-left">Prix d'achat final : 210 points </h5>
				    	<h5 class="card-title text-right"><a href="/ProjetEncheres/PageEncherir.html">détails</a></h5>		
				  	</div>
				</div>
    		</div>
    		<!-- fin mes acquisitions -->
		</div>
	</body>
</html>