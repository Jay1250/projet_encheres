package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class UtilisateurDAOFactory {
    public static UtilisateurDAO getUtilisateurDao() { return new UtilisateurDAOJdbcImpl();}
}
