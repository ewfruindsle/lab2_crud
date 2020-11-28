package model.beans;

import dao.DaoBill;
import model.Bill;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name = "billBean")
@SessionScoped
public class BillBean implements Serializable {
    @EJB
    private final DaoBill daoBill;
    private Bill selectedBill;

    public Bill getSelectedBill() {
        return selectedBill;
    }

    public BillBean() {
       daoBill = new DaoBill();
    }

    public List<Bill> getBills() {
        return daoBill.getAll();
    }

    public String billInfo(Bill bill) {
        selectedBill = bill;
        return "edit";
    }

    public String saveSelectedBill() {
        if(selectedBill.getId() == -1) {
            daoBill.save(selectedBill);
        } else {
            daoBill.update(selectedBill);
        }
        return "menu";
    }
    public String addBill() {
        selectedBill = new Bill(-1,0,0,0,null);
        return "edit";
    }
    public String removeBill() {
        daoBill.delete(selectedBill);
        return "menu";
    }
}
