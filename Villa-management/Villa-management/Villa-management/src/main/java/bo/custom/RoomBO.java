package bo.custom;

import bo.SuperBO;
import dto.RoomDTO;
import entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomBO extends SuperBO {
    public ArrayList<RoomDTO> LoadAllRoom() throws SQLException, ClassNotFoundException;

    public boolean RoomSave(RoomDTO dto) throws SQLException, ClassNotFoundException;

    public boolean RoomUpdate(RoomDTO dto) throws SQLException, ClassNotFoundException;

    public boolean RoomDelete(String id) throws SQLException, ClassNotFoundException;

    public RoomDTO SearchRoom(String id) throws SQLException, ClassNotFoundException;

    public boolean existRoom(String id) throws SQLException, ClassNotFoundException;
}
