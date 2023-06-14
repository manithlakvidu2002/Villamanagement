package bo.custom.impl;

import bo.custom.SafaryBO;
import dao.DaoFactory;
import dao.custom.CustomerDAO;
import dao.custom.DriverDAO;
import dao.custom.Impl.CustomerDAOImpl;
import dao.custom.Impl.DriverDAOImpl;
import dao.custom.Impl.QuaryDAOImpl;
import dao.custom.Impl.SafaryDAOImpl;
import dao.custom.QuaryDAO;
import dao.custom.SafaryDAO;
import dto.CustomEntityDTO;
import dto.DriverDTO;
import dto.SafaryDTO;
import entity.CustomEntity;
import entity.Driver;
import entity.Safary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SafaryBOImpl implements SafaryBO {
    SafaryDAO safaryDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.SAFARY);
    DriverDAO driverDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.DRIVER);
    CustomerDAO customerDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.CUSTOMER);
    QuaryDAO quaryDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.QUARY_DAO);

    public DriverDTO searchDriver(String id) throws SQLException, ClassNotFoundException {
        Driver d = driverDAO.search(id);
        if(d != null) {
            return new DriverDTO(d.getDriverId(),d.getDriverName(),d.getAddress(),d.getContact());
        }
        return null;
    }
    public ArrayList<DriverDTO> getAllDriver() throws SQLException, ClassNotFoundException {
        ArrayList<Driver> drive = driverDAO.getAll();
        ArrayList<DriverDTO> dto = new ArrayList<>();
        for (Driver d: drive) {
            dto.add(new DriverDTO(d.getDriverId(),d.getDriverName(),d.getAddress(),d.getContact()));
        }
        return dto;
    }
    public ArrayList<SafaryDTO> searchAllSafary(String type) throws SQLException, ClassNotFoundException {
        ArrayList<Safary> saf = safaryDAO.searchAllSafary(type);
        ArrayList<SafaryDTO> dto = new ArrayList<>();
        for (Safary s: saf) {
            dto.add(new SafaryDTO(s.getSafaryId(),s.getType(),s.getDate(),s.getTime(),s.getDriverId()));
        }
        return dto;
    }
    public SafaryDTO search(String id) throws SQLException, ClassNotFoundException {
        Safary s = safaryDAO.search(id);
        if(s != null) {
            return new SafaryDTO(s.getSafaryId(),s.getType(),s.getDate(),s.getTime(),s.getDriverId());
        }
        return null;
    }
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return safaryDAO.delete(id);
    }
    public boolean save(SafaryDTO dto) throws SQLException, ClassNotFoundException {
        return safaryDAO.save(new Safary(dto.getSafaryId(),dto.getType(),dto.getDate(),dto.getTime(),dto.getDriverId()));
    }

    public boolean update(SafaryDTO dto) throws SQLException, ClassNotFoundException {
        return safaryDAO.update(new Safary(dto.getSafaryId(),dto.getType(),dto.getDate(),dto.getTime(),dto.getDriverId()));
    }
    public  boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.existCustomer(id);
    }
    public  ArrayList<CustomEntityDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> all = quaryDAO.getAll();
        ArrayList<CustomEntityDTO> dto = new ArrayList<>();
        for (CustomEntity s: all) {
            dto.add(new CustomEntityDTO(s.getSafaryId(),s.getSafaryType(),s.getDate(),s.getTime(),s.getDriverId(),s.getDriverName(),s.getDriverContact()));
        }
        return dto;
    }
}
