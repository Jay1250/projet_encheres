package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.RetraitDAOJdbcImpl;

/**
 * @author Kévin Le Devéhat
 */
public class RetraitDAOFactory {
    public static RetraitDAO getRetraitDAO() {
        return new RetraitDAOJdbcImpl();
    }
}
