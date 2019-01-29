package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.IVenteDAOJdbcImpl;

/**
 * @author Kévin Le Devéhat
 */
public class VenteDAOFactory {
    public static IVenteDAO getVenteDAO() { return new IVenteDAOJdbcImpl(); }
}
