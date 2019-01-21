package org.trocencheres.dal.jdbc;

import org.trocencheres.beans.Utilisateur;
import org.trocencheres.dal.DALException;
import org.trocencheres.dal.UtilisateurDAO;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
    public Utilisateur selectById(Integer idUtilisateur) throws DALException {
        return new Utilisateur();
    }

    public List<Utilisateur> selectAll() throws DALException {
        return new ArrayList<>();
    }

    public void update(Utilisateur utilisateur) throws DALException {

    }

    public void insert(Utilisateur utilisateur) throws DALException {

    }

    public void delete(Integer idUtilisateur) throws DALException {

    }
}
