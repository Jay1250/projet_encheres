package org.trocencheres.dal.jdbc;

import org.trocencheres.beans.Utilisateur;
import org.trocencheres.dal.ConnectionProvider;
import org.trocencheres.dal.DALException;
import org.trocencheres.dal.IUtilisateurDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kévin Le Devéhat
 */
public class IUtilisateurDAOJdbcImpl implements IUtilisateurDAO {

	@Override
	public Utilisateur selectById(Integer noUtilisateur) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
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
		try (Connection connection = ConnectionProvider.getConnection()) {
			Utilisateur utilisateur = new Utilisateur();
			String sqlRequest = "SELECT * FROM UTILISATEURS WHERE (pseudo COLLATE Latin1_General_CS_AS = ? OR email = ?) AND mot_de_passe COLLATE Latin1_General_CS_AS = ?";
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
			throw new DALException("User - Select by pseudo or email, and password", e);
		}
	}

	@Override
	public boolean pseudoExists(String pseudo) throws DALException {
		return this.paramExists("pseudo", pseudo);
	}

	@Override
	public boolean emailExists(String email) throws DALException {
		return this.paramExists("email", email);
	}

	@Override
	public boolean telephoneExists(String telephone) throws DALException {
		return this.paramExists("telephone", telephone);
	}

	@Override
	public List<Utilisateur> selectAll() throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
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
		try (Connection connection = ConnectionProvider.getConnection()) {
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
		try (Connection connection = ConnectionProvider.getConnection()) {
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
		try (Connection connection = ConnectionProvider.getConnection()) {
			String sqlRequest = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
			PreparedStatement statement = connection.prepareStatement(sqlRequest);
			statement.setInt(1, noUtilisateur);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new DALException("User - Delete", e);
		}
	}

	private boolean paramExists(String paramName, Object param) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			String sqlRequest = "SELECT COUNT(*) AS existing FROM UTILISATEURS WHERE " + paramName + " COLLATE Latin1_General_CS_AS = ?";
			PreparedStatement statement = connection.prepareStatement(sqlRequest);
			statement.setString(1, (String) param);
			ResultSet resultSet = statement.executeQuery();
			boolean existing = false;
			if (resultSet != null && resultSet.next())
				existing = resultSet.getInt("existing") == 1;
			return existing;
		} catch (SQLException e) {
			throw new DALException("User - " + this.capitalize(paramName) + " exists", e);
		}
	}

	private String capitalize(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1);
	}

	private PreparedStatement getStatementFromMode(String mode, Connection connection, Utilisateur utilisateur) throws SQLException {
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
		boolean updatePassword = utilisateur.getMotDePasse() != null && !utilisateur.getMotDePasse().equals("");
		String passwordSQLField = updatePassword ? ", mot_de_passe = ?" : "";
		String sqlRequest = "UPDATE UTILISATEURS "
				+ "SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, credit = ?, administrateur = ?"
				+ passwordSQLField + " WHERE no_utilisateur = ?";
		PreparedStatement statement = connection.prepareStatement(sqlRequest);
        this.setStatementWithGenericInfosFromUtilisateur(statement, utilisateur);
		if (updatePassword) {
			statement.setString(11, utilisateur.getMotDePasse());
			statement.setInt(12, utilisateur.getNoUtilisateur());
		} else {
			statement.setInt(11, utilisateur.getNoUtilisateur());
		}
		return statement;
	}

	private void setStatementWithGenericInfosFromUtilisateur(PreparedStatement statement, Utilisateur utilisateur) throws SQLException {
		statement.setString(1, utilisateur.getPseudo());
		statement.setString(2, utilisateur.getNom());
		statement.setString(3, utilisateur.getPrenom());
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
