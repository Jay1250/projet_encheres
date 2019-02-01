<!-- author JY + JI -->
<%@page import="org.trocencheres.beans.Utilisateur"%>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
	<head>
	  <title>Troc Encheres</title>
	  <meta charset="UTF-8">
	  <link rel="stylesheet" href="<%=request.getContextPath()%>/theme/bootstrap/css/bootstrap.min.css">
	  <link rel="stylesheet" href="<%=request.getContextPath()%>/theme/css/style.css">
	</head>
	<% Utilisateur utilisateurConnecte=(Utilisateur)request.getSession().getAttribute("utilisateurConnecte");
%>	
	<body>
		<nav class="navbar navbar-inverse">
	  		<div class="container-fluid">
			    <div class="navbar-header">
			      <a class="navbar-brand" href="<%=request.getContextPath()%>">TrocEncheres.org</a>
			    </div>
			    <ul class="nav navbar-nav">
			      <li class="active"><a href="<%=request.getContextPath()%>/MonProfil">Mon profil</a></li>
			      <li><a href="<%=request.getContextPath()%>/ListEncheres">Les enchères</a></li>
			      <li><a href="<%=request.getContextPath()%>/VendreUnArticle">Vendre un article</a></li>
			    </ul>
			    <ul class="nav navbar-nav navbar-right">
			      <li><a href="<%=request.getContextPath()%>/Deconnexion"><span class="glyphicon glyphicon-user"></span> Déconnexion</a></li>
			    </ul>
	  		</div>
		</nav>
		<div class="container">
			<div class="row top-buffer">
				<div class="text-center"><img  style="max-width:300px;" src="<%=request.getContextPath()%>/logoProjet.png"></div>
				<h3 class="text-center"><%=utilisateurConnecte.getPseudo() %></h3><br>
			</div>
			<form class="row ">
			  <div class="form-group col-md-12 col-xs-12 text-left">
				  <div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label><strong>Pseudo :</strong></label></div>
				  <div class="col-md-3 col-xs-5"><label><%=utilisateurConnecte.getPseudo() %></label></div>
			  </div>
			  <div class="form-group col-md-12 col-xs-12 text-left">
				<div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Nom :</label></div>
				<div class="col-md-3 col-xs-5"><label><%=utilisateurConnecte.getNom() %></label></div>
			  </div>
			  <div class="form-group col-md-12 col-xs-12 text-left">
				<div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Prénom :</label></div>
				<div class="col-md-3 col-xs-5"><label><%=utilisateurConnecte.getPrenom()%></label></div>
			  </div>
			  <div class="form-group col-md-12 col-xs-12 text-left">
				<div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Email :</label></div>
				<div class="col-md-3 col-xs-5"><label><%=utilisateurConnecte.getEmail() %></label></div>
			  </div>
			  <div class="form-group col-md-12 col-xs-12 text-left">
				<div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Téléphone :</label></div>
				<div class="col-md-3 col-xs-5"><label><%=utilisateurConnecte.getTelephone() %></label></div>
			  </div>
			  <div class="form-group col-md-12 col-xs-12 text-left">
			    <div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Rue :</label></div>
			    <div class="col-md-3 col-xs-5"><label><%=utilisateurConnecte.getRue() %></label></div>
			  </div>
			  <div class="form-group col-md-12 col-xs-12 text-left">
			    <div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Code Postal :</label></div>
			    <div class="col-md-3 col-xs-5"><label><%=utilisateurConnecte.getCodePostal() %></label></div>
			  </div>
			  <div class="form-group col-md-12 col-xs-12 text-left">
			    <div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Ville :</label></div>
			    <div class="col-md-3 col-xs-5"><label><%=utilisateurConnecte.getVille() %></label></div>
			  </div>
			    <div class="form-group col-md-12 col-xs-12 text-left">
			    <div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><h3>Credit : </h3></div>
			    <div class="col-md-3 col-xs-5"><h3><%=utilisateurConnecte.getCredit()%></h3></div>
			  </div>
			  <div class="text-center">
			  	<a href="<%=request.getContextPath()%>/ListEncheres" class="btn btn-primary marge" >Retour</a>
			  	<a href="<%=request.getContextPath()%>/MonProfil?modifierProfil=true" class="btn btn-primary marge">Modifier</a>
			  </div>
			</form>
		</div>
	</body>
</html>