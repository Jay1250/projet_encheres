package org.trocencheres.dal.jdbc;

import org.trocencheres.beans.*;
import org.trocencheres.dal.ConnectionProvider;
import org.trocencheres.dal.DALException;
import org.trocencheres.dal.IVenteDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Kévin Le Devéhat
 */
public class IVenteDAOJdbcImpl implements IVenteDAO {

    @Override
    public Vente selectById(Integer noVente) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            Vente vente = new Vente();
            String sqlRequest = "SELECT * FROM VENTES WHERE no_vente = ?";
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, noVente);
            ResultSet resultset = statement.executeQuery();
            if (resultset != null && resultset.next())
                vente = this.createSaleFromResultSet(resultset);
            statement.close();
            return vente;
        } catch (SQLException e) {
            throw new DALException("Sale - Select by id", e);
        }
    }

    @Override
    public ArrayList<Vente> selectAll() throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            ArrayList<Vente> allVentes = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM VENTES");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                allVentes.add(this.createSaleFromResultSet(resultSet));
            }
            return allVentes;
        } catch (SQLException e) {
            throw new DALException("Sale - Select all", e);
        }
    }

    @Override
    public ArrayList<Vente> selectAllByUser(int noUtilisateur) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            ArrayList<Vente> allVentes = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM VENTES WHERE no_utilisateur = ?");
            statement.setInt(1, noUtilisateur);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                allVentes.add(this.createSaleFromResultSet(resultSet));
            }
            return allVentes;
        } catch (SQLException e) {
            throw new DALException("Sale - Select all", e);
        }
    }

    @Override
	public ArrayList<Vente> selectAllEndedByUser(int noUtilisateur) throws DALException {
    	try (Connection connection = ConnectionProvider.getConnection()) {
            ArrayList<Vente> allVentes = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM VENTES WHERE no_utilisateur = ? AND date_fin_encheres < ?");
            statement.setInt(1, noUtilisateur); 
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                allVentes.add(this.createSaleFromResultSet(resultSet));
            }
            return allVentes;
        } catch (SQLException e) {
            throw new DALException("Sale - Select all ended", e);
        }
	}

	@Override
	public ArrayList<Vente> selectAllCurrentByUser(int noUtilisateur) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
            ArrayList<Vente> allVentes = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM VENTES WHERE no_utilisateur = ? AND date_fin_encheres > ?");
            statement.setInt(1, noUtilisateur);
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                allVentes.add(this.createSaleFromResultSet(resultSet));
            }
            return allVentes;
        } catch (SQLException e) {
            throw new DALException("Sale - Select all current", e);
        }
	}

	@Override
    public void update(Vente vente) throws DALException {
        try (Connection connection = ConnectionProvider.getConnection()) {
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
        try (Connection connection = ConnectionProvider.getConnection()) {
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
        try (Connection connection = ConnectionProvider.getConnection()) {
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
                + "VENTES (nomarticle, description, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) "
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
        this.setStatementWithGenericInfosFromVente(statement, vente);
        statement.setInt(8, vente.getNoVente());
        return statement;
    }

    private void setStatementWithGenericInfosFromVente(PreparedStatement statement, Vente vente) throws SQLException {
        statement.setString(1, vente.getNomArticle());
        statement.setString(2, vente.getDescription());
        statement.setTimestamp(3, new Timestamp(vente.getDateFinEncheres().getTime()));
        statement.setInt(4, vente.getPrixInitial());
        statement.setInt(5, vente.getPrixVente());
        statement.setInt(6, vente.getNoUtilisateur());
        statement.setInt(7, vente.getNoCategorie());
    }

    private Vente createSaleFromResultSet(ResultSet resultSet) throws SQLException {
        int noVente = resultSet.getInt("no_vente");
        String nomArticle = resultSet.getString("nomarticle");
        String description = resultSet.getString("description");
        Date dateFinEncheres = new Date(resultSet.getTimestamp("date_fin_encheres").getTime());
        int prixInitial = resultSet.getInt("prix_initial");
        int prixVente = resultSet.getInt("prix_vente");
        int noUtilisateur= resultSet.getInt("no_utilisateur");
        int noCategorie= resultSet.getInt("no_categorie");
        ArrayList<Integer> encheres = new ArrayList<>();
        Retrait retrait = new Retrait(noVente);
        return new Vente(noVente, nomArticle, description, dateFinEncheres, prixInitial, prixVente, encheres, noUtilisateur, noCategorie, retrait);
    }
}
