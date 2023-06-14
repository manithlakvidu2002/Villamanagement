package bo.custom.impl;

import bo.custom.EmployeeBO;
import dao.DaoFactory;
import dao.custom.EmployeeDAO;
import dao.custom.Impl.EmployeeDAOImpl;
import dto.EmployeeDTO;
import entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.EMPLOYEE);
    public ArrayList<EmployeeDTO> LoadAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> all = employeeDAO.getAll();
        ArrayList<EmployeeDTO> emp = new ArrayList<>();
        for (Employee e:all) {
            emp.add(new EmployeeDTO(e.getEmpId(), e.getName(), e.getAddress(), e.getAge(), e.getNic(), e.getContact(), e.getSalary(), e.getUserName(), e.getPassword()));
        }
        return emp;
    }
    public boolean EmployeeSave(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(dto.getEmpId(), dto.getName(), dto.getAddress(), dto.getAge(), dto.getNic(), dto.getContact(), dto.getSalary(), dto.getUserName(), dto.getPassword()));
    }
    public boolean EmployeeUpdate(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmpId(), dto.getName(), dto.getAddress(), dto.getAge(), dto.getNic(), dto.getContact(), dto.getSalary(), dto.getUserName(), dto.getPassword()));

    }
    public boolean EmployeeDelete(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);

    }
    public EmployeeDTO SearchEmployee(String id) throws SQLException, ClassNotFoundException {
        Employee e = employeeDAO.search(id);
        if(e != null) {
            return new EmployeeDTO(e.getEmpId(), e.getName(), e.getAddress(), e.getAge(), e.getNic(), e.getContact(), e.getSalary(), e.getUserName(), e.getPassword());
        }
        return null;

    }
    public  boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.existCustomer(id);

    }
}
