package org.trocencheres.dal.jdbc;

import org.trocencheres.dal.EnchereDAO;

public class EnchereDAOFactory {
    public static EnchereDAO getEnchereDAO() {
        return new EnchereDAOJdbcImpl();
    }
}
