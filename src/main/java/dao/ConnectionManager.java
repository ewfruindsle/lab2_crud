package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private Connection connection;

    private ConnectionManager() {
    }

    public void open() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/communal_bills",
                    "anastasiia",
                    "qwerty");
        }
    }

    public void close() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }
    }

    private static class ConnectionManagerSingleton {
        public static final ConnectionManager instance;

        static {
            ConnectionManager connectionManager;
            try {
                connectionManager = new ConnectionManager();
            } catch (Exception ex) {
                connectionManager = null;
            }
            instance = connectionManager;
        }
    }

    public static ConnectionManager shared() {
        return ConnectionManagerSingleton.instance;
    }

    public DaoGeneric getDAO(TableName tableName) throws SQLException {
        if (connection == null || connection.isClosed()) {
            open();
        }
        switch (tableName) {
            case CLIENT:
                return new DaoClient(connection);
            case BILL:
                return new DaoBill(connection);
            case SERVICE:
                return new DaoService(connection);
            default:
                throw new SQLException("Error. You are trying to connect to a non-existing table.");
        }
    }
}


