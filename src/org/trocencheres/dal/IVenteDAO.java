package org.trocencheres.dal;

import org.trocencheres.beans.Vente;

import java.util.ArrayList;

/**
 * @author Kévin Le Devéhat
 */
public interface IVenteDAO {
    Vente selectById(Integer noVente) throws DALException;

    ArrayList<Vente> selectAll() throws DALException;

    ArrayList<Vente> selectAllByUser(int noUtilisateur) throws DALException;

    void update(Vente vente) throws DALException;

    void insert(Vente vente) throws DALException;

    void delete(Integer noVente) throws DALException;
}
