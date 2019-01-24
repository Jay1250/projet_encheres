package org.trocencheres.dal;

import org.trocencheres.beans.Utilisateur;

import java.util.List;

/**
 * @author Kévin Le Devéhat
 */
public interface UtilisateurDAO {
    Utilisateur selectById(Integer id) throws DALException;

    Utilisateur selectByLogin(String pseudoOrEmail, String password) throws DALException;

    boolean pseudoExists(String pseudo) throws DALException;

    boolean emailExists(String email) throws DALException;

    boolean telephoneExists(String telephone) throws DALException;

    List<Utilisateur> selectAll() throws DALException;

    void update(Utilisateur utilisateur) throws DALException;

    void insert(Utilisateur utilisateur) throws DALException;

    void delete(Integer id) throws DALException;
}
