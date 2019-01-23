package org.trocencheres.dal.jdbc;

import org.trocencheres.beans.Retrait;
import org.trocencheres.dal.DALException;
import org.trocencheres.dal.RetraitDAO;
import org.trocencheres.util.AccesBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        return null;
    }

    @Override
    public void update(Retrait retrait) throws DALException {

    }

    @Override
    public void insert(Retrait retrait) throws DALException {

    }

    @Override
    public void delete(Integer noVente) throws DALException {

    }

    private Retrait createWithdrawalFromResultSet(ResultSet resultSet) throws SQLException {
        int no_vente = resultSet.getInt("no_vente");
        String rue = resultSet.getString("rue");
        String code_postal = resultSet.getString("code_postal");
        String ville = resultSet.getString("ville");
        return new Retrait();
    }
}
