<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Erreur</title>
</head>
<body>
<% Exception e=(Exception) request.getAttribute("erreur"); %>
<h2>ERREUR</h2>
<p>erreur détectée : <%=e.getMessage()%></p>
</body>
</html>