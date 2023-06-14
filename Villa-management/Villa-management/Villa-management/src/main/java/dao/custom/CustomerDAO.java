package dao.custom;

import dao.CrudDAO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer,String> {


    public boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException ;
    public int getCount() throws SQLException, ClassNotFoundException ;

    //----------------------existCustomer--------------------------------------------------------------
    public  boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
}
