package org.trocencheres.dal.jdbc;

import org.trocencheres.beans.Utilisateur;
import org.trocencheres.beans.Vente;
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
        try {
            Connection connection = AccesBase.getConnection();
            PreparedStatement statement = this.getStatementFromMode("update", connection, utilisateur);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DALException("User - Update", e);
        }
    }

    @Override
    public void insert(Utilisateur utilisateur) throws DALException {
        try(Connection connection = AccesBase.getConnection()) {
            PreparedStatement statement = this.getStatementFromMode("insert", connection, utilisateur);
            int nbRows = statement.executeUpdate();
            if (nbRows == 1) {
                ResultSet resultset = statement.getGeneratedKeys();
                if (resultset != null && resultset.next()) {
                    long key = resultset.getLong(1);
                    utilisateur.setNoUtilisateur((int)key);
                }
            }
            statement.close();
        } catch (SQLException e) {
            throw new DALException("User - Insert", e);
        }
    }

    @Override
    public void delete(Integer idUtilisateur) throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            String sqlRequest = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, idUtilisateur);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DALException("User - Delete", e);
        }
    }

    private PreparedStatement getStatementFromMode(String mode, Connection connection, Utilisateur utilisateur) {
        PreparedStatement statement;
        String sqlRequest = mode.equals("insert")
                ? "INSERT INTO UTILISATEURS (no_utilisateur, pseudo, nom, ) VALUES ()"
                : "";
        return statement;
    }

    private PreparedStatement getInsertStatement(Connection connection, Utilisateur utilisateur) throws SQLException {
        String sqlRequest = "INSERT INTO " +
                "UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlRequest, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, utilisateur.getPseudo());
        statement.setString(2, utilisateur.getNom());
        statement.setString(3, utilisateur.getprenom());
        statement.setString(4, utilisateur.getEmail());
        statement.setString(5, utilisateur.getTelephone());
        statement.setString(6, utilisateur.getRue());
        statement.setString(7, utilisateur.getCodePostal());
        statement.setString(8, utilisateur.getVille());
        statement.setInt(9, utilisateur.getCredit());
        statement.setByte(10, (byte)(utilisateur.isAdministrateur() ? 1 : 0));
        return statement;
    }

    private Utilisateur createUserFromResultSet(ResultSet resultset) throws SQLException {
        int noUtilisateur = resultset.getInt("no_utilisateur");
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
        boolean administrateur = resultset.getByte("administrateur") != 0;
        ArrayList<Vente> ventes = new ArrayList<>();
        return new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur, ventes);
    }
}
