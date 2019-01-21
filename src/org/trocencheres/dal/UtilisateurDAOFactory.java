package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class UtilisateurDAOFactory {
    public static UtilisateurDAO getUserDao() { return new UtilisateurDAOJdbcImpl();}
}
