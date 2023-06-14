package dao.custom.Impl;

import dao.custom.RoomDetailsDAO;
import db.DBConnection;
import entity.RoomDetails;
import dao.custom.Impl.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDetailsDAOImpl implements RoomDetailsDAO {

    public boolean save(RoomDetails dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO roomDetails VALUES (?,?,?,?)",
                dto.getRoomId(),
                dto.getCusId(),
                dto.getPaymentType(),
                dto.getPayment()
        );
    }

    public boolean update(RoomDetails dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE roomDetails SET paymentType=?, payment=? WHERE roomId=? && cusId=?",
                dto.getPaymentType(),
                dto.getPayment(),
                dto.getRoomId(),
                dto.getCusId());
    }

    public boolean delete(String roomId,String cusId) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM roomDetails WHERE roomId=? && cusId=?", roomId, cusId);
    }

    public  RoomDetails search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("select*from roomDetails where cusId=?", id);
        if (rst.next()){
            return new RoomDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return null;
    }

    @Override
    public ArrayList<RoomDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

   public  boolean existRoom(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT cusId FROM roomDetails WHERE cusId=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();
    }
}
