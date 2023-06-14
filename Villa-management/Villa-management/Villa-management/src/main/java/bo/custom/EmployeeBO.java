package bo.custom;

import bo.SuperBO;
import dao.custom.Impl.EmployeeDAOImpl;
import dto.EmployeeDTO;
import entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public ArrayList<EmployeeDTO> LoadAllEmployee() throws SQLException, ClassNotFoundException;

    public boolean EmployeeSave(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public boolean EmployeeUpdate(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public boolean EmployeeDelete(String id) throws SQLException, ClassNotFoundException;

    public EmployeeDTO SearchEmployee(String id) throws SQLException, ClassNotFoundException;

    public  boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
}
