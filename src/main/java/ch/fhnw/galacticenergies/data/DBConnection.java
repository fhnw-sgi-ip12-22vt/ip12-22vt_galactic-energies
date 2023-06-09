package ch.fhnw.galacticenergies.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Creates the Connection to the SQL Database
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
public class DBConnection {
    private Connection conn;

    public Connection getConnection() {


        String connectionUrl = "jdbc:mysql://localhost:3306/galacticenergies?serverTimezone=UTC";

        try {

            conn = DriverManager.getConnection(connectionUrl, "galacticenergies", "galacticenergies");
        } catch (SQLException ignored) {

        }
        return conn;
    }

}