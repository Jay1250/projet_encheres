package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.RetraitDAOJdbcImpl;

public class RetraitDAOFactory {
    public static RetraitDAO getRetraitDAO() {
        return new RetraitDAOJdbcImpl();
    }
}
