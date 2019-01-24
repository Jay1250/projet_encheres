package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.EnchereDAOJdbcImpl;

public class EnchereDAOFactory {
    public static EnchereDAO getEnchereDAO() {
        return new EnchereDAOJdbcImpl();
    }
}
