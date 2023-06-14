package bo.custom.impl;

import bo.custom.HomeBO;
import dao.DaoFactory;
import dao.custom.*;
import dao.custom.Impl.CustomerDAOImpl;
import dao.custom.Impl.DriverDAOImpl;
import dao.custom.Impl.RoomDAOImpl;
import dao.custom.Impl.SafaryDAOImpl;

import java.sql.SQLException;

public class HomeBOImpl implements HomeBO {
    RoomDAO roomDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.ROOM);
    DriverDAO driverDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.DRIVER);
    SafaryDAO safaryDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.SAFARY);
    CustomerDAO customerDAO = DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.CUSTOMER);

    public int getRoomCount() throws SQLException, ClassNotFoundException {
       return roomDAO.getCount();
    }
    public int getDriverCount() throws SQLException, ClassNotFoundException {
        return driverDAO.getCount();
    }
    public int getSafaryCount() throws SQLException, ClassNotFoundException {
        return safaryDAO.getCount();
    }
    public int getCoustomerCount() throws SQLException, ClassNotFoundException {
        return customerDAO.getCount();
    }
}
