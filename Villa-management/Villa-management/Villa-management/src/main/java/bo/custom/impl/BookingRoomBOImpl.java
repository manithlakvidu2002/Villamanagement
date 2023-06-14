package bo.custom.impl;

import bo.custom.BookingRoomBO;
import dao.DaoFactory;
import dao.custom.*;
import dao.custom.Impl.*;
import dao.custom.Impl.util.CrudUtil;
import db.DBConnection;
import dto.*;
import entity.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingRoomBOImpl implements BookingRoomBO {
    CustomerDAO customerDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.CUSTOMER);
    RoomDAO roomDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.ROOM);
    BookingRoomDAO bookingRoomDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.BOOKING);
    RoomDetailsDAO roomDetailsDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.ROOM_DETAILS);
    QuaryDAO quaryDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.QUARY_DAO);
    public CustomerDTO SearchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.search(id);
        if(c != null) {
            return new CustomerDTO(c.getCusId(),c.getName(),c.getAddress(),c.getDob(),c.getNic(),c.getContact(),c.getSex(),c.getSafaryId(),c.getSafaryType());
        }
        return null;
    }

    public RoomDTO SearchRoom(String id) throws SQLException, ClassNotFoundException {
        Room r = roomDAO.search(id);
        if(r != null) {
            return new RoomDTO(r.getRoomId(), r.getType(), r.getDescription(), r.getAvailable(), r.getPrice());
        }
        return null;

    }
    public RoomDetailsDTO searchRoomType(String type) throws SQLException, ClassNotFoundException {
        RoomDetails r = roomDetailsDAO.search(type);
        if(r != null) {
            return new RoomDetailsDTO(r.getRoomId(),r.getCusId(),r.getPaymentType(),r.getPayment());
        }
        return null;
    }
    public ArrayList<RoomDTO> searchRoomDetailType(String type) throws SQLException, ClassNotFoundException {
        ArrayList<Room> all = roomDAO.searchRoomType(type);
        ArrayList<RoomDTO> dto = new ArrayList<>();
        for (Room r:all) {
            dto.add(new RoomDTO(r.getRoomId(), r.getType(), r.getDescription(), r.getAvailable(), r.getPrice()));
        }
        return dto;
    }
    public ArrayList<CustomerDTO> LoadAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> alls = customerDAO.getAll();
        ArrayList<CustomerDTO> dto = new ArrayList<>();
        for (Customer c: alls) {
            dto.add(new CustomerDTO(c.getCusId(),c.getName(),c.getAddress(),c.getDob(),c.getNic(),c.getContact(),c.getSex(),c.getSafaryId(),c.getSafaryType()));
        }
        return dto;
    }
    public ArrayList<CustomEntityDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity>  book = quaryDAO.getAllBookingRoom();
        ArrayList<CustomEntityDTO> dto = new ArrayList<>();
        for (CustomEntity b: book) {
            dto.add(new CustomEntityDTO(b.getCusId(),b.getName(),b.getContact(),b.getRoomId(),b.getRoomType(),b.getRoomPrice(),b.getPaymentType(),b.getPayment(),b.getCash()));
        }
        return dto;
    }
    public boolean saveBooking(BookingRoomDTO b) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean save = bookingRoomDAO.save(new BookingRoom(b.getCusId(),b.getRoomId(),b.getPaymentType(),b.getPayment()));
            if (save){
                boolean update = roomDAO.updateAvailable(b.getRoomId());
                if (update){
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException | ClassNotFoundException throwables) {
            con.rollback();System.out.println("sql error ");
            return false;
        }finally {
            con.setAutoCommit(true);
        }
    }

    public boolean delete(String roomId, String cusId) throws SQLException, ClassNotFoundException {
        return bookingRoomDAO.delete(roomId,cusId);

    }

    public boolean update(BookingRoom b) throws SQLException, ClassNotFoundException {
       return bookingRoomDAO.update(new BookingRoom(b.getRoomId(),b.getCusId(),b.getPaymentType(),b.getPayment()));
    }
    public  boolean existRoom(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.existRoom(id);
    }
}
