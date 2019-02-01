package org.trocencheres.dal.jdbc;

import org.trocencheres.beans.Enchere;
import org.trocencheres.dal.ConnectionProvider;
import org.trocencheres.dal.DALException;
import org.trocencheres.dal.IEnchereDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
            throw new DALException("Auction - Select by ids", e);
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
            throw new DALException("Auction - Select last by sale", e);
        }
    }

    @Override
    public ArrayList<Enchere> selectAll() throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            ArrayList<Enchere> allEncheres = new ArrayList<>();
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
    public ArrayList<Enchere> selectAllBySale(int noVente) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            ArrayList<Enchere> allEncheresBySale = new ArrayList<>();
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
    public ArrayList<Enchere> selectAllByUser(int noUtilisateur) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            ArrayList<Enchere> allEncheresByUser = new ArrayList<>();
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
    public ArrayList<Enchere> selectAllCurrentByUser(int noUtilisateur) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            ArrayList<Enchere> allEncheresByUser = new ArrayList<>();
            String sqlRequest = "SELECT en.no_vente, count(*) " +
                    "FROM ENCHERES en " +
                    "INNER JOIN VENTES v ON en.no_vente = v.no_vente " +
                    "WHERE en.no_utilisateur = ? AND v.date_fin_encheres > ? " +
                    "GROUP BY en.no_vente";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, noUtilisateur);
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                int noVente = resultSet.getInt("no_vente");
                String subSqlRequest = "SELECT TOP 1 * " +
                        "FROM ENCHERES " +
                        "WHERE no_vente= ? AND no_utilisateur = ? " +
                        "ORDER BY date_enchere DESC";
                PreparedStatement subStatement = connection.prepareStatement(subSqlRequest);
                subStatement.setInt(1, noVente);
                subStatement.setInt(2, noUtilisateur);
                ResultSet subResultset = subStatement.executeQuery();
                if (subResultset != null && subResultset.next())
                    allEncheresByUser.add(this.createAuctionFromResultSet(subResultset));
                subStatement.close();
            }
            statement.close();
            return allEncheresByUser;
        } catch (SQLException e) {
            throw new DALException("Auction - Select all current by user", e);
        }
    }

    @Override
    public ArrayList<Enchere> selectAllEndedByUser(int noUtilisateur) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            ArrayList<Enchere> allEncheresByUser = new ArrayList<>();
            String sqlRequest = "SELECT en.no_vente, count(*) " +
                    "FROM ENCHERES en " +
                    "INNER JOIN VENTES v ON en.no_vente = v.no_vente " +
                    "WHERE en.no_utilisateur = ? AND v.date_fin_encheres < ? " +
                    "GROUP BY en.no_vente";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, noUtilisateur);
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                int noVente = resultSet.getInt("no_vente");
                String subSqlRequest = "SELECT TOP 1 * " +
                        "FROM ENCHERES " +
                        "WHERE no_vente= ? AND no_utilisateur = ? " +
                        "ORDER BY date_enchere DESC";
                PreparedStatement subStatement = connection.prepareStatement(subSqlRequest);
                subStatement.setInt(1, noVente);
                subStatement.setInt(2, noUtilisateur);
                ResultSet subResultset = subStatement.executeQuery();
                if (subResultset != null && subResultset.next())
                    allEncheresByUser.add(this.createAuctionFromResultSet(subResultset));
                subStatement.close();
            }
            statement.close();
            return allEncheresByUser;
        } catch (SQLException e) {
            throw new DALException("Auction - Select all ended by user", e);
        }
    }

    @Override
    public HashMap<Integer, Integer> selectAllLosersMaxBid(int noVente, int winnerId) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            HashMap<Integer, Integer> allLosersMaxBid = new HashMap<>();
            String sqlRequest = "SELECT en.no_utilisateur, max(en.montant_enchere) as montant_enchere " +
                    "FROM ENCHERES en " +
                    "INNER JOIN VENTES v ON en.no_vente = v.no_vente " +
                    "WHERE en.no_vente = ? AND en.no_utilisateur != ? " +
                    "GROUP BY en.no_utilisateur";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, noVente);
            statement.setInt(2, winnerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                int noUtilisateur = resultSet.getInt("no_utilisateur");
                int montantEnchere = resultSet.getInt("montant_enchere");
                allLosersMaxBid.put(noUtilisateur, montantEnchere);
            }
            statement.close();
            return allLosersMaxBid;
        } catch (SQLException e) {
            throw new DALException("Auction - Select all losers max bid", e);
        }
    }

    @Override
    public void insert(Enchere enchere) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            String sqlRequest = "INSERT INTO ENCHERES (date_enchere, no_utilisateur, no_vente, montant_enchere) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setTimestamp(1, new Timestamp(enchere.getDateEnchere().getTime()));
            statement.setInt(2, enchere.getNoUtilisateur());
            statement.setInt(3, enchere.getNoVente());
            statement.setInt(4, enchere.getMontantEnchere());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DALException("Auction - Insert", e);
        }
    }

    @Override
    public void deleteLast(Integer noVente, Integer noUtilisateur) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            String sqlRequest = "DELETE FROM ENCHERES WHERE no_vente = ? AND no_utilisateur = ? AND date_enchere = (SELECT MAX(e.date_enchere) FROM ENCHERES e WHERE e.no_vente = ? AND e.no_utilisateur = ?)";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, noVente);
            statement.setInt(2, noUtilisateur);
            statement.setInt(1, noVente);
            statement.setInt(2, noUtilisateur);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DALException("Auction - Delete", e);
        }
    }

    private Enchere createAuctionFromResultSet(ResultSet resultSet) throws SQLException {
        int noVente = resultSet.getInt("no_vente");
        int noUtilisateur = resultSet.getInt("no_utilisateur");
        int montantEnchere = resultSet.getInt("montant_enchere");
        Date dateEncheres = new Date(resultSet.getTimestamp("date_enchere").getTime());
        return new Enchere(noVente, noUtilisateur, dateEncheres,montantEnchere );
    }
}
