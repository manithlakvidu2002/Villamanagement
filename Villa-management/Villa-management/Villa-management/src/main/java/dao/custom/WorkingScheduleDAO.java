package dao.custom;

import dao.CrudDAO;
import dto.BookingRoomDTO;
import dto.WorkingScheduleDTO;
import entity.WorkingSchedule;

import java.util.ArrayList;

public interface WorkingScheduleDAO extends CrudDAO<WorkingSchedule,String> {

    public boolean delete(String id) ;


}
