package dao;

import model.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoService extends DaoGeneric<Service> {

    protected DaoService(Connection connection){
        super(connection, "services");
    }

    @Override
    Service getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        int serviceId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        return new Service(serviceId, name);
    }

    @Override
    String getInsertStatement(Service service) {
        return "INSERT INTO " + tableName + " (name)" +
                "VALUES ('" + service.getName() + "');";
    }

    @Override
    String getUpdateStatement(Service service) {
        return "UPDATE " + tableName
                + " SET name = '" + service.getName()
                + " WHERE id = " + service.getId() + ";";
    }

    @Override
    String getDeleteStatement(Service service) {
        return "DELETE FROM " + tableName + " WHERE id = " + service.getId() + ";";
    }
}
