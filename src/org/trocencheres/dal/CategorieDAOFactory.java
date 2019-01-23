package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.CategorieDAOJdbcImpl;

public class CategorieDAOFactory {
    public static CategorieDAO getCategorieDAO() {
        return new CategorieDAOJdbcImpl();
    }
}
