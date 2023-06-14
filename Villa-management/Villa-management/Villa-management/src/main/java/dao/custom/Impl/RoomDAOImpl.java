package dao.custom.Impl;

import dao.custom.RoomDAO;
import db.DBConnection;
import entity.Room;
import dao.custom.Impl.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDAOImpl implements RoomDAO {

    public boolean save(Room dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO room VALUES (?,?,?,?,?)",
                dto.getRoomId(),
                dto.getType(),
                dto.getDescription(),
                dto.getAvailable(),
                dto.getPrice()
        );
    }

    public boolean update(Room dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE room SET type=?, description=?,available=?,price=? WHERE roomId=?",
                dto.getType(),
                dto.getDescription(),
                dto.getAvailable(),
                dto.getPrice(),
                dto.getRoomId()
        );
    }

    public  boolean updateAvailable(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE room SET available=? WHERE roomId=?", "not-Available", id);
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM room WHERE roomId=?", id);
    }

    public  Room search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("select*from room where roomId=?", id);
        if (rst.next()) {
           return new Room(
                   rst.getString(1),
                   rst.getString(2),
                   rst.getString(3),
                   rst.getString(4),
                   rst.getString(5)
           );
        }
        return null;
    }

    public ArrayList<Room> searchRoomType(String type) throws SQLException, ClassNotFoundException {
        ArrayList<Room> all = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("select * from room where type=?", type);
        while (rst.next()) {
             all.add(new Room(
                             rst.getString(1),
                             rst.getString(2),
                             rst.getString(3),
                             rst.getString(4),
                             rst.getString(5)
                     ));
        }
        return all;
    }

    public ArrayList<Room> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Room> all = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("select*from room");
        while (rst.next()) {
            all.add(new Room(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return all;
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        int count = 0;
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM room where available='Available' || available='available'");
        while (rst.next()) {
            count++;
        }
        return count;
    }

   public  boolean existRoom(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT roomId FROM room WHERE roomId=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();
    }
}
