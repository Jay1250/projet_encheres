package org.trocencheres.dal;

import org.trocencheres.dal.jdbc.VenteDAOJdbcImpl;

public class VenteDAOFactory {
    public static VenteDAO getVenteDAO() { return new VenteDAOJdbcImpl(); }
}
