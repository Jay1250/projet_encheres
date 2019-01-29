<!-- author JY -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Erreur</title>
</head>
<body>
<% String id=(String)request.getParameter("identifiant");%>
<h2>ERREUR</h2>
<p>erreur détectée : Le mot de passe ou l'identifiant
<%=id%>
 saisis ne sont pas corrects.
</p>
<a href="/ProjetEncheres/WEB-INF/connexion.jsp">Réessayez</a>
</body>
</html>