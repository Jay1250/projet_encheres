package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.IRetraitDAOJdbcImpl;

/**
 * @author Kévin Le Devéhat
 */
public class RetraitDAOFactory {
    public static IRetraitDAO getRetraitDAO() {
        return new IRetraitDAOJdbcImpl();
    }
}
