package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.IEnchereDAOJdbcImpl;

/**
 * @author Kévin Le Devéhat
 */
public class EnchereDAOFactory {
    public static IEnchereDAO getEnchereDAO() {
        return new IEnchereDAOJdbcImpl();
    }
}
