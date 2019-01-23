package org.trocencheres.dal;

import org.trocencheres.beans.Enchere;

import java.util.List;

public interface EnchereDAO {
    Enchere selectByIds(Integer noVente, Integer noUtilisateur) throws DALException;

    List<Enchere> selectAll() throws DALException;

    List<Enchere> selectAllBySale(int noVente) throws DALException;

    List<Enchere> selectAllByUser(int noUtilisateur) throws DALException;

    void update(Enchere enchere) throws DALException;

    void insert(Enchere enchere) throws DALException;

    void delete(Integer noVente, Integer noUtilisateur) throws DALException;
}
