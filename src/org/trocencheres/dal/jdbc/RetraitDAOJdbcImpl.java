package org.trocencheres.dal.jdbc;

import org.trocencheres.beans.Retrait;
import org.trocencheres.dal.DALException;
import org.trocencheres.dal.RetraitDAO;
import org.trocencheres.util.AccesBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kévin Le Devéhat
 */
public class RetraitDAOJdbcImpl implements RetraitDAO {

    @Override
    public Retrait selectByIdVente(Integer noVente) throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            Retrait retrait = new Retrait();
            String sqlRequest = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, noVente);
            ResultSet resultset = statement.executeQuery();
            if (resultset != null && resultset.next())
                retrait = this.createWithdrawalFromResultSet(resultset);
            statement.close();
            return retrait;
        } catch (SQLException e) {
            throw new DALException("Withdrawal - Select by id", e);
        }
    }

    @Override
    public List<Retrait> selectAll() throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            List<Retrait> allCategories = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM RETRAITS");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                allCategories.add(this.createWithdrawalFromResultSet(resultSet));
            }
            return allCategories;
        } catch (SQLException e) {
            throw new DALException("Withdrawal - Select all", e);
        }
    }

    @Override
    public void update(Retrait retrait) throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            PreparedStatement statement = this.getStatementFromMode("update", connection, retrait);
            if (statement != null) {
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException e) {
            throw new DALException("Withdrawal - Update", e);
        }
    }

    @Override
    public void insert(Retrait retrait) throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            PreparedStatement statement = this.getStatementFromMode("insert", connection, retrait);
            if (statement != null) {
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException e) {
            throw new DALException("Withdrawal - Insert", e);
        }
    }

    @Override
    public void delete(Integer noVente) throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            String sqlRequest = "DELETE FROM RETRAITS WHERE no_vente = ?";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, noVente);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DALException("Withdrawal - Delete", e);
        }
    }

    private PreparedStatement getStatementFromMode(String mode, Connection connection, Retrait retrait) throws SQLException {
        String sqlRequest = mode.equals("insert")
                ? "INSERT INTO RETRAITS (rue, code_postal, ville, no_vente) VALUES (?, ?, ?, ?)"
                : mode.equals("update")
                    ? "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_vente = ?"
                    : null;
        if (sqlRequest == null)
            return null;
        else {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setString(1, retrait.getRue());
            statement.setString(1, retrait.getCodePostal());
            statement.setString(1, retrait.getVille());
            statement.setInt(4, retrait.getNoVente());
            return statement;
        }
    }

    private Retrait createWithdrawalFromResultSet(ResultSet resultSet) throws SQLException {
        int no_vente = resultSet.getInt("no_vente");
        String rue = resultSet.getString("rue");
        String code_postal = resultSet.getString("code_postal");
        String ville = resultSet.getString("ville");
        return new Retrait(no_vente, rue, code_postal, ville);
    }
}
