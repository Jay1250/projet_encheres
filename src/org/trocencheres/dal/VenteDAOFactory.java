package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.VenteDAOJdbcImpl;

/**
 * @author Kévin Le Devéhat
 */
public class VenteDAOFactory {
    public static VenteDAO getVenteDAO() { return new VenteDAOJdbcImpl(); }
}
