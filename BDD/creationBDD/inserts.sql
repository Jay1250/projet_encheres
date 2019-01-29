-- CATEGORIES INSERTS
INSERT INTO dbo.CATEGORIES VALUES ('Informatique');
INSERT INTO dbo.CATEGORIES VALUES ('Vêtement');
INSERT INTO dbo.CATEGORIES VALUES ('Jouet');
INSERT INTO dbo.CATEGORIES VALUES ('Décoration');
INSERT INTO dbo.CATEGORIES VALUES ('Meuble');
INSERT INTO dbo.CATEGORIES VALUES ('Autre');

-- VENTES INSERTS
INSERT INTO VENTES VALUES ('canape','canape neuf en cuir blanc 3 places','30/01/2019',50,null,1,5);

--Requetes select--
select*from utilisateurs u 
inner join VENTES v on u.no_utilisateur=v.no_utilisateur;

select u.pseudo, v.no_utilisateur, e.no_utilisateur from utilisateurs u 
inner join VENTES v on u.no_utilisateur=v.no_utilisateur
inner join ENCHERES e on v.no_vente=e.no_vente;