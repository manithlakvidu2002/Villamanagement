package bo.custom;

import bo.SuperBO;
import dto.*;
import entity.BookingRoom;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingRoomBO extends SuperBO {
    public CustomerDTO SearchCustomer(String id) throws SQLException, ClassNotFoundException;

    public RoomDTO SearchRoom(String id) throws SQLException, ClassNotFoundException;

    public RoomDetailsDTO searchRoomType(String type) throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerDTO> LoadAllCustomer() throws SQLException, ClassNotFoundException;

    public ArrayList<CustomEntityDTO> getAll() throws SQLException, ClassNotFoundException;
    public boolean saveBooking(BookingRoomDTO b) throws SQLException;

    public boolean delete(String roomId, String cusId) throws SQLException, ClassNotFoundException;

    public boolean update(BookingRoom b) throws SQLException, ClassNotFoundException;

    public  boolean existRoom(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<RoomDTO> searchRoomDetailType(String type) throws SQLException, ClassNotFoundException;

    }
