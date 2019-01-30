<%--Author Kévin Le Devéhat + JI--%>
<%@ page import="org.trocencheres.beans.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Troc Encheres</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="/ProjetEncheres/theme/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="/ProjetEncheres/theme/css/style.css">
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/ProjetEncheres/Connexion">TrocEncheres.org</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/ProjetEncheres/MonProfil">Mon profil</a></li>
                    <li><a href="/ProjetEncheres/ListEncheres">Les enchères</a></li>
                    <li><a href="/ProjetEncheres/VendreUnArticle">Vendre un article</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/ProjetEncheres/Deconnexion"><span class="glyphicon glyphicon-user"></span> Déconnexion</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <div class="row top-buffer">
                <div class="text-center"><img style="max-width:300px;" src="/ProjetEncheres/logoProjet.png"></div>
                <h3 class="text-center">Profil utilisateur</h3><br>
            </div>
            <form class="row ">
                <% Object requestOtherUser = request.getAttribute("autreUtilisateur");
                    if (requestOtherUser != null) {
                        Utilisateur currentUser = (Utilisateur) requestOtherUser;
                %>
                    <div class="form-group col-md-12 col-xs-12 text-left">
                        <div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Pseudo :</label></div>
                        <div class="col-md-3 col-xs-5"><label><%=currentUser.getPseudo() %>
                        </label></div>
                    </div>
                    <div class="form-group col-md-12 col-xs-12 text-left">
                        <div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Nom :</label></div>
                        <div class="col-md-3 col-xs-5"><label><%=currentUser.getNom() %>
                        </label></div>
                    </div>
                    <div class="form-group col-md-12 col-xs-12 text-left">
                        <div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Prénom :</label></div>
                        <div class="col-md-3 col-xs-5"><label><%=currentUser.getPrenom() %>
                        </label></div>
                    </div>
                    <div class="form-group col-md-12 col-xs-12 text-left">
                        <div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Email :</label></div>
                        <div class="col-md-3 col-xs-5"><label><%=currentUser.getEmail() %>
                        </label></div>
                    </div>
                    <div class="form-group col-md-12 col-xs-12 text-left">
                        <div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Téléphone :</label></div>
                        <div class="col-md-3 col-xs-5"><label><%=currentUser.getTelephone() %>
                        </label></div>
                    </div>
                    <%--Not displayed for privacy--%>
                    <%--<div class="form-group col-md-12 col-xs-12 text-left">
                        <div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Rue :</label></div>
                        <div class="col-md-3 col-xs-5"><label><%=currentUser.getRue() %>
                        </label></div>
                    </div>
                    <div class="form-group col-md-12 col-xs-12 text-left">
                        <div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Code Postal :</label></div>
                        <div class="col-md-3 col-xs-5"><label><%=currentUser.getCodePostal() %>
                        </label></div>
                    </div>
                    <div class="form-group col-md-12 col-xs-12 text-left">
                        <div class="col-md-3 col-xs-5 col-md-offset-4 col-xs-offset-1"><label>Ville :</label></div>
                        <div class="col-md-3 col-xs-5"><label><%=currentUser.getVille() %>
                        </label></div>
                    </div>--%>
                <%} else {%>
                    <div class="text-center">
                        <p>Aucun utilisateur à afficher</p>
                    </div>
                <%}%>
                <div class="text-center">
                    <% Object requestFromSale = request.getAttribute("fromSale");
                        if (requestFromSale != null) {
                    %>
                        <a href="/ProjetEncheres/Vente?saleId=<%=(String)requestFromSale%>"
                           class="btn btn-primary marge">
                            Retour
                        </a>
                    <% } else { %>
                        <a href="/ProjetEncheres/ListEncheres" class="btn btn-primary marge">Retour</a>
                    <%}%>
                </div>
            </form>
        </div>
    </body>
</html>
