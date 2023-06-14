package dao.custom.Impl;

import dao.custom.WorkingScheduleDAO;
import dto.BookingRoomDTO;
import entity.BookingRoom;
import entity.WorkingSchedule;

import java.sql.SQLException;
import java.util.ArrayList;

public class WorkingScheduleDAOImpl implements WorkingScheduleDAO {
    public ArrayList<WorkingSchedule> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean save(WorkingSchedule dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(WorkingSchedule dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public WorkingSchedule search(String id) {
        return null;
    }

    public ArrayList<BookingRoom> getAll(String id) {
        return null;
    }
}
