package fr.eni.encheres.dal;

import fr.eni.encheres.bean.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {
    Utilisateur selectById(Integer id) throws DALException;

    List<Utilisateur> selectAll() throws DALException;

    void update(Utilisateur user) throws DALException;

    void insert(Utilisateur user) throws DALException;

    void delete(Integer id) throws DALException;
}
