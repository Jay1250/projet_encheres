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
	public Utilisateur selectById(Integer noUtilisateur) throws DALException {
		try (Connection connection = AccesBase.getConnection()) {
			Utilisateur utilisateur = new Utilisateur();
			String sqlRequest = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
			PreparedStatement statement = connection.prepareStatement(sqlRequest);
			statement.setInt(1, noUtilisateur);
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
	public Utilisateur selectByLogin(String pseudoOrEmail, String password) throws DALException {
		try (Connection connection = AccesBase.getConnection()) {
			Utilisateur utilisateur = new Utilisateur();
			String sqlRequest = "SELECT * FROM UTILISATEURS WHERE (pseudo = ? OR email = ?) AND mot_de_passe = ?";
			PreparedStatement statement = connection.prepareStatement(sqlRequest);
			statement.setString(1, pseudoOrEmail);
			statement.setString(2, pseudoOrEmail);
			statement.setString(3, password);
			ResultSet resultset = statement.executeQuery();
			if (resultset != null && resultset.next())
				utilisateur = this.createUserFromResultSet(resultset);
			statement.close();
			return utilisateur;
		} catch (SQLException e) {
			throw new DALException("User - Select by pseudo and password", e);
		}
	}

	@Override
	public List<Utilisateur> selectAll() throws DALException {
		try (Connection connection = AccesBase.getConnection()) {
			List<Utilisateur> allUtilisateurs = new ArrayList<>();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM UTILISATEURS");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet != null && resultSet.next()) {
				allUtilisateurs.add(this.createUserFromResultSet(resultSet));
			}
			return allUtilisateurs;
		} catch (SQLException e) {
			throw new DALException("User - Select all", e);
		}
	}

	@Override
	public void update(Utilisateur utilisateur) throws DALException {
		try (Connection connection = AccesBase.getConnection()) {
			PreparedStatement statement = this.getStatementFromMode("update", connection, utilisateur);
			if (statement != null) {
				statement.executeUpdate();
				statement.close();
			}
		} catch (SQLException e) {
			throw new DALException("User - Update", e);
		}
	}

	@Override
	public void insert(Utilisateur utilisateur) throws DALException {
		try (Connection connection = AccesBase.getConnection()) {
			PreparedStatement statement = this.getStatementFromMode("insert", connection, utilisateur);
			if (statement != null) {
				int nbRows = statement.executeUpdate();
				if (nbRows == 1) {
					ResultSet resultset = statement.getGeneratedKeys();
					if (resultset != null && resultset.next()) {
						long key = resultset.getLong(1);
						utilisateur.setNoUtilisateur((int) key);
					}
				}
				statement.close();
			}
		} catch (SQLException e) {
			throw new DALException("User - Insert", e);
		}
	}

	@Override
	public void delete(Integer noUtilisateur) throws DALException {
		try (Connection connection = AccesBase.getConnection()) {
			String sqlRequest = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
			PreparedStatement statement = connection.prepareStatement(sqlRequest);
			statement.setInt(1, noUtilisateur);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new DALException("User - Delete", e);
		}
	}

	private PreparedStatement getStatementFromMode(String mode, Connection connection, Utilisateur utilisateur)
			throws SQLException {
		return mode.equals("insert") ? this.getInsertStatement(connection, utilisateur)
				: mode.equals("update") ? this.getUpdateStatement(connection, utilisateur) : null;
	}

	private PreparedStatement getInsertStatement(Connection connection, Utilisateur utilisateur) throws SQLException {
		String sqlRequest = "INSERT INTO "
				+ "UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur, mot_de_passe) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sqlRequest, PreparedStatement.RETURN_GENERATED_KEYS);
		this.setStatementWithGenericInfosFromUtilisateur(statement, utilisateur);
		statement.setString(11, utilisateur.getMotDePasse());
		return statement;
	}

	private PreparedStatement getUpdateStatement(Connection connection, Utilisateur utilisateur) throws SQLException {
		boolean updatePassword = utilisateur.getMotDePasse() != null;
		String passwordSQLField = updatePassword ? ", mot_de_passe = ?" : "";
		String sqlRequest = "UPDATE UTILISATEURS "
				+ "SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, credit = ?, administrateur = ?"
				+ passwordSQLField + " WHERE no_utilisateur = ?";
		PreparedStatement statement = connection.prepareStatement(sqlRequest);
		if (updatePassword) {
			statement.setString(11, utilisateur.getMotDePasse());
			statement.setInt(12, utilisateur.getNoUtilisateur());
		} else {
			statement.setInt(11, utilisateur.getNoUtilisateur());
		}
		this.setStatementWithGenericInfosFromUtilisateur(statement, utilisateur);
		return statement;
	}

	private void setStatementWithGenericInfosFromUtilisateur(PreparedStatement statement, Utilisateur utilisateur)
			throws SQLException {
		statement.setString(1, utilisateur.getPseudo());
		statement.setString(2, utilisateur.getNom());
		statement.setString(3, utilisateur.getprenom());
		statement.setString(4, utilisateur.getEmail());
		statement.setString(5, utilisateur.getTelephone());
		statement.setString(6, utilisateur.getRue());
		statement.setString(7, utilisateur.getCodePostal());
		statement.setString(8, utilisateur.getVille());
		statement.setInt(9, utilisateur.getCredit());
		statement.setByte(10, (byte) (utilisateur.isAdministrateur() ? 1 : 0));
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
		// String mot_de_passe = resultset.getString("mot_de_passe");
		int credit = resultset.getInt("credit");
		boolean administrateur = resultset.getByte("administrateur") != 0;
		ArrayList<Integer> ventes = new ArrayList<>();
		return new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit,
				administrateur, ventes);
	}
}
