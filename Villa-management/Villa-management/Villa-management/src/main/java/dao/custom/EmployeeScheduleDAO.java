package dao.custom;

import dao.CrudDAO;
import dto.BookingRoomDTO;
import dto.EmployeeScheduleDTO;
import entity.EmployeeSchedule;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeScheduleDAO extends CrudDAO<EmployeeSchedule,String> {


    public boolean delete(String id);
    public ArrayList<EmployeeSchedule> getAll() throws SQLException, ClassNotFoundException ;

}
