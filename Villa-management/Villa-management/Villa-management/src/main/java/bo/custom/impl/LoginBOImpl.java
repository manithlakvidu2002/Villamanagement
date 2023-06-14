package bo.custom.impl;

import bo.custom.LoginBO;
import dao.DaoFactory;
import dao.custom.EmployeeDAO;
import dao.custom.Impl.EmployeeDAOImpl;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    EmployeeDAO employeeDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.EMPLOYEE);

    public boolean existEmployee(String name,String psw) throws SQLException, ClassNotFoundException {
        return employeeDAO.existEmployee( name, psw);
    }
}
