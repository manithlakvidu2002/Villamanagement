package bo.custom;

import bo.SuperBO;
import dto.EmployeeDTO;
import entity.Employee;

import java.sql.SQLException;

public interface SignInBO extends SuperBO {
     boolean save(EmployeeDTO e) throws SQLException, ClassNotFoundException;
     boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
}
