<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<title>Troc Encheres</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="/ProjetEncheres/theme/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/ProjetEncheres/theme/css/style.css">
<script src="/ProjetEncheres/theme/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>

	<div class="container">
		<div>
			<h1>TrocEncheres.org</h1>
		</div>
		<div class="row top-buffer">
			<h2 class="text-center">Créer un compte</h2>
			<br>
		</div>
		<form class="row" action="./ServletCreerCompte" method="post">
			<div class="form-group col-md-6 col-xs-12">
				<div class="col-md-4 col-xs-4">
					<label for="pseudo">Pseudo :</label>
				</div>
				<div class="col-md-8 col-xs-8 mt-3">
					<input type="text" class="form-control" id="pseudo" name="pseudo"
						<%if (request.getParameter("pseudo") != null) {%>
						value="<%=request.getParameter("pseudo")%>
						">
					<%
						}
					%>

				</div>
			</div>
			<div class="form-group col-md-6 col-xs-12">
				<div class="col-md-4 col-xs-4">
					<label for="nom">Nom :</label>
				</div>
				<div class="col-md-8 col-xs-8">
					<input type="text" class="form-control" id="nom" name="nom"
						<%if (request.getParameter("nom") != null) {%>
						value="<%=request.getParameter("nom")%>
						">
					<%
						}
					%>
				</div>
			</div>
			<div class="form-group col-md-6 col-xs-12">
				<div class="col-md-4 col-xs-4">
					<label for="Prenom">Prénom :</label>
				</div>
				<div class="col-md-8 col-xs-8">
					<input type="text" class="form-control" id="prenom" name="prenom"
						<%if (request.getParameter("prenom") != null) {%>
						value="<%=request.getParameter("prenom")%>
						">
					<%
						}
					%>
				</div>
			</div>
			<div class="form-group col-md-6 col-xs-12">
				<div class="col-md-4 col-xs-4">
					<label for="email">Email :</label>
				</div>
				<div class="col-md-8 col-xs-8">
					<input type="email" class="form-control" id="email" name="email"
						<%if (request.getParameter("email") != null) {%>
						value="<%=request.getParameter("email")%>
						">
					<%
						}
					%>
				</div>
			</div>
			<div class="form-group col-md-6 col-xs-12">
				<div class="col-md-4 col-xs-4">
					<label for="telephone">Téléphone :</label>
				</div>
				<div class="col-md-8 col-xs-8">
					<input type="text" class="form-control" id="telephone"
						name="telephone"
						<%if (request.getParameter("telephone") != null) {%>
						value="<%=request.getParameter("telephone")%>
						">
					<%
						}
					%>
				</div>
			</div>
			<div class="form-group col-md-6 col-xs-12">
				<div class="col-md-4 col-xs-4">
					<label for="rue">Rue :</label>
				</div>
				<div class="col-md-8 col-xs-8">
					<input type="text" class="form-control" id="rue" name="rue"
						<%if(request.getParameter("rue")!=null){
						%> 
						value="<%=request.getParameter("rue")%>
						">
						<%
						} %>
				</div>
			</div>
			<div class="form-group col-md-6 col-xs-12">
				<div class="col-md-4 col-xs-4">
					<label for="codepostal">Code Postal :</label>
				</div>
				<div class="col-md-8 col-xs-8">
					<input type="text" class="form-control" id="codepostal"
						name="codepostal" 
						<%if(request.getParameter("codepostal")!=null){
						%> 
						value="<%=request.getParameter("codepostal")%>
						">
						<%
						} %>
				</div>
			</div>
			<div class="form-group col-md-6 col-xs-12">
				<div class="col-md-4 col-xs-4">
					<label for="ville">Ville :</label>
				</div>
				<div class="col-md-8 col-xs-8">
					<input type="text" class="form-control" id="ville" name="ville"
					<%if(request.getParameter("ville")!=null){
						%> 
						value="<%=request.getParameter("ville")%>
						">
						<%
						} %>
				</div>
			</div>
			<div class="form-group col-md-6 col-xs-12">
				<%
					if (request.getParameter("motdepasse") != null) {
				%>
				<p class="text-danger">Les mots de passe saisis ne sont pas
					identiques</p>
				<%
					}
				%>
				<div class="col-md-4 col-xs-4">
					<label for="motdepasse">Mot de passe :</label>
				</div>
				<div class="col-md-8 col-xs-8">
					<input type="password" class="form-control" id="motdepasse"
						name="motdepasse">
				</div>
			</div>
			<div class="form-group col-md-6 col-xs-12">
				<div class="col-md-4 col-xs-4">
					<label for="confirmation">Confirmation :</label>
				</div>
				<div class="col-md-8 col-xs-8">
					<input type="password" class="form-control" id="confirmation"
						name="confirmation">
				</div>
			</div>
			<div class="text-center">
				<button type="submit" class="btn btn-primary marge ">Créer</button>
				<button type="button" class="btn btn-primary marge ">Annuler</button>
			</div>
		</form>
	</div>

</body>
</html>