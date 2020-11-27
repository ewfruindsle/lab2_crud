package model;

import model.validation.Price;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
//@SecondaryTable(name = "bills")
@NamedQueries({
        @NamedQuery(name = "Bill.findAll", query = "select b from Bill b")
})
public class Bill {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "idservice")
    private int idService;
    @Basic
    @Column(name = "idclient")
    private int idClient;
    @Column(name = "totalprice")
    @Price
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
