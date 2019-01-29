package org.trocencheres.dal;

import org.trocencheres.beans.Enchere;

import java.util.List;

/**
 * @author Kévin Le Devéhat
 */
public interface IEnchereDAO {
    Enchere selectLastByIds(Integer noVente, Integer noUtilisateur) throws DALException;

    Enchere selectLastBySale(Integer noVente) throws DALException;

    List<Enchere> selectAll() throws DALException;

    List<Enchere> selectAllBySale(int noVente) throws DALException;

    List<Enchere> selectAllByUser(int noUtilisateur) throws DALException;

    void insert(Enchere enchere) throws DALException;

    void delete(Integer noVente, Integer noUtilisateur) throws DALException;
}
