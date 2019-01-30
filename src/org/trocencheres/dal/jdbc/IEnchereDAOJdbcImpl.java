package org.trocencheres.dal.jdbc;

import org.trocencheres.beans.Enchere;
import org.trocencheres.dal.ConnectionProvider;
import org.trocencheres.dal.DALException;
import org.trocencheres.dal.IEnchereDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Kévin Le Devéhat
 */
public class IEnchereDAOJdbcImpl implements IEnchereDAO {

    @Override
    public Enchere selectLastByIds(Integer noVente, Integer noUtilisateur) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            Enchere enchere = new Enchere();
            String sqlRequest = "SELECT TOP 1 * " +
                    "FROM ENCHERES " +
                    "WHERE no_vente= ? AND no_utilisateur = ? " +
                    "ORDER BY date_enchere DESC";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, noVente);
            statement.setInt(2, noUtilisateur);
            ResultSet resultset = statement.executeQuery();
            if (resultset != null && resultset.next())
                enchere = this.createAuctionFromResultSet(resultset);
            statement.close();
            return enchere;
        } catch (SQLException e) {
            throw new DALException("Auction - Select by id", e);
        }
    }

    @Override
    public Enchere selectLastBySale(Integer noVente) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            Enchere enchere = new Enchere();
            String sqlRequest = "SELECT TOP 1 * FROM ENCHERES WHERE no_vente = ? ORDER BY date_enchere DESC";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, noVente);
            ResultSet resultset = statement.executeQuery();
            if (resultset != null && resultset.next())
                enchere = this.createAuctionFromResultSet(resultset);
            statement.close();
            return enchere;
        } catch (SQLException e) {
            throw new DALException("Auction - Select by id", e);
        }
    }

    @Override
    public List<Enchere> selectAll() throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            List<Enchere> allEncheres = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ENCHERES");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                allEncheres.add(this.createAuctionFromResultSet(resultSet));
            }
            return allEncheres;
        } catch (SQLException e) {
            throw new DALException("Auction - Select all", e);
        }
    }

    @Override
    public List<Enchere> selectAllBySale(int noVente) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            List<Enchere> allEncheresBySale = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ENCHERES WHERE no_vente = ?");
            statement.setInt(1, noVente);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                allEncheresBySale.add(this.createAuctionFromResultSet(resultSet));
            }
            return allEncheresBySale;
        } catch (SQLException e) {
            throw new DALException("Auction - Select all by sale", e);
        }
    }

    @Override
    public List<Enchere> selectAllByUser(int noUtilisateur) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            List<Enchere> allEncheresByUser = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ENCHERES WHERE no_utilisateur = ?");
            statement.setInt(1, noUtilisateur);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                allEncheresByUser.add(this.createAuctionFromResultSet(resultSet));
            }
            return allEncheresByUser;
        } catch (SQLException e) {
            throw new DALException("Auction - Select all by user", e);
        }
    }

    @Override
    public void insert(Enchere enchere) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            String sqlRequest = "INSERT INTO ENCHERES (date_enchere, no_utilisateur, no_vente) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setDate(1, this.convertJavaDataToSQLDate(enchere.getDateEnchere()));
            statement.setInt(2, enchere.getNoUtilisateur());
            statement.setInt(3, enchere.getNoVente());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DALException("Auction - Insert", e);
        }
    }

    @Override
    public void delete(Integer noVente, Integer noUtilisateur) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            String sqlRequest = "DELETE FROM ENCHERES WHERE no_vente = ? AND no_utilisateur = ?";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, noVente);
            statement.setInt(2, noUtilisateur);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DALException("Auction - Delete", e);
        }
    }

    private java.sql.Date convertJavaDataToSQLDate(Date javaDate) {
        return new java.sql.Date(javaDate.getTime());
    }

    private Date convertSQLDateToJavaDate(java.sql.Date SQLDate) {
        return new Date(SQLDate.getTime());
    }

    private Enchere createAuctionFromResultSet(ResultSet resultSet) throws SQLException {
        int noVente = resultSet.getInt("no_vente");
        int noUtilisateur = resultSet.getInt("no_utilisateur");
        int montantEnchere=resultSet.getInt("montant_enchere");
        Date dateEncheres = this.convertSQLDateToJavaDate(resultSet.getDate("date_enchere"));
        return new Enchere(noVente, noUtilisateur, dateEncheres,montantEnchere );
    }
}
