package dao.custom;

import dao.CrudDAO;
import entity.Driver;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverDAO extends CrudDAO<Driver,String> {


    public boolean delete(String id) throws SQLException, ClassNotFoundException ;


    public ArrayList<Driver> getAll() throws SQLException, ClassNotFoundException ;
    public int getCount() throws SQLException, ClassNotFoundException ;

    //----------------------existCustomer--------------------------------------------------------------
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException ;
}
