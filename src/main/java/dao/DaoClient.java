package dao;

import model.Client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoClient extends DaoGeneric<Client> {

    protected DaoClient(Connection connection){
        super(connection, "clients");
    }
    @Override
    Client getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        int clientId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        return new Client(clientId, name, surname);
    }

    @Override
    String getInsertStatement(Client client) {
        return "INSERT INTO " + tableName + "(name, surname) " +
                "VALUES (" + client.getName() + ", " + client.getSurname() + ");";
    }

    @Override
    String getUpdateStatement(Client client) {
        return "UPDATE " + tableName + " SET name = '" + client.getName() + "'"
                + ", surname = '" + client.getSurname() + "' WHERE id = " + client.getId() + ";";
    }

    @Override
    String getDeleteStatement(Client client) {
        return "DELETE FROM " + tableName + " WHERE id = " +client.getId() + ";";
    }
}
