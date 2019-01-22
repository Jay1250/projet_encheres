package org.trocencheres.dal.jdbc;

import org.trocencheres.beans.Vente;
import org.trocencheres.dal.DALException;
import org.trocencheres.dal.VenteDAO;

import java.util.ArrayList;
import java.util.List;

public class VenteDAOJdbcImpl implements VenteDAO {
    public Vente selectById(Integer idVente) throws DALException {
        return new Vente();
    }

    public List<Vente> selectAll() throws DALException {
        return new ArrayList<>();
    }

    public void update(Vente vente) throws DALException {

    }

    public void insert(Vente vente) throws DALException {

    }

    public void delete(Integer idVente) throws DALException {

    }
}
