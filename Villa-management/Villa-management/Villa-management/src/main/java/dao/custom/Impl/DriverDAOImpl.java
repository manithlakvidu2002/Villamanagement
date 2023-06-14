package dao.custom.Impl;

import dao.custom.DriverDAO;
import db.DBConnection;
import entity.Driver;
import dao.custom.Impl.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverDAOImpl implements DriverDAO {
    public boolean save(Driver dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Driver VALUES (?,?,?,?)",
                dto.getDriverId(),
                dto.getDriverName(),
                dto.getAddress(),
                dto.getContact()
        );
    }

    public boolean update(Driver dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Driver SET driverName=?, address=?,contact=? WHERE driverId=?",
                dto.getDriverName(),
                dto.getAddress(),
                dto.getContact(),
                dto.getDriverId()
        );
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Driver WHERE driverId=?",id);
    }

    public Driver search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("select*from Driver where driverId=?", id);
        if (rst.next()) {
            return new Driver(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return null;
    }

    public  ArrayList<Driver> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Driver> all = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("select*from Driver");
        while (rst.next()) {
            all.add(new Driver(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)));
        }
        return all;
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        int count=0;
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Driver");
        while (rst.next()){
            count++;
        }
        return count;
    }

    //----------------------existCustomer--------------------------------------------------------------
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT driverId FROM driver WHERE driverId=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();
    }
}
