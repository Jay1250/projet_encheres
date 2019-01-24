package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.EnchereDAOJdbcImpl;

/**
 * @author Kévin Le Devéhat
 */
public class EnchereDAOFactory {
    public static EnchereDAO getEnchereDAO() {
        return new EnchereDAOJdbcImpl();
    }
}
