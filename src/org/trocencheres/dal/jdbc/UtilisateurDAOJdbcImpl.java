package org.trocencheres.dal.jdbc;

import org.trocencheres.beans.Utilisateur;
import org.trocencheres.dal.DALException;
import org.trocencheres.dal.UtilisateurDAO;
import org.trocencheres.util.AccesBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

    @Override
    public Utilisateur selectById(Integer idUtilisateur) throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            Utilisateur utilisateur = new Utilisateur();
            String sqlRequest = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, idUtilisateur);
            ResultSet resultset = statement.executeQuery();
            if (resultset != null && resultset.next())
                utilisateur = this.createUserFromResultSet(resultset);
            statement.close();
            return utilisateur;
        } catch (SQLException e) {
            throw new DALException("User - Select by id", e);
        }
    }

    @Override
    public List<Utilisateur> selectAll() throws DALException {
        return new ArrayList<>();
    }

    @Override
    public void update(Utilisateur utilisateur) throws DALException {

    }

    @Override
    public void insert(Utilisateur utilisateur) throws DALException {

    }

    @Override
    public void delete(Integer idUtilisateur) throws DALException {

    }

    private Utilisateur createUserFromResultSet(ResultSet resultset) throws SQLException {
        int id = resultset.getInt("no_utilisateur");
        String pseudo = resultset.getString("pseudo");
        String nom = resultset.getString("nom");
        String prenom = resultset.getString("prenom");
        String email = resultset.getString("email");
        String telephone = resultset.getString("telephone");
        String rue = resultset.getString("rue");
        String code_postal = resultset.getString("code_postal");
        String ville = resultset.getString("ville");
        //String mot_de_passe = resultset.getString("mot_de_passe");
        int credit = resultset.getInt("credit");
        Boolean administrateur = resultset.getByte("administrateur") != 0;

        Utilisateur newUtilisateur = new Utilisateur();
        return newUtilisateur;
    }
}
