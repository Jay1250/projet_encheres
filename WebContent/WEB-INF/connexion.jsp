<!-- author JY + JI -->
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Connexion</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/theme/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/theme/css/style.css">
    <script src="<%=request.getContextPath()%>/theme/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="<%=request.getContextPath()%>">TrocEncheres.org</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="<%=request.getContextPath()%>/Connexion"><span class="glyphicon glyphicon-user"></span> Connexion</a>
            </li>
            <li><a href="<%=request.getContextPath()%>/CreerCompte"><span class="glyphicon glyphicon glyphicon-pencil"></span>
                Créer un compte</a></li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="row top-buffer">
        <div class="text-center"><img style="max-width:300px;" src="<%=request.getContextPath()%>/logoProjet.png"
                                      alt="Logo Troc Enchères - Les objets sont nos amis"></div>
        <h2 class="text-center">Connexion</h2><br>
    </div>
    <form action="<%=request.getContextPath()%>/Connexion" method="post">
        <div class="col-md-4 col-md-offset-4">
            <% if (request.getAttribute("identifiantOuMdpIncorrects") != null && request.getAttribute("identifiantOuMdpIncorrects").equals(true)) {%>
                <p class="text-danger">Identifiant ou mot de passe incorrect</p>
            <%}%>
            <div class="form-group">
                <label for="exampleInputEmail1">Identifiant : </label>
                <% if (request.getAttribute("identifiantNonRenseigne") != null && request.getAttribute("identifiantNonRenseigne").equals(true)) {%>
                <p class="text-danger">Veuillez saisir votre identifiant</p>
                <%} %>
                <input type="text"
                       class="form-control"
                       placeholder="Saisir votre identifiant (email ou username)"
                       name="identifiant"
                       maxlength="30"
                    <% if (request.getParameter("identifiant") != null && !request.getParameter("identifiant").trim().equals("")) { %>
                       value="<%=request.getParameter("identifiant")%>"
                    <%}%>
                >
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Mot de passe : </label>
                <% if (request.getAttribute("mdpNonRenseigne") != null && request.getAttribute("mdpNonRenseigne").equals(true)) {%>
                <p class="text-danger">Veuillez saisir votre mot de passe</p>
                <%} %>
                <input type="password"
                       class="form-control"
                       placeholder="Saisir votre mot de passe"
                       name="motdepasse"
                       maxlength="30"
                    <% if (request.getParameter("motdepasse") != null && !request.getParameter("motdepasse").trim().equals("")) { %>
                       value="<%=request.getParameter("motdepasse")%>"
                    <%}%>
                >
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