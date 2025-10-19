package org.example.hms.tests;

import org.example.hms.dao.BillingDAO;
import org.example.hms.models.Billing;

import java.util.List;

public class BillingTest {
    BillingDAO  billingDAO = new BillingDAO();
    public void TestInsert(Billing bill){
        billingDAO.insert(bill);
    }
    public void TestGetAll(){
        List<Billing> bills = billingDAO.getAll();
        bills.forEach(System.out::println);
    }

    public void TestUpdate(Billing bill, int id){
        billingDAO.update(bill, id);
    }
    public void TestDelete(int id){
        billingDAO.delete(id);
    }
}
