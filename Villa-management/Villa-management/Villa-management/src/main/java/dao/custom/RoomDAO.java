package dao.custom;

import dao.CrudDAO;
import entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomDAO extends CrudDAO<Room,String> {


    public  boolean updateAvailable(String id) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<Room> getAll() throws SQLException, ClassNotFoundException ;


    public  ArrayList<Room> searchRoomType(String type) throws SQLException, ClassNotFoundException ;



    public int getCount() throws SQLException, ClassNotFoundException ;

    public boolean existRoom(String id) throws SQLException, ClassNotFoundException ;
}
