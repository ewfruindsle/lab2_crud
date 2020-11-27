package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "clients")
@NamedQueries({
        @NamedQuery(name = "Client.findAll", query = "select c from Client c")
})
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotNull
    @Pattern(regexp = "[A-Za-z]{2,20}")
    private String name;

    @NotNull
    @Pattern(regexp = "[A-Za-z]{2,20}")
    private String surname;

    @Enumerated(EnumType.STRING)
    private SubsidyType subsidyType;


    public Client() {
    }

    public Client(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id: " + id +
                ", name: ='" + name +
                ", surname='" + surname +
                '}';
    }

    public SubsidyType getSubsidyType() {
        return subsidyType;
    }

    public void setSubsidyType(SubsidyType subsidyType) {
        this.subsidyType = subsidyType;
    }
}
