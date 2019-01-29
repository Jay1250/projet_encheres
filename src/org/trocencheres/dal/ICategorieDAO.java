package org.trocencheres.dal;

import org.trocencheres.beans.Categorie;

import java.util.List;

/**
 * @author Kévin Le Devéhat
 */
public interface ICategorieDAO {
    Categorie selectById(Integer noCategorie) throws DALException;

    List<Categorie> selectAll() throws DALException;

    void update(Categorie categorie) throws DALException;

    void insert(Categorie categorie) throws DALException;

    void delete(Integer noCategorie) throws DALException;
}
