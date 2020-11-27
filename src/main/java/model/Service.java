package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "Service.findAll", query = "select s from Service s")
})
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(max = 100, message = "Name of the service must be less than 100 characters")
    @NotNull
    private String name;

    public Service(){}

    public Service(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Service{" +
                "id: " + id +
                ", name: '" + name + '\'' +
                '}';
    }
}
