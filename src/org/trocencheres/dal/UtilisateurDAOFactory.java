package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.IUtilisateurDAOJdbcImpl;

/**
 * @author Kévin Le Devéhat
 */
public class UtilisateurDAOFactory {
    public static IUtilisateurDAO getUtilisateurDao() { return new IUtilisateurDAOJdbcImpl();}
}
