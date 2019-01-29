package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.ICategorieDAOJdbcImpl;

/**
 * @author Kévin Le Devéhat
 */
public class CategorieDAOFactory {
    public static ICategorieDAO getCategorieDAO() {
        return new ICategorieDAOJdbcImpl();
    }
}
