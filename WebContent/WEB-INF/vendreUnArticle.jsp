<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="fr">
	<head>
	  <title>Troc Encheres</title>
	  <meta charset="ISO-8859-1">
	  <link rel="stylesheet" href="/ProjetEncheres/theme/bootstrap/css/bootstrap.min.css">
	  <link rel="stylesheet" href="/ProjetEncheres/theme/css/style.css">
	</head>
	
	<body>
		<nav class="navbar navbar-inverse">
	  		<div class="container-fluid">
			    <div class="navbar-header">
			      <a class="navbar-brand" href="/ProjetEncheres/PageProfil.html">TrocEncheres.org</a>
			    </div>
			    <ul class="nav navbar-nav">
			      <li><a href="/ProjetEncheres/PageProfil.html">Mon profil</a></li>
			      <li><a href="/ProjetEncheres/PageListeEncheres.html">Les enchères</a></li>
			      <li class="active"><a href="/ProjetEncheres/PageVendreUnArticle.html">Vendre un article</a></li>
			    </ul>
			    <ul class="nav navbar-nav navbar-right">
			      <li><a href=/ProjetEncheres/PageConnexion.html#"><span class="glyphicon glyphicon-user"></span> Déconnexion</a></li>
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
					<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Articles :</label></div>
				  	<div class="col-md-3 col-xs-5 col-md-offset-1"><input type="text" class="form-control" name="article"></div>
			  	</div>
			 	<div class="form-group col-md-12 col-xs-12 text-left">
					<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Description :</label></div>
				  	<div class="col-md-3 col-xs-5 col-md-offset-1"><textarea class="form-control" rows="3" name="description"></textarea></div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
					<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Photo de l'article :</label></div>
					<div class="col-md-3 col-xs-5 col-md-offset-1"><button type="button" class="btn btn-primary">Uploader</button></div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
					<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Prix initial :</label></div>
					<div class="col-md-3 col-xs-5 col-md-offset-1"><input class="form-control" type="number" value="220" name="prixinitial"></div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
					<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Fin de l'enchère :</label></div>
					<div class="col-md-3 col-xs-5 col-md-offset-1"><input type="date" class="form-control" name="finenchere"></div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
					<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label for="inputState">Catégories</label></div>
					<div class="col-md-3 col-xs-5 col-md-offset-1">   				
						<select class="form-control" name="categorie">
        					<option selected>Toutes</option>
        					<option>...</option>
      					</select>
      				</div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
			    	<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><h4>Retrait :</h4></div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
			    	<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>rue :</label></div>
			    	<div class="col-md-3 col-xs-5 col-md-offset-1"><input type="text" class="form-control" name="rue"></div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
			    	<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>code postal :</label></div>
			    	<div class="col-md-3 col-xs-5 col-md-offset-1"><input type="text" class="form-control" name="codepostal"></div>
			  	</div>
			  	<div class="form-group col-md-12 col-xs-12 text-left">
			    	<div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>ville :</label></div>
			    	<div class="col-md-3 col-xs-5 col-md-offset-1"><input type="text" class="form-control" name="ville"></div>
			  	</div>
			  	<div class="text-center">
			  		<button type="submit" class="btn btn-primary marge ">Publier</button>
			  		<button type="button" class="btn btn-primary marge ">Enregistrer</button>
			  		<button type="button" class="btn btn-primary marge ">Annuler</button>
			  	</div>
			</form>
		</div>
	</body>
</html>