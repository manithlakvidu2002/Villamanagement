package bo.custom.impl;

import bo.custom.DriverBO;
import dao.DaoFactory;
import dao.custom.DriverDAO;
import dao.custom.EmployeeDAO;
import dao.custom.Impl.DriverDAOImpl;
import dao.custom.Impl.EmployeeDAOImpl;
import dto.DriverDTO;
import entity.Driver;

import java.sql.SQLException;
import java.util.ArrayList;

public class DriverBOImpl implements DriverBO {
    DriverDAO driverDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.DRIVER);
    EmployeeDAO employeeDAO =DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.EMPLOYEE);
    public ArrayList<DriverDTO> LoadAllDriver() throws SQLException, ClassNotFoundException {
        ArrayList<Driver> alls = driverDAO.getAll();
        ArrayList<DriverDTO> drive = new ArrayList<>();
        for (Driver d:alls) {
            drive.add(new DriverDTO(d.getDriverId(),d.getDriverName(),d.getAddress(), d.getContact()));
        }
        return drive;
    }
    public boolean DriverSave(DriverDTO dto) throws SQLException, ClassNotFoundException {
        return driverDAO.save(new Driver(dto.getDriverId(),dto.getDriverName(),dto.getAddress(), dto.getContact()));
    }
    public boolean DriverUpdate(DriverDTO dto) throws SQLException, ClassNotFoundException {
        return driverDAO.update(new Driver(dto.getDriverId(),dto.getDriverName(),dto.getAddress(), dto.getContact()));
    }
    public boolean DriverDelete(String id) throws SQLException, ClassNotFoundException {
        return driverDAO.delete(id);
    }
    public DriverDTO SearchDriver(String id) throws SQLException, ClassNotFoundException {
         Driver d = driverDAO.search(id);
        if(d != null) {
            return new DriverDTO(d.getDriverId(),d.getDriverName(),d.getAddress(), d.getContact());
        }
        return null;
    }
    public  boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.existCustomer(id);
    }
}
