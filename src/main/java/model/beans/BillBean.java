package model.beans;

import dao.ConnectionManager;
import dao.DaoBill;
import dao.TableName;
import model.Bill;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name = "billBean")
@SessionScoped
public class BillBean implements Serializable {
    private DaoBill daoBill;
    private Bill selectedBill;

    public Bill getSelectedBill() {
        return selectedBill;
    }

    public BillBean() {
        try {
            daoBill = (DaoBill) ConnectionManager.shared().getDAO(TableName.BILL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Bill> getBills() {
        List<Bill> bills = daoBill.getAll();
        bills.sort(Comparator.comparing(Bill::getId));
        return bills;
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
