package bo.custom.impl;

import bo.custom.SignInBO;
import dao.DaoFactory;
import dao.custom.EmployeeDAO;
import dao.custom.Impl.EmployeeDAOImpl;
import dto.EmployeeDTO;
import entity.Employee;

import java.sql.SQLException;

public class SignInBOImpl implements SignInBO {
    EmployeeDAO employeeDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.EMPLOYEE);

    public boolean save(EmployeeDTO e) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(e.getEmpId(),e.getName(),e.getAddress(),e.getAge(),e.getNic(),e.getContact(),e.getSalary(),e.getUserName(),e.getPassword()));
    }
    public  boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        boolean b = employeeDAO.existCustomer(id);
        return b;
    }
}
