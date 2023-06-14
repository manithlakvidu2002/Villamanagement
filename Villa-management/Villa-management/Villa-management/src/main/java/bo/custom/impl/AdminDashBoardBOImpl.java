package bo.custom.impl;

import bo.custom.AdminBO;
import dao.DaoFactory;
import dao.custom.CustomerDAO;
import dao.custom.DriverDAO;
import dao.custom.Impl.CustomerDAOImpl;
import dao.custom.Impl.DriverDAOImpl;
import dao.custom.Impl.RoomDAOImpl;
import dao.custom.Impl.SafaryDAOImpl;
import dao.custom.RoomDAO;
import dao.custom.SafaryDAO;

import java.sql.SQLException;

public class AdminDashBoardBOImpl implements AdminBO {
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
    public int getSuffariCount() throws SQLException, ClassNotFoundException {
        return safaryDAO.getCount();
    }
    public int getCoustomerCount() throws SQLException, ClassNotFoundException {
        return customerDAO.getCount();
    }
}

