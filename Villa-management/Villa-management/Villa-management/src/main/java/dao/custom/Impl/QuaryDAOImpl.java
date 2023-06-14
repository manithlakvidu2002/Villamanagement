package dao.custom.Impl;

import dao.custom.QuaryDAO;
import dto.CusRoomDetailDTO;
import entity.Bill;
import entity.BookingRoom;
import entity.CusRoomDetail;
import entity.CustomEntity;
import dao.custom.Impl.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuaryDAOImpl implements QuaryDAO<CustomEntity,String>{
   public ArrayList<CustomEntity> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> all = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("select safary.safaryId,safary.type,safary.date,safary.time,safary.driverId,Driver.driverName,Driver.contact FROM safary LEFT JOIN Driver ON safary.driverId=Driver.driverId");
        while (rst.next()) {
            all.add(new CustomEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        return all;
    }
    public CustomEntity search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT customer.cusId,customer.name,customer.contact,customer.type,room.roomId,room.type,room.price,roomdetails.payment,bill.bill,bill.cash,bill.balance FROM roomdetails INNER JOIN room ON room.roomId = roomdetails.roomId INNER JOIN customer ON customer.cusId = roomdetails.cusId  INNER JOIN bill ON bill.cusId = customer.cusId where customer.cusId=?",id);
        if(rst.next()) {
            return new CustomEntity (
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11)

            );
        }
        return null;
    }
    public CustomEntity searchCust(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT c.name, c.contact, r.roomId, r.type ,r.price FROM customer c JOIN roomdetails rd on c.cusId = rd.cusId JOIN room r on rd.roomId = r.roomId WHERE c.cusId=?";
        ResultSet resultSet = CrudUtil.executeQuery(sql,id);
        CustomEntity CustomEntity = null;
        if(resultSet.next()) {
            CustomEntity = new CustomEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                    resultSet.getString(4),resultSet.getString(5));
        }
        return CustomEntity;

    }
    public ArrayList<CustomEntity> getAllBill() throws SQLException, ClassNotFoundException {

        ArrayList<CustomEntity> AllBooking = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT customer.cusId,customer.name,customer.contact,customer.type,room.roomId,room.type,room.price,roomdetails.payment,bill.bill,bill.cash,bill.balance FROM roomdetails INNER JOIN room ON room.roomId = roomdetails.roomId INNER JOIN customer ON customer.cusId = roomdetails.cusId  INNER JOIN bill ON bill.cusId = customer.cusId");
        while (rst.next()) {
            AllBooking.add(new CustomEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11)
            ));
        }
        return AllBooking;
    }
    public ArrayList<CustomEntity> getAllBookingRoom() throws SQLException, ClassNotFoundException {

        ArrayList<CustomEntity> AllBooking = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT customer.cusId,customer.name,customer.contact,room.roomId,room.type,room.price,roomDetails.paymentType,roomDetails.payment,(room.price-roomDetails.payment) FROM customer JOIN roomDetails ON customer.cusId = roomDetails.cusId JOIN room ON room.roomId= roomDetails.roomId;");
        while (rst.next()) {
            AllBooking.add(new CustomEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8),
                    rst.getString(9)
            ));
        }
        return AllBooking;
    }
}
