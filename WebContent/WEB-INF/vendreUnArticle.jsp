<%@page import="java.util.Map,
				org.trocencheres.beans.Categorie
"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
	<head>
	  <title>Troc Encheres</title>
	  <meta charset="UTF-8">
	  <link rel="stylesheet" href="/ProjetEncheres/theme/bootstrap/css/bootstrap.min.css">
	  <link rel="stylesheet" href="/ProjetEncheres/theme/css/style.css">
	  <link rel="stylesheet" href="/ProjetEncheres/theme/css/clockpicker.css">
	</head>
	<% 
	 Categorie categories = (Categorie)request.getSession().getAttribute("categorie");
	 String rue = (String) request.getAttribute("rue");
	 String codePostal = (String) request.getAttribute("codePostal");
	 String ville = (String) request.getAttribute("ville");
	 String article = (String) request.getAttribute("article");
	 String description = (String) request.getAttribute("description");
	 String prixInitial =  (String) request.getAttribute("prixInitial");
	 %>
	<body>
		<nav class="navbar navbar-inverse">
	  		<div class="container-fluid">
			    <div class="navbar-header">
			      <a class="navbar-brand" href="/ProjetEncheres">TrocEncheres.org</a>
			    </div>
			    <ul class="nav navbar-nav">
			      <li><a href="/ProjetEncheres/MonProfil">Mon profil</a></li>
			      <li><a href="/ProjetEncheres/ListEncheres">Les enchères</a></li>
			      <li class="active"><a href="/ProjetEncheres/VendreUnArticle">Vendre un article</a></li>
			    </ul>
			    <ul class="nav navbar-nav navbar-right">
			      <li><a href="/ProjetEncheres/Deconnexion"><span class="glyphicon glyphicon-user"></span> Déconnexion</a></li>
			    </ul>
	  		</div>
		</nav>
		<div class="container">
			<div class="row top-buffer">
				<div class="text-center"><img  style="max-width:300px;" src="/ProjetEncheres/logoProjet.png"></div>
				<h3 class="text-center">Nouvelle vente</h3><br>
			</div>
			<form class="row" action = "/ProjetEncheres/VendreUnArticle" method="POST">
			 	<div class="form-group col-md-12 col-xs-12 text-left">
			 		<% if ((request.getAttribute("articleNonRenseigne") != null && request.getAttribute("articleNonRenseigne").equals(true))
			 				|| (request.getAttribute("descriptionNonRenseigne") != null && request.getAttribute("descriptionNonRenseigne").equals(true))
			 				|| (request.getAttribute("descriptionNonRenseigne") != null && request.getAttribute("descriptionNonRenseigne").equals(true))
			 				|| (request.getAttribute("finEnchereNonRenseigne") != null && request.getAttribute("finEnchereNonRenseigne").equals(true))
			 				|| (request.getAttribute("articleNonRenseigne") != null && request.getAttribute("articleNonRenseigne").equals(true))
			 				|| (request.getAttribute("rueNonRenseigne") != null && request.getAttribute("rueNonRenseigne").equals(true))
			 				|| (request.getAttribute("codePostalNonRenseigne") != null && request.getAttribute("codePostalNonRenseigne").equals(true))
			 				|| (request.getAttribute("villeNonRenseigne") != null && request.getAttribute("villeNonRenseigne").equals(true))
			 				) 
			 		{%>
                	<h4 class="text-danger text-center">Tous les champs sont obligatoires</h4>
            		<%}%>
            		<% if ((request.getAttribute("erreur") != null && request.getAttribute("erreur").equals(true))) 
			 		{%>
                	<h4 class="text-danger text-center">Un problème est survenu lors de la création de la vente</h4>
            		<%}%>
					<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Articles :</label></div>
				  	<div class="col-md-3 col-xs-5 col-md-offset-1">
				  		<input type="text" class="form-control" name="article" <%if (article != null) {%> value="<%=article%>"<%}%>>
				  	</div>
			  	</div>
			 	<div class="form-group col-md-12 col-xs-12 text-left">
					<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Description :</label></div>
				  	<div class="col-md-3 col-xs-5 col-md-offset-1">
				  		<textarea class="form-control" rows="3" name="description">
				  			<%if (article != null) {%> <%=description%> <%} %>
				  		</textarea>
				  	</div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
					<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Photo de l'article :</label></div>
					<div class="col-md-3 col-xs-5 col-md-offset-1"><button type="button" class="btn btn-primary">Uploader</button></div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
					<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Prix initial :</label></div>
					<div class="col-md-3 col-xs-5 col-md-offset-1">
						<input class="form-control" type="number" min="1" value="220" name="prixinitial" <%if (prixInitial != null) {%> value="<%=prixInitial%>"<%}%>>
					</div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
					<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Fin de l'enchère :</label></div>
					<div class="col-md-3 col-xs-5 col-md-offset-1" data-autoclose="true">
						<input type="text" name="finencheretime" class="timepicker form-control" value="00:00" data-autoclose="true" readonly/>
						<input type="date" class="form-control" name="finencheredate">
					</div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
					<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label for="inputState">Catégories</label></div>
					<div class="col-md-3 col-xs-5 col-md-offset-1">   				
						<select class="form-control" name="categorie">
        					<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>		
	      					<c:forEach var='item' items='${categorie}'>
								<option value="${item.key}"><c:out value='${item.value.libelle}'/></option>
							</c:forEach>
      					</select>
      				</div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
			    	<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><h4>Retrait :</h4></div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
			    	<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>rue :</label></div>
			    	<div class="col-md-3 col-xs-5 col-md-offset-1">
			    		<input type="text" class="form-control" name="rue" <%if (rue != null) {%> value="<%=rue%>"<%}%>/>
					</div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
			    	<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>code postal :</label></div>
			    	<div class="col-md-3 col-xs-5 col-md-offset-1">
			    		<input type="text" class="form-control" name="codepostal" <%if (codePostal != null) {%> value="<%=codePostal%>"<%}%>>
			    	</div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
			    	<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>ville :</label></div>
			    	<div class="col-md-3 col-xs-5 col-md-offset-1">
			    		<input type="text" class="form-control" name="ville" <%if (ville != null) {%> value="<%=ville%>"<%}%>>
			    	</div>
			  	</div>
			  	<div class="text-center">
			  		<button type="submit" class="btn btn-primary marge ">Publier</button>
			  		<a href="/ProjetEncheres/ListEncheres" class="btn btn-primary marge ">Annuler</a>
			  	</div>
			</form>
		</div>
		<script src="/ProjetEncheres/theme/js/jquery-3.3.1.js"></script>
		<script src="/ProjetEncheres/theme/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/ProjetEncheres/theme/js/clockpicker.js"></script>
		<script type="text/javascript">
		   $('.timepicker').clockpicker({
			    placement: 'top',
			    align: 'left',
			    donetext: 'Done'
			});
		 </script>
	</body>
</html>