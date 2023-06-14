package dao.custom;

import dao.CrudDAO;
import entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee,String> {


    public boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException ;

    //----------------------existCustomer--------------------------------------------------------------
    public  boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean existEmployee(String name,String psw) throws SQLException, ClassNotFoundException ;
}
