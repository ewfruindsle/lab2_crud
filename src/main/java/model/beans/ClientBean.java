package model.beans;


import dao.DaoClient;
import model.Client;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name = "clientBean")
@RequestScoped
public class ClientBean {
    @EJB
    private final DaoClient daoClient;
    private Client selectedClient;


    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    public ClientBean() {
        daoClient = new DaoClient();
    }

    public List<Client> getClients() {
        return daoClient.getAll();
    }

    public String clientInfo(Client client) {
        selectedClient = client;
        return "edit";
    }

    public String saveSelectedClient() {
        if (selectedClient.getId() == -1) {
            daoClient.save(selectedClient);
        } else {
            daoClient.update(selectedClient);
        }
        return "menu";
    }

    public String addClient() {
        selectedClient = new Client();
        return "edit";
    }

    public String removeClient() {
        daoClient.delete(selectedClient);
        return "menu";
    }
}
