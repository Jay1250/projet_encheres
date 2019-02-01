<%--Author Kévin Le Devéhat + JI--%>
<%@ page import="org.trocencheres.beans.Vente" %>
<%@ page import="org.trocencheres.beans.Retrait" %>
<%@ page import="org.trocencheres.beans.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Troc Encheres</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/theme/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/theme/css/style.css">
        <script src="<%=request.getContextPath()%>/theme/js/jquery-3.3.1.js"></script>
        <script src="<%=request.getContextPath()%>/theme/bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="<%=request.getContextPath()%>/Connexion">TrocEncheres.org</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/MonProfil">Mon profil</a></li>
                    <li class="active"><a href="<%=request.getContextPath()%>/ListEncheres">Les enchères</a></li>
                    <li><a href="<%=request.getContextPath()%>/VendreUnArticle">Vendre un article</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="<%=request.getContextPath()%>/Deconnexion"><span class="glyphicon glyphicon-user"></span> Déconnexion</a></li>
                </ul>
            </div>
        </nav>
        <p class="container">
            <div class="row top-buffer">
                <div class="text-center"><img  style="max-width:300px;" src="<%=request.getContextPath()%>/logoProjet.png"></div>
                <h3 class="text-center">Détail vente</h3><br>
            </div>
            <% Object requestSale = request.getAttribute("vente");
                if (requestSale != null) {
                    Vente currentSale = (Vente) requestSale;
                    if (currentSale.getNoVente() != 0) {

                        Object requestLastAuctionPrice = request.getAttribute("montantDerniereEnchere");
                        Object requestLastBidder = request.getAttribute("dernierEncherisseur");
                        int lastAuctionPrice = 0;
                        Utilisateur lastBidder = null;
                        if (requestLastAuctionPrice != null && requestLastBidder != null) {
                            lastAuctionPrice = (int) requestLastAuctionPrice;
                            lastBidder = (Utilisateur) requestLastBidder;
                        }

                        Utilisateur seller = null;
                        Object requestSeller = request.getAttribute("vendeur");
                        if (requestSeller != null) seller = (Utilisateur) requestSeller;

                        Utilisateur currentUser = (Utilisateur)session.getAttribute("utilisateurConnecte");
                        boolean currentUserIsSeller = false;
                        boolean currentUserIsLastBidder = false;
                        if (currentUser != null) {
                            if (seller != null) currentUserIsSeller = seller.getNoUtilisateur() == currentUser.getNoUtilisateur();
                            if (lastBidder != null) currentUserIsLastBidder = lastBidder.getNoUtilisateur() == currentUser.getNoUtilisateur();
                        }
            %>
                 <% Object requestEndedSale = request.getAttribute("venteTerminee");
                     boolean saleEnded = false;
                    if (requestEndedSale != null) saleEnded = (boolean)requestEndedSale;
                 %>
                <form class="row" action="./Vente?saleId=<%=currentSale.getNoVente()%>" method="post">
                    <div class="form-group col-md-12 col-xs-12 text-left">
                        <div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Article :</label></div>
                        <div class="col-md-3 col-xs-5 col-md-offset-1"><label><%=currentSale.getNomArticle()%></label></div>
                    </div>
                    <div class="form-group col-md-12 col-xs-12 text-left hidden-xs">
                        <div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Description :</label></div>
                        <div class="col-md-3 col-xs-5 col-md-offset-1"><label><%=currentSale.getDescription()%></label></div>
                    </div>
                    <div class="form-group col-md-12 col-xs-12 text-left">
                        <div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Meilleure offre :</label></div>
                        <div class="col-md-3 col-xs-5 col-md-offset-1">
                            <% if (requestLastAuctionPrice != null && requestLastBidder != null) { %>
                                <label><%=lastAuctionPrice%> points par
                                    <a href="./Profil?userId=<%=lastBidder.getNoUtilisateur()%>&fromSale=<%=currentSale.getNoVente()%>">
                                        <%=lastBidder.getPseudo()%>
                                    </a>
                                </label>
                            <% } else { %>
                                <label>Aucune offre</label>
                            <% } %>
                        </div>
                    </div>
                    <div class="form-group col-md-12 col-xs-12 text-left">
                        <div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Mise à prix :</label></div>
                        <div class="col-md-3 col-xs-5 col-md-offset-1"><label><%=currentSale.getPrixInitial()%></label></div>
                    </div>
                    <div class="form-group col-md-12 col-xs-12 text-left">
                        <div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Fin de l'enchère :</label></div>
                        <div class="col-md-3 col-xs-5 col-md-offset-1"><label><%=currentSale.getDateFinEncheres()%></label></div>
                    </div>
                    <%Retrait withdrawal = currentSale.getRetrait();%>
                    <div class="form-group col-md-12 col-xs-12 text-left">
                        <div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Retrait :</label></div>
                        <div class="col-md-3 col-xs-5 col-md-offset-1">
                            <label>
                                <%=withdrawal.getRue() + " " + withdrawal.getCodePostal() + " " +
                                        withdrawal.getVille()%>
                            </label>
                        </div>
                    </div>
                    <% if (requestSeller != null) { %>
                        <div class="form-group col-md-12 col-xs-12 text-left">
                            <div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Vendeur :</label></div>
                            <div class="col-md-3 col-xs-5 col-md-offset-1">
                                <label>
                                    <a href="<%=request.getContextPath()%>/Profil?userId=<%=seller.getNoUtilisateur()%>&fromSale=<%=currentSale.getNoVente()%>">
                                        <%=seller.getPseudo()%>
                                    </a>
                                </label>
                            </div>
                        </div>
                        <% if (!currentUserIsSeller && !currentUserIsLastBidder && !saleEnded) {
                            int proposedPrice = (requestLastAuctionPrice != null && requestLastBidder != null) ? lastAuctionPrice +1 : currentSale.getPrixInitial();
                        %>
                            <div class="form-group col-md-12 col-xs-12 text-left">
                                <div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Ma proposition :</label></div>
                                <div class="col-md-2 col-xs-5 col-md-offset-1">
                                    <input class="form-control" type="number" name="newbid" value="<%=proposedPrice%>">
                                        <% Object requestErrorBidding = request.getAttribute("errorBidding");
                                            if (requestErrorBidding != null) {
                                                String errorBidding = (String) requestErrorBidding;
                                        %>
                                        <p class="text-danger"><%=errorBidding%></p>
                                        <% } %>
                                </div>
                            </div>
                            <% if (currentUser != null) {%>
                                <div class="form-group col-md-12 col-xs-12 text-left">
                                    <div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Votre credit :</label></div>
                                    <div class="col-md-3 col-xs-5 col-md-offset-1"><label><%=currentUser.getCredit()%></label></div>
                                </div>
                            <% } %>
                        <% } %>
                    <% } else { %>
                        <div class="form-group col-md-12 col-xs-12 text-left">
                            <div class="col-md-3 col-xs-5 col-md-offset-3 col-xs-offset-1"><label>Vendeur :</label></div>
                            <div class="col-md-3 col-xs-5 col-md-offset-1"><label>unknown</label></div>
                        </div>
                    <% } %>
                    <div class="form-group col-md-12 col-xs-12 text-center">
                        <a  class="visible-xs" href="ModalInfosVente.html" data-toggle="modal" data-target="#infosVente">Détails</a>
                    </div>
                    <% if (saleEnded) {%>
                    <div class="text-center">
                        <h3 class="text-center">Vente terminée</h3><br>
                    </div>
                    <%}%>
                    <div class="text-center">
                        <% if (!saleEnded) { %>
                            <% if (!currentUserIsSeller) { %>
                                <% if (!currentUserIsLastBidder) { %>
                                    <button type="submit" class="btn btn-primary marge ">Enchérir</button>
                                <% } else { %>
                                    <%--<button type="button" class="btn btn-primary marge ">Annuler ma dernière enchère</button>--%>
                                <% } %>
                            <% } else { %>
                                <a href="<%=request.getContextPath()%>/Vente?saleId=<%=currentSale.getNoVente()%>&delete=true" class="btn btn-primary marge">Supprimer cette vente</a>
                            <% } %>
                        <% } %>
                        <a href="<%=request.getContextPath()%>/ListEncheres" class="btn btn-primary marge">Retour</a>
                    </div>
                </form>
            <%} else {%>
                <div class="text-center">
                    <p>Aucune vente à afficher</p>
                </div>
                <div class="text-center">
                    <a href="<%=request.getContextPath()%>/ListEncheres" class="btn btn-primary marge">Retour</a>
                </div>
            <%}} else {%>
            <div class="text-center">
                <p>Aucune vente à afficher</p>
            </div>
            <div class="text-center">
                <a href="<%=request.getContextPath()%>/ListEncheres" class="btn btn-primary marge">Retour</a>
            </div>
            <%}%>
            <!--  modal détails vente -->
            <div id="infosVente" class="modal fade text-center">
                <div class="modal-dialog"><div class="modal-content"></div></div>
            </div>
        </div>
    </body>
</html>
