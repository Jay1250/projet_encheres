package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.UtilisateurDAOJdbcImpl;

/**
 * @author Kévin Le Devéhat
 */
public class UtilisateurDAOFactory {
    public static UtilisateurDAO getUtilisateurDao() { return new UtilisateurDAOJdbcImpl();}
}
