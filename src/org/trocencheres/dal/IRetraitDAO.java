package org.trocencheres.dal;

import org.trocencheres.beans.Retrait;

import java.util.List;

/**
 * @author Kévin Le Devéhat
 */
public interface IRetraitDAO {
        Retrait selectByIdVente(Integer noVente) throws DALException;

        List<Retrait> selectAll() throws DALException;

        void update(Retrait retrait) throws DALException;

        void insert(Retrait retrait) throws DALException;

        void delete(Integer noVente) throws DALException;
}
