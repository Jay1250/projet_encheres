<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
	<head>
	  <title>Connexion</title>
	  <meta charset="UTF-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="/ProjetEncheres/theme/bootstrap/css/bootstrap.min.css">
	  <link rel="stylesheet" href="/ProjetEncheres/theme/css/style.css">
	  <script src="/ProjetEncheres/theme/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
		<nav class="navbar navbar-inverse">
		  	<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="/ProjetEncheres/PageConnexion.html">TrocEncheres.org</a>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/ProjetEncheres/VerifConnexion"><span class="glyphicon glyphicon-user"></span> Connexion</a></li>
					<li><a href="/ProjetEncheres/ServletCreerCompte"><span class="glyphicon glyphicon glyphicon-pencil"></span> Créer un compte</a></li>
				</ul>
		  	</div>
		</nav>
		<div class="container">
			<div class="row top-buffer">
				<h2 class="text-center">Connexion</h2><br>
			</div>
			<form action="/ProjetEncheres/VerifConnexion" method="post">
				<div class="col-md-4 col-md-offset-4">
					<div class="form-group">
						<label for="exampleInputEmail1">Identifiant : </label>
					    <input type="text" class="form-control" placeholder="Saisir votre identifiant (email ou username)">
					 </div>
					 <div class="form-group">
					    <label for="exampleInputPassword1">Mot de passe : </label>
					    <input type="password" class="form-control" placeholder="Saisir votre mot de passe">
					 </div>
					 <div class="form-check">
					    <input type="checkbox" class="form-check-input">
					    <label class="form-check-label" for="exampleCheck1">Se souvenir de moi</label>
					 </div>
					 <div class="form-group">
					    <a>Mot de passe oublié ?</a>
					    
					 </div>
					 <div class="text-center">
					 	<button type="submit" class="btn btn-primary">Connexion</button>
					 </div>
					 
				</div>
			</form>
		</div>
	</body>
</html>