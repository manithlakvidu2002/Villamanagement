package dao.custom;

import dao.SuperDAO;
import dto.CusRoomDetailDTO;
import entity.Bill;
import entity.BookingRoom;
import entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QuaryDAO<T,ID> extends SuperDAO {
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public T search(ID id) throws SQLException, ClassNotFoundException;
    public CustomEntity searchCust(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<CustomEntity> getAllBill() throws SQLException, ClassNotFoundException;
    public ArrayList<CustomEntity> getAllBookingRoom() throws SQLException, ClassNotFoundException;
}
