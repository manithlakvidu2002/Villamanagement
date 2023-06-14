package dao;

import dao.custom.Impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){

    }

    public static DaoFactory getDaoFactory(){
        return (daoFactory == null)? daoFactory = new DaoFactory() :daoFactory;
    }
    public enum DAOTypes{
        BILL, BOOKING, CUSTOMER, DRIVER, EMPLOYEE, QUARY_DAO, ROOM, ROOM_DETAILS, SAFARY
    }
    public <T extends  SuperDAO> T getDAO(DAOTypes res){
        switch (res){
            case BILL:
                return (T) new BillDAOImpl();
            case BOOKING:
                return (T) new BookingRoomDAOImpl();
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case DRIVER:
                return (T) new DriverDAOImpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case QUARY_DAO:
                return (T) new QuaryDAOImpl();
            case ROOM:
                return (T) new RoomDAOImpl();
            case ROOM_DETAILS:
                return (T) new RoomDetailsDAOImpl();
            case SAFARY:
                return (T) new SafaryDAOImpl();
            default:
                return null;
        }
    }
}
