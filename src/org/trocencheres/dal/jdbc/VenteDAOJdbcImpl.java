package org.trocencheres.dal.jdbc;

import org.trocencheres.beans.Vente;
import org.trocencheres.dal.DALException;
import org.trocencheres.dal.VenteDAO;
import org.trocencheres.util.AccesBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VenteDAOJdbcImpl implements VenteDAO {

    @Override
    public Vente selectById(Integer noVente) throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            Vente vente = new Vente();
            String sqlRequest = "SELECT * FROM VENTES WHERE no_vente = ?";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, noVente);
            ResultSet resultset = statement.executeQuery();
            if (resultset != null && resultset.next())
                vente= this.createSaleFromResultSet(resultset);
            statement.close();
            return vente;
        } catch (SQLException e) {
            throw new DALException("Sale - Select by id", e);
        }
    }

    @Override
    public List<Vente> selectAll() throws DALException {
        return new ArrayList<>();
    }

    @Override
    public void update(Vente vente) throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            PreparedStatement statement = this.getStatementFromMode("update", connection, vente);
            if (statement != null) {
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException e) {
            throw new DALException("Sale - Update", e);
        }
    }

    @Override
    public void insert(Vente vente) throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            PreparedStatement statement = this.getStatementFromMode("insert", connection, vente);
            if (statement != null) {
                int nbRows = statement.executeUpdate();
                if (nbRows == 1) {
                    ResultSet resultset = statement.getGeneratedKeys();
                    if (resultset != null && resultset.next()) {
                        long key = resultset.getLong(1);
                        vente.setNoVente((int) key);
                    }
                }
                statement.close();
            }
        } catch (SQLException e) {
            throw new DALException("Sale - Insert", e);
        }
    }

    @Override
    public void delete(Integer noVente) throws DALException {
        try (Connection connection = AccesBase.getConnection()) {
            String sqlRequest = "DELETE FROM VENTES WHERE no_vente = ?";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, noVente);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DALException("Sale - Delete", e);
        }
    }

    private PreparedStatement getStatementFromMode(String mode, Connection connection, Vente vente) throws SQLException {
        return mode.equals("insert") ? this.getInsertStatement(connection, vente)
                : mode.equals("update") ? this.getUpdateStatement(connection, vente) : null;
    }

    private PreparedStatement getInsertStatement(Connection connection, Vente vente) throws SQLException {
        String sqlRequest = "INSERT INTO "
                + "VENTES (nomarticle, description, date_fin_encheres, prix_initial, prix_vente, no_categorie, no_categorie) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlRequest, PreparedStatement.RETURN_GENERATED_KEYS);
        this.setStatementWithGenericInfosFromVente(statement, vente);
        return statement;
    }

    private PreparedStatement getUpdateStatement(Connection connection, Vente vente) throws SQLException {
        String sqlRequest = "UPDATE VENTES "
                + "SET nomarticle = ?, description = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?, no_utilisateur = ?, no_categorie = ?"
                + " WHERE no_vente = ?";
        PreparedStatement statement = connection.prepareStatement(sqlRequest);
        statement.setInt(8, vente.getNoVente());
        this.setStatementWithGenericInfosFromVente(statement, vente);
        return statement;
    }

    private void setStatementWithGenericInfosFromVente(PreparedStatement statement, Vente vente) throws SQLException {
        statement.setString(1, vente.getNomArticle());
        statement.setString(2, vente.getDescription());
        statement.setDate(3, this.convertJavaDataToSQLDate(vente.getDateFinEncheres()));
        statement.setInt(4, vente.getPrixInitial());
        statement.setInt(5, vente.getPrixVente());
        statement.setInt(6, vente.getUtilisateur().getNoUtilisateur());
        statement.setInt(7, vente.getCategorie().getNoCategorie());
    }

    private java.sql.Date convertJavaDataToSQLDate(Date javaDate) {
        return new java.sql.Date(javaDate.getTime());
    }

    private Date convertSQLDateToJavaDate(java.sql.Date SQLDate) {
        return new Date(SQLDate.getTime());
    }

    private Vente createSaleFromResultSet(ResultSet resultset) throws SQLException {
        int noVente = resultset.getInt("no_vente");
        String nomArticle = resultset.getString("nomarticle");
        String description = resultset.getString("description");
        Date dateFinEncheres = this.convertSQLDateToJavaDate(resultset.getDate("date_fin_encheres"));
        int prixInitial = resultset.getInt("prix_initial");
        int prixVente = resultset.getInt("prix_vente");
        int noUtilisateur= resultset.getInt("no_utilisateur");
        int noCategorie= resultset.getInt("no_categorie");
        //return new Vente(noVente, nomArticle, description, dateFinEncheres, prixInitial, prixVente, noUtilisateur, noCategorie);
        return new Vente();
    }
}
