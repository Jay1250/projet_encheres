<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

    <%
        Object compt = session.getAttribute("compt");
        if(compt != null) {
    %>
        <p>Nombre d'acces : <%=(int)compt %></p>
    <%
        } else {
    %>
        <p>Compteur inexistant</p>
    <%
        }
    %>
    <p>
        With cookies
        <a href="<%=request.getContextPath()%>/ServletTestSession">Servlet</a>
    </p>

    <p>
        Without cookies
        <a href="<%=response.encodeURL(request.getContextPath()+"/ServletTestSession")%>">Servlet</a>
    </p>

    <p>
        <a href="">Test</a>
    </p>
</body>
</html>
