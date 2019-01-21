package org.trocencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {
    public static Connection getConnection() throws SQLException {
        String urldb = "jdbc:sqlserver://169.254.64.121:1433;databasename=BDD_Encheres";
        String userdb = "sa";
        String passworddb = "Pa$$w0rd";
        return DriverManager.getConnection(urldb, userdb, passworddb);
    }
}
