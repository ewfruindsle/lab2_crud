package model.beans;


import dao.ConnectionManager;
import dao.DaoClient;
import dao.TableName;
import model.Client;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name = "clientBean")
@RequestScoped
public class ClientBean {
    private DaoClient daoClient;
    private Client selectedClient;


    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    public ClientBean() {
        try {
            daoClient = (DaoClient) ConnectionManager.shared().getDAO(TableName.CLIENT);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Client> getClients() {
        List<Client> clients = daoClient.getAll();
        clients.sort(Comparator.comparing(Client::getId));
        return clients;
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
        selectedClient = new Client(-1, "", "");
        return "edit";
    }

    public String removeClient() {
        daoClient.delete(selectedClient);
        return "menu";
    }
}
