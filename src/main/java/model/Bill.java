package model;

import java.time.LocalDate;

public class Bill {
    private int id;
    private int idService;
    private int idClient;
    private int totalPrice;
    private LocalDate date;

    public Bill() {
    }

    public Bill(int id, int idService, int idClient, int totalPrice, LocalDate date) {
        this.id = id;
        this.idService = idService;
        this.idClient = idClient;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id:" + id +
                ", idService:" + idService +
                ", idClient:" + idClient +
                ", totalPrice:" + totalPrice +
                ", date:" + date +
                '}';
    }
}
