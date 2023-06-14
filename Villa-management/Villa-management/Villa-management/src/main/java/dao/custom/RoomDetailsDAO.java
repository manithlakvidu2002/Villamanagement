package dao.custom;

import dao.CrudDAO;
import dto.RoomDetailsDTO;
import entity.RoomDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomDetailsDAO extends CrudDAO<RoomDetails,String> {


    public boolean delete(String roomId,String cusId) throws SQLException, ClassNotFoundException ;

    public ArrayList<RoomDetails> getAll() throws SQLException, ClassNotFoundException ;
    public  boolean existRoom(String id) throws SQLException, ClassNotFoundException ;
}
