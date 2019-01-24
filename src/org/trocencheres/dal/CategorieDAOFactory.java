package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.CategorieDAOJdbcImpl;

/**
 * @author Kévin Le Devéhat
 */
public class CategorieDAOFactory {
    public static CategorieDAO getCategorieDAO() {
        return new CategorieDAOJdbcImpl();
    }
}
