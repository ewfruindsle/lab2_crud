package model.beans;

import dao.ConnectionManager;
import dao.DaoClient;
import dao.DaoService;
import dao.TableName;
import model.Client;
import model.Service;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name = "serviceBean")
@SessionScoped
public class ServiceBean implements Serializable {
    private DaoService daoService;
    private Service selectedService;

    public Service getSelectedService() {
        return selectedService;
    }

    public ServiceBean() {
        try {
            daoService = (DaoService) ConnectionManager.shared().getDAO(TableName.SERVICE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Service> getServices() {
        List<Service> services = daoService.getAll();
        services.sort(Comparator.comparing(Service::getId));
        return services;
    }

    public String serviceInfo(Service service) {
        selectedService = service;
        return "edit";
    }

    public String saveSelectedService() {
        if(selectedService.getId() == -1) {
            daoService.save(selectedService);
        } else {
            daoService.update(selectedService);
        }
        return "menu";
    }
    public String addService() {
        selectedService = new Service(-1,"");
        return "edit";
    }
    public String removeService() {
        daoService.delete(selectedService);
        return "menu";
    }
}
