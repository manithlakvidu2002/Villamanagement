package dao.custom.Impl;

import dao.custom.BookingRoomDAO;
import entity.BookingRoom;
import dao.custom.Impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingRoomDAOImpl implements BookingRoomDAO {
    public boolean save(BookingRoom dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO roomDetails VALUES (?,?,?,?)",
                dto.getRoomId(),
                dto.getCusId(),
                dto.getPaymentType(),
                dto.getPayment()
        );

    }

    public boolean delete(String roomId, String cusId) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM roomDetails WHERE roomId=? && cusId=?", roomId, cusId);

    }

    @Override
    public boolean update(BookingRoom dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE roomDetails SET paymentType=?, payment=? WHERE roomId=? && cusId=?",
                dto.getPaymentType(),
                dto.getPayment(),
                dto.getRoomId(),
                dto.getCusId());
    }

    public BookingRoom search(String id) {
        return null;
    }

}

