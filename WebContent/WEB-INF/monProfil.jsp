<%@page import="org.trocencheres.beans.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<% Utilisateur utilisateurConnecte=(Utilisateur)request.getSession().getAttribute("utilisateurConnecte");
String identifiant = utilisateurConnecte.getPseudo(); %>
<body>
Mon Profil
<br><br>
<p>Bonjour <%=identifiant %> !</p>
<br><br>
<a href="./VerifConnexion">Accueil</a>
</body>
</html>