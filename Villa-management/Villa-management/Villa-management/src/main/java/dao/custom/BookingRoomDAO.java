package dao.custom;

import dao.CrudDAO;
import entity.BookingRoom;

import java.sql.SQLException;

public interface BookingRoomDAO extends CrudDAO<BookingRoom,String> {

    //Transaction
  //  public boolean saveBooking(BookingRoom bookingRoomDTO) throws SQLException ;

    public boolean delete(String roomId, String cusId) throws SQLException, ClassNotFoundException ;


}
