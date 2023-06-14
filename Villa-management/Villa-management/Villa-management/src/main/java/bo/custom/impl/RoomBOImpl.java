package bo.custom.impl;

import bo.custom.RoomBO;
import dao.DaoFactory;
import dao.custom.EmployeeDAO;
import dao.custom.Impl.EmployeeDAOImpl;
import dao.custom.Impl.RoomDAOImpl;
import dao.custom.RoomDAO;
import dto.RoomDTO;
import entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.ROOM);
    EmployeeDAO employeeDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.EMPLOYEE);
    public ArrayList<RoomDTO> LoadAllRoom() throws SQLException, ClassNotFoundException {
        ArrayList<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> room = new ArrayList<>();
        for (Room r:all) {
            room.add(new RoomDTO(r.getRoomId(), r.getType(), r.getDescription(), r.getAvailable(), r.getPrice()));
        }
        return room;
    }
    public boolean RoomSave(RoomDTO dto) throws SQLException, ClassNotFoundException {
        return roomDAO.save(new Room(dto.getRoomId(), dto.getType(), dto.getDescription(), dto.getAvailable(), dto.getPrice()));
    }
    public boolean RoomUpdate(RoomDTO dto) throws SQLException, ClassNotFoundException {
        return roomDAO.update(new Room(dto.getRoomId(), dto.getType(), dto.getDescription(), dto.getAvailable(), dto.getPrice()));
    }
    public boolean RoomDelete(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.delete(id);
    }
    public RoomDTO SearchRoom(String id) throws SQLException, ClassNotFoundException {
        Room r = roomDAO.search(id);
        if(r != null) {
            return new RoomDTO(r.getRoomId(), r.getType(), r.getDescription(), r.getAvailable(), r.getPrice());
        }
        return null;
    }
    public boolean existRoom(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.existRoom(id);
    }
}
