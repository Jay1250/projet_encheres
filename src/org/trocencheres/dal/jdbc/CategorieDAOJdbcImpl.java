package org.trocencheres.dal.jdbc;

import org.trocencheres.beans.Categorie;
import org.trocencheres.dal.CategorieDAO;
import org.trocencheres.dal.DALException;
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
public class CategorieDAOJdbcImpl implements CategorieDAO {
    @Override
    public Categorie selectById(Integer noCategorie) throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            Categorie categorie = new Categorie();
            String sqlRequest = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, noCategorie);
            ResultSet resultset = statement.executeQuery();
            if (resultset != null && resultset.next())
                categorie = this.createCategoryFromResultSet(resultset);
            statement.close();
            return categorie;
        } catch (SQLException e) {
            throw new DALException("Category - Select by id", e);
        }
    }

    @Override
    public List<Categorie> selectAll() throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            List<Categorie> allCategories = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM CATEGORIES");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                allCategories.add(this.createCategoryFromResultSet(resultSet));
            }
            return allCategories;
        } catch (SQLException e) {
            throw new DALException("Category - Select all", e);
        }
    }

    @Override
    public void update(Categorie categorie) throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            PreparedStatement statement = this.getStatementFromMode("update", connection, categorie);
            if (statement != null) {
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException e) {
            throw new DALException("Category - Update", e);
        }
    }

    @Override
    public void insert(Categorie categorie) throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            PreparedStatement statement = this.getStatementFromMode("insert", connection, categorie);
            if (statement != null) {
                int nbRows = statement.executeUpdate();
                if (nbRows == 1) {
                    ResultSet resultset = statement.getGeneratedKeys();
                    if (resultset != null && resultset.next()) {
                        long key = resultset.getLong(1);
                        categorie.setNoCategorie((int) key);
                    }
                }
                statement.close();
            }
        } catch (SQLException e) {
            throw new DALException("Category - Insert", e);
        }
    }

    @Override
    public void delete(Integer noCategorie) throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            String sqlRequest = "DELETE FROM CATEGORIES WHERE no_categorie = ?";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, noCategorie);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DALException("Category - Delete", e);
        }
    }

    private PreparedStatement getStatementFromMode(String mode, Connection connection, Categorie categorie) throws SQLException {
        return mode.equals("insert") ? this.getInsertStatement(connection, categorie)
                : mode.equals("update") ? this.getUpdateStatement(connection, categorie) : null;
    }

    private PreparedStatement getInsertStatement(Connection connection, Categorie categorie) throws SQLException {
        String sqlRequest = "INSERT INTO CATEGORIES (libelle) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(sqlRequest, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, categorie.getLibelle());
        return statement;
    }

    private PreparedStatement getUpdateStatement(Connection connection, Categorie categorie) throws SQLException {
        String sqlRequest = "UPDATE CATEGORIES SET libelle = ? WHERE no_categorie = ?";
        PreparedStatement statement = connection.prepareStatement(sqlRequest);
        statement.setString(1, categorie.getLibelle());
        statement.setInt(2, categorie.getNoCategorie());
        return statement;
    }

    private Categorie createCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        int no_categorie = resultSet.getInt("no_categorie");
        String libelle = resultSet.getString("libelle");
        return new Categorie(no_categorie, libelle);
    }
}
