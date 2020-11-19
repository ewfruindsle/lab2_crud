package dao;

import model.Bill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DaoBill extends DaoGeneric<Bill> {

    protected DaoBill(Connection connection) {
        super(connection, "bills");
    }

    @Override
    Bill getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        int billId = resultSet.getInt("id");
        int idService = resultSet.getInt("idservice");
        int idClient = resultSet.getInt("idclient");
        int totalPrice = resultSet.getInt("totalprice");
        LocalDate date = resultSet.getDate("date").toLocalDate();
        return new Bill(billId, idService, idClient, totalPrice, date);
    }

    @Override
    String getInsertStatement(Bill bill) {
        return "INSERT INTO " + tableName + "(id service, idclient, totalprice, date " +
                "VALUES (" + bill.getIdService() + ", " + bill.getIdClient() + ", " + bill.getTotalPrice() + ", " + bill.getDate() + ");";
    }

    @Override
    String getUpdateStatement(Bill bill) {
        return "UPDATE " + tableName + " " +
                "SET idservice = " + bill.getIdService()
                + ", idclient = " + bill.getIdClient()
                + ", totalprice = " + bill.getTotalPrice()
                + ", date" + bill.getDate() +
                " WHERE id = " + bill.getId() + ";";
    }

    @Override
    String getDeleteStatement(Bill bill) {
        return "DELETE FROM " + tableName + " WHERE id = " + bill.getId() + ";";
    }
}
