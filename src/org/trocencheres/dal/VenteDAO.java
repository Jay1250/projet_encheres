package org.trocencheres.dal;

import org.trocencheres.beans.Vente;

import java.util.List;

public interface VenteDAO {
    Vente selectById(Integer id) throws DALException;

    List<Vente> selectAll() throws DALException;

    void update(Vente vente) throws DALException;

    void insert(Vente vente) throws DALException;

    void delete(Integer id) throws DALException;
}
