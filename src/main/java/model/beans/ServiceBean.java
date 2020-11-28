package model.beans;

import dao.DaoService;
import model.Service;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name = "serviceBean")
@SessionScoped
public class ServiceBean implements Serializable {
    @EJB
    private final DaoService daoService;
    private Service selectedService;

    public Service getSelectedService() {
        return selectedService;
    }

    public ServiceBean() {
        daoService = new DaoService();
    }

    public List<Service> getServices() {
        return daoService.getAll();
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
