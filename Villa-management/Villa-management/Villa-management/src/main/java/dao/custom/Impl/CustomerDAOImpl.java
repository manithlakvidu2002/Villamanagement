package dao.custom.Impl;

import dao.custom.CustomerDAO;
import db.DBConnection;
import entity.Customer;
import dao.custom.Impl.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("Insert into Customer Values (?,?,?,?,?,?,?,?,?)",
                dto.getCusId(),
                dto.getName(),
                dto.getAddress(),
                dto.getDob(),
                dto.getNic(),
                dto.getContact(),
                dto.getSex(),
                dto.getSafaryId(),
                dto.getSafaryType()
                );
    }

    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Customer SET name=?, address=?,dob=?,nic=?,contact=?,sex=?,safaryId=?,type=? WHERE cusId=?",
                dto.getName(),
                dto.getAddress(),
                dto.getDob(),
                dto.getNic(),
                dto.getContact(),
                dto.getSex(),
                dto.getSafaryId(),
                dto.getSafaryType(),
                dto.getCusId()
                );
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return CrudUtil.executeUpdate("DELETE FROM Customer WHERE cusId=?",id);
    }

    public  Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("select*from customer where cusId=?", id);
        if (rst.next()){
            return new Customer(
                    rst.getString("cusId"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getString("dob"),
                    rst.getString("Nic"),
                    rst.getString("Contact"),
                    rst.getString("sex"),
                    rst.getString("safaryId"),
                    rst.getString("type")
            );
        }
        return null;
    }

    public  ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> AllCustomer = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("select*from customer");
        while (rst.next()) {
            AllCustomer.add(new Customer(
                    rst.getString("cusId"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getString("dob"),
                    rst.getString("Nic"),
                    rst.getString("Contact"),
                    rst.getString("sex"),
                    rst.getString("safaryId")
                    ));
        }
        return AllCustomer;
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        int count=0;
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM customer");
        while (rst.next()){
            count++;
        }
        return count;
    }

    //----------------------existCustomer--------------------------------------------------------------
   public  boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT cusId FROM Customer WHERE cusId=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();
    }
}
