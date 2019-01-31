package org.trocencheres.dal;

import org.trocencheres.beans.Enchere;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Kévin Le Devéhat
 */
public interface IEnchereDAO {
    Enchere selectLastByIds(Integer noVente, Integer noUtilisateur) throws DALException;

    Enchere selectLastBySale(Integer noVente) throws DALException;

    ArrayList<Enchere> selectAll() throws DALException;

    ArrayList<Enchere> selectAllBySale(int noVente) throws DALException;

    ArrayList<Enchere> selectAllByUser(int noUtilisateur) throws DALException;

    ArrayList<Enchere> selectAllCurrentByUser(int noUtilisateur) throws DALException;

    ArrayList<Enchere> selectAllEndedByUser(int noUtilisateur) throws DALException;

    void insert(Enchere enchere) throws DALException;

    void deleteLast(Integer noVente, Integer noUtilisateur) throws DALException;
}
