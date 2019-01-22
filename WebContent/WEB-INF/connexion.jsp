<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Connexion</title>
</head>
<body>
	<div class="jumbotron text-center">
		<h1>Bienvenue sur TrocEnchere.org</h1>
	</div>

	<div class="col-sm-12 col-xs-12">

		<div class="col-sm-10 col-xs-10">
			<form action="/ProjetEncheres/ServletConnexion" method="post">
				<div class="form-group">
					<label for="identifiant">Identifiant :</label> <input type="text"
						class="form-control" id="identifiant" name="identifiant">
				</div>
		</div>
		<br> <br>
		<div class="col-sm-10 col-xs-10">
			<div class="form-group">
				<label for="motdepasse">Mot de passe :</label> <input
					type="password" class="form-control" id="motdepasse"
					name="motdepasse">
			</div>
		</div>
		<br> <br>
		<div class="col-sm-10 col-xs-10"
			<div class="col-sm-4 col-xs-4">
			<button type="submit" class="btn btn-default">Connexion</button>
		</div>
			<div class="col-sm-5 col-xs-5">
			<button type="checkbox" class="btn btn-default">Se souvenir
				de moi</button>
		</div>
			<div class="col-sm-5 col-xs-5">
			<a href="pageMdpOublie">Mot de passe oublié</a>
		</div>
			</form>

			<br> <br>

			<div class="col-sm-10 col xs-10">
				<form action="/ProjetEncheres/ServletCreerUnCompte" method="post">
					<div class="form-group">
						<button type="submit" class="btn btn-default">Créer un
							compte</button>
					</div>
				</form>
			</div>
		</div>

		<div class="row">
			<div class="footer col-sm-12 col xs-12">
				<p>Copyright (c) TrocEncheres.org</p>
			</div>
		</div>
</body>
</html>