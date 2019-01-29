package org.trocencheres.dal;

import org.trocencheres.beans.Vente;

import java.util.List;

/**
 * @author Kévin Le Devéhat
 */
public interface IVenteDAO {
    Vente selectById(Integer noVente) throws DALException;

    List<Vente> selectAll() throws DALException;

    void update(Vente vente) throws DALException;

    void insert(Vente vente) throws DALException;

    void delete(Integer noVente) throws DALException;
}
