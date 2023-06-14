package dao.custom.Impl;

import dao.custom.EmployeeScheduleDAO;
import entity.EmployeeSchedule;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeScheduleDAOImpl implements EmployeeScheduleDAO {

    public boolean save(EmployeeSchedule employeeDTO){
        return false;
    }

    public boolean update(EmployeeSchedule employeeDTO){
        return false;
    }

    public boolean delete(String id){
        return false;
    }

    public EmployeeSchedule search(String id){
        return null;
    }

    @Override
    public ArrayList<EmployeeSchedule> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

}
