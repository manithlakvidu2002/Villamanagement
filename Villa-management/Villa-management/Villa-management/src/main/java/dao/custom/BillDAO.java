package dao.custom;

import dao.CrudDAO;
import entity.Bill;

import java.sql.SQLException;

public interface BillDAO extends CrudDAO<Bill,String> {

    public boolean delete(String cusId) throws SQLException, ClassNotFoundException;

}
