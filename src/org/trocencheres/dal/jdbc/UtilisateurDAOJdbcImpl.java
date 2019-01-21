package org.trocencheres.dal.jdbc;

import org.trocencheres.beans.Utilisateur;
import org.trocencheres.dal.DALException;
import org.trocencheres.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
    public Utilisateur selectById(Integer idUtilisateur) throws DALException {
        try(Connection connection = JdbcTools.getConnection())
    }
}
