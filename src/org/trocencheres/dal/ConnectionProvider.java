package org.trocencheres.dal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionProvider {
    private static DataSource dataSource;

    static {
        Context context;
        try {
            context = new InitialContext();
            ConnectionProvider.dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/pool_connection");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't access database");
        }
    }

    public static Connection getConnection() throws SQLException {
        return ConnectionProvider.dataSource.getConnection();
    }
}
